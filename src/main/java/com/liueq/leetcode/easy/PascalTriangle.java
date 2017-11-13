package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：构造一个n层帕斯卡三角
 * [1]
 * [1, 1]
 * [1, 2, 1]
 * [1, 3, 3, 1]
 * [1, 4, 6, 4, 1]
 * <p>
 * 解：查找规律，除了0和n-1每一行都为1外, cur[n] = last[n-1] + last[n]
 */
public class PascalTriangle {
    public static void main(String[] args) {
        PascalTriangle obj = new PascalTriangle();
        List<List<Integer>> data = obj.generate(5);
        for (int i = 0; i < data.size(); i++) {
            List<Integer> inn = data.get(i);
            for (int j = 0; j < inn.size(); j++) {
                System.out.print(inn.get(j));
            }
            System.out.println("");
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
        List<Integer> lastInner = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> inner = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int sum = 0;
                if (j == 0 || j == i - 1) {
                    sum = 1;
                } else {
                    if (lastInner.size() > j - 1) {
                        sum += lastInner.get(j - 1);
                    }
                    if (lastInner.size() > j) {
                        sum += lastInner.get(j);
                    }
                }
                inner.add(sum);
            }
            pascal.add(inner);
            lastInner = inner;
        }
        return pascal;
    }
}
