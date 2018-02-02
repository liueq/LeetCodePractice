package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

/**
 * 问题描述：计算一个二叉树中，所有左叶子节点之和
 *
 * 解：遍历二叉树，遇到左叶子节点累加。左叶子节点的判定条件是 node.left != null && node.left.left == null && node.left.right == null
 */
public class SumOfLeftLeaves {
    public static void main(String[] args){

    }


    public int sumOfLeftLeaves(TreeNode root) {
        recursive(root);
        return mSum;
    }

    public void recursive(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null && node.left.left == null && node.left.right == null) {
            mSum += node.left.val;
        }


        recursive(node.left);
        recursive(node.right);
    }


    private int mSum = 0;
}

