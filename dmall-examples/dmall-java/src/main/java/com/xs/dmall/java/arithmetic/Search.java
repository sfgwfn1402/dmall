package com.xs.dmall.java.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class Search {

    public static void main(String[] args) {
        int[] nums = {1, 1, 6, 8, 8, 14, 14, 14, 15, 18, 22, 22, 42, 55};
        //1.最终找到索引位置是
//        System.out.println("最终找到索引位置是：" + searchInsert(nums, 24));
        //2.查找重复的数字
//        System.out.println("查找重复的数字：" + findRepeatNumber(nums));

        // 3.查找第一个值等于给定值的元素
        int index = findFirstEqualValue(nums, 1);
        if (index == -1) {
            System.out.println("查找第一个值等于给定值的元素不存在！");
        } else {
            System.out.println("查找第一个值等于给定值的元素的位置是" + index + ", 值是" + nums[index]);
        }
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 利用二分查找思路
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, center = 0;
        if (target <= nums[left]) {
            return left;
        } else if (target >= nums[right]) {
            return right;
        }
        while (true) {
            center = (right + left) / 2;
            int i = right - left;
            int j = i == 1 ? ++center : center;
            if (target == nums[center] || i == 1) {
                break;
            }
            System.out.println("查询位置：" + center);
            if (target > nums[center]) {
                left = center;
            } else if (target < nums[center]) {
                right = center;
            }
        }
        System.out.println("left：" + left + " center：" + center + " right：" + right);
        return center;
    }

    /**
     * 查找数组中重复的任意一个数字
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        Map m = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(nums[i]) != null && (int) m.get(nums[i]) == 1) {
                return nums[i];
            }
            m.put(nums[i], 1);
        }
        return 0;
    }

    /**
     * 变体一：查找第一个值等于给定值的元素的位置
     * 在有序集合中存在重复的数据，我们要找到第一个值等于给定的数据。
     *
     * @return
     */
    public static int findFirstEqualValue(int[] nums, int value) {
        int n = 0;
        int left = 0, right = nums.length - 1, center = 0;
        while (left <= right) {
            //>>1 表示除以2
            center = left + ((right - left) >> 1);
            System.out.println("第" + (++n) + "次查找，中间位置是" + center + ", 由left:" + left + " right:" + right + " >> 算出center");
            if (nums[center] == value) {
                //todo 向左再查是否重复，找到第一个相等的
                right = center;
                if (left == right) {
                    return center;
                }
            } else if (value > nums[center]) {
                left = center;
                if (value != nums[left]) {
                    left = center + 1;
                }
            } else {
                right = center;
                if (value != nums[right]) {
                    right = center - 1;
                }
            }
        }
        return -1;
    }
}
