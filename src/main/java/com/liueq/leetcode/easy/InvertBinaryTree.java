package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 问题描述：反转二叉树
 *
 * 解：
 * 1 循环交换左右子树 （凡是循环遍历二叉树，都需要一个 queue 辅助。）
 * 2 递归交换左右子树
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 循环遍历交换左右子树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return root;
    }

    /**
     * 递归交换左右子树
     *
     * @param root
     */
    public void invertTreeInner(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
    }

    /**
     * 递归更简洁的写法
     * @param root
     * @return
     */
    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) {
            return root;
        }

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        return root;
    }


}
