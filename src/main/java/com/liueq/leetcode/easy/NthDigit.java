package com.liueq.leetcode.easy;

/**
 * 问题描述：在从1到 Integer.MAX_VALUE 这样一个队列中，将每一位数字都视作是一个单位。求 n 位置的数字?
 *
 * 解：
 * 1 直接遍历，从1开始对遍历的数字进行计数，直到n位置，然后判断此时的n所在位置的数字是多少。
 * 2 划分区域
 *      1 ~ 9 每个位代表一个数字，起始位置是1
 *      10 ~ 99 每2个位代表一个数字，起始位置是10
 *      100 ~ 999 每3个位代表一个数字，起始位置是100
 *      ...
 *
 *      因此需要先找出所求的n所在数字落在哪个区域内，然后再通过取余的方式得到n具体在该数字的哪一位。
 *
 */
public class NthDigit {
    public static void main(String[] args) {
        NthDigit obj = new NthDigit();
        System.out.println(obj.findNthDigitSuccess(1));
    }

    /**
     * 单纯的遍历会导致超时
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int counter = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int temp = count(i);
            if (counter + temp >= n) {
                String num = String.valueOf(i);
                System.out.println("num is " + num);
                return Integer.valueOf(num.substring(n - counter - 1, n - counter));
            } else {
                counter += temp;
            }
        }
        return -1;
    }

    StringBuffer buffer = new StringBuffer();

    private int count(int i) {
        String str = String.valueOf(i);
        buffer.append(str);
        return str.length();
    }

    public int findNthDigitSuccess(int n) {
        int len = 1; // 步长，当前区域内，每一个数字的位数 1, 2, 3, 4 ...
        long count = 9; // 当前区域内，数字的数量 9, 90, 900, 9000 ...
        int start = 1; // 一个区域的开始位置, 1, 10, 100, 1000, 10000 ...
        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        int target = start + (n - 1) / len; //由于计数是从1开始，所以要 -1
        char ct = String.valueOf(target).charAt((n - 1) % len);
        return Character.getNumericValue(ct);
    }
}
