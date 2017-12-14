package com.liueq.leetcode.easy;

/**
 * 问题描述：求 n 以内的素数的数量
 *
 * 解：没有讨巧方法，还是按照素数定义从 2 开始遍历，只是可以一次性标记处以i为因子非素数，减少时间复杂度。
 */
public class CountPrimes {
    public static void main(String[] args) {

    }

    /**
     * 使用一个数组进行辅助，记录该位置上是否是素数，默认都是素数。
     * 外循环从2开始遍历，内循环从 i * j < n，将 i 作为因子的所有小于 n 的数字都一次性标记为非素数。
     * 虽然表面上看时间复杂度是 O(n2)，但是由于一次会标记后边的很多数字，越到后边遍历速度越快。
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        boolean[] notPrime = new boolean[n];
        for(int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for(int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        return count;
    }
}
