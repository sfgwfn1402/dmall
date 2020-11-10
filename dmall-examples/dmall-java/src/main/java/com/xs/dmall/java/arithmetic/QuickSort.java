package com.xs.dmall.java.arithmetic;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序
 * 基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

    public static void main(String[] args) {
        int num[] = {111, 99, 88, 77, 66, 55, 44, 33, 22, 11};//temp =10
        //1. i =6, j=10
        QuickSort quickSort = new QuickSort();

        System.out.println("原始数据: " + JSON.toJSONString(num));
        quickSort.quick(num, 0, num.length - 1);
        System.out.println("最终结果：" + JSON.toJSONString(num));
    }

    public void quick(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        //处理分区点pivot两边数据（）
        int i = p, j = r, t = 1;
        int temp = a[r];//取最右的数据作为中间数据
        while (i < j) {
            if (a[i] > temp && t == 1) {//从左向右找第一个大于temp的数
                a[j--] = a[i];
                t = 2;//交给右边开始找
            } else if (t == 1) {
                i++;//没有找到，增一个位置继续找
                continue;
            }
            if (a[j] < temp && t == 2) {//从右边向左找第一个小于temp的数
                a[i++] = a[j];
                t = 1;
            } else if (t == 2) {
                j--;//没有找到，减一个位置继续找
                continue;
            }
        }
        a[i] = temp;
        System.out.println("区间是：[" + p + "," + r + "] ,选中位置：" + r + "，比较的值是：" + temp + "--------");
        System.out.println("排序后：" + JSON.toJSONString(a));

        //获得分区点的索引位置
        quick(a, p, i - 1);
        quick(a, i + 1, r);

    }

}
