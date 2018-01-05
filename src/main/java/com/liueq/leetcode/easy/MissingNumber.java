package com.liueq.leetcode.easy;

/**
 * 问题描述：给定一个数组，从0到n，然后缺少一个数字，求出该数字
 *
 * 解：关键在于该数组的起点必定是0
 *
 * 1 最好的方法，使用阶层，先计算出 !n ，然后减去数组的每一项累加之和，最后得出的就是缺少的数字
 * 2 排序，经过排序后，就能够找出缺少的那个数字，如果该数组不是从0开始，那么排序适用范围更广。
 * 3 HashSet，先将 0 ~ n 放入，然后找出缺少的一个数字。
 */
public class MissingNumber {

    public static void main(String[] args) {
        MissingNumber obj = new MissingNumber();
        int[] testCase = {0, 1, 3, 2, 5};
        int n = obj.missingNumber(testCase);
        System.out.println(n);
    }

    public int missingNumber(int[] nums) {
        int predict = 0;
        int reality = 0;
        for(int i = 0; i < nums.length; i++) {
            predict += i + 1;
            reality += nums[i];
        }

        return predict - reality;
    }
}
