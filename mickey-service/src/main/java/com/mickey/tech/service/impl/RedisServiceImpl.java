package com.mickey.tech.service.impl;

import com.mickey.tech.manager.DistributeLockManager;
import com.mickey.tech.manager.RedisClientManager;
import com.mickey.tech.service.RedisService;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis测试实现类
 * @author suzhengxiao
 * @date 2020/3/7 15:42
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisClientManager redisClientManager;

    @Autowired
    private DistributeLockManager distributeLockManager;

    @Override
    public String test(String key, String val) {
        redisClientManager.set(key, val);
        return redisClientManager.getString(key);
    }

    @Override
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        return distributeLockManager.lock(lockKey, unit, timeout);
    }
}
