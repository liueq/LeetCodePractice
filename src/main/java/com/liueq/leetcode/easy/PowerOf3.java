package com.liueq.leetcode.easy;

/**
 * 问题描述：判断一个数字是否是3的n次方
 *
 * 解：
 * 1 使用循环，取余和除结合
 * 2 转为3进制，然后判断是否是1000..这样的模式
 * 3 使用数学公式转换未log，并计算
 * 4 找到 int 范围内最大的3的n次方，然后只要能够被它整除，那么就是3的n次方。
 */
public class PowerOf3 {
    public static void main(String[] args) {

    }

    public boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 对于3进制来说，任何3的次方都是开头一个1，后边接多个0的格式。所以这里将10进制转为3进制，然后判断是否满足正则表达式。
     * @param n
     * @return
     */
    public boolean isPowerOfThreeBase(int n) {
        String rd3 = Integer.toString(n, 3);
        return rd3.matches("^10$");
    }


    /**
     * 数学公式实现
     * 1 n = (3)i 3的i次方
     * 2 使用log表达
     *      log3(n) = i
     * 3 变换
     *      log10(n) / log10(3) = i
     * 4 用 Java 表达。
     * @param n
     * @return
     */
    public boolean isPowerOfTreeLog(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    /**
     * 由于输入类型是 int，最大只是 Integer.MAX_VALUE
     * 在此范围内，3的n次方最大的n是19
     * 因此3的19次方能够整除n，那么n就必定是3的次方。
     * @param n
     * @return
     */
    public boolean isPowerOfThreeLimit(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
