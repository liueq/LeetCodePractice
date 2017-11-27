package com.liueq.leetcode.easy;

/**
 * 问题描述：将数字转为 Excel column 那样的ABC表示。
 * <p>
 * 解：使用余数乘积法，但是为了避开0，需要先 n--，然后用 'A' + 余数
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        ExcelSheetColumnTitle obj = new ExcelSheetColumnTitle();
        for (int i = 0; i < 100; i++) {
            System.out.println(obj.convertToTitle(i));
        }
    }

    /**
     * 官方答案，
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            /**
             * 关键的区别在于先 n--，然后用 'A' + n%26 作为数字
             *
             * 如果是直接 64 + n%26，未 --的情况下，就无法避开0这个数字。
             * */
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

}
