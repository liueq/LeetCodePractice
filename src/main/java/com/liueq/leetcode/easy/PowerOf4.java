package com.liueq.leetcode.easy;

/**
 * 问题描述：判断n是否是4的i次方
 *
 * 解：同 PowerOf3 那个问题
 * 1 循环遍历，i从1开始直到m
 * 2 数学方法，转为 log计算
 * 3 转成4进制，然后判断是否满足 10000 这样的模式
 * 4 找出 Integer.MAX_VALUE 范围内最大的4的次方，然后除以num，判断能否整除。
 */
public class PowerOf4 {
    public static void main(String[] args) {

    }

    public boolean isPowerOfFour(int num) {
        return Math.log10(num) / Math.log10(4) % 1 == 0;
    }

    public boolean isPowerOfFourBase(int num) {
        return Integer.toString(num, 4).matches("^10*$");
    }

}
