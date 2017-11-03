package com.liueq.leetcode.easy;

/**
 * 描述：移除数组中某个值的所有元素，对顺序无要求
 *
 * 基础解法和 Remove Duplicates from Sorted Array 相同。
 *
 * 存在一个优化手段，在遇到要删除的元素时，将其和数组最后的元素交换，这样能够减少一些比较次数和移动，当 val 在数组中存在较少时。
 */
public class RemoveElement {

    public static void main(String[] args){
        RemoveElement re = new RemoveElement();
        int[] testCase = {1, 2, 3, 2, 2, 3};

        System.out.println(re.removeElement(testCase, 3));
        System.out.println();

        for(int i : testCase){
            System.out.println(i);
        }
    }

    /**
     * 使用两个游标，和 Remove Duplicates from Sorted Array 一样。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0){
            return 0;
        }

        int i = 0, j = 0;
        for(; j < nums.length; j++){
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

    /**
     * 第二种方法：
     *
     * 由于题目中没有要求顺序，当 val 在数组中存在很少的时候，可以通过将 val 交换到数组末端，来减少比较和移动次数
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
