package com.liueq.leetcode.easy;

/**
 * 问题描述：输入一个int数字，判断其二进制形式下，有多少个1。
 *
 * 解：
 * 1 通过与一个 mask 进行与运算，然后不断左右mask，根据与运算的是否为0的次数得出1的个数。
 * 2 n & (n - 1) 直到 n 为0，运算的次数就是1的个数。
 *
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        NumberOf1Bits obj = new NumberOf1Bits();
        int m1 = obj.hammingWeightOfficial1(11);
        int m2 = obj.hammingWeightOfficial2(11);
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);
    }

    /**
     * 使用一个 mask 来和 n 进行 & 运算.
     *
     * 0001001
     * &
     * 0000001
     * =
     * 1 判断结果是否为0，如果不为零，说明当前位是1
     *
     * 进过几次对 mask 的左移
     * 0001001
     * &
     * 0001000
     *
     *=
     * 0001000 判断是否为0，不为玲，说明当前位是1
     *
     *
     * @param n
     * @return
     */
    public int hammingWeightOfficial1(int n) {
        int bits = 0;
        int mask = 1;
        for(int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }

            mask <<= 1;
        }
        return bits;
    }

    /**
     * 将 n 和 n-1进行与预算， 直到n等于0，计算的次数就是 1的个数。
     *
     * 原理：
     * 如果 n 是 1000，那么 n-1 就是 0111，进行与运算后就是 0000，此时说明只有1个1。
     *
     * @param n
     * @return
     */
    public int hammingWeightOfficial2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = (n & (n - 1));
        }
        return sum;
    }
}
