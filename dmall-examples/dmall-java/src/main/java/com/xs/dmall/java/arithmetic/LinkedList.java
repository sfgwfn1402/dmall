package com.xs.dmall.java.arithmetic;

public class LinkedList<E> implements ILinked<E> {

    int size = 0;

    Node<E> first;

    Node<E> last;

    public LinkedList(){}

    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next){
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }



    /**
     * 返回列表大小
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    /**
     * 在列表尾部加入指定元素
     * @param o
     * @return
     */
    @Override
    public boolean add(E o) {
        linkLast(o);
        return true;
    }

    /**
     * 连接e元素到最后元素上
     * @param e
     */
    void linkLast(E e){
        Node l = last;
        Node newNode = new Node(l, e, null);
        last = newNode;
        if (l != null){
            l.next = newNode;
        }
        if (first ==null){
            first = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    public static void main(String[] args){
        ILinked linkedList = new LinkedList();
        linkedList.add(1);
        for (int i =0; i < 100; i++){
            linkedList.add(i);
        }
    }
}
