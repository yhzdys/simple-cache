package com.yhzdys.cache.simple.proxy.cglib;

import com.yhzdys.cache.simple.annotation.CacheAble;
import com.yhzdys.cache.simple.lock.Lock;
import com.yhzdys.cache.simple.lock.LockFactory;
import com.yhzdys.cache.simple.manager.CacheManager;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private CacheManager cacheManager;

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // TODO 当前仅是简单的实现，待补全逻辑（分组\刷新\清除...）
        CacheAble cacheAble = method.getAnnotation(CacheAble.class);
        if (cacheAble == null) {
            return methodProxy.invokeSuper(target, args);
        }
        Object cacheValue = cacheManager.get(cacheAble.key());
        if (cacheValue != null) {
            return cacheValue;
        }
        Lock lock = LockFactory.getLock(cacheAble.lock());
        String session = "lock-session-" + cacheAble.key();
        lock.lock(session);
        // double check
        cacheValue = cacheManager.get(cacheAble.key());
        if (cacheValue != null) {
            lock.unlock(session);
            return cacheValue;
        }
        cacheValue = methodProxy.invokeSuper(target, args);
        cacheManager.set(cacheAble.key(), cacheValue);
        lock.unlock(session);
        return cacheValue;
    }

}
