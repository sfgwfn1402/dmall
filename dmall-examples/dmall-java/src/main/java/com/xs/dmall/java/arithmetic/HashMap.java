package com.xs.dmall.java.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements IMap<K, V> {

    final static int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 初始化容量
     */
    int capacity;
    /**
     * 加载因子
     */
    float loadFactor;
    /**
     * 在这个map中包含键值对的数量
     */
    int size;

    transient Entry<K, V>[] table;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {

        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }

    static class Entry<K, V> {
        final K key;
        V value;

        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Entry) {
                Entry e = (Entry) o;
                if (Objects.equals(key, e.key) && Objects.equals(value, e.value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * upperMinPowerOf2 的作用是获取大于capacity的最小的2次幂。在HashMap中，开发者采用了更精妙的位运算的方式完成了这个功能，效率比这种方式要更高。
     *
     * @param n
     * @return
     */
    private static int upperMinPowerOf2(int n) {
        int power = 1;
        while (power <= n) {
            power *= 2;
        }
        return power;
    }

    @Override
    public V put(K key, V value) {
        //是否需要扩容
        if (size >= capacity * loadFactor) {
            resize(2 * capacity);
        }
        //得到HASH值，计算出数组中的位置
        int index = hash(key) & (capacity - 1);

        Entry<K, V> current = table[index];
        if (current != null) {
            //遍历链表是否有相等的Key，有则替换且返回旧值
            while (current != null) {
                if (current.key.equals(key)) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                current = current.next;
            }
            table[index] = new Entry<K, V>(key, value, table[index]);
            size++;
            return null;
        }
        //table[index]为空，直接赋值
        table[index] = new Entry<K, V>(key, value, null);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        int index = hash(key) & (capacity - 1);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V remove(K key) {
        int index = hash(key) & (capacity - 1);
        Entry<K, V> current = table[index];
        //如果直接匹配第一个节点
        if (current.key == key) {
            table[index] = null;
            size--;
            return current.value;
        }
        //在链表中删除节点
        while (current.next != null) {
            if (current.next.key == key) {
                V oldValue = current.next.value;
                current.next = current.next.next;
                size--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    /**
     * 扩容map中entry数组
     *
     * @param i 新设置数组容量
     */
    private void resize(int i) {
        Entry[] newTable = new Entry[i];
        //改变了数组的大小
        capacity = i;
        size = 0;
        rehash(newTable);
    }

    /**
     * 重组HashMap中数组数据
     *
     * @param newTable 新的entry数组
     */
    private void rehash(Entry<K, V>[] newTable) {
        //得到原来老的Entry集合，注意遍历单链表
        List<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    entryList.add(entry);
                    entry = entry.next;
                } while (entry != null);
            }
        }
        //覆盖旧的引用
        if (newTable.length > 0) {
            table = newTable;
        }
        //所谓重新Hash，就是重新PUT entry到Hashmap
        for (Entry<K, V> entry : entryList) {
            put(entry.key, entry.value);
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> m = new HashMap();
        for (int i = 0; i < 111111; i++) {
            m.put("key" + i, "value" + i);
        }

        for (int i = 0; i < 111111; i++) {
            System.out.println("key" + i + ", value is " + m.get("key" + i));
        }
    }
}
