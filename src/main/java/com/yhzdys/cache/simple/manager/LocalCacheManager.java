package com.yhzdys.cache.simple.manager;

import com.yhzdys.cache.simple.cache.CacheClient;
import com.yhzdys.cache.simple.cache.LocalCacheClient;

public class LocalCacheManager implements CacheManager {
    private final CacheClient cacheClient = new LocalCacheClient();

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
