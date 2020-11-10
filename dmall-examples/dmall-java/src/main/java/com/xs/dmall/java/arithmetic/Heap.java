package com.xs.dmall.java.arithmetic;

import com.alibaba.fastjson.JSON;

import java.util.Random;

/**
 * 堆通常是一个可以被看做一棵树的数组对象。堆总是满足下列性质：
 * 1.堆中某个节点的值总是不大于或不小于其父节点的值；
 * 2.堆总是一棵完全二叉树。
 * <p>
 * 堆是一颗完全二叉树，在这棵树中，所有父节点都满足大于等于其子节点的堆叫大根堆，所有父节点都满足小于等于其子节点的堆叫小根堆。
 * 堆虽然是一颗树，但是通常存放在一个数组中，父节点和孩子节点的父子关系通过数组下标来确定
 */
public class Heap {
    /**
     * 数组，从下标1开始存储数据
     */
    private int[] a;
    /**
     * 堆可以存储的最大数据个数
     */
    private int n;
    /**
     * 堆中已经存储的数据个数
     */
    private int count;

    /**
     * 构造方法，初始化指定大小的堆容量
     *
     * @param capacity
     */
    public Heap(int capacity) {
        a = new int[capacity];
        n = capacity;
        count = 0;
    }

    /**
     * 插入数据
     * <p>
     * 大根堆实现
     * 原理：从下往上堆化操作。顺着节点所在的路径，向上，对比，然后交换。
     * 新插入的节点与父节点对比大小。如果不满足子节点小于等于父节点的大小关系，就互换两个节点。一直重复这个过程，
     * 直到父子节点之间满足大根堆关系。
     *
     * @param data
     */
    public void insert(int data) {
        if (count >= n-1) {
            return;
        }
        a[count] = data;
        int i = count;
        //父节点大于0 并且 当前节点数据大于父节点数据
        while (parent(i) >= 0 && a[i] > a[parent(i)]) {
            swap(a, i, parent(i));
            i = parent(i);
        }
        count++;
    }

    /**
     * 删除堆顶元素
     * <p>
     * 原理：从上往下的堆化操作。为了在删除堆顶元素后，满足完全二叉树的特性，我们把最后一个节点放到堆顶，然后利用同样的父子节点对比方法。
     * 对于不满足父子节点大小关系的，互换两个节点，并且重复进行这个过程，直到父子节点满足大小关系为止。
     *
     * @return
     */
    public void removeMax() {
        if (count == 0) {
            return;
        }
        System.out.println("取出值：" + a[0]);
        int num = a[count];
        a[count] = 0;
        a[0] = num;

        count--;
        //堆化方法
        heapify(a, count, 0);
    }

    /**
     * 自上往下堆化
     *
     * @param a     堆数组
     * @param count 当前堆大小
     * @param i     当前删除索引位置
     */
    private void heapify(int[] a, int count, int i) {
        while (left(i) < count && a[i] < a[left(i)]) {
            swap(a, i, left(i));
            i = left(i);
        }
    }

    private void swap(int[] a, int i, int p) {
        int temp = a[i];
        a[i] = a[p];
        a[p] = temp;
    }

    /**
     * 当前节点的左子节点
     *
     * @param i 当前节点索引
     * @return
     */
    public int left(int i) {
        return (i + 1) * 2 - 1;
    }

    /**
     * 当前节点的右子节点
     *
     * @param i 当前节点索引
     * @return
     */
    public int right(int i) {
        return (i + 1) * 2;
    }

    /**
     * 当前节点的父节点
     *
     * @param i
     * @return
     */
    public int parent(int i) {
        // i为根结点
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }


    public static void main(String[] args) {
        Heap heap = new Heap(6);
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            heap.insert(random.nextInt(100));
        }
        System.out.printf("堆数据：%s\n", JSON.toJSONString(heap.a));
        System.out.println("移除最大元素");
        int x = heap.count;
        for (int j = 0; j < x; j++) {
            heap.removeMax();
            System.out.printf("堆数据：%s\n", JSON.toJSONString(heap.a));
        }
        System.out.printf("堆数据：%s", JSON.toJSONString(heap.a));
    }

}
