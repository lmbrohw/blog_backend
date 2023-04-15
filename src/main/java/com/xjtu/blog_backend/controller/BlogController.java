package com.xjtu.blog_backend.controller;


import com.xjtu.blog_backend.annotation.VisitLogger;
import com.xjtu.blog_backend.entity.Blog;
import com.xjtu.blog_backend.entity.vo.BlogInfo;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.PageResult;
import com.xjtu.blog_backend.entity.vo.Result;
import com.xjtu.blog_backend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
@RestController
@RequestMapping("/blog_backend/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * @param pageNum
     * @return com.xjtu.blog_backend.entity.vo.Result
     * @Author Huwwww
     * @Date 2023/4/15 18:26
     */
    @VisitLogger(behavior = "访问页面", content = "首页")
    @GetMapping(value = "/blogs")
    public Result blogs(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByIsPublished(pageNum);
        return Result.ok("成功", pageResult);
    }
}
