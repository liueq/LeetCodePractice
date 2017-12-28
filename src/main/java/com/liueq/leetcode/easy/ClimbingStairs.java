package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

/**
 * 问题描述：使用跨度1，2，到达长度 n 的所有可能性。
 *
 * 不应该构建完全二叉树，太耗时。
 * 能否在构建二叉树的时候就进行计算。
 * 即使在构建二叉树就进行计算，即使只用一颗二叉树，仍然速度达不到要求。
 *
 * 此问题的最好解法是 dp。
 *
 * 解：
 * 1 答案中第一种方法就是使用二叉树强行遍历，但是时间复杂度无法达到要求，2(n).
 * 2 第二个答案说可以将n次的查询保存下来，然后能够快速得到 n+1 的结果。但是个人觉得，如果直接就查 n，仍然速度很慢。
 * 2.5 派生出了一个类似 dp ，但是仍然是递归的算法，时间比1快，但是远慢于3
 * 3 DP， dp(i) = dp(i - 1) + dp(i - 2)。应该总结的是 dp 永远是从数组头开始，这样才能利用到前边的 dp 数组值。
 * 4 标准答案还可以用斐波那契解决，但是太复杂了，不看。
 */
public class ClimbingStairs {

    private int mHeight;
    private int ways = 0;

    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();
        int testCase = 50;
        Thread t1 = new Thread(() -> {
            System.out.println("[Thread1]" + obj.climbingStairs(testCase));
        });
        Thread t2 = new Thread(() -> {
            System.out.println("[Thread2]" + obj.climbingStairsFakeDp(testCase));
        });
        Thread t3 = new Thread(() -> {
            System.out.println("[Thread3]" + obj.climbingStairsDp(testCase));
        });

//        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 构建二叉树遍历，时间复杂度无法达到。
     * <p>
     * 左节点表示1，右节点表示2，创建二叉树的时候就记录当前路径的值，达到 n 时，way++，最后统计。
     *
     * @param n
     * @return
     */
    public String climbingStairs(int n) {
        long start = System.currentTimeMillis();
        if (n == 0) {
            return "0";
        } else if (n == 1) {
            return "1";
        }

        mHeight = n;
        BinaryTree tree1 = new BinaryTree(n, 0);//层数小于等于 n
        int ways = tree1.getWays();
        long end = System.currentTimeMillis();
        return String.format("[%d]%d", end - start, ways);
    }

    /**
     * 实际上并不算是 dp ？？因为是从n开始递归，所以并没有用到前边的 dp 数组
     * 实际上仍然是递归
     * @param n
     * @return
     */
    public String climbingStairsFakeDp(int n) {
        int[] dp = new int[n + 1];
        long start = System.currentTimeMillis();
        int ways = climb(dp, n);
        long end = System.currentTimeMillis();
        return String.format("[%d]%d", end - start, ways);
    }

    private int climb(int[] dp, int i) {
        if (i == 0) {
            dp[i] = 0;
            return dp[i];
        } else if (i == 1) {
            dp[i] = 1;
            return dp[i];
        } else if (i == 2) {
            dp[i] = 2;
            return dp[i];
        }

        dp[i] = climb(dp, i - 1) + climb(dp, i - 2);

        return dp[i];
    }

    /**
     * 真正的 dp，使用到了前边的dp数组，时间复杂度是线性。
     * @param n
     * @return
     */
    public String climbingStairsDp(int n){
        long start = System.currentTimeMillis();
        if(n == 0 || n == 1 || n == 2){
            return String.valueOf(n);
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        int i = 3;
        while(i <= n){
            dp[i] = dp[i - 1] + dp[i - 2];
            i++;
        }

        int ways = dp[n];
        long end = System.currentTimeMillis();
        return String.format("[%d]%d", end - start, ways);
    }

    public static class BinaryTree {

        private int mWeight;
        private int ways;

        private TreeNode mRootNode;


        public BinaryTree(int weight, int rootValue) {
            mWeight = weight;

            mRootNode = new TreeNode(rootValue);//以1 作为根节点的情况
            buildTree(mRootNode, rootValue);
        }

        private void buildTree(TreeNode parent, int weight) {
            if (weight == mWeight) {
                ways++;
                return;
            } else if (weight > mWeight) {
                return;
            }

            TreeNode left = new TreeNode(1);
            TreeNode right = new TreeNode(2);
            parent.left = left;
            parent.right = right;

            buildTree(left, weight + 1);
            buildTree(right, weight + 2);
        }

        public TreeNode getRoot() {
            return mRootNode;
        }

        public int getWays() {
            return ways;
        }
    }

}