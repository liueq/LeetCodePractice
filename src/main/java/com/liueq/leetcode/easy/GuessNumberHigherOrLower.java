package com.liueq.leetcode.easy;

/**
 * 问题描述：从1~n 的数字中，找出数字m
 *
 * 解：
 * 1 二分查找
 * 2 三分查找（实际上最坏情况的比较次数多于二分查找）
 */
public class GuessNumberHigherOrLower {
    public static void main(String[] args) {
        GuessNumberHigherOrLower obj = new GuessNumberHigherOrLower();
        obj.setGuess(1702766719);
        int number = obj.guessNumber(2126753390);
        System.out.println(number);
    }

    public int guessNumber(int n) {
        long low = 1, high = n;
        while (low <= high) {
            long mid = (low + high) / 2;
            //long mid = low + (high - low) / 2; //如果是这样的写法，就不会超出 int
            int gussed = guess((int) mid);
            if (gussed == 0) {
                return (int) mid;
            } else if (gussed > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return 0;
    }

    public int guessNumberTernary(int n) {
        int low = 1, high = n;
        while (low <= high) {
            int mid1 = (low + (high - low) / 3);
            int mid2 = (high - (high - low) / 3);

            if(guess(mid1) < 0){
                high = mid1 - 1;
            } else if (guess(mid1) == 0) {
                return mid1;
            } else if (guess(mid1) > 0 && guess(mid2) < 0) {
                low = mid1 + 1;
                high = mid2 - 1;
            } else if (guess(mid2) == 0) {
                return mid2;
            } else {
                low = mid2 + 1;
            }
        }

        return 0;
    }

    int mGuess;

    void setGuess(int n) {
        mGuess = n;
    }

    int guess(int n) {
        if (n == mGuess) {
            return 0;
        } else if (n > mGuess) {
            return 1;
        } else {
            return -1;
        }
    }
}
