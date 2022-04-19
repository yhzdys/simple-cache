package com.yhzdys.cache.simple.lock;

import com.yhzdys.cache.simple.exception.LockFailedException;
import com.yhzdys.cache.simple.exception.LockTimeoutException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class InJvmLock implements Lock {
    private static final Map<String, ReentrantLock> _lockCache = new ConcurrentHashMap<>(128);

    public void lock(String session) {
        ReentrantLock lock = _lockCache.computeIfAbsent(
                session,
                // 使用公平锁，减少部分锁超时问题
                rLock -> new ReentrantLock(true)
        );
        try {
            boolean result = lock.tryLock(40000, TimeUnit.SECONDS);
            if (!result) {
                throw new LockFailedException(session);
            }
        } catch (InterruptedException e) {
            throw new LockTimeoutException(session);
        } catch (Exception e) {
            throw new LockFailedException(session);
        }
    }

    public void unlock(String session) {
        _lockCache.get(session).unlock();
    }

}
