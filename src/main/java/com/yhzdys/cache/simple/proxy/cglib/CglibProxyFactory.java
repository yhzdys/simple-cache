package com.yhzdys.cache.simple.proxy.cglib;

import com.yhzdys.cache.simple.manager.LocalCacheManager;
import com.yhzdys.cache.simple.proxy.AbstractProxyFactory;
import com.yhzdys.cache.simple.proxy.ProxyFactory;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory extends AbstractProxyFactory {
    public static final ProxyFactory _instance = new CglibProxyFactory();

    @Override
    public Object proxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // TODO
        CglibProxy proxy = new CglibProxy();
        proxy.setCacheManager(new LocalCacheManager());

        enhancer.setCallback(proxy);
        return enhancer.create();
    }

}
