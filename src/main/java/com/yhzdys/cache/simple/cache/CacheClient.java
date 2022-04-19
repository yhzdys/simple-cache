package com.yhzdys.cache.simple.cache;

public interface CacheClient {

    void set(String key, Object value);

    Object get(String key);

    void del(String key);

}
