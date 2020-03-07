package com.mickey.tech.manager;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * @author suzhengxiao
 * @date 2020/1/13 14:22
 **/
public interface DistributeLockManager {
    /**
     * 加锁
     * @param lockKey
     * @return
     */
    RLock lock(String lockKey);

    /**
     * 加锁 + 锁时长，默认秒
     * @param lockKey
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, int timeout);

    /**
     * 加锁 + 锁时长，自定义时间单位
     * @param lockKey
     * @param unit
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     * 加锁
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     * 解锁
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     * 解锁
     * @param lock
     */
    void unlock(RLock lock);
}
