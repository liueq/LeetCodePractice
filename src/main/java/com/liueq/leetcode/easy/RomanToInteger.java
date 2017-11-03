package com.liueq.leetcode.easy;

public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(romanToInt("I"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("VI"));
        System.out.println(romanToInt("VIII"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("X"));
        System.out.println(romanToInt("XI"));
        System.out.println(romanToInt("IC"));
        System.out.println(romanToInt("CXI"));
    }

    /**
     * 自己的解决方法(主流的思路也是遍历，然后比较相邻两个的大小来判断是否该减去小的数字)
     * 先将 String 转为 char 数组，然后遍历：
     * 1 如果是最后一个字符，那么肯定是一个单独的数字
     * 2 判断当前字符是否小于下一个字符，如果是，那么可以组合成 b - a 的数字
     * 3 2不满足的时候，认为当前数字是一个单独数字
     * <p>
     * 累加 1，2，3 的结果即可。此方法较为通用，但是这里罗马数字的规则并不是最严格，本来减应该不能用于夸位的情况。例如 IC 是不合法的
     * 因为 I 是1， C 是100，中间垮了1位。
     *
     * @param s
     * @return
     */
    private static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; ) {
            if (i == chars.length - 1) {
                sum += toInt(chars[i]);
                i++;
            } else if (littleThan(chars[i], chars[i + 1])) {
                //当前字母比后一个字母小时，认为可以减
                //转为 int
                sum += toInt(chars[i], chars[i + 1]);
                i += 2;
            } else {
                //当前字母不小于后一个字母时，认为是非组合
                sum += toInt(chars[i]);
                i++;
            }
        }
        return sum;
    }

    private static int toInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }

        return 0;
    }

    private static int toInt(char c1, char c2) {
        return toInt(c2) - toInt(c1);
    }

    private static boolean littleThan(char a, char b) {
        if (a == 'I') {
            if (b == 'I') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'V') {
            if (b == 'I' || b == 'V') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'X') {
            if (b == 'I' || b == 'V' || b == 'X') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'L') {
            if (b == 'I' || b == 'V' || b == 'X' || b == 'L') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'C') {
            if (b == 'I' || b == 'V' || b == 'X' || b == 'L' || b == 'C') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'D') {
            if (b == 'I' || b == 'V' || b == 'X' || b == 'L' || b == 'C' || b == 'D') {
                return false;
            } else {
                return true;
            }
        } else if (a == 'M') {
            return false;
        }

        return false;
    }

    /**
     * 网上的一个答案，直接先枚举出所有"减"的组合，看字符串中是否有，然后再枚举出所有"加"的组合，最后累加。
     * 此方法仅适用于已知数字范围的情况下，因为此题目本来数字范围限制在了 3999以内，所以可以用该方法。
     * @param s
     */
    private static int romanToInt1(String s) {
        int sum = 0;
        if (s.indexOf("IV") != -1) {
            sum -= 2;
        }
        if (s.indexOf("IX") != -1) {
            sum -= 2;
        }
        if (s.indexOf("XL") != -1) {
            sum -= 20;
        }
        if (s.indexOf("XC") != -1) {
            sum -= 20;
        }
        if (s.indexOf("CD") != -1) {
            sum -= 200;
        }
        if (s.indexOf("CM") != -1) {
            sum -= 200;
        }

        char c[] = s.toCharArray();
        int count = 0;

        for (; count <= s.length() - 1; count++) {
            if (c[count] == 'M') sum += 1000;
            if (c[count] == 'D') sum += 500;
            if (c[count] == 'C') sum += 100;
            if (c[count] == 'L') sum += 50;
            if (c[count] == 'X') sum += 10;
            if (c[count] == 'V') sum += 5;
            if (c[count] == 'I') sum += 1;

        }

        return sum;
    }
}
