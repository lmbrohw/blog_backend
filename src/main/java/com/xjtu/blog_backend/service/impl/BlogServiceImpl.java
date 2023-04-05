package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.config.RedisKeyConfig;
import com.xjtu.blog_backend.entity.Blog;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.RandomBlog;
import com.xjtu.blog_backend.mapper.BlogMapper;
import com.xjtu.blog_backend.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjtu.blog_backend.service.RedisService;
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
    @Autowired
    private RedisService redisService;
    @Autowired
    private BlogMapper blogMapper;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;

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
}
