package com.liueq.leetcode.easy;

/**
 * 问题描述：给出一个数组，找出两个元素最大的差值，其中较大元素需要在较小元素之后。
 * <p>
 * 解：标准答案是遍历的过程中，维护 minPrice 和 maxProfit 两个变量，非常巧妙。
 * 同时，也可以将其转换成 dp 问题，只是空间复杂度更高。
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock obj = new BestTimeToBuyAndSellStock();
        int[] stocks = {7, 1, 5, 3, 6, 4};
        int profit = obj.maxProfitOfficial(stocks);
        System.out.println(profit);
    }

    /**
     * 将问题转化为 dp 问题
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int[] diff = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            diff[i] = prices[i + 1] - prices[i];
        }

        //dp
        int[] dp = new int[diff.length];
        dp[0] = diff[0];
        int max = dp[0];
        for (int i = 1; i < diff.length; i++) {
            dp[i] = dp[i - 1] + diff[i] > diff[i] ? dp[i - 1] + diff[i] : diff[i];
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max > 0 ? max : 0;
    }

    /**
     * 时间复杂度和空间复杂度最小的算法。
     * 同时记录下 minPrice 和 maxProfit，遍历数组的过程中，一旦发现当前 price 比 minPrice小，就替换。
     * 然后总是用 minPrice 来和当前的 price 相减得到 profit，保留 maxProfit.
     *
     * @param prices
     * @return
     */
    public int maxProfitOfficial(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
