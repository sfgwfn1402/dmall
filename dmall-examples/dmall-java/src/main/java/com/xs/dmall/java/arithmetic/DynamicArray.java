package com.xs.dmall.java.arithmetic;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class DynamicArray<E> implements IArray<E> {

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    Object[] elementData;

    /**
     * 构造一个空间列表
     */
    public DynamicArray() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity：" + initialCapacity);
        }
    }

    /**
     * 返回列表中元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 如果列表中无元素，则返回true
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        rangCheck(index);
        return elementData(index);
    }

    private void rangCheck(int index){
        if (index >=size){
            throw new IndexOutOfBoundsException("Index: " + index +", Size: "+ size);
        }
    }
    E elementData(int index){
        return (E) elementData[index];
    }

    /**
     * 增加一个指定元素到列表的尾部
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        //校验是否需要扩容数组
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * 确保内部容量
     *
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        // 容量大于现有列表中元数个数，需要扩容列表
        if (minCapacity - elementData.length > 0) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    public static void main(String[] args) {
        DynamicArray da = new DynamicArray();
        da.add(1);
        for (int i =0; i <10000; i++){
            da.add(i);
        }
        System.out.println(JSON.toJSONString(da));

        System.out.println(da.get(55));

    }
}
