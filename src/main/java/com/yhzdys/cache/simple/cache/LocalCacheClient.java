package com.yhzdys.cache.simple.cache;

import java.util.HashMap;
import java.util.Map;

public class LocalCacheClient implements CacheClient {
    private static final Map<String, Object> _cache = new HashMap<>();

    public void set(String key, Object value) {
        _cache.put(key, value);
    }

    public Object get(String key) {
        return _cache.get(key);
    }

    public void del(String key) {
        _cache.remove(key);
    }

}
