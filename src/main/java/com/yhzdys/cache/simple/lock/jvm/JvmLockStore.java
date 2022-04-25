package com.yhzdys.cache.simple.lock.jvm;

import com.yhzdys.cache.simple.lock.Lock;
import com.yhzdys.cache.simple.lock.LockSession;
import com.yhzdys.cache.simple.lock.LockStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JvmLockStore implements LockStore {
    private static final int threshold = 1024;
    private static final Map<String, JvmLock> _lockStore = new ConcurrentHashMap<>(256);

    @Override
    public Lock getLock(String session) {
        return getLock(new LockSession(session));
    }

    @Override
    public Lock getLock(String session, Long expire) {
        return getLock(new LockSession(session, expire));

    }

    @Override
    public Lock getLock(LockSession session) {
        return _lockStore.computeIfAbsent(
                session.getSessionKey(), rLock -> new JvmLock(session)
        );
    }

    @Override
    public void eliminateLock() {
        // TODO
    }
}
