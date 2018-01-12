package com.liueq.leetcode.easy;

/**
 * 问题描述：给出一个不变的数组，求任意 i，j index 之间的元素之和。实现此功能的方法会被多次调用。
 *
 * 解：该问题的关键在于当 sumRange 被多次调用的时候，如何能够利用之前的计算结果来降低后续计算的时间复杂度。
 * 由于问题给出了一个构造方法，所以可以在构造方法里做缓存。最佳解法是将 0~n 的所有结果都事先进行缓存，然后求 i, j 时
 * 变相的使用 0~j - 0~i，从而得出 i,j 之间的和。
 *
 */
public class RangeSumQueryImmutable {
    public static void main(String[] args) {

    }

    private int[] sum;

    public RangeSumQueryImmutable(int[] nums) {
        sum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
