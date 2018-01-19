package com.liueq.leetcode.easy;

import java.util.*;

/**
 * 问题描述：求两个数组中的所有不重复共同元素
 *
 * 解：
 * 1 使用 HashSet
 * 2 先对两个数组排序，然后双指针遍历
 * 3 可以使用二分查找
 */
public class IntersectionOf2Array {
    public static void main(String[] args) {
        IntersectionOf2Array obj = new IntersectionOf2Array();
        int[] nums1 = new int[]{1,2,3,4,5};
        int[] nums2 = new int[]{3, 4};
        int[] intersection = obj.intersection(nums1, nums2);
        for(int i = 0; i < intersection.length; i++) {
            System.out.println(intersection[i]);
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        Set<Integer> set2 = new HashSet<>();
        for(int i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }

        int[] array = new int[set2.size()];
        int j = 0;
        for (Integer i : set2) {
            array[j++] = i;
        }

        return array;
    }

    public int[] intersectionSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> set = new HashSet<>();
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length;){
            if (nums1[i] != nums2[j]) {
                if (nums1[i] > nums2[j]) {
                    j++;
                }else{
                    i++;
                }
            }else{
                i++;
                j++;
                set.add(nums1[i]);
            }
        }

        int[] array = new int[set.size()];
        int j = 0;
        for (Integer i : set) {
            array[j++] = i;
        }

        return array;
    }
}
