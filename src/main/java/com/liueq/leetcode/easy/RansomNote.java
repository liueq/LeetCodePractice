package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题描述：求一个字符串 m 中的字符是否可以全部从 n 中获取，重复的字符仍然需要计数。
 *
 * 解：
 * 1 使用Map计数
 * 2 排序之后双指针遍历
 * 3 使用一个计数的数组，类似于1
 */
public class RansomNote {
    public static void main(String[] args) {
        RansomNote obj = new RansomNote();
        String str1 = "aaa";
        String str2 = "aab";
        System.out.println(obj.canConstruct(str1, str2));
    }

    public boolean canConstruct(String ransomNote, String magazine){
        char[] ransom = ransomNote.toCharArray();
        char[] m = magazine.toCharArray();
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < m.length; i++) {
            list.add(m[i]);
        }

        for(int i = 0; i < ransom.length; i++) {
            if (list.contains(ransom[i])) {
                list.remove(Character.valueOf(ransom[i]));
            }else{
                return false;
            }
        }

        return true;
    }

    public boolean canConstruct2Pointer(String ransomNote, String magazine) {
        char[] array1 = ransomNote.toCharArray();
        char[] array2 = magazine.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);

        int hit = 0;
        for(int p1 = 0, p2 = 0; p1 < array1.length && p2 < array2.length; ) {
            char c1 = array1[p1];
            char c2 = array2[p2];

            if (c1 == c2) {
                hit++;
                p1++;
                p2++;
            }else{
                p2++;
            }
        }

        return hit == array1.length;
    }

    public boolean canConstructCount(String ransomNote, String magazine) {
        int[] counter = new int[26];

        for(int i = 0; i < magazine.length(); i++) {
            counter[(magazine.charAt(i) - 'a')]++;
        }
        for(int i = 0; i < ransomNote.length(); i++) {
            if (--counter[(ransomNote.charAt(i) - 'a')] < 0) {
                return false;
            }
        }

        return true;
    }
}
