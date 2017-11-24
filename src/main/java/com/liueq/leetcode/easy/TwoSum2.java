package com.liueq.leetcode.easy;

import java.util.HashMap;

/**
 * 问题描述：在一个递增有序数组中，找到两个数字的下标，需要满足这两个数字的和等于target。
 * <p>
 * 解：
 * 1 用 TwoSum 的解决方法，但是没用到有序数组
 * 2 从两头向中间靠拢的遍历方法，标准答案
 */
public class TwoSum2 {
    public static void main(String[] args) {

    }

    /**
     * 利用HashMap，虽然可以解决问题，但是没有用到有序数组的条件
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] index = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            if (map.containsKey(diff)) {
                index[0] = map.get(diff) + 1;
                index[1] = i + 1;
                return index;
            } else {
                map.put(numbers[i], i);
            }
        }

        return null;
    }

    /**
     * 标准答案，利用有序数组的条件，从数组两端向中间缩小范围，直到找到。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumOfficial(int[] numbers, int target) {
        int[] index = new int[2];
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                index[0] = low + 1;
                index[1] = high + 1;
                break;
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }

        return index;
    }


}
