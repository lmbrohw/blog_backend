package com.xjtu.blog_backend.service;

import com.xjtu.blog_backend.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.RandomBlog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface BlogService extends IService<Blog> {

    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
}
