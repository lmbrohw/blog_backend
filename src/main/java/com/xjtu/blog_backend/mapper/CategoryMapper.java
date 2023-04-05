package com.xjtu.blog_backend.mapper;

import com.xjtu.blog_backend.entity.Category;
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
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> getCategoryNameList();
}
