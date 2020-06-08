package com.xs.dmall.java.container;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;


public class test_concurrentHashMap {
    public static void main(String[] args) {
        //使用HashTable测试
        final Map<String, String> map = new Hashtable<String, String>();
        //使用concurrentHashMap测试
//        final Map<String,String> map=new ConcurrentHashMap<String, String>();
        Thread[] array = new Thread[1000];
        final Random r = new Random();
        //定义一个门栓
        final CountDownLatch latch = new CountDownLatch(array.length);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        map.put("key" + r.nextInt(1000000), "value" + r.nextInt(1000000));
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
        System.out.println("执行时间为：" + (end - begin));
    }

}
