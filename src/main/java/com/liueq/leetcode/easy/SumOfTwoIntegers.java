package com.liueq.leetcode.easy;

/**
 * 问题描述：不使用+-的情况下，进行加减运算
 *
 * 解：换成二进制的位运算。
 */
public class SumOfTwoIntegers {
    public static void main(String[] args) {

    }



    public int getSume(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    public int getSubtract(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b; //和加法相同，只是将 a 改成了 ~a
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }
}
