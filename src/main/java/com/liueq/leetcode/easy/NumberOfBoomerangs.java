package com.liueq.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：给定 n 个点，找出 i 到 j， k 两个点距离相等的所有排列组合。
 * 比如： a 到 b, c, d 三个点的距离相同，那么路径有 ab_ac, ac_ab, ab_ad, ad_ab, ac_ad, ad_ac
 *
 * 解：遍历一个点到其余所有点的距离，用距离作为key，路径数作为value 保存在map 中。
 * 遍历完成后，能得到所有相同距离的点的路径，按照排列组合公式，一个点到 n 个点的距离不同的组合有 n * (n-1)
 * 计算出所有组合。
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {

    }

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();

        // 遍历所有的点，这里points 的二维数组是 m * 2 的形式，因为坐标是 x，y表示
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                // i == j 意味着 points[i] == points[j] ，即是同一个点
                // 所以不做任何处理
                if (i == j) {
                    continue;
                }

                // 计算两个点的距离，不需要开根号，因为并不是需要真正的距离，只要有差别即可
                int d = getDistance(points[i], points[j]);
                // 将距离 i 相同距离的点的数量累加保存到 map 中
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            //遍历所有的距离
            for (int val : map.values()) {
                // 有 val 个点是处在同一距离，并不需要知道距离多少
                // 一个点到 n 个点的j，k两个路径，所有可能性的排列组合公式为 !n / !(n - 2) = n * (n - 1)
                res += val * (val - 1);
            }
            // 完成一次遍历，清空map
            map.clear();
        }

        return res;
    }

    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}
