package com.liueq.leetcode.easy;

/**
 * 问题描述：有这样一种游戏，给出n个石头，两人每次从石头中取出 1~3 个石头，判断作为先手者，必定赢得情况。
 * 假设两人取石头的策略都是最佳
 *
 * 解：只要是4的倍数，那么先手者必定数。
 */
public class NimGame {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
