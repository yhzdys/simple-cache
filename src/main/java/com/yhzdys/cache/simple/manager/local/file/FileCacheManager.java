package com.yhzdys.cache.simple.manager.local.jvm;

import com.yhzdys.cache.simple.cache.CacheClient;
import com.yhzdys.cache.simple.cache.LocalCacheClient;
import com.yhzdys.cache.simple.manager.local.AbstractLocalCacheManager;

public class JvmCacheManager extends AbstractLocalCacheManager {
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
