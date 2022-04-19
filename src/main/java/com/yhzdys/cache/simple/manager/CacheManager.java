package com.yhzdys.cache.simple.manager;

public interface CacheManager {

    void set(String key, Object value);

    Object get(String key);

    void del(String key);

}
