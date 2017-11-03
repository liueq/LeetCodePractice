package com.liueq.leetcode.easy;

/**
 * 描述：反转一个32位有符号整数，如果越界，返回0。
 * 解答：
 * 1. 最傻的方式，将整数转为字符串后，再转 char 数组，进行反转。
 * 2. 利用余数，每次取余后乘以10再加上余数，最巧妙和直观。
 * 3. 利用余数，将余数立即拼接在当前数字后边，用0隔开，看起来很巧妙，但是速度并不快，而且很难想到。
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse2(153423646));
    }

    public static int reverse(long x) {
        String input = String.valueOf(x);
        int inputLength = input.length();
        if (input.startsWith("-")) {
            if (inputLength > 11) {
                return 0;
            } else {
                String num = input.substring(1);
                try {
                    int result = -(Integer.valueOf(reverseInner(num)));
                    return result;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        } else {
            if (inputLength > 10) {
                return 0;
            } else {
                try {
                    int result = Integer.valueOf(reverseInner(input));
                    return result;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
    }

    private static String reverseInner(String num) {
        char[] chars = num.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[length - i - 1];
            chars[length - i - 1] = temp;
        }

        return String.valueOf(chars);
    }


    /**
     * 利用取余的方式，并且巧妙的将余数拼接在整个数字之后，例如：
     * 1234
     * 12304
     * 12043
     * 10432
     * 04321
     * 4321
     *
     * 但是这个方法实在是太不直观了，重现都很难
     * @param x
     * @return
     */
    private static int reverse1(int x) {
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    /**
     * 同样是取余，更优雅和直观
     * @param x
     * @return
     */
    private static int reverse2(int x){
        long rev = 0;
        while(x != 0){
            rev = rev * 10 + x % 10;
            x = x / 10;
            if(rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int) rev;
    }
}
