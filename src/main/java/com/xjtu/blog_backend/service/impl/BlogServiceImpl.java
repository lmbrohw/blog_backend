package com.xjtu.blog_backend.service.impl;

import com.github.pagehelper.PageInfo;
import com.xjtu.blog_backend.config.RedisKeyConfig;
import com.xjtu.blog_backend.entity.Blog;
import com.xjtu.blog_backend.entity.vo.BlogInfo;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.PageResult;
import com.xjtu.blog_backend.entity.vo.RandomBlog;
import com.xjtu.blog_backend.mapper.BlogMapper;
import com.xjtu.blog_backend.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjtu.blog_backend.service.RedisService;
import com.xjtu.blog_backend.service.TagService;
import com.xjtu.blog_backend.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;


import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    private static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";
    @Autowired
    private RedisService redisService;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //每页显示5条博客简介
    private static final int pageSize = 5;
    //博客简介列表排序方式
    private static final String orderBy = "is_top desc, create_time desc";

    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {
        String redisKey = RedisKeyConfig.NEW_BLOG_LIST;
        List<NewBlog> newBlogListFromRedis = redisService.getListByValue(redisKey);
        if (newBlogListFromRedis != null) {
            return newBlogListFromRedis;
        }
        PageHelper.startPage(1, newBlogPageSize);
        List<NewBlog> newBlogList = blogMapper.getNewBlogListByIsPublished();
        for (NewBlog newBlog : newBlogList) {
            if (!"".equals(newBlog.getPassword())) {
                newBlog.setPrivacy(true);
                newBlog.setPassword("");
            } else {
                newBlog.setPrivacy(false);
            }
        }
        redisService.saveListToValue(redisKey, newBlogList);
        return newBlogList;
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        List<RandomBlog> randomBlogs = blogMapper.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(randomBlogLimitNum);
        for (RandomBlog randomBlog : randomBlogs) {
            if (!"".equals(randomBlog.getPassword())) {
                randomBlog.setPrivacy(true);
                randomBlog.setPassword("");
            } else {
                randomBlog.setPrivacy(false);
            }
        }
        return randomBlogs;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        //redis中已有当前页面的缓存
        String redisKey = RedisKeyConfig.HOME_BLOG_INFO_LIST;
        PageResult<BlogInfo> blogInfoPageResultByHash = redisService.getBlogInfoPageResultByHash(redisKey, pageNum);
        if (blogInfoPageResultByHash != null) {
            setBlogViewsFromRedisToPageResult(blogInfoPageResultByHash);
            return blogInfoPageResultByHash;
        }
        //redis中没有缓存,则从数据库中查询并且添加进缓存
        PageHelper.startPage(pageNum, pageSize, orderBy);
        //处理带密码的文章
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByIsPublished());
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        //添加缓存
        redisService.saveKVToHash(redisKey, pageNum, pageResult);
        return pageResult;
    }

    /**
     * 将pageResult中博客对象的浏览量设置为Redis中的最新值
     *
     * @param blogInfoPageResultByHash
     * @Author Huwwww
     * @Date 2023/4/15 18:38
     */
    private void setBlogViewsFromRedisToPageResult(PageResult<BlogInfo> blogInfoPageResultByHash) {
        String redisKey = RedisKeyConfig.BLOG_VIEWS_MAP;
        List<BlogInfo> blogInfos = blogInfoPageResultByHash.getList();
        for (int i = 0; i < blogInfos.size(); i++) {
            BlogInfo blogInfo = JacksonUtils.convertValue(blogInfos.get(i), BlogInfo.class);
            Long blogId = blogInfo.getId();
            int view = (int) redisService.getValueByHashKey(redisKey, blogId);
            blogInfo.setViews(view);
            blogInfos.set(i, blogInfo);
        }
    }

    /**
     * 处理带密码的文章
     *
     * @param blogInfos
     * @return java.util.List<com.xjtu.blog_backend.entity.vo.BlogInfo>
     * @Author Huwwww
     * @Date 2023/4/15 18:58
     */
    private List<BlogInfo> processBlogInfosPassword(List<BlogInfo> blogInfos) {
        for (BlogInfo blogInfo : blogInfos) {
            if (!"".equals(blogInfo.getPassword())) {
                blogInfo.setPrivacy(true);
                blogInfo.setPassword("");
                blogInfo.setDescription(PRIVATE_BLOG_DESCRIPTION);
            } else {
                blogInfo.setPrivacy(false);
                blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogInfo.getDescription()));
            }
            blogInfo.setTags(tagService.getTagListByBlogId(blogInfo.getId()));
        }
        return blogInfos;
    }
}
