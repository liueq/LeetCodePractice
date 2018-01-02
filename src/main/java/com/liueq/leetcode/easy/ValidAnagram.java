package com.liueq.leetcode.easy;

import java.util.Arrays;

/**
 * 问题描述：判断两个字符串是否是 Anagram（由相同字母组成的顺序不同的字符串）
 *
 * 解：
 * 1 排序后比较
 * 2 利用 Hash 26 个字母，其中一个字符串对hash表进行加操作，另一个进行减操作，最后查看hash表上每个元素是否都为0.
 */
public class ValidAnagram {
    public static void main(String[] args) {

    }

    /**
     * 排序之后比较两个数组是否相等。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) {
            return true;
        } else if (s != null && t != null) {

        }else {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        // 可以直接比较 Array 是否相等
        // return Arrays.equals(c1, c2);
        for(int i = 0; i < c1.length; i++) {
            if(c1[i] != c2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 使用一个 counter 来存放是否命中一个字母，然后遍历，s则+，t则-。最后遍历一次 counter，看是否每个字母都为0
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramHash(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for(int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
            chars[t.charAt(i) - 'a']--;
        }

        for(int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
