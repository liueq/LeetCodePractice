package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 问题描述：层级遍历二叉树，并且倒序返回每一层的数据。
 * <p>
 * 解：首先应该要层级遍历二叉树，然后再考虑每一层是逆向，层中则没有逆序。
 * <p>
 * 1 深度优先，递归，记录下 level 信息，用 list.size - level 。
 * 2 广度优先，从 root 开始遍历每一层的节点，然后获取下一层的子节点，循环直到下一层没有子节点。然后逆序。
 */
public class BinaryTreeLevelOrderTraversal2 {

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal2 obj = new BinaryTreeLevelOrderTraversal2();
        int[] test = {1, 2, 3, 4, 5, 6, 7};
        TreeNode tree = obj.buildTree(test);
        List<List<Integer>> traversal = obj.levelOrderBottom(tree);
        for (List<Integer> l : traversal) {
            System.out.print('[');
            for (Integer i : l) {
                System.out.print(i);
                System.out.print(',');
            }
            System.out.print(']');
        }
    }

    private void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        printTree(node.left);
        printTree(node.right);
    }

    private TreeNode buildTree(int[] data) {
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

    public List<List<Integer>> levelOrderBottom(TreeNode node) {
        List<List<Integer>> lists = new ArrayList<>();
        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();
        if (node == null) {
            return lists;
        }

        children.add(node);
        while (!children.isEmpty()) {
            parents.clear();
            parents.addAll(children);
            children.clear();

            List<Integer> subList = new ArrayList<>();
            for (TreeNode n : parents) {
                subList.add(n.val);
                if (n.left != null) {
                    children.add(n.left);
                }
                if (n.right != null) {
                    children.add(n.right);
                }
            }
            lists.add(subList);
        }

        Collections.reverse(lists);
        return lists;
    }

    /**
     * 深度优先
     * <p>
     * 只要记录下每次递归的 level，就能够实现层级遍历。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null) return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<Integer>()); //只要是当前层级不小于 list 长度，那么就list++
        }
        levelMaker(list, node.left, level + 1); //递归，注意当前的 level 并没有++
        levelMaker(list, node.right, level + 1);
        list.get(list.size() - level - 1).add(node.val); //关键部分，list.size 每深入一层就永久+1，而level 只是在最底层的递归中和 list 相等。
        //每当递归返回一层，那么 level 就-1，而 list.size 不变。那么 list.size -level - 1 就越来越大，这样就满足了条件中了逆向。
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
