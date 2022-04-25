package com.yhzdys.cache.simple.lock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockSession {
    private static final long defaultExpire = 1000L * 8;

    private String sessionKey;
    /**
     * 会话超时时间 timeUnit=MILLISECONDS
     */
    private Long expire;

    public LockSession(String sessionKey) {
        this.sessionKey = sessionKey;
        this.expire = defaultExpire;
    }

    public LockSession(String sessionKey, Long expire) {
        this.sessionKey = sessionKey;
        this.expire = expire;
    }

}