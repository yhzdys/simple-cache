package com.yhzdys.cache.simple.exception;

import com.yhzdys.cache.simple.lock.LockType;

public class UnsupportedLockTypeException extends RuntimeException {
    private final LockType lockType;

    public UnsupportedLockTypeException(LockType lockType) {
        this.lockType = lockType;
    }

    @Override
    public String getMessage() {
        return "unsupported lockType: " + this.lockType.name();
    }

}
