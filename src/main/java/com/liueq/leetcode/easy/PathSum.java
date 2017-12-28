package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

/**
 * 问题描述：给定一个数字，查看一个二叉树是否存在从 root 到某一个叶子节点，所经过节点值之和是否等于这个数字的路径。
 *
 * 解：递归过程中累加所经过的路径，直到等于 sum。（从sum递减到0更简洁）
 */
public class PathSum {
    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        recursiveTree(root, 0, sum);
        return false;
    }

    /**
     * 递归，最简洁的写法，使用 sum 递减的方式。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumOfficial(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }

        return hasPathSumOfficial(root.left, sum - root.val)
                || hasPathSumOfficial(root.right, sum - root.val);
    }

    private boolean mHasSum = false;

    /**
     * 自己想的，比较容易理解，但是并不简洁。
     *
     * @param node
     * @param sum
     * @param target
     */
    private void recursiveTree(TreeNode node, int sum, int target) {
        if (node == null) {
            return;
        }

        sum += node.val;
//      不能够判断 sum > target，因为 sum 可能是负数
//        if (sum > target) {
//            return;
//        } else
        if (sum == target && node.left == null && node.right == null) {
            mHasSum = true;
            return;
        }

        recursiveTree(node.left, sum, target);
        recursiveTree(node.right, sum, target);
    }

}
