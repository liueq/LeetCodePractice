package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：以1作为开始，然后用"读"的方式来获得 n+1 的字符串。1可以被读做 1个1，即得到 11；11可以被独坐 2个1，即得到 21;21可以被读作1个2，1个1，得到 1211。

 * 解： 思想是找到数组中，相邻两个字符不等的位置，然后计算两个不等位置之间的距离，从而得到 size，根据该不等位置的值，得到 value，最后再拼接起来。
 *
 * 我的方法是先遍历一次数组，找出所有的不等位置，然后计算出各个部分的 size 和value，然后拼接。
 *
 * 也可以一边遍历，一边对 size 进行累加，然后到不等的位置后，拼接一个 size,value，然后继续。
 *
 */
public class CountAndSay {

    public static void main(String[] args) {
        CountAndSay cas = new CountAndSay();
//        System.out.println(cas.countAndSay(1));
//        System.out.println(cas.countAndSay(2));
//        System.out.println(cas.countAndSay(3));
        System.out.println(cas.countAndSay(4));
//        System.out.println(cas.countAndSay(5));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String str = "1";
        for (int i = 0; i < n - 1; i++) {
            str = say(str);
        }

        return str;
    }

    private String say(String s) {
        int length = s.length();
        char[] array = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < length - 1; i++) {
            if (array[i] != array[i + 1]) {
                list.add(i + 1);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) {
            int size = list.get(i + 1) - list.get(i);
            Character value = array[list.get(i)];
            sb.append(size)
                    .append(Character.getNumericValue(value));
        }

        sb.append(array.length - list.get(list.size() - 1));
        sb.append(array[list.get(list.size() - 1)]);

        return sb.toString();
    }
}
