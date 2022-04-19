package com.yhzdys.cache.simple.lock;

/**
 * 缓存锁控制
 */
public enum LockType {
    /**
     * 无锁
     */
    NONE,
    /**
     * JVM锁
     */
    IN_JVM,
    /**
     * 分布式锁（考虑下是否有必要去做）
     */
    GLOBAL
}
