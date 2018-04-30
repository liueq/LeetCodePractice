package com.liueq.leetcode.easy;

/**
 * 问题描述：Hamming Distance 是指两个二进制数字比特位相异的个数。
 * 比如： 0001 vs 0100 -> 2
 *
 * 解：按照定义，先将两个数字异或，就能够得到一个数字，其中1的个数就是 Hamming Distance。
 * 如何计算一个二进制数字中1的个数？每次右移一位，判断最后一位是否为1，即 %2 是否不为0.
 */
public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance obj = new HammingDistance();
        int result = obj.hammingDistance(1, 4);

        System.out.println(result);
    }

    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            if (z % 2 != 0) {
                count++;
            }

            z = z >> 1;
        }

        return count;
    }
}
