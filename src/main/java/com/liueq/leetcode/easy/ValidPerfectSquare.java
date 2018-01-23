package com.liueq.leetcode.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题描述： 判断一个数字是否是某个数字的平方，也就是说判断其平方根是否是一个整数
 *
 * 解：
 * 1 可以手动开方，但是计算过程太复杂了，不适合写代码
 * 2 一个 perfect square，必定是由从1开始的连续奇数累加所组成，所以遍历
 * 3 二分查找法
 * 4 牛顿迭代，主要是 x 减小的过程需要注意
 */
public class ValidPerfectSquare {
    public static void main(String[] args) {

    }


    public boolean isPerfectSquarePlus(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }

        return num == 0;
    }

    public boolean isPerfectSquareDivide(int num) {
        long low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid > num) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return false;
    }

    public boolean isPerfectSquareNewton(int num) {
        int x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }

        return x * x == num;
    }


    /**
     * 试商法，理解的话还好，但是用代码实现比较麻烦，主要是各种数据类型的转换很麻烦。
     * @param num
     * @return
     */
    public boolean isPerfectSquareRemainder(int num) {
        List<String> list = convertIntoArray(num);

        // 1. 求出最高位的平方根，由于数量级小，遍历实现。可得到最高位准确的试商。
        // 2. 求出最高位平方根平方后的值，然后用当前数字减去得到商2
        // 3. 商2 除以1得到的试商，得出次高位的试商
        // 4. 逆向3的过程，(2*试商1 + 试商2)*试商2，在小于 3 的商2的情况下，最大的试商2就是次高位的准确值
        // 5. 继续2的过程

        return false;
    }

    /**
     * 转换成相邻两位一组的数组
     * @param num
     * @return
     */
    public List<String> convertIntoArray(int num) {
        String s = String.valueOf(num);
        List<String> list = new LinkedList<>();
        for(int i = s.length() - 1; i >= 0; i = i - 2) {
            String str = "";
            if (i - 1 >= 0) {
                char c2 = s.charAt(i - 1);
                str += c2;
            }
            char c1 = s.charAt(i);
            str += c1;
            list.add(0, str);
        }

        return list;
    }
}
