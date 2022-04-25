package com.yhzdys.cache.simple.lock.none;

import com.yhzdys.cache.simple.lock.Lock;

public class NoneLock implements Lock {
    public static final NoneLock _instance = new NoneLock();

    @Override
    public void lock() throws Exception {

    }

    @Override
    public void unlock() {

    }
}
