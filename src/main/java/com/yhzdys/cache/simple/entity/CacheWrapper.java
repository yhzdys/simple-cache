package com.yhzdys.cache.simple.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
public class CacheWrapper {
    private String group;
    private String key;
    private String value;
    private Long expire;
    private TimeUnit timeUnit;
}
