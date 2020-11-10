package com.xs.dmall.java.arithmetic;

public interface IMap<K, V> {
    V put(K key, V value);

    V get(K key);

    int size();

    V remove(K key);

    boolean isEmpty();

    void clear();
}
