package com.liueq.leetcode.easy;

/**
 * 描述，将一个有序数组中的重复项目移动到数组末端，并且统计出不重复的长度
 *
 * 解答：
 *
 * 1 我自己是通过两层的遍历实现，时间复杂度太高了。
 * 2 标准答案只遍历一次数组，利用两个游标同时移动的机制，非常巧妙。
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args){
        RemoveDuplicatesFromSortedArray remove = new RemoveDuplicatesFromSortedArray();
        int[] testCase1 = {1, 1, 2, 2, 3};
        System.out.println(remove.removeDuplicates1(testCase1));
        System.out.println("\n");
        for(int i : testCase1){
            System.out.println(i);
        }
    }

    /**
     * 自己的答案：
     *
     * 比较容易想到，用 i 遍历，当和 i + 1 相等时，将 i + 1 移动到数组末端。
     *
     * 存在的问题是所花费时间太长，时间复杂度是 O(n)2
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int i = 0;
        int endOfi = nums.length - 1;
        while(i != endOfi){
            if(nums[i] < nums[i + 1]){
                i++;
            }else{
                //i + 1 to end
                for(int j = i + 1; j < endOfi; j++){
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
                endOfi--;
            }
        }

        return i + 1;
    }

    /**
     * 标准答案
     *
     * 同时移动 2 个游标，当数字不等时，两个都前移，并且将j赋值给i，这样i总是不重复的。
     * 当i，j 相等，那么说明存在重复，移动j跳过重复部分。
     *
     * 时间复杂度是 O(n)
     * @param nums
     * @return
     */
    public int removeDuplicates1(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
