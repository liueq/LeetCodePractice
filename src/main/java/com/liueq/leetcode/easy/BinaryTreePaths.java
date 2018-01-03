package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：找出一个二叉树的从根节点到所有叶子节点的路径
 *
 * 解：
 * 递归遍历的过程中，维护一个 String，直到叶子节点，将其添加到 list。
 */
public class BinaryTreePaths {

    public static void main(String[] args) {

    }

    /**
     * 递归遍历，在没有左右节点的子节点添加到 list
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        recursive(root, list, "");
        return list;
    }

    public void recursive(TreeNode node, List<String> list, String s) {
        if (node == null) {
            return;
        }

        if (s != null && !"".equals(s)) {
            s = s + "->" + node.val;
        } else {
            s = "" + node.val;
        }

        recursive(node.left, list, s);
        recursive(node.right, list, s);

        if (node.left == null && node.right == null) {
            list.add(s);
        }
    }

    public void recursiveImprove(TreeNode node, String path, List<String> list) {
        if (node.left == null && node.right == null) {
            list.add(path + node);
        }

        if (node.left != null) {
            recursiveImprove(node.left, path + node.val + "->", list);
        }

        if (node.right != null) {
            recursiveImprove(node.right, path + node.val + "->", list);
        }
    }
}
