package com.yhzdys.cache.simple.proxy.cglib;

import com.yhzdys.cache.simple.manager.local.jvm.JvmCacheManager;
import com.yhzdys.cache.simple.proxy.AbstractProxyFactory;
import com.yhzdys.cache.simple.proxy.ProxyFactory;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory extends AbstractProxyFactory {
    public static final ProxyFactory _instance = new CglibProxyFactory();

    @Override
    public Object proxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        CglibProxy proxy = new CglibProxy();

        // TODO 需要动态加载cacheManager，考虑引入SPI机制?
        proxy.setCacheManager(new JvmCacheManager());

        enhancer.setCallback(proxy);
        return enhancer.create();
    }

}
