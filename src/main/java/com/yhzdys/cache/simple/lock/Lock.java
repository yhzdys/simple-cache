package com.yhzdys.cache.simple.lock;

public interface Lock {

    void lock(String session);

    void unlock(String session);

}
