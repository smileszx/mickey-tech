package com.mickey.tech.service.impl;

import com.mickey.tech.manager.RedisClientManager;
import com.mickey.tech.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Redis测试实现类
 * @author suzhengxiao
 * @date 2020/3/7 15:42
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisClientManager redisClientManager;

    @Override
    public String test(String key, String val) {
        redisClientManager.set(key, val);
        return redisClientManager.getString(key);
    }
}
