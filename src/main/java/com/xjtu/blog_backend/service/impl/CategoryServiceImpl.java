package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.config.RedisKeyConfig;
import com.xjtu.blog_backend.entity.Category;
import com.xjtu.blog_backend.mapper.CategoryMapper;
import com.xjtu.blog_backend.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjtu.blog_backend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private RedisService redisService;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategoryNameList() {
        String redisKey = RedisKeyConfig.CATEGORY_NAME_LIST;
        List<Category> categoryListFromRedis = redisService.getListByValue(redisKey);
        if (categoryListFromRedis != null) {
            return categoryListFromRedis;
        }
        List<Category> categoryList = categoryMapper.getCategoryNameList();
        redisService.saveListToValue(redisKey, categoryList);
        return categoryList;
    }
}
