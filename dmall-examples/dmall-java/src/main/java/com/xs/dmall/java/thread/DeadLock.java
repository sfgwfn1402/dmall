package com.xs.dmall.java.thread;

public class DeadLock {
    public static void main(String[] args) {
        Demo d1 = new Demo(true);
        Demo d2 = new Demo(false);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        t1.start();
        t2.start();
    }
}

class Demo implements Runnable {
    private boolean flag;

    Demo(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        /*
         * 两个线程同时启动，lock1和lock2被同时锁定
         * 线程1结束lock1需要锁定lock2的时候
         * lock2正好被线程2锁定，造成死锁
         */
        if (flag) {
            synchronized (MyLock.lock1) {
                System.out.println(Thread.currentThread().getName() + "...if....lock1");
                synchronized (MyLock.lock2) {
                    System.out.println(Thread.currentThread().getName() + "...if....lock2");
                }
            }
        } else {
            synchronized (MyLock.lock2) {
                System.out.println(Thread.currentThread().getName() + "...if....lock2");
                synchronized (MyLock.lock1) {
                    System.out.println(Thread.currentThread().getName() + "...if....lock1");
                }
            }
        }
    }
}

class MyLock {
    static MyLock lock1 = new MyLock();
    static MyLock lock2 = new MyLock();
}
