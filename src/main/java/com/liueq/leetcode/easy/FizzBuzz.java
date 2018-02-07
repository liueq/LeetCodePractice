package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：从1到n，遇到3的倍数输出Fizz，遇到5的倍数输出Buzz，同时是3，5倍数时输出FizzBuzz。求该String list。
 *
 * 解：简单逻辑判断即可，将 FizzBuzz 放在第一个判断条件。
 */
public class FizzBuzz {
    public static void main(String[] args){

    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }

        return list;
    }
}
