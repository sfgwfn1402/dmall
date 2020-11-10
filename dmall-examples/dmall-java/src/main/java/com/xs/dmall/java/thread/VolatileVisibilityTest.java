package com.xs.dmall.java.thread;

/**
 * JMM内存模型验证--volatile保证可见性测试
 */
public class VolatileVisibilityTest {

    //此处是否添加volatile,来验证内存模型
    private volatile static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("等待数据中....");
            while (!initFlag) {
                //线程在等待数据，initFlag初始值为false，！initFlag进入到死循环中卡在此处。
                //这也就间接验证了JMM的存在，即每个线程在工作时，都会将共享数据拷贝到自己的工作内存来操作。
                // 如果不是的话，此处多线程下执行也不会出现问题。
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("等待initFlag为true");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("--------------initFlag is true, success-----------");
        }).start();

        Thread.sleep(3000);

        new Thread(() -> {
            prepareData();
        }).start();
    }

    public static void prepareData() {
        System.out.println("\n准备数据中....");
        initFlag = true; //此处为第30行代码
        System.out.println("initFlag = " + initFlag);
        System.out.println("数据准备完成！");
    }
}
