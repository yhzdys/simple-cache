package com.yhzdys.cache.simple.lock;

public interface LockStore {

    Lock getLock(String session);

    Lock getLock(String session, Long expire);

    Lock getLock(LockSession session);

    void eliminateLock();

}
