package com.mickey.tech.service;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类测试
 * @author suzhengxiao
 * @date 2020/3/7 15:42
 **/
public interface RedisService {

    String test (String key, String val);

    /**
     * 加锁测试
     * @param lockKey
     * @param unit
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);
}
