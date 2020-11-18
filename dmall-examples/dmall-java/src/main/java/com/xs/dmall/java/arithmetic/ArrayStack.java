package com.xs.dmall.java.arithmetic;

/**
 * 数组栈
 *
 * 数组栈结构：
 * 0，1，2，3，4，5
 * 出栈从最后面一个元素出，表示后进先出操作。
 *
 * 其他：
 * 链表栈结构
 * head -> 1 ->3 ->5 -> tail
 * 入栈从head入，出栈从head出
 */
public class ArrayStack<E> {

    // 数组
    private Object[] elementData;
    // 元素个数
    private int elementCount;
    // 栈大小
    private int n;

    /**
     * 初始化数组，申请一个大小为n的数组空间
     *
     * @param n
     */
    private ArrayStack(int n) {
        this.elementData = new Object[n];
        this.n = n;
        this.elementCount = 0;
    }

    /**
     * 入栈操作
     *
     * @param item 入栈元素
     * @return
     */
    public synchronized E push(E item) throws Exception {
        if (elementCount == n) {
            throw new Exception("数组空间不够了");
        }
        elementData[elementCount++] = item;
        return item;
    }

    /**
     * 出栈操作
     *
     * @return
     * @throws Exception
     */
    public synchronized E pop() throws Exception {
        int len = size();
        if (len == 0) {
            throw new Exception("空栈");
        }
        E e = (E) elementData[len - 1];
        elementCount--;
        elementData[len - 1] = null;
        return e;
    }

    public synchronized int size() {
        return elementCount;
    }

    public synchronized boolean isEmpty() {
        return elementCount == 0;
    }

}
