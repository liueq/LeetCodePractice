package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：计算二叉树的最低高度
 * <p>
 * 解：递归或者层级遍历都可以解决
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        MinimumDepthOfBinaryTree obj = new MinimumDepthOfBinaryTree();
        TreeNode tree = obj.createTree(new int[]{1, 2});
//        obj.printTree(tree);
        System.out.println(obj.minDepthOfficial(tree));
    }

    private void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        printTree(node.left);
        printTree(node.right);
    }

    private TreeNode createTree(int[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();
        TreeNode root = new TreeNode(data[0]);
        children.add(root);
        int i = 0;
        while (i < data.length) {
            parents.clear();
            parents.addAll(children);
            children.clear();

            for (TreeNode node : parents) {
                if (2 * i + 1 < data.length) {
                    node.left = new TreeNode(data[2 * i + 1]);
                    children.add(node.left);
                }

                if (2 * i + 2 < data.length) {
                    node.right = new TreeNode(data[2 * i + 2]);
                    children.add(node.right);
                }

                i++;
            }
        }

        return root;
    }

    /**
     * 递归方式，通过比较左右子树，取更小的高度作为当前高度。
     *
     * @param root
     * @return
     */
    public int minDepthOfficial(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepthOfficial(root.left);
        int right = minDepthOfficial(root.right);

        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 迭代方式，层级遍历，如果每一个parent 都有 child，说明这一层不是最低高度。直到有一个 parent 没有 child，那么这一层就是最低高度。
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();
        children.add(root);
        int depth = 0;

        while (!children.isEmpty()) {
            depth++;
            parents.clear();
            parents.addAll(children);
            children.clear();
            boolean hasChild = true;
            for (TreeNode node : parents) {
                if (node.left == null && node.right == null) {
                    hasChild = false;
                }

                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
            }
            if (!hasChild) {
                return depth;
            }
        }

        return depth;
    }


}
