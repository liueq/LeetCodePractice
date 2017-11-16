package com.liueq.leetcode.easy;

/**
 * 问题描述：模拟股票买卖，数组表示价格，可以多次买卖，但是同时只能够执行一次。求多次买卖后，最大的利润。
 * <p>
 * 解：两种思路
 * 1 自己计算每次从最低到最高的差值，然后累加，需要注意的是维护min, max 稍稍麻烦。
 * 2 宏观上看，计算 peak - valley 的差值，然后累加
 */
public class BestTimeToBuyAndSellStock2 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock2 obj = new BestTimeToBuyAndSellStock2();
        int[] stocks = {3, 1, 7, 10, 5, 6};
        int profit = obj.maxProfit(stocks);
        System.out.println(profit);
    }

    /**
     * 自己根据 BestTimeToBuyAndSellStock 的思路，同时记录下当前的 minPrice, maxPrice，
     * 计算每一次从最低到最高之前的差值，就是一次 profit，然后累加。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (maxPrice < prices[i]) {
                maxPrice = prices[i];
            } else {
                if (maxPrice - minPrice > 0) {
                    profit += maxPrice - minPrice;
                    minPrice = prices[i];
                    maxPrice = prices[i];
                }
            }

            if (minPrice > prices[i]) {
                minPrice = prices[i];
                maxPrice = prices[i]; // 每次对 min 赋值，都要重置 max
            }
        }

        if (maxPrice - minPrice > 0) {
            profit += maxPrice - minPrice;
        }

        return profit;
    }

    /**
     * 标准答案，统计 valley 和 peak 的个数，然后用 peak - valley 就是一次买卖所产生的profit。
     * valley 的定义是不满足 prices[i] < prices[i + 1]时，说明 i 就是最低点
     * peak 的定义是不满足 prices[i] > prices[i + 1] 时，说明 i 就是最高点
     *
     * @param prices
     * @return
     */
    public int maxProfitOfficial(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int profit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            profit += peak - valley;
        }

        return profit;
    }
}
