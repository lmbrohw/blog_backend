package com.xjtu.blog_backend.service.impl;

import com.xjtu.blog_backend.entity.vo.BlogInfo;
import com.xjtu.blog_backend.entity.vo.PageResult;
import com.xjtu.blog_backend.service.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Huwwww
 * @Date: 2023/4/6 00:12
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public PageResult<BlogInfo> getBlogInfoPageResultByHash(String hash, Integer pageNum) {
        return null;
    }

    @Override
    public void saveKVToHash(String hash, Object key, Object value) {

    }

    @Override
    public void saveMapToHash(String hash, Map map) {

    }

    @Override
    public Map getMapByHash(String hash) {
        return null;
    }

    @Override
    public Object getValueByHashKey(String hash, Object key) {
        return null;
    }

    @Override
    public void incrementByHashKey(String hash, Object key, int increment) {

    }

    @Override
    public void deleteByHashKey(String hash, Object key) {

    }

    @Override
    public <T> List<T> getListByValue(String key) {
        return null;
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list) {

    }

    @Override
    public <T> Map<String, T> getMapByValue(String key) {
        return null;
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {

    }

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        return null;
    }

    @Override
    public void incrementByKey(String key, int increment) {

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
