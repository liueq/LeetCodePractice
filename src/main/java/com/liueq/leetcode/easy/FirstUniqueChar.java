package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 问题描述：查找一个字符串中，第一个不重复字符的位置
 *
 * 解：使用 int[26] 保存每个字母出现的频率，然后遍历字符串，当字符的频率为1时，返回下标。
 */
public class FirstUniqueChar {
    public static void main(String[] args) {
        FirstUniqueChar obj = new FirstUniqueChar();
        String s = "ihiahoifdhowndfjghoai";
        System.out.println(obj.firstUniqueChar(s));
        System.out.println(obj.firstUniqChar(s));
    }

    public int firstUniqueChar(String s){
        Set<Character> set = new HashSet<>();
        List<Character> duplicated = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                duplicated.add(c);
            } else {
                set.add(c);
            }
        }

        for(int i = 0; i < s.length(); i++) {
            if (!duplicated.contains(s.charAt(i))) {
                return i;
            }
        }

        return -1;
    }

    public int firstUniqChar(String s) {
        int[] array = new int[26];
        for(int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < s.length(); i++) {
            if (array[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }
}
