package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 问题描述：判断一颗二叉树是否是镜像的。
 *
 * 解：可以使用递归和迭代两种方法，但是基本思想是一样的。
 * 1 root 节点必须相等，然后左子树是右子树的镜像。
 * 2 当从 root 把一棵树分为了左右子树后，任意一个左子树的节点p，必须满足其在右子树的镜像节点q，存在 p == q，且 p.left == q.right, p.right == q.left。
 * 3 建立递归
 */
public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree obj = new SymmetricTree();
        TreeNode tree = obj.buildTree(new int[]{1, 2, 2, 3, 4, 4, 4});
//        obj.firstPrint(tree);
        System.out.println(obj.isSymmetricOffical2(tree));
    }

    public boolean isSymmetric(TreeNode root) {
        String before = layerIterate(root);
        reverseBinaryTree(root);
        String after = layerIterate(root);
        System.out.println(before);
        System.out.println(after);
        return before.equals(after);
    }

    /**
     * 递归，满足镜像的条件是，
     * <p>
     * 1 root 节点必须相等，然后左子树是右子树的镜像。
     * 2 当从 root 把一棵树分为了左右子树后，任意一个左子树的节点p，必须满足其在右子树的镜像节点q，存在 p == q，且 p.left == q.right, p.right == q.left。
     * 3 建立递归
     *
     * @param root
     * @return
     */
    public boolean isSymmetricOffical1(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val
                && isMirror(p.left, q.right)
                && isMirror(p.right, q.left);
    }

    /**
     * 遍历，需要一个 queue 辅助，思想和递归差不多，只是针对 queue 的结构，细节操作不同。
     *
     * 1 每次比较 queue 的相邻两项，这两项是二叉树中位于镜像位置的节点。
     * 2 在p，q节点相等后，还需要比较其子树是否相等，为了保证镜像，应该将 p.left, q.right 相邻，p.right, q.left 相邻。
     * 3 直到 queue 为空，表示遍历完了整棵树，如果还未返回 false，表示满足镜像。
     *
     * @param root
     * @return
     */
    public boolean isSymmetricOffical2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();

            if (p == null && q == null) {
                continue;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }

            queue.add(p.left);
            queue.add(q.right);
            queue.add(p.right);
            queue.add(q.left);
        }

        return true;
    }

    private void reverseBinaryTree(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        reverseBinaryTree(node.left);
        reverseBinaryTree(node.right);
    }

    private String layerIterate(TreeNode root) {
        StringBuffer buffer = new StringBuffer();
        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();

        children.add(root);
        while (!allHolder(children)) {
            parents.clear();
            parents.addAll(children);
            children.clear();

            for (TreeNode p : parents) {
                if (p instanceof Holder) {
                    buffer.append('_');
                } else {
                    buffer.append(p.val);
                }

                if (p.left == null) {
                    p.left = new Holder(-1);
                }
                if (p.right == null) {
                    p.right = new Holder(-1);
                }

                children.add(p.left);
                children.add(p.right);
            }
        }

        return buffer.toString();
    }


    private boolean allHolder(List<TreeNode> list) {
        boolean allHolder = true;
        for (TreeNode node : list) {
            if (!(node instanceof Holder)) {
                allHolder = false;
            }
        }

        return allHolder;
    }

    public static class Holder extends TreeNode {
        public Holder(int x) {
            super(x);
        }
    }

    StringBuffer buffer1 = new StringBuffer();
    StringBuffer buffer2 = new StringBuffer();

    private void firstRecursive(TreeNode node) {
        if (node == null) {
            return;
        }

        buffer1.append(node.val);
        firstRecursive(node.left);
        firstRecursive(node.right);
    }

    private void firstRecursiveReverse(TreeNode node) {
        if (node == null) {
            return;
        }

        buffer2.append(node.val);
        firstRecursiveReverse(node.right);
        firstRecursiveReverse(node.left);
    }

    private void midRecursive(TreeNode node) {
        if (node == null) {
            return;
        }

        firstRecursive(node.left);
        buffer1.append(node.val);
        firstRecursive(node.right);
    }

    private void midRecursiveReverse(TreeNode node) {
        if (node == null) {
            return;
        }

        firstRecursiveReverse(node.right);
        buffer2.append(node.val);
        firstRecursiveReverse(node.left);
    }


    private TreeNode buildTree(int[] data) {
        List<TreeNode> parents = new ArrayList<>();
        List<TreeNode> children = new ArrayList<>();

        TreeNode root = new TreeNode(data[0]);
        children.add(root);
        for (int i = 0; i < data.length; ) {
            parents.clear();
            parents.addAll(children);
            children.clear();
            for (TreeNode p : parents) {
                if (2 * i + 1 < data.length) {
                    p.left = new TreeNode(data[2 * i + 1]);
                    children.add(p.left);
                } else {
                    return root;
                }

                if (2 * i + 2 < data.length) {
                    p.right = new TreeNode(data[2 * i + 2]);
                    children.add(p.right);
                } else {
                    return root;
                }
                i++;
            }
        }

        return root;
    }

    private void firstPrint(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        firstPrint(node.left);
        firstPrint(node.right);
    }

}
