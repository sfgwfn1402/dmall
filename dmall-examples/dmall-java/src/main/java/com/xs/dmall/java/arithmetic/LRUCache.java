package com.xs.dmall.java.arithmetic;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Hash链表列表
 * 必要条件>>>
 * 1、显然 cache 中的元素必须有时序，以区分最近使用的和久未使用的数据，当容量满了之后要删除最久未使用的那个元素腾位置。
 * 2、我们要在 cache 中快速找某个 key 是否已存在并得到对应的 val；
 * 3、每次访问 cache 中的某个 key，需要将这个元素变为最近使用的，也就是说 cache 要支持在任意位置快速插入和删除元素。
 * LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体。
 * 分析3个条件>>>
 * 1、如果我们每次默认从链表尾部添加元素，那么显然越靠尾部的元素就是最近使用的，越靠头部的元素就是最久未使用的。
 * 2、对于某一个 key，我们可以通过哈希表快速定位到链表中的节点，从而取得对应 val。
 * 3、链表显然是支持在任意位置快速插入和删除的，改改指针就行。只不过传统的链表无法按照索引快速访问某一个位置的元素，而这里借助哈希表，可以通过 key 快速映射到任意一个链表节点，然后进行插入和删除。
 */
public class LRUCache implements Serializable {

    private static final long serialVersionUID = 3585287781697331254L;

    //Hash表Map  key -> Node(key, val)
    private HashMap<Integer, Node> map;
    //双链表cache    Node(k1,v1)    <->   Node(k2,v2)...
    private DoubleList cache;
    //最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap();
        cache = new DoubleList();
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        //将数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            //删除旧的数据
            deleteKey(key);
            //新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }
        //校验节点容量，删除最久未使用的元素
        if (cap == cache.size) {
            removeLeastRecently();
        }
        //添加为最近使用的元素
        addRecently(key, val);
    }

    /**
     * 将某个key提升为最近使用的
     *
     * @param key
     */
    private void makeRecently(int key) {
        Node x = map.get(key);
        //先从链表中删除这个节点
        cache.remove(x);
        //重新插到队尾
        cache.addLast(x);
    }

    /**
     * 添加最近使用的元素
     */
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        //链表尾部就是最近使用的元素
        cache.addLast(x);
        //别忘了在map中添加key的映射
        map.put(key, x);
    }

    /**
     * 删除某一个key
     *
     * @param key
     */
    private void deleteKey(int key) {
        //快速定位节点x
        Node x = map.get(key);
        //从链表中删除
        cache.remove(x);
        map.remove(key);
    }

    /**
     * 删除最久未使用的元素
     */
    private void removeLeastRecently() {
        //链表头部的第一个元素就是最久未使用的
        Node deleteNode = cache.removeFirst();
        //同时别忘了从map中删除它的key
        int deleteKey = deleteNode.key;
        map.remove(deleteKey);
    }

    /**
     * 节点
     */
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    /**
     * 双链表
     * 通过Node类型构建一个双链表
     * 实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
     */
    class DoubleList {
        //头尾虚节点
        private Node head, tail;
        //链表元素数
        private int size;

        public DoubleList() {
            //初始化双向链表的数据
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;//头节点的下一个节点是尾结节
            tail.prev = head;//尾结节的上一个节点是头节点
            size = 0;
        }

        /**
         * 在链表尾部添加节点x，时间O(1)
         *
         * <p>
         * -- 加入x
         * 首  中   尾
         * A   B   C      >> 加入x
         * -- 加入后
         * A   B   x   C
         * </p>
         *
         * @param x 节点x
         */
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        /**
         * 删除链表中的x节点
         * 由于是双链表且给的是目标Node节点，时间O(1)
         * A   B   x   C
         *
         * @param x
         */
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        /**
         * 删除链表中第一个节点，并返回该节点，时间O(1)
         *
         * @return
         */
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        for (int i = 1; i < 10; i++) {
            cache.put(i, i);
            Node node = cache.cache.head.next;
            do {
                System.out.print(node.val + " ");
                node = node.next;
            } while (node.val != 0);
            System.out.println();
        }
    }
}
