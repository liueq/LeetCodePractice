package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.TreeNode;

import java.util.HashMap;

/**
 * 问题描述：给一个二叉树，找出路径之和为 n 的所有路径的个数。
 *
 * 解：我使用的方法是嵌套遍历二叉树，在每一个节点上，都找出以该节点为 root 的子树是否存在路径为 n 的情况，使用一个 field 记录下来。
 */
public class PathSum3 {
    public static void main(String[] args){

    }

    public int pathSum(TreeNode root, int sum) {
        outerRecursive(root, sum);

        return mTotal;
    }

    private int mTotal = 0;

    private void recursive(TreeNode node, int current, int sum) {
        if (node == null) {
            return;
        }

        if (current + node.val == sum) {
            mTotal++;
        }

        recursive(node.left, current + node.val, sum);
        recursive(node.right, current + node.val, sum);
    }

    private void outerRecursive(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        recursive(node, 0, sum);

        outerRecursive(node.left, sum);
        outerRecursive(node.right, sum);
    }


    /**
     * 其中一个时间复杂度为 O(n) 的方法，使用一个 HashMap 来记录路径信息。感觉稍微复杂，并不直观，暂时不管了。
     * @param root
     * @param sum
     * @return
     */
    public int pathSumOfficial(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }

    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum)
                + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) + 1);
        return res;
    }
}
