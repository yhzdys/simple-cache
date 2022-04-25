package com.yhzdys.cache.simple.lock.jvm;

import com.yhzdys.cache.simple.lock.Lock;
import com.yhzdys.cache.simple.lock.LockSession;

import java.util.concurrent.locks.ReentrantLock;

public class JvmLock extends ReentrantLock implements Lock {
    private final LockSession session;
    private Long lastUsedTime;

    public JvmLock(LockSession session) {
        // 使用公平锁，减少部分锁超时问题
        super(true);
        this.session = session;
        refresh();
    }

    public Long getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(Long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    @Override
    public void lock() {
        refresh();
        super.lock();
    }

    private void refresh() {
        this.lastUsedTime = System.currentTimeMillis();
    }

    @Override
    public void unlock() {
        super.unlock();
    }

}
