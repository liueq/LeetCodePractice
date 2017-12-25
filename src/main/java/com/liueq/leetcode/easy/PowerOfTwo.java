package com.liueq.leetcode.easy;

/**
 * 问题描述：判断n是否是2的次方
 *
 * 解：
 * 1 循环判断余数和商
 * 2 使用 bit 判断，当n为2的次方时，二进制表示只有一个1
 *
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        PowerOfTwo obj = new PowerOfTwo();
//        for(int i = 1; i < 100; i++) {
//            if (obj.isPowerOfTwo(i)) {
//                System.out.println(i);
//            }
//        }
        System.out.println(obj.isPowerOfTwo(-16));
    }

    /**
     * 计算 n 被 2 整除，直到最后一个余数等于1 所循环的次数，然后计算2的该次方能否得到原本的数字。
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int pow = 0;
        final int init = n;
        while (n != 1){
            n = n / 2;
            pow++;
        }

        if (Math.pow(2, pow) == init) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 如果是2 的power，那么二进制表示只有一个 bit 是1，其他全是0
     * @param n
     * @return
     */
    public boolean isPowerOfTwoTrick(int n) {
        if (n <= 0) {
            return false;
        }

        return (n & (n - 1)) != 0;
    }

    /**
     * 将 n 整除2，直到取余不为0，当此时n 为1时，认为 n 是2的次方。
     * @param n
     * @return
     */
    public boolean isPowerOfTwoLoop(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n = n / 2;
        }

        return n == 1;
    }
}
