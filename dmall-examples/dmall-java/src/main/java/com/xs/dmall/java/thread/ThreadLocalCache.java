package com.xs.dmall.java.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocalCache缓存工具
 *
 * @author lemontree
 * @version 2.0, 2017/8/24
 * @since 2.0
 */
public class ThreadLocalCache {

    private static final ThreadLocal<Map<Object, Object>> store;

    private void ThreadLocalCache() {

    }

    static {
        store = new InheritableThreadLocal<Map<Object, Object>>() {
            @Override
            protected Map<Object, Object> initialValue() {
                return new HashMap<>();
            }
        };
    }

    public static <K, V> void put(K key, V value) {
        store.get().put(key, value);
    }

    public static <K, V> V get(K key) {
        return (V) store.get().get(key);
    }

    public static void main(String[] args){
        ThreadLocalCache.put("zstAmountFrist",Boolean.FALSE);
        boolean payAmountFrist = ThreadLocalCache.get("payAmountFrist");
        System.out.println(payAmountFrist);
    }
}
