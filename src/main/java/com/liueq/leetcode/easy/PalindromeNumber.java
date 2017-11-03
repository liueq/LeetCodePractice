package com.liueq.leetcode.easy;

/**
 * 描述，在不使用额外空间的前提下，判断 int 是否是回文。
 * 由于不能使用额外空间，因此不能将 int 转换成 string，然后用入栈，出栈的方式判断。
 *
 * 答案似乎只有一种方法，将数字分为两部分，然后对后半部分进行逆序，判断前后部分是否相等。
 * 具体做法是通过不断对 x 取余，然后将余数逆序后累加，最后判断 x 剩余部分和 x 余数部分的大小来区分是否到了中间。
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(-1));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(11));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(1101));
    }

    /**
     * 按照答案的思路，自己写的代码。
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        int aHalf = x, bHalf = 0;

        while (aHalf >= bHalf) {
            //由于这判断是在操作之前，所以最后会是 aHalf >= bHalf 的情况，所以是 aHalf / 10
            //这里和标准答案略有不同，但是思路是一样的
            if (aHalf == bHalf || aHalf / 10 == bHalf) {
                return true;
            }

            bHalf = bHalf * 10 + aHalf % 10;
            aHalf = aHalf / 10;
        }

        return false;
    }

    /**
     * 推荐答案
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x){
        //负数不可能是回文
        //除了0以外，以0结尾的数字都不可能是回文，因为没有以0开头的数字
        if(x < 0 || (x != 0 && x % 10 == 0)){
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber){
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        //此算法， 如果为奇数个数字，那么 revertedNumber 会多处一位，最后一位不影响是否是回文，因此有了后边部分的判断
        //如果 x 为偶数，那么 x 严格等于 revertedNumber
        return x == revertedNumber || x == revertedNumber / 10 ;
    }
}
