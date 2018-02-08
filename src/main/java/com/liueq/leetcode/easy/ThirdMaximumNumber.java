package com.liueq.leetcode.easy;

/**
 * 问题描述：从一个数组中，找出第三大的数字，如果不存在，那么返回最大的数字（相同数字只算一次）
 *
 * 解：遍历数组，使用一个额外的数据结构来保存遍历途中的最大三个值。我这里使用的就是3个int类型。
 *  需要注意的是，当出现第三大数字不存在时，如何判断。我这里使用了 Long 类型来赋初始值，其实也可以用 Integer = null。
 */
public class ThirdMaximumNumber {
    public static void main(String[] args){

    }

    long max1, max2, max3;

    public int thirdMax(int[] nums) {
        for(int i = 0; i < nums.length; i ++) {
            sortMax(nums[i]);
        }

        if (max3 == Long.MIN_VALUE) {
            return (int) max1;
        }else {
            return (int) max3;
        }
    }

    private void sortMax(int one) {
        if (one <= max3 || one == max2 || one == max1) {

        } else if (one > max1) {
            max3 = max2;
            max2 = max1;
            max1 = one;
        } else if (one > max2 && one < max1) {
            max3 = max2;
            max2 = one;
        } else if (one > max3 && one < max2) {
            max3 = one;
        }
    }
}

