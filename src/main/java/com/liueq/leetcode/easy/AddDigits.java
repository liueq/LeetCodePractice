package com.liueq.leetcode.easy;

/**
 * 问题描述：将一个数字上各个位数的数字相加，如果小于10，就返回，否则对相加得出来的数字再进行一次刚才的操作。直到小于10，返回。
 *
 * 解：
 * 1 递归解决
 * 2 寻找规律， 实际就是数字取余9的结果。
 */
public class AddDigits {

    public static void main(String[] args) {

    }

    /**
     * 递归，简单解决
     * @param num
     * @return
     */
    public int addDigits(int num) {
        int sum = 0;
        while (num != 0) {
            int remainder = num % 10;
            sum += remainder;
            num /= 10;
        }
        if (sum < 10) {
            return sum;
        }else{
            return addDigits(sum);
        }
    }

    /**
     * 巧妙方法， 只要取余9即可获得
     * @param num
     * @return
     */
    public int addDigitsTrick(int num) {
        return num == 0 ? 0 : (num%9 == 0 ? 9 : num%9);
    }
}
