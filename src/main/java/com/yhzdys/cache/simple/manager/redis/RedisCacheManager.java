package com.yhzdys.cache.simple.manager.redis;

import com.yhzdys.cache.simple.cache.CacheClient;
import com.yhzdys.cache.simple.cache.redis.RedisCacheClient;
import com.yhzdys.cache.simple.manager.CacheManager;

public class RedisCacheManager implements CacheManager {
    private static final CacheClient cacheClient = new RedisCacheClient();


    @Override
    public void set(String key, Object value) {
        cacheClient.set(key, value);
    }

    @Override
    public Object get(String key) {
        return cacheClient.get(key);
    }

    @Override
    public void del(String key) {
        cacheClient.del(key);
    }
}
