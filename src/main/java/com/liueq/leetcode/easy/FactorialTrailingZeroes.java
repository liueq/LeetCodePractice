package com.liueq.leetcode.easy;

/**
 * 问题描述：计算 n! 末尾的0的个数
 * <p>
 * 解：不能够直接计算，因为数字会非常巨大。
 * 末尾的0都是来自于 2 * 5，而5的出现次数少于2，所以0的个数和因子5的个数有关。
 * n / 5 = 0 为止，所有商的和就是5的个数，也就是0的个数。
 * 例如 100! 中，100 /5 = 20 + 20 / 5 = 4 + 4/5 = 0，所以最后是 20 + 4 = 24 个。
 * 因为在100 中，能被5整除的有 20 个数字，在这20个数字中，还能被5整除的有4个数字，所以累加。
 */
public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        FactorialTrailingZeroes obj = new FactorialTrailingZeroes();
        System.out.println(obj.trailingZeroes(25));
    }

    public int trailingZeroes(int n) {
        int sum = 0;
        while (n != 0) {
            n = n / 5;
            sum += n;
        }
        return sum;
    }

}

