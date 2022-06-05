package com.example.examsys.Support.Redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

public class CustomRedisCacheManager extends RedisCacheManager {

    //默认构造器
    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    //重写父类createRedisCache方法
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        //名称中存在#标记进行到期时间配置
        if (!name.isEmpty() && name.contains("#")) {
            String[] SPEL = name.split("#");
            if (StringUtils.isNumeric(SPEL[1])) {
                //配置缓存到期时间
                int cycle = Integer.parseInt(SPEL[1]);
                //分钟为单位
                return super.createRedisCache(SPEL[0], cacheConfig.entryTtl(Duration.ofMinutes(cycle)));
            }
        }
        return super.createRedisCache(name, cacheConfig);
    }
}