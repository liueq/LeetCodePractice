package com.liueq.leetcode.easy;

/**
 * 问题描述： 在一个有序数组中，查找一个数字是否存在，如果存在，返回其下标，如果不存在，返回比他大的最小数字的下标。
 *
 * 解答：
 *
 * 1 直接遍历，时间复杂度是 O(n)
 * 2 二分查找 时间复杂度 O(log(n))
 */
public class SearchInsertPosition {

    public static void main(String[] args){

    }

    /**
     * 自己的方法：顺序遍历即可，注意边界条件
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= target){
                return i;
            }
        }

        return nums.length - 1;
    }

    /**
     * 之前也想过用二分查找，但是边界没想清楚，实际上是可以实现的。
     * 获取 mid 的时候，如果数组是奇数，那么 mid 则是中间那个数字的下标，如果是偶数，那么是前半部分最后一个的下标。总之都是 low + high / 2，其中 low, high 都是合法下标。
     *
     * @param A
     * @param target
     * @return
     */
    public int searchInsertOffical(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
}
