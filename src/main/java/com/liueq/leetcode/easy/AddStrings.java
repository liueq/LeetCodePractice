package com.liueq.leetcode.easy;

/**
 * 问题描述：实现两个字符串形式的数字相加
 *
 * 解：手动对每一位进行加法，并且注意进位。
 */
public class AddStrings {
    public static void main(String[] args){
        AddStrings obj = new AddStrings();
        String num1 = "0";
        String num2 = "0";
        System.out.println(obj.addStrings(num1, num2));
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuffer buffer = new StringBuffer();
        boolean carry = false;
        while (i >= 0 && j >= 0) {
            int x = Character.getNumericValue(num1.charAt(i)) + Character.getNumericValue(num2.charAt(j));
            if (carry) {
                x++;
            }
            if (x > 9) {
                x = x % 10;
                carry = true;
            }else {
                carry = false;
            }

            buffer.insert(0, x);
            i--;
            j--;
        }

        while (i >= 0) {
            int x = Character.getNumericValue(num1.charAt(i));
            if (carry) {
                x++;
            }
            if (x > 9) {
                x = x % 10;
                carry = true;
            } else {
                carry = false;
            }

            buffer.insert(0, x);
            i--;
        }

        while (j >= 0) {
            int x = Character.getNumericValue(num2.charAt(j));
            if (carry) {
                x++;
            }
            if (x > 9) {
                x = x % 10;
                carry = true;
            } else {
                carry = false;
            }

            buffer.insert(0, x);
            j--;
        }

        if (carry) {
            buffer.insert(0, "1");
        }

        return buffer.toString();
    }
}
