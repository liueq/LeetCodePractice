package com.liueq.leetcode.easy;

/**
 * 问题描述：给出 1 ~ n ，并且有一个判断算法 isBadVersion，找出 m，从m开始 isBadVersion 返回false。
 *
 * 解：二分查找，注意 mid 计算
 */
public class FirstBadVersion {
    public static void main(String[] args) {
        int low = 1;
        int high = 2;
        System.out.println(low + (high - low) / 2);
        System.out.println((low + high) / 2);
    }

    public long firstBadVersion(long n) {
        if (n == 1) {
            return 1;
        }
        return search(1, n);
    }

    private long search(long low, long high) {
        System.out.println(low + ":" + high);
        if (low >= high) {
            return low;
        }
        long mid = (low + high) / 2;
        if (isBadVersion(mid)) {
            if (isBadVersion(mid - 1)) {
                return search(low, mid - 1);
            } else {
                return mid;
            }
        } else {
            return search(mid + 1, high);
        }
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(long version) {
//        return version >= 73;
        return version >= 1702766719;
    }
}
