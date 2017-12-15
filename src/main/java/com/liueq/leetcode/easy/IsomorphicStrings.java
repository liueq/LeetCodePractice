package com.liueq.leetcode.easy;

/**
 * 问题描述：比较两个字符串，判断是否是相似（aba, cdc 相似，abb, bbc 不相似）
 *
 * 解：
 * 对于 i 来说，char[i] 在此之前出现的次数和位置在两个字符串中相同，就能够认为满足条件。
 * 此问题中，char 具体是什么不重要，重要的是出现次数和位置必须相同。
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        IsomorphicStrings obj = new IsomorphicStrings();
        String str1 = "aba";
        String str2 = "baa";
        boolean is = obj.isIsomorphic(str1, str2);
        System.out.println(is);
    }


    /**
     * 对于 i 来说，char[i] 在此之前出现的次数和位置在两个字符串中相同，就能够认为满足条件。
     * 此问题中，char 具体是什么不重要，重要的是出现次数和位置必须相同。
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();

        for(int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }

            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }

        return true;
    }
}
