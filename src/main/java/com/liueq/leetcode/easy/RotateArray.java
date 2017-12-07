package com.liueq.leetcode.easy;

import java.util.Arrays;

/**
 * 问题描述：向右平移一个数组，求平移 k 之后，数组的结果。
 * <p>
 * 解：有多种解法
 * <p>
 * 1 不考虑空间复杂度的情况下， 可以利用一个新的数组，然后复制需要的部分就行了。
 * 2 循环遍历法，较为复杂，无需额外空间
 * 3 多次反转法，简单，且无需额外空间。
 */
public class RotateArray {
    public static void main(String[] args) {
        RotateArray obj = new RotateArray();
        int[] test = {1, 2, 3, 4, 5, 6, 7};
        obj.rotateOfficial2(test, 3);
        for (int i : test) {
            System.out.println(i);
        }
    }

    /**
     * 尝试冒泡一下
     * 时间复杂度达不到要求
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            for (int l = nums.length - 1; l > 0; l--) {
                int temp = nums[l];
                nums[l] = nums[l - 1];
                nums[l - 1] = temp;
            }
        }
    }

    /**
     * 空间换时间
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int[] another = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            int index = i - k;
            while (index < 0) {
                index = nums.length - Math.abs(index);
            }
            nums[i] = another[index];
        }
    }

    /**
     * 循环遍历法
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * <p>
     * 从数组开头，间隔 k 的位置进行遍历，交换除第一个元素外的相邻两个元素的值，持续遍历直到游标回到了此次遍历开始的下标。
     * 完成上一步后，从下一个元素开始继续相同操作。直到交换元素的次数等于数组的长度为止。
     *
     * @param nums
     * @param k
     */
    public void rotateOfficial1(int[] nums, int k) {
        int count = 0;
        k = k % nums.length;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = start;
            do {
                int next = (current + k) % nums.length;

                //交换 next 和 prev 的值
                int swap = nums[next];
                nums[next] = nums[prev];
                nums[prev] = swap;

                //current 只是用来计算出下一个next
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * 反转数组法
     * <p>
     * 先反转整个数组，然后再反转 0 ~ k-1，再反转 k~length，那么得到结果就是所求的结果。
     *
     * @param nums
     * @param k
     */
    public void rotateOfficial2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int swap = nums[start];
            nums[start] = nums[end];
            nums[end] = swap;
            start++;
            end--;
        }
    }
}
