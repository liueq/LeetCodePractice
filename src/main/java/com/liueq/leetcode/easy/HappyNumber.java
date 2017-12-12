package com.liueq.leetcode.easy;

import java.util.HashSet;

/**
 * 问题描述：判断一个数字是否是 Happy Number. 一个 Happy Number 是将自己每一位伤的数字平方后累加起来的作为一次运算。
 * 当这个和等于1时，该数字是Happy Number，如果无限循环了，就不是。
 *
 * 解：
 * 1 用 HashSet 记录出现过的和，重复时说明无限循环，那就不是 Happy Number.
 * 2 用 Floyd 判圈算法
 * 3 找出规律，[2, 6] 因子的数字不是。
 */
public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber obj = new HappyNumber();
        System.out.println(obj.isHappyFact(19));
    }

    HashSet<Integer> coll = new HashSet<>();

    /**
     * 使用 HashSet 记录出现过的数字，可以算是一种方法，目前递归的写法在 LeetCode 上通不过，如果用循环是可以的。
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }

        System.out.println("sum = " + sum);
        if (sum == 1) {
            return true;
        } else if(coll.contains(sum)){
            return false;
        }

        coll.add(sum);
        return isHappy(sum);
    }

    /**
     * Floyd 判圈算法： 和之前判断链表上是否存在环同理，两个速度不同的指针开始赛跑，当两个指针相遇的时候，就找到了环存在的点。
     *
     * 此方法用在这个地方，和 HashSet 一样，都能够找到重复的那个值，至此判断出不是 HappyNumber。
     * @param n
     * @return
     */
    public boolean isHappyFloydCycle(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast); //fast 比 slow 快1步。
            fast = digitSquareSum(fast);
        } while (slow != fast);

        if (slow == 1) {
            return true;
        } else {
            return false;
        }
    }

    private int digitSquareSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }

        return sum;
    }

    /**
     * 如果出现了 [2, 6] 的因子，那么不是 Happy Number。但是这个规律本身很难发现。
     * @param n
     * @return
     */
    public boolean isHappyFact(int n) {
        while (n > 6) {
            int next = 0;
            while (n != 0) {
                next += (n % 10) * (n % 10);
                n /= 10;
            }
            n = next;
        }

        return n == 1;
    }
}
