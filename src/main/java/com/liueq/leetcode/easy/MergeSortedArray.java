package com.liueq.leetcode.easy;

/**
 * 问题描述：合并两个有序数组 nums1, nums2，其中 nums1 有多余空间足够容纳 nums2。
 *
 * 解：
 * 由于 nums1提供了额外空间，此问题就应该在 nums1 的额外空间解决。分别从 nums1, nums2 的末尾开始比较取最大值，然后放入 nums1 后面的剩余空间内。
 */
public class MergeSortedArray {

    public static void main(String[] args){
        MergeSortedArray obj = new MergeSortedArray();
        int[] case1 = {4, 5, 6, 0, 0, 0};
        int[] case2 = {1, 2, 3};

        obj.merge(case1, 3, case2, 3);

        for(int i : case1){
            System.out.println(i);
        }
    }

    /**
     * 自己想出了标准答案
     *
     * 由于是数组，尽量不要进行无意义的数据移动，因此这里要从末尾开始比较，取出最大值。因为 nums1 中的剩余空间实在末尾。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int start = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0){
            if(nums1[i] >= nums2[j]){
                nums1[start] = nums1[i];
                i--;
            }else{
                nums1[start] = nums2[j];
                j--;
            }
            start--;
        }

        while(j >= 0){
            nums1[start] = nums2[j];
            j--;
            start--;
        }
    }

    /**
     * 只是更简洁的写法
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void mergeOfficial(int A[], int m, int B[], int n) {
        int i=m-1, j=n-1, k=m+n-1;
        while (i>-1 && j>-1) A[k--]= (A[i]>B[j]) ? A[i--] : B[j--];
        while (j>-1)         A[k--]=B[j--];
    }
}
