package com.liueq.leetcode.easy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：将 integer 转换成二进制后，反转，然后给出反转之后的二进制数字所对应无符号的十进制的值。
 *
 * 解：位操作，含义不明，暂时先这样吧。
 */
public class ReverseBits {
    public static void main(String[] args) {
        System.out.println(15 & 1);
    }

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {

            // n & 1 是指的取n的二进制第一位，和1&上之后的结果。
            result =  result + n & 1;

            // >>> 无符号右移
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) // CATCH: for last digit, don't shift!
                result <<= 1;
        }
        return result;
    }

    private List<Integer> toBits(int n) {
        List<Integer> buffer= new ArrayList<>();
        int count = 0;
        while (n != 0 || count != 32) {
            if (n != 0) {
                buffer.add(0, n % 2);
                n /= 2;
            } else {
                buffer.add(0, 0);
            }
            count++;
        }

        return buffer;
    }

    private void reverse(List<Integer> chars) {
        int start = 0, end = chars.size() - 1;
        while (start < end) {
            Integer swap = chars.get(start);
            chars.set(start, chars.get(end));
            chars.set(end, swap);
            start++;
            end--;
        }
    }

    private int toInteger(List<Integer> chars) {
        long sum = 0;
        for(int i = chars.size() - 1, j = 0; i >= 0; i--, j++) {
            sum += ((int) Math.pow(2, j)) * chars.get(i);
        }

        return Integer.parseUnsignedInt(String.valueOf(sum));
    }
}
