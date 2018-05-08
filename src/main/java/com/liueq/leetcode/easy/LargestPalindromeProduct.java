package com.liueq.leetcode.easy;

/**
 * 问题描述： 给出两个 n 位数( n = [1, 8]) ，求出其乘积中，最大的回文数字。由于数字很大，使用 num % 1337 作为结果返回。
 * <p>
 * 解：如果直接逆向遍历因子，实现乘法，并不能得到最优解。
 * 那么应该考虑逆向遍历乘积，找出所有的 palindrome，然后分解因子，如果两个因子都介于 [min, max] 之间，那么就找到了最大的 palindrome。
 * 难点：逆向遍历 palindrome，只需要对 maxNum * maxNum 的前半部镜像，就可以得到一个 palindrome。然后依次减少前半部分，就能够得到下一个 palindrome。
 * 遍历因子，只有从高到低，如果得到的因子2不在范围内，那么直接跳出循环，这样可以节省一些时间。
 */
public class LargestPalindromeProduct {
    public static void main(String[] args) {
        LargestPalindromeProduct obj = new LargestPalindromeProduct();
        int num = 5;
        int result = obj.largestPalindromeOfficial(num);
        System.out.println("In " + num + " the most biggest palindrome is " + result);
    }

    /**
     * 尝试通过逆向遍历直接计算，因为乘法是可以分解的，但是这样并不能找到最优解
     */
    public int largestPalindrome(int n) {
        long x = (long) Math.pow(10, n) - 1;
        for (; x >= Math.pow(10, n - 1); x--) {
            for (long y = (long) Math.pow(10, n) - 1; y >= Math.pow(10, n - 1); y--) {
                char[] splitY = String.valueOf(y).toCharArray();
                long sum = 0;
                for (int i = splitY.length - 1, j = 0; i >= 0; i--, j++) {
//                    System.out.print(splitY[i] +  ", ");
                    long num = Character.getNumericValue(splitY[i]);
                    sum += x * num * Math.pow(10, j);
//                    System.out.println("sum = " + sum);
                }
                System.out.println();
                System.out.println(String.format("%d * %d = %d", x, y, sum));
                String palindrome = String.valueOf(sum);
                boolean isPalindrome = true;
                for (int i = 0, j = palindrome.length() - 1; i < j; i++, j--) {
                    if (palindrome.charAt(i) != palindrome.charAt(j)) {
                        isPalindrome = false;
                    }
                }

                if (isPalindrome) {
                    return (int) (sum % 1337);
                }
            }
        }

        return -1;
    }

    public int largestPalindromeOfficial(int n) {
        if (n == 1) return 9;
        long maxNum = (int) Math.pow(10, n) - 1;
        long minNum = (int) Math.pow(10, n - 1);
        long maxProduct = maxNum * maxNum; // 计算出最大的乘积
        int halfProduct = (int) (maxProduct / Math.pow(10, n)); // 取前一半，然后构建出 palindrome
        for (; halfProduct >= minNum; halfProduct--) {
            long palindrome = createPalindrome(halfProduct);
            if (palindrome > maxProduct) {
                continue;
            }
            for (long i = maxNum; i >= minNum; i--) { //遍历，如果构建出的 palindrome 可以找到 [minNum, maxNum] 的因子，那么即找到
                long anotherFactor = palindrome / i;
                if (anotherFactor > maxNum) {
                    break;
                }
                if (palindrome % anotherFactor == 0) {
                    return (int) (palindrome % 1337);
                }
            }
        }

        return -1;
    }

    private long createPalindrome(long halfProduct) {
        String num = halfProduct + new StringBuilder().append(halfProduct).reverse().toString();
        return Long.valueOf(num);
    }

    public int largestPalindromeOfficial2(int n) {
        if (n == 1) return 9;
        long maxNum = (long) Math.pow(10, n) - 1;
        long minNum = (long) Math.pow(10, n - 1);
        long maxProduct = maxNum * maxNum;
        long firstHalf = maxProduct / (long) Math.pow(10, n);

        while (true) {
            long candidate = createPalindrome((int) firstHalf--);
            if (candidate > maxProduct) continue;
            // elinminate candidate like 9889,998899. Which generated from original firstHalf but larger than maxProduct
            for (long i = maxNum; i >= minNum; i--) {
                if (candidate / i > maxNum) break;
                if (candidate % i == 0) return (int) (candidate % 1337);
            }
        }
    }
}
