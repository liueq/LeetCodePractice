package com.liueq.leetcode.easy;

/**
 * 问题描述：从一个数组中，将所有的0移动到数组末尾，且保持其他元素相对位置不变。
 *
 * 解：
 * 1 两层循环，时间复杂度稍高
 * 2 双指针，最佳解决办法
 */
public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros obj = new MoveZeros();
        int[] array = {0, 1, 0, 3, 12};
        obj.moveZeros(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 两层循环的方法，时间复杂度稍弱
     * @param nums
     */
    public void moveZeros(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 寻找下一个不是0的交换
                for(int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 双指针， 只遍历一次，时间复杂度为 O(n)。
     * @param nums
     */
    public void moveZeros2Pointer(int[] nums) {
        int lastNoneZero = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[lastNoneZero];
                nums[lastNoneZero] = nums[i];
                nums[i] = temp;
                lastNoneZero++;
            }
        }
    }
}
