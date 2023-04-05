package com.xjtu.blog_backend.mapper;

import com.xjtu.blog_backend.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjtu.blog_backend.entity.vo.NewBlog;
import com.xjtu.blog_backend.entity.vo.RandomBlog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface BlogMapper extends BaseMapper<Blog> {

    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(int randomBlogLimitNum);
}