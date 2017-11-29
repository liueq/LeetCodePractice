package com.liueq.leetcode.easy;

/**
 * 问题描述：给出 Excel column 字母，返回对应的数字
 * <p>
 * 解：关键是使用 char - 'A' + 1 的计算方式来获得每一位的值，然后就是进制的计算了。
 */
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        ExcelSheetColumnNumber obj = new ExcelSheetColumnNumber();
        String title = "AZ";
        System.out.println(obj.titleToNumber(title));
    }

    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = chars.length - 1, j = 0; i >= 0; i--, j++) {
            char c = chars[i];
            int val = (int) Math.pow(26, j) * (c - 'A' + 1);
            sum += val;
        }
        return sum;
    }
}
