package com.liueq.leetcode.easy;

/**
 * 问题描述：将一个有序数组转换成一个平衡查找二叉树
 * <p>
 * 解：首先查找二叉树的性质是：1左节点小于等于当前节点，右节点大于等于当前节点。平衡二叉树的性质是，左右子树的权重差小于2。
 * 再结合所提供的有序数组，可以得出，二分查找数组，每次取得中间值作为节点值，再二分左半部分数组，取得中间值作为左子节点，同理获得右子节点。
 */
public class ConvertedSortedArrayToBST {
    public static void main(String[] args) {
        ConvertedSortedArrayToBST obj = new ConvertedSortedArrayToBST();
        int[] testCase = {1, 3};
        TreeNode root = obj.sortedArrayToBSTOfficial(testCase);
        obj.printTree(root);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        } else if (nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(nums[(nums.length - 1) / 2]);
        createMidNode(root, nums, 0, nums.length - 1);

        return root;
    }

    /**
     * 太复杂了，由于要保持传入的node是一个对象，所以造成了必须在上一层中生成node的问题。
     * 如果在这里生成node，那么无法通过参数返回给上层。
     * <p>
     * 但是！！
     * <p>
     * 递归可以有返回值啊，返回值返回当前节点就能够大大简化。
     *
     * @param node
     * @param nums
     * @param low
     * @param high
     */
    private void createMidNode(TreeNode node, int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        int leftMid = low + (mid - 1 - low) / 2;
        int rightMid = (mid + 1) + (high - (mid + 1)) / 2;

        //特殊情况，以为取 mid 的方法，在偶数时，可能会偏向前边的数字，所以会有 low = mid的情况。
        //因为 mid 已经在上一层中生成了节点，所以 low 就不应该再生成了。
        if (mid > low) {
            node.left = new TreeNode(nums[leftMid]);
        }

        node.right = new TreeNode(nums[rightMid]);
        createMidNode(node.left, nums, low, mid - 1);
        createMidNode(node.right, nums, mid + 1, high);
    }

    public TreeNode sortedArrayToBSTOfficial(int[] nums) {
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode helper(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, low, mid - 1);
        node.right = helper(nums, mid + 1, high);
        return node;
    }

    private void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        printTree(node.left);
        printTree(node.right);
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
