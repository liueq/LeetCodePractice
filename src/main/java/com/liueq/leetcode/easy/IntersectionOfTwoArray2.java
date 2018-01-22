package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题描述：求两个数组中的所有重合的共同元素（包括重复）
 *
 * 解：
 * 1 用 HashSet 可以解决
 * 2 排序后双指针
 */
public class IntersectionOfTwoArray2 {
    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] < nums2[j]) {
                i++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] array = new int[list.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
