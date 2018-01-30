package com.liueq.leetcode.easy;

import java.util.*;

/**
 * 问题描述：给定一个字符串 s，将其打乱后在任意位置添加一个字符组成 t，求t中所多出的字符。
 *
 * 解：
 * 1 排序后双指针
 * 2 遍历存入一个列表，然后再遍历另一个字符串从列表取出，最后剩余一个。
 * 3 按位异或，0^b^b = 0 最后能够得出多的那个字符
 */
public class FindTheDifference {
    public static void main(String[] args) {
        FindTheDifference obj = new FindTheDifference();
        char c = obj.findTheDifferenceBit("abcd", "abcde");
        System.out.println(c);
    }

    public char findTheDifference(String s, String t) {
        char[] origin = s.toCharArray();
        char[] added = t.toCharArray();
        Arrays.sort(origin);
        Arrays.sort(added);
        for(int i = 0; i < origin.length; i++) {
            if (origin[i] != added[i]) {
                return added[i];
            }
        }

        return added[added.length - 1];
    }

    public char findTheDifferenceHash(String s, String t) {
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < t.length(); i++) {
            list.add(t.charAt(i));
        }

        for(int i = 0; i < s.length(); i++) {
            list.remove(Character.valueOf(s.charAt(i)));
        }

        return list.get(0);
    }

    public char findTheDifferenceBit(String s, String t) {
        char c = 0;
        for(int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
        }

        for(int i = 0; i < t.length(); i++) {
            c ^= t.charAt(i);
        }

        return c;
    }
}
