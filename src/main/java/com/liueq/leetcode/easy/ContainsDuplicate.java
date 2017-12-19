package com.liueq.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 问题描述：查找一个数组中是否存在重复的数字
 *
 * 解：
 * 1 使用 HashSet
 * 2 先排序，后遍历
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        ContainsDuplicate obj = new ContainsDuplicate();
        int[] array = {3, 5, 1, 2, 3, 33, 23, 10, 2, 23};
        obj.quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return true;
            } else {
                hashSet.add(nums[i]);
            }
        }

        return false;
    }

    public boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int flag = low;
        int p = low;
        int q = high + 1;
        while (p < q) {
            while (nums[++p] < nums[flag]) {
                if (p == high) {
                    break;
                }
            }
            while (p < q && nums[--q] > nums[flag]) {
                if (q == low) {
                    break;
                }
            }

            if (p >= q) {
                break;
            }

            int temp = nums[p];
            nums[p] = nums[q];
            nums[q] = temp;
        }

        nums[low] = nums[q];
        nums[q] = nums[flag];

        quickSort(nums, low, q - 1);
        quickSort(nums, q + 1, high);
    }
}
