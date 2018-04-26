package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：给定一个长度为 n 的数组，其中的元素都 1 <= nums[i] <= n。找出该数组中 1~n 范围内，未出现的数字。不可使用额外空间，除了返回的 list 外。
 *
 * 解：由于nums中的元素都是介于 1~n 之间的数字，所以遍历一次，将其对应的下标的值改为负数；第二次再次遍历，找出非负数的值，其下标就是未出现的数字。
 * 注意一些细节：由于数字是 1~n 之间，所以在转换成下标时，应该减一，第二次遍历还原时应该加一。
 * 当第一次遍历将元素设置为负值时，便利到该负值的元素，应该先转换成正值来获取正确下标。
 */
public class FindAllNumberDisappearedInString {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        FindAllNumberDisappearedInString obj = new FindAllNumberDisappearedInString();
        List<Integer> list = obj.findDisappearedNumbersOfficial(nums);
        for (int i : list) {
            System.out.println(i);
        }
    }

    /**
     * 时间复杂度不满足
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            list.add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                list.remove((Integer) nums[i]);
            }
        }

        return list;
    }

    public List<Integer> findDisappearedNumbersOfficial(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = (nums[i] > 0 ? nums[i] : -nums[i]) - 1;

            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }

        return list;
    }
}
