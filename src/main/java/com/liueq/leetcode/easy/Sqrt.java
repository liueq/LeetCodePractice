package com.liueq.leetcode.easy;

/**
 * 问题描述：求平方根
 *
 * 解：
 *
 * 1 直接遍历，直到 i*i > x，效率太低。
 * 2 二分查找
 * 3 牛顿迭代法
 */
public class Sqrt {

    public static void main(String[] args) {
        Sqrt obj = new Sqrt();
        System.out.println(obj.mySqrtOffical2(2147395600));
    }

    /**
     * 遍历查找，效率太低
     * @param x
     * @return
     */
    public long mySqrt(int x) {
        if (x == 0) {
            return 0;
        } else if (x < 0) {
            return x;
        }

        long max = 1;
        for (long i = 1; i <= x / 2; i++) {
            if (i * i <= x) {
                max = i;
            } else {
                break;
            }
        }

        return max;
    }

    public int mySqrtOfficial1(int x) {
        if (x <= 0) {
            return x;
        }

        int left = 1, right = x;
        while (true) {
            int mid = left + ((right - left) / 2);
            if (mid <= x / mid) {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
    }

    /**
     * 二分查找，
     * @param x
     * @return
     */
    public int sqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

    /**
     * 使用 牛顿迭代法，主要是递减的量的算法。为何一定是 (r + x/r) / 2 ?
     * @param x
     * @return
     */
    public int mySqrtOffical2(int x) {
        long r = x;
        while (r * r > x)
            r = (r + x / r) / 2;
        return (int) r;
    }
}
