package com.liueq.leetcode.easy;

/**
 * 问题描述：翻转字符串
 *
 * 解：最简单高效的方法就是双指针交换靠拢。
 */
public class ReverseString {
    public static void main(String[] args) {
        ReverseString obj = new ReverseString();
        System.out.println(obj.reverseString(null));
    }

    public String reverseString(String s){
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        for(int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }

        return String.valueOf(chars);
    }
}
