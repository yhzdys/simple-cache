package com.yhzdys.cache.simple.annotation;

import com.yhzdys.cache.simple.lock.LockType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Cache
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAble {

    String[] groups() default {};

    String[] keys() default {};

    String group() default "";

    String key();

    int expire() default 120;

    TimeUnit timeunit() default TimeUnit.SECONDS;

    /**
     * 缓存锁类型，默认无锁，并发量高时建议使用JVM锁控制并发
     */
    LockType lock() default LockType.NONE;

    /**
     * 允许存储空的缓存
     */
    boolean allowEmptyValue() default true;

}
