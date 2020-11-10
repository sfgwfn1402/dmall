package com.xs.dmall.java.arithmetic;

public interface ILinked<E> {

    /**
     * 返回列表大小
     * @return
     */
    int size();

    E get(int index);

    E set(int index, E element);

    boolean add(E e);

    void add(int index, E element);

    E remove(int index);

    void clear();

}
