package com.liueq.leetcode.easy;

/**
 * 问题描述：判断是否是回文，只关注字母和数字，其他字符不管
 *
 * 解：跳过其他字符，然后判断回文即可
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
//        System.out.println(obj.isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(obj.isPalindrome("race a car"));
        System.out.println(obj.isPalindrome(",."));
    }

    /**
     * 自己想的，基本就是标准思路，主要是注意跳过非数字字母的字符，然后比较头尾即可。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // 可以直接用该方法判断是否是数字或字符 Character.isLetterOrDigit()，实现方式是使用位运算，效率高于直接比较 ASCII 码
            while (i < s.length() && (s.charAt(i) < '0' || (s.charAt(i) > '9' && s.charAt(i) < 'a') || s.charAt(i) > 'z')) {
                i++;
            }
            while (j >= 0 && (s.charAt(j) < '0' || (s.charAt(j) > '9' && s.charAt(j) < 'a') || s.charAt(j) > 'z')) {
                j--;
            }
            if (i < j && i < s.length() - 1 && j >= 0 && s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }

        return true;
    }

    /**
     * 非常巧妙，先用正则表达式，替换掉所有非数字字母的字符，然后对比逆序后的字符串和未逆序的字符串。
     * 回文的新思路，逆序后比较。
     * @param s
     * @return
     */
    public boolean isPalindromeOffical1(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
