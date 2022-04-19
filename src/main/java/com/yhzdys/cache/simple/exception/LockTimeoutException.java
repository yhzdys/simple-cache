package com.yhzdys.cache.simple.exception;

/**
 * 获取锁超时
 */
public class LockTimeoutException extends RuntimeException {
    private final String key;

    public LockTimeoutException(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "try lock cache session: " + this.key + " timeout";
    }
}
