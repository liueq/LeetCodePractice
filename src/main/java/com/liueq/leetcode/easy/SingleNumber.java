package com.liueq.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：一个数组中，所有数字都出现2次，只有一个数字是出现1次，求该数字。
 * <p>
 * 解：有多种解法:
 * 1 主推 XOR。
 * 2 利用HashMap，但是需要额外空间
 * 3 先排序，然后比较 i和i+1，需要排序这个时间复杂度。
 */
public class SingleNumber {
    public static void main(String[] args) {
        SingleNumber obj = new SingleNumber();
        int[] array = {1, 2, 3, 2, 1, 3, 4, 5, 6, 5, 6};
        int single = obj.singleNumberOfficial2(array);
        System.out.println(single);
    }

    /**
     * 自己想的，先排序，然后比较 i 和 i+1是否相等，只要每个数字都出现2次，只有一个数字出现1次。
     * 那么出现 i!=i+1时，就是找出该数字的时候。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
//        for (int i : nums) {
//            System.out.print(i);
//        }
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int center = nums[low];
        int i = low, j = high + 1;
        while (true) {
            while (nums[++i] < center) {
                if (i == high) {
                    break;
                }
            }
            while (nums[--j] > center) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        int temp = nums[low];
        nums[low] = nums[j];
        nums[j] = temp;

        quickSort(nums, low, j - 1);
        quickSort(nums, j + 1, high);
    }

    /**
     * 利用 HashMap 查找时间为1的特性，迅速判断是否已经有过某一元素了。
     * 此算法需要额外空间n
     *
     * @param nums
     * @return
     */
    public int singleNumberOfficial1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, 0);
            } else {
                map.put(i, 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 使用逻辑异或（XOR）运算轻松解决。
     *
     * @param nums
     * @return
     */
    public int singleNumberOfficial2(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}
