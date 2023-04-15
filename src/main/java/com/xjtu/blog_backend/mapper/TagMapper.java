package com.xjtu.blog_backend.mapper;

import com.xjtu.blog_backend.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> getTagListNotId();

    List<Tag> getTagListByBlogId(Long id);
}
