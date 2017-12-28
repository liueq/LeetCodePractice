package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

/**
 * 问题描述：判断一个二叉树是否是平衡二叉树
 * <p>
 * 解：递归，判断左右子树的高度是否相差不超过1即可。
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        calculateHeight(root);
        return mBalanced;
    }

    private boolean mBalanced = true;

    /**
     * 自己想出来的，同时也是标准方法。
     * 计算左右子树的高度，然后比较判断是否是平衡。可以优化的地方是当发现不平衡后，可以返回 -1，定义为不平衡。这样就可以不再继续遍历之后的内容了。
     * 如果是现在这样，用一个 field 来保存是否平衡，会完全遍历，时间稍微长一点。
     *
     * @param node
     * @return
     */
    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            mBalanced = false;
        }

        return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

}
