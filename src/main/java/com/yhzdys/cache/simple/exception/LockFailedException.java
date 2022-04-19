package com.yhzdys.cache.simple.exception;

/**
 * 获取锁失败
 */
public class LockFailedException extends RuntimeException {
    private final String key;

    public LockFailedException(String key) {
        this.key = key;
    }

    public LockFailedException(String key, Throwable cause) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "try lock cache session: " + this.key + " failed";
    }
}
