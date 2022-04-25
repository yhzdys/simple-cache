package com.yhzdys.cache.simple.lock;

import com.yhzdys.cache.simple.exception.UnsupportedLockTypeException;
import com.yhzdys.cache.simple.lock.jvm.JvmLockStore;
import com.yhzdys.cache.simple.lock.none.NoneLock;

import java.util.HashMap;
import java.util.Map;

public class LockFactory {
    private static final Map<LockType, LockStore> lockStores = new HashMap<>(4);
    private static final Object storeLock = new Object();

    public static Lock getLock(LockType type, LockSession lockSession) {
        if (LockType.NONE.equals(type)) return NoneLock._instance;

        LockStore lockStore = lockStores.get(type);
        if (lockStore != null) return lockStore.getLock(lockSession);

        synchronized (storeLock) {
            lockStore = lockStores.get(type);
            // double check
            if (lockStore == null) {
                lockStore = createLockStore(type);
                lockStores.put(type, lockStore);
            }
        }
        return lockStore.getLock(lockSession);
    }

    private static LockStore createLockStore(LockType type) {
        if (LockType.IN_JVM.equals(type)) {
            return new JvmLockStore();
        }
        throw new UnsupportedLockTypeException(type);
    }

}
