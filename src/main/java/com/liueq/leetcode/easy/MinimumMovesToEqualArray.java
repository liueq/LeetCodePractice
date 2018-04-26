package com.liueq.leetcode.easy;

import java.util.Arrays;
import java.util.Random;


/**
 * 问题描述： 给定一个数组 n，每次可以对整个数组的 n - 1 个元素加一。请问使得 n 个数组中所有元素都相等，需要经过多少次？
 *
 * 解：纯数学问题，如果按照每一步来编写，虽然可以得出正确答案，但是时间复杂度达不到要求。
 * 具体解答在 official 中具体描述。
 */
public class MinimumMovesToEqualArray {
    public static void main(String[] args) {
        MinimumMovesToEqualArray obj = new MinimumMovesToEqualArray();

        Random random = new Random();
        int rand = random.nextInt(Integer.MAX_VALUE);
        String randStr = String.valueOf(rand);
        char[] randChars = randStr.toCharArray();
        int[] randInts = new int[randChars.length];
        for (int i = 0; i < randChars.length; i++) {
            randInts[i] = Character.digit(randChars[i], 10);
        }
        System.out.println("rand is " + randStr);
        int res2 = obj.minMoves2(randInts);
        int res = obj.minMoves(randInts);
        System.out.println(res);
        System.out.println(res2);
    }

    /**
     * 理论上可行，但是时间复杂度不够，极端情况下非常慢，每次都要排序
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        while (nums[0] != nums[nums.length - 1]) {
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i]++;
            }
            count++;
            Arrays.sort(nums);
        }

        return count;
    }

    /**
     * 经过的步数是最小值和其他所有元素的差值，原理不明。
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            count += nums[i] - min;
        }

        return count;
    }

    /**
     * 纯数学
     *
     * @param nums
     * @return
     */
    public int minMoveOfficial(int[] nums) {
        /*
            sum 初始数组的总和
            m 总共移动的步数
            x 最终相等后的值
            min 最初最小的值
            sum + m * (n - 1) = x * n
            x = min + m
            m = sum - min * n
         */

        Arrays.sort(nums);
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        return sum - nums[0] * nums.length;
    }
}
