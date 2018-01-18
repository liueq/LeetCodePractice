package com.liueq.leetcode.easy;

/**
 * 问题描述：翻转字符串中的所有元音字母
 *
 * 解：
 * 使用双指针法，需要加上是否是原因字母的判断
 */
public class ReverseVowelsOfAString {
    public static void main(String[] args) {

    }

    public String reverseVowels(String s) {
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        for(int i = 0, j = chars.length - 1; i < j;) {
            if (isVowels(chars[i]) && isVowels(chars[j])) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            } else if (isVowels(chars[i]) && !isVowels(chars[j])) {
                j--;
            } else if (!isVowels(chars[i]) && isVowels(chars[j])) {
                i++;
            } else {
                i++;
                j--;
            }

        }

        return String.valueOf(chars);
    }

    private boolean isVowels(char c){
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'i' || c == 'o' || c == 'e' || c == 'u';
    }
}
