package com.liueq.leetcode.easy;

import java.util.Arrays;

/**
 * 问题描述：分配饼干。孩子：g，饼干：s。只有当 g[i] <= s[i] 时，此分配才能让孩子满足，求最多能让多少孩子满足。
 *
 * 解：首先按递增排序。
 * 分别遍历 g, s，只要 g[i] <= s[i] 则分配成功，g++, s++。反之，仅s++。
 */
public class AssignCookies {
    public static void main(String[] args) {
        AssignCookies obj = new AssignCookies();
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        int[] g3 = {10, 9, 8, 7};
        int[] s3 = {5, 6, 7, 8};
        System.out.println(obj.findContentChildren(g1, s1));
        System.out.println(obj.findContentChildren(g2, s2));
        System.out.println(obj.findContentChildren(g3, s3));
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int p1 = 0, p2 = 0;
        while (p1 < g.length && p2 < s.length) {
            if (g[p1] <= s[p2]) {
                p1++;
                p2++;
                count++;
            } else {
                p2++;
            }
        }

        return count;
    }
}
