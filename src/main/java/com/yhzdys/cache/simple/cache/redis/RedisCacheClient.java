package com.yhzdys.cache.simple.cache.redis;

import com.alibaba.fastjson.JSON;
import com.yhzdys.cache.simple.cache.CacheClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCacheClient implements CacheClient {
    private static final GenericObjectPoolConfig<Jedis> jedisPoolConfig = new GenericObjectPoolConfig<>();
    private static final JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

    static {
        jedisPoolConfig.setMinIdle(2);
        jedisPoolConfig.setMaxIdle(4);
        jedisPoolConfig.setMaxTotal(32);
    }

    @Override
    public void set(String key, Object value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, JSON.toJSONString(value));
        jedisPool.returnResource(jedis);
    }

    @Override
    public Object get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedisPool.returnResource(jedis);
        return JSON.parse(value);
    }

    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedisPool.returnResource(jedis);
    }


}
