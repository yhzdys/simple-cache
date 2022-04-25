package com.yhzdys.cache.simple.lock;

public interface Lock {

    void lock() throws Exception;

    void unlock();

}
