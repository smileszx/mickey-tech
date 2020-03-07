package com.mickey.tech.manager.impl;

import com.mickey.tech.manager.RedisClientManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis客户端
 * @author suzhengxiao
 * @date 2020/2/19 22:39
 **/
@Slf4j
@Component
public class RedisClientManagerImpl implements RedisClientManager {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("[Redis] 设置key过期时间异常, key:{}, time:{}", key, time, e);
            return false;
        }
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.warn("[Redis] 查询key值异常, key:{}", key, e);
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 根据pattern删除
     * @param pattern
     */
    @Override
    public void deleteByPattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @Override
    public String getString(String key) {
        return key == null ? null : (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis] set设置key值异常, key:{}, value:{}", key, value, e);
            return false;
        }
    }

    @Override
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("[Redis] set设置key值异常, key:{}, value:{}, time:{}", key, value, time, e);
            return false;
        }
    }

    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    @Override
    public Object hmGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean hmSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("[Redis] Map设置异常, key:{}, map:{}", key, map, e);
            return false;
        }
    }

    @Override
    public boolean hmSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("[Redis] Map设置异常, key:{}, map:{}, time:{}", key, map, time, e);
            return false;
        }
    }

    @Override
    public boolean hmSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis] Map设置字段值异常, key:{}, item:{}, value:{}", key, item, value, e);
            return false;
        }
    }

    @Override
    public boolean hmSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("[Redis] Map设置字段值异常, key:{}, item:{}, value:{}, time:{}", key, item, value, time, e);
            return false;
        }
    }

    @Override
    public void hmDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    @Override
    public boolean hmHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    @Override
    public double hmIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    @Override
    public double hmDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================

    @Override
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("[Redis] 根据key值获取Set异常, key:{}", key, e);
            return null;
        }
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("[Redis] 根据key查询Set中是否存在value值异常, key:{}, value:{}", key, value, e);
            return false;
        }
    }

    @Override
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("[Redis] 根据key查询Set中是否存在多个value值异常, key:{}, value:{}", key, values, e);
            return 0;
        }
    }

    @Override
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            log.error("[Redis] Set设置多个值并设置过期时间异常, key:{}, value:{}, time:{}", key, values, time, e);
            return 0;
        }
    }

    @Override
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("[Redis] Set根据key值查询size异常, key:{}", key, e);
            return 0;
        }
    }

    @Override
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("[Redis] Set根据key删除多个value异常, key:{}, value:{}", key, values, e);
            return 0;
        }
    }

    @Override
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("[Redis] List根据key查询对象异常, key:{}, start:{}, end:{}", key, start, end, e);
            return null;
        }
    }

    @Override
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("[Redis] List根据key查询对象size异常, key:{}", key, e);
            return 0;
        }
    }

    @Override
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("[Redis] List根据key查询index对象异常, key:{}, index:{}", key, index, e);
            return null;
        }
    }

    @Override
    public boolean rSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis] List right push对象异常, key:{}, value:{}", key, value, e);
            return false;
        }
    }

    @Override
    public boolean rSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            log.error("[Redis] List right push对象异常, key:{}, value:{}, time:{}", key, value, time, e);
            return false;
        }
    }

    @Override
    public boolean rSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis] List right push list对象异常, key:{}, value:{}", key, value, e);
            return false;
        }
    }

    @Override
    public boolean rSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            log.error("[Redis] List right push list对象异常, key:{}, value:{}, time:{}", key, value, time, e);
            return false;
        }
    }

    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis] List根据索引更新对象异常, key:{}, index:{}, value:{}", key, index, value, e);
            return false;
        }
    }

    @Override
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error("[Redis] List 删除指定个数对象异常, key:{}, count:{}, value:{}", key, count, value, e);
            return 0;
        }
    }

}
