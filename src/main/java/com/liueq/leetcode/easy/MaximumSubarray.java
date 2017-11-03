package com.liueq.leetcode.easy;

/**
 * 问题描述： 在一串数字中，找到相邻的一个子串，累加起来具有最大值
 *
 * 解法： 遇到的第一个 DP 问题，需要找到最优解。
 *
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        MaximumSubarray method = new MaximumSubarray();
        int[] testCast1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(method.maxSubArrayDP(testCast1));
    }

    public int maxSubArray(int[] nums){
        int start, end;
        int finalMax = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){

            int maxInner = nums[i];
            int maxJ = i;
            for(int j = i + 1; j < nums.length; j++){
                int total = 0;
                for(int k = i; k <=j; k++){
                    total += nums[k];
                }

                if(maxInner < total){
                    maxJ = j;
                    maxInner = total;
                }
            }

            if(finalMax < maxInner){
                finalMax = maxInner;
                start = i;
                end = maxJ;
            }
        }

        return finalMax;
    }


    public int maxSubArray2(int[] nums){
        int i = 0, j = nums.length - 1;
        while(i != j){
            if(nums[i] <= nums[j]){
                i++;
            }else{
                j--;
            }
        }

        int center = i;
        int sum = nums[center];
        int maxSum = sum;
        int maxI = center, maxJ = center;

        while(i > 0 && j < nums.length - 1){
            if(nums[i - 1] > nums[j + 1]){
                sum += nums[i - 1];
                if(maxSum < sum){
                    maxSum = sum;
                    maxI = i;
                    maxJ = j;
                }
                i--;
            }else{
                sum += nums[j + 1];
                if(maxSum < sum){
                    maxSum = sum;
                    maxI = i;
                    maxJ = j;
                }
                j++;
            }
        }

        return maxSum;
    }

    /**
     * 动态规划（Dynamic Programming）
     *
     * 动态规划的思想主要是将主要问题分解为若干子问题，然后通过解决，合并子问题的解，得到主要问题的解。
     *
     * 在这里，主要是使用 dp[i] = dp[i - 1] > 0 ? dp[i - 1] + A[i] : A[i]
     * i - 1 范围内的最优解，如果大于0，那么认为是对于 i 来说是有益的，所以可以纳入 i 这个范围的最优解。
     * 如果 i - 1 范围内的最优解小于0，那么认为 A[i] 就是 i 范围的最优解。
     *
     * @param nums
     * @return
     */
    public int maxSubArrayDP(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(dp[i], max);
        }

        return max;
    }

    /**
     * 此方法和 DP 有点不一样，没有使用额外空间，但是思想类似。
     *
     * 注意，无论是DP，还是 NoDP 的解法，都不能找到最优子数组的具体下标的起始和终止位置。
     *
     * 在这里，maxEndingHere 被认为是之前累加的量，如果 maxEndingHere + A[i] 是小于 A[i]，那么认为 maxEndingHere 并非最优，所以 maxEndingHere 重制为 A[i]
     * 如果 maxEndingHere + A[i] 大于了 A[i]，那么 maxEndingHere 带来的是增量，所以 maxEndingHere = maxEndingHere + A[i]
     *
     * 最后遍历整个数组，找出 maxEndingHere 最大的值 maxSoFar。
     *
     * @param nums
     * @return
     */
    public int maxSubArrayNoDP2(int[] nums){
        int maxSoFar=nums[0], maxEndingHere=nums[0];
        for (int i=1;i<nums.length;++i){
            maxEndingHere= Math.max(maxEndingHere+nums[i],nums[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * 思想基本上和 NoDP3 一样，只是写法更易懂
     * @param A
     * @return
     */
    public int maxSubArrayNoDP3(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0)
                sum = A[i];
            else
                sum += A[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }
}
