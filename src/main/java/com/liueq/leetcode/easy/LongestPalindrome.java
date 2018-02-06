package com.liueq.leetcode.easy;

import java.util.Arrays;

/**
 * 问题描述：求使用一个字符串中的所有字符，能够组成的最长回文串的长度
 *
 * 解：
 * 1 官方解答，先用一个数组统计所有字符出现的次数，然后取其偶数最大值，如果有一个落单，那么可以设置为中心点。
 * 2 先排序，然后顺序遍历，同样是统计重复字符串出现的最大偶数次之和，如果有一个字符，那么可以设为中心点。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome obj = new LongestPalindrome();
        int len = obj.longestPalindrome("abccccdd");
        String s = "abccccdd";
        System.out.println(len);
    }

    public int longestPalindrome(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);

        int count = 0;
        boolean single = false;
        for (int i = 0; i < array.length - 1; ) {
            int j = i;
            int temp = 1;
            while (j < array.length - 1 && array[j] == array[j + 1]) {
                temp++;
                j++;
            }

            if (temp % 2 == 0) {
                count += temp / 2;
            } else {
                count += (temp - 1) / 2;
                single = true;
            }
            i = j + 1;
        }

        return count * 2 + (single ? 1 : 0);
    }

    public int longestPalindromeOfficial(String s) {
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int sum = 0;
        for (int i : count) {
            sum += i / 2 * 2; //sum 加上 i 范围内的最大偶数

            if (sum % 2 == 0 && i % 2 == 1) {
                sum++; // 仅会使用一次，只有 sum 为偶数时，说明没有一个中心点，那么可以设一个。
            }
        }

        return sum;
    }
}
