package com.yhzdys.cache.simple.lock;

import com.yhzdys.cache.simple.exception.UnsupportedLockTypeException;
import com.yhzdys.cache.simple.lock.jvm.JvmLock;
import com.yhzdys.cache.simple.lock.none.NoneLock;

import java.util.HashMap;
import java.util.Map;

public class LockFactory {
    private static final Map<LockType, Lock> lockMap = new HashMap<>(4);
    private static final Object lockObject = new Object();

    public static Lock getLock(LockType type) {
        Lock lock = lockMap.get(type);
        if (lock != null) return lock;
        synchronized (lockObject) {
            lock = lockMap.get(type);
            if (lock != null) return lock;
            lockMap.put(type, createLock(type));
        }
        return lock;
    }

    private static Lock createLock(LockType type) {
        if (LockType.NONE.equals(type)) {
            return new NoneLock();
        }
        if (LockType.IN_JVM.equals(type)) {
            return new JvmLock();
        }
        throw new UnsupportedLockTypeException(type);
    }

}
