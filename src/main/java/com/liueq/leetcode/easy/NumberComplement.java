package com.liueq.leetcode.easy;

/**
 * 问题描述：求一个 int 二进制下各位翻转后的数字。
 *
 * 解：位翻转只需要每一位 xor 1 即可。另外需要求得 input 二进制长度的 1。
 *
 */
public class NumberComplement {
    public static void main(String[] args) {
        NumberComplement obj = new NumberComplement();
        obj.findComplement(5);
    }

    public int findComplement(int num) {
        String originBinary = Integer.toBinaryString(num);
        int length = originBinary.length();
        int xorInt = 0;
        for (int i = 0; i < length; i++) {
            int pow = (int) Math.pow(2, i);
            xorInt += pow;
        }

        System.out.println("Before: " + Integer.toBinaryString(num));
        System.out.println("Max: " + Integer.toBinaryString(xorInt));
        System.out.println("After flip: " + Integer.toBinaryString(num ^ xorInt));
        return num ^ xorInt;
    }
}
