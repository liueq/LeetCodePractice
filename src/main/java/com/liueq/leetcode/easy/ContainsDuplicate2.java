package com.liueq.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 问题描述：给定一个数组 nums，以及一个距离 k，判断在数组中，距离小于等于k的子数组中是否存在两个相等的值。
 *
 * 解：
 * 使用 HashSet 保存子数组k，然后使k不断向前移动，往后加入数组，移除最开始的数字。
 */
public class ContainsDuplicate2 {
    public static void main(String[] args) {
        ContainsDuplicate2 obj = new ContainsDuplicate2();
        int[] nums = {-1, -1};
        int k = 1;
        boolean b = obj.containsNearbyDuplicate(nums, k);
        System.out.println(b);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i <= k && i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return true;
            } else {
                hashSet.add(nums[i]);
            }
        }

        for (int i = k + 1; i < nums.length; i++) {
            hashSet.remove(nums[i - k - 1]);
            if (hashSet.contains(nums[i])) {
                return true;
            } else {
                hashSet.add(nums[i]);
            }
        }

        return false;
    }

    /**
     * 更简单的写法
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicateSimple(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k)
                set.remove(nums[i - k - 1]);
            if (!set.add(nums[i]))
                return true;
        }
        return false;
    }
}
