package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：计算一个二叉树的最大深度（一个节点的深度为1）
 *
 * 解：两种方法，迭代或者递归
 *
 * 1 迭代的情况下，按层级遍历整个二叉树，直到下一层没有子节点，此时就是最大深度。
 * 2 递归的情况下，比较左右子树的最大深度，然后加上自身的1深度，作为最大深度。
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7};
        MaximumDepthOfBinaryTree obj = new MaximumDepthOfBinaryTree();
        TreeNode root = obj.buildTree(data);
//        obj.firstPrint(root);
        System.out.println(obj.maxDepthOfficial(root));
    }

    /**
     * 自己想到了，通过层级遍历，直到下一层没有子节点为止，就是最大深度。
     *
     * @param node
     * @return
     */
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return layerIterate(node);
        }
    }

    /**
     * 终极简洁的递归方法，直接通过取子树的最深深度化解问题。
     *
     * @param node
     * @return
     */
    public int maxDepthOfficial(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthOfficial(node.left), maxDepthOfficial(node.right));
    }

    private void firstPrint(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        firstPrint(node.left);
        firstPrint(node.right);
    }

    private int layerIterate(TreeNode root) {
        List<TreeNode> parent = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();

        int depth = 0;
        children.add(root);
        while (!children.isEmpty()) {
            parent.clear();
            parent.addAll(children);
            children.clear();

            boolean isAddChild = false;
            for (TreeNode node : parent) {
                if (node.left != null) {
                    children.add(node.left);
                    isAddChild = true;
                }
                if (node.right != null) {
                    children.add(node.right);
                    isAddChild = true;
                }
            }

            if (isAddChild) {
                depth++;
            } else {
                return ++depth; //只有一个节点的时候深度是1
            }
        }

        return 0;
    }

    private TreeNode buildTree(int[] data) {
        int length = data.length;
        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();

        TreeNode root = new TreeNode(data[0]);
        children.add(root);
        for (int i = 0; i < data.length; ) {
            parents.clear();
            parents.addAll(children);
            children.clear();

            for (TreeNode node : parents) {
                if (2 * i + 1 < data.length) {
                    node.left = new TreeNode(data[2 * i + 1]);
                    children.add(node.left);
                } else {
                    return root;
                }
                if (2 * i + 2 < data.length) {
                    node.right = new TreeNode(data[2 * i + 2]);
                    children.add(node.right);
                } else {
                    return root;
                }
                i++;
            }
        }

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
