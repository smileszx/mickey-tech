package com.mickey.tech.common.core.util;

import java.util.*;

/**
 * ThreadLocal工具类
 * @author suzhengxiao
 */
public final class ThreadLocalUtil {

    /**
     * 初始化ThreadLocal为Map
     */
    private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(() -> new HashMap<>(16));

    /**
     * 获取ThreadLocal值
     * @return
     */
    public static Map<String, Object> getThreadLocal() {
        return threadLocal.get();
    }

    /**
     * 根据key值获取ThreadLocal中Map的value
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T get(String key) {
        Map map = threadLocal.get();
        return (T)map.get(key);
    }

    /**
     * 根据key值获取ThreadLocal中Map的value
     * 如果value为空，返回defaultValue
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T get(String key, T defaultValue) {
        Map map = threadLocal.get();
        return map.get(key) == null ? defaultValue : (T)map.get(key);
    }

    /**
     * ThreadLocal中的Map赋值key-value
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        Map map = threadLocal.get();
        map.put(key, value);
    }

    /**
     * ThreadLocal中的Map赋值传入的Map数据
     * @param keyValueMap
     */
    public static void set(Map<String, Object> keyValueMap) {
        Map map = threadLocal.get();
        map.putAll(keyValueMap);
    }

    /**
     * 根据key前缀获取ThreadLocal中Map的key-value值
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> fetchVarsByPrefix(String prefix) {
        Map<String, T> vars = new HashMap<>();
        if( prefix == null ){
            return vars;
        }
        Map map = threadLocal.get();
        Set<Map.Entry> set = map.entrySet();

        for( Map.Entry entry : set ){
            Object key = entry.getKey();
            if( key instanceof String ){
                if( ((String) key).startsWith(prefix) ){
                    vars.put((String)key,(T)entry.getValue());
                }
            }
        }
        return vars;
    }

    /**
     * 根据key值删除ThreadLocal中Map对应的value值
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T remove(String key) {
        Map map = threadLocal.get();
        return (T)map.remove(key);
    }

    /**
     * 根据key前缀删除ThreadLocal中Map对应的key-value
     * @param prefix
     */
    public static void clear(String prefix) {
        if( prefix == null ){
            return;
        }
        Map map = threadLocal.get();
        Set<Map.Entry> set = map.entrySet();
        List<String> removeKeys = new ArrayList<>();

        for( Map.Entry entry : set ){
            Object key = entry.getKey();
            if( key instanceof String ){
                if( ((String) key).startsWith(prefix) ){
                    removeKeys.add((String)key);
                }
            }
        }

        for( String key : removeKeys ){
            map.remove(key);
        }
    }

    /**
     * 清空ThreadLocal
     */
    public static void remove() {
        threadLocal.remove();
    }
}