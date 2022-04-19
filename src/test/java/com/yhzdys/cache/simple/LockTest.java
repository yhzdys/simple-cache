package com.yhzdys.cache.simple;

import com.yhzdys.cache.simple.lock.Lock;
import com.yhzdys.cache.simple.lock.LockFactory;
import com.yhzdys.cache.simple.lock.LockType;
import com.yhzdys.cache.simple.manager.CacheManager;
import com.yhzdys.cache.simple.manager.redis.RedisCacheManager;

import java.util.concurrent.TimeUnit;

public class LockTest {
    //    public static volatile CacheManager cacheManager = new LocalCacheManager();
    public static volatile CacheManager cacheManager = new RedisCacheManager();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            LockThread thread = new LockThread(i + "");
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(cacheManager.get("123"));
    }

    static class LockThread extends Thread {
        public LockThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String key = "123";

            Object value = cacheManager.get(key);
            if (value != null) {
                System.out.println("thread: " + getName() + " get cache value: " + value);
                return;
            }
            Lock lock = LockFactory.getLock(LockType.IN_JVM);
            String session = "session" + key;
            try {
                lock.lock(session);
                value = cacheManager.get(key);
                // double check
                if (value != null) {
                    System.out.println("thread: " + getName() + " get cache value2: " + value);

                    lock.unlock(session);
                    return;
                }
                System.out.println("thread: " + getName() + " set cache");
                cacheManager.set(key, getName());
                lock.unlock(session);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
