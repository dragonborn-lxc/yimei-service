package com.ymcoffee.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private RedisUtils() {}

    private static JedisPool jedisPool = null;

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (RedisUtils.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(500);
                    poolConfig.setMaxIdle(24);
                    poolConfig.setMaxWaitMillis(3*1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
                }
            }
        }
        return jedisPool;
    }

}
