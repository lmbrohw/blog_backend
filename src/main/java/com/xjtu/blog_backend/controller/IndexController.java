package com.xjtu.blog_backend.controller;

import com.xjtu.blog_backend.entity.Category;
import com.xjtu.blog_backend.entity.Tag;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.RandomBlog;
import com.xjtu.blog_backend.entity.vo.Result;
import com.xjtu.blog_backend.service.BlogService;
import com.xjtu.blog_backend.service.CategoryService;
import com.xjtu.blog_backend.service.SiteSettingService;
import com.xjtu.blog_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: Huwwww
 * @Date: 2023/4/5 16:36
 **/
@RestController
public class IndexController {
    @Autowired
    SiteSettingService siteSettingService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    /**
     *
     * @Author Huwwww
     * @Date 2023/4/5 16:40
     * @return com.xjtu.blog_backend.entity.vo.Result
     */
    @GetMapping("/site")
    public Result site() {
        Map<String, Object> map = siteSettingService.getSiteInfo();
        List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
        List<Category> categoryList = categoryService.getCategoryNameList();
        List<Tag> tagList = tagService.getTagListNotId();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("categoryList", categoryList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Result.ok("请求成功", map);
    }
}
