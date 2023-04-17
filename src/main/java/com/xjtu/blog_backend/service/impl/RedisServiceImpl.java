package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.entity.vo.BlogInfo;
import com.xjtu.blog_backend.entity.vo.PageResult;
import com.xjtu.blog_backend.service.RedisService;
import com.xjtu.blog_backend.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author: Huwwww
 * @Date: 2023/4/6 00:12
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate jsonRedisTemplate;

    @Override
    public PageResult<BlogInfo> getBlogInfoPageResultByHash(String hash, Integer pageNum) {
        if (jsonRedisTemplate.opsForHash().hasKey(hash, pageNum)) {
            Object redisResult = jsonRedisTemplate.opsForHash().get(hash, pageNum);
            PageResult<BlogInfo> pageResult = JacksonUtils.convertValue(redisResult, PageResult.class);
            return pageResult;
        } else {
            return null;
        }
    }

    @Override
    public void saveKVToHash(String hash, Object key, Object value) {
        jsonRedisTemplate.opsForHash().put(hash, key, value);
    }

    @Override
    public void saveMapToHash(String hash, Map map) {
        jsonRedisTemplate.opsForHash().putAll(hash, map);

    }

    @Override
    public Map getMapByHash(String hash) {
        return jsonRedisTemplate.opsForHash().entries(hash);
    }

    @Override
    public Object getValueByHashKey(String hash, Object key) {
        return jsonRedisTemplate.opsForHash().get(hash, key);
    }

    @Override
    public void incrementByHashKey(String hash, Object key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        jsonRedisTemplate.opsForHash().increment(hash, key, increment);
    }

    @Override
    public void deleteByHashKey(String hash, Object key) {

    }

    @Override
    public <T> List<T> getListByValue(String key) {
        List<T> redisResult = (List<T>) jsonRedisTemplate.opsForValue().get(key);
        return redisResult;
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list) {
        jsonRedisTemplate.opsForValue().set(key, list);

    }

    @Override
    public <T> Map<String, T> getMapByValue(String key) {
        Map<String, T> redisResult = (Map<String, T>) jsonRedisTemplate.opsForValue().get(key);
        return redisResult;
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {
        jsonRedisTemplate.opsForValue().set(key, map);

    }

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        return null;
    }

    @Override
    public void incrementByKey(String key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        jsonRedisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public void saveObjectToValue(String key, Object object) {

    }

    @Override
    public void saveValueToSet(String key, Object value) {

    }

    @Override
    public int countBySet(String key) {
        return 0;
    }

    @Override
    public void deleteValueBySet(String key, Object value) {

    }

    @Override
    public boolean hasValueInSet(String key, Object value) {
        return false;
    }

    @Override
    public void deleteCacheByKey(String key) {

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public void expire(String key, long time) {

    }
}
