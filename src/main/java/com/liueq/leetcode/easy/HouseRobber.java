package com.liueq.leetcode.easy;

/**
 * 问题描述：给定一个非负数数组作为可抢劫的金额，不能够连续抢劫两个相邻的对象，计算出能够抢劫的最大金额。
 *
 * 解：使用 dp 解决，关键是处理 nums[i] 情况下，上一次抢劫与否的计算。
 */
public class HouseRobber {
    public static void main(String[] args) {

    }

    public int rob(int[] nums) {
        int rob = 0;
        int notrob = 0;
        for(int i = 0; i < nums.length; i++) {
            // 1 计算当前抢劫的情况，那么上一次肯定是不能抢劫，所以 currentRob = notrob + nums[i]
            int currentRob = notrob + nums[i];
            // 2 计算当前不抢劫的情况，上一次可以抢劫，也可以不抢劫，所以是 Math.max(notrob, rob)
            notrob = Math.max(notrob, rob);
            // 3 将当前抢劫赋值给 rob
            rob = currentRob;
        }

        // 4 返回最大值
        return Math.max(rob, notrob);
    }
}
