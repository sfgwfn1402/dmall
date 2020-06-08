package com.xs.dmall.java.container;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class test_copyonWriterlist {
    public static void main(String[] args) {
        //测试Vectot
       final List list=new Vector();
        //测试CopyOnWriterArrayList
//        final List<String> list = new CopyOnWriteArrayList<String>();
        Thread[] array = new Thread[100];
        final Random r = new Random();
        //定义一个门栓
        final CountDownLatch latch = new CountDownLatch(array.length);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        list.add("value" + r.nextInt(100000));
                    }
                    latch.countDown();
                }
            });
        }
        for (Thread t : array) {
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - begin));
    }
}
