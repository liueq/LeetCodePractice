package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 问题描述：查找一个BST 中两个节点最近的一个共同祖先
 *
 * 解：从root 开始遍历，比较  current, p, q 的大小，当 current 介于 p, q 之间时，说明 pq 开始分叉，此时的current 就是最近的共同祖先。
 */
public class LowestCommonAncestorOfBST {
    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.val < Math.min(p.val, q.val)) {
                queue.add(current.right);
            } else if (current.val > Math.max(p.val, q.val)) {
                queue.add(current.left);
            } else {
                return current;
            }
        }

        return root;
    }
}
