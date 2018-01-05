package com.liueq.leetcode.easy;

/**
 * 问题描述：判断一个数字是否是 ugly number（因子只有2，3，5）
 *
 * 解：取余2，3，5 等于0的情况下，说明能够被整除，然后再除取余的数字，直到取余不为0
 * 此时如果剩下的数字是1，那么说明所有的因子都是，2，3，5，如果不是1，那么说明包含有其他因子
 */
public class UglyNumber {
    public static void main(String[] args) {

    }

    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 2 == 0
                || num % 3 == 0
                || num % 5 == 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            }
        }

        return num == 1;
    }
}
