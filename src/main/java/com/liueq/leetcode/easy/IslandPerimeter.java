package com.liueq.leetcode.easy;

/**
 * 问题描述：把一个只包含0，1元素的二维数组中的1看作是小岛，0看作是海。小岛必定连接，中间不可能有湖。求小岛的边长。
 * <p>
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 这种形状的小岛边长为 : 16
 * <p>
 * 解：首先正确的计算出二维数组的长，宽。
 * 产生一个边长的条件是，当前元素是1，查看其上下左右相邻元素，如果是0，或者数组边界，那么认为产生了一个边。
 * 最终统计即可。
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        IslandPerimeter obj = new IslandPerimeter();
        int[][] test = {{0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}};
        obj.islandPerimeter(test);
    }

    public int islandPerimeter(int[][] grid) {
        int side = 0;
        int x = grid.length;
        int y = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                y++;
            }
        }
        y = y / x;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        System.out.println((i - 1) + ", " + j);
                        side++;
                    }

                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        System.out.println(i + ", " + (j - 1));
                        side++;
                    }

                    if (i + 1 >= x || grid[i + 1][j] == 0) {
                        System.out.println((i + 1) + ", " + j);
                        side++;
                    }

                    if (j + 1 >= y || grid[i][j + 1] == 0) {
                        System.out.println(i + ", " + (j + 1));
                        side++;
                    }
                }
            }
        }

        return side;
    }
}
