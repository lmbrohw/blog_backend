package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.config.RedisKeyConfig;
import com.xjtu.blog_backend.entity.Tag;
import com.xjtu.blog_backend.mapper.TagMapper;
import com.xjtu.blog_backend.service.RedisService;
import com.xjtu.blog_backend.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> getTagListNotId() {
        String redisKey = RedisKeyConfig.TAG_CLOUD_LIST;
        List<Tag> tagListFromRedis = redisService.getListByValue(redisKey);
        if (tagListFromRedis != null) {
            return tagListFromRedis;
        }
        List<Tag> tagList = tagMapper.getTagListNotId();
        redisService.saveListToValue(redisKey, tagList);
        return tagList;
    }

    @Override
    public List<Tag> getTagListByBlogId(Long id) {
        return tagMapper.getTagListByBlogId(id);
    }
}
