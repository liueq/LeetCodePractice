package com.liueq.leetcode.easy;

/**
 * 问题描述：n 个硬币按照梯形排列，如:
 *  *
 *  **
 *  ***
 *  ****
 *  *****
 * 只有当第 k 层有 k 个元素时，认为是 full row。
 *
 * 求 n 个硬币的 full row 层数。
 *
 * 解：由于 full row 的定义，可以直接求 i 层的所有元素 (i * (1 + i)) / 2。当具有 i 层时，超过 i 的元素 介于 [0, i] 之间，都满足 i 层定义。
 * 由此可得计算公式。
 * 在缩小范围的时候，使用二分查找
 */
public class ArrangingCoins {

    public static void main(String[] args) {
        ArrangingCoins ac = new ArrangingCoins();
        int k = ac.arrangeCoins(5);
        System.out.println("n = 5, k = " + k);
        k = ac.arrangeCoins(8);
        System.out.println("n = 8, k = " + k);
        k = ac.arrangeCoins(1804289383);
        System.out.println("n = 1804289383, k = " + k);
        k = ac.arrangeCoins(1);
        System.out.println("n = 1, k = " + k);
    }

    public int arrangeCoins(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        long i = n / 2;
        long low = 1, high = n;
        while (true) {
            // i 行时候的和，此时必须满足 n - sum >=0 && n - sum <= i，这样才能不多一行或者少一行
            long sum = (i * (i + 1)) / 2;
            long k = n - sum;
            if (k <= i && k >= 0) {
                return (int) i;
            } else if (k < 0) {
                // 在缩小区间的时候，使用二分查找，i++ 效率太低
                // 注意二分查找时候，边界的缩小
                high = i;
                i = (low + i) / 2;
            } else if (k > i) {
                low = i;
                i = (i + high) / 2;
            }
        }
    }
}
