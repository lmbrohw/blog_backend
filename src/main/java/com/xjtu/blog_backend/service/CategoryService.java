package com.xjtu.blog_backend.service;

import com.xjtu.blog_backend.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huwwww
 * @since 2023-04-05
 */
public interface CategoryService extends IService<Category> {

    List<Category> getCategoryNameList();
}
