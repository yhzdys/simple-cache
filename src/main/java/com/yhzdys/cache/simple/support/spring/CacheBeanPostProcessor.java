package com.yhzdys.cache.simple.support.spring;


import com.yhzdys.cache.simple.annotation.Cache;
import com.yhzdys.cache.simple.proxy.cglib.CglibProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CacheBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        boolean needCreateProxy = false;
        for (Method method : methods) {
            Cache cacheAnno = AnnotationUtils.findAnnotation(method, Cache.class);
            if (cacheAnno != null) {
                needCreateProxy = true;
                break;
            }
        }
        if (!needCreateProxy) return bean;
        return CglibProxyFactory._instance.proxy(bean);
    }

}
