package com.liueq.leetcode.easy;

/**
 * 问题描述：判断两个二叉树是否相同。
 * <p>
 * 解：不能够分别遍历，这样耗时过长，肯定是在遍历的过程中就进行比较。
 * <p>
 * 1 直接在递归遍历的过程中带入两颗树，简洁。
 * 2 在两个线程中同时遍历，需要处理线程调度问题，实现 a，b，a，b 这样的顺序来遍历，太复杂，而且线程调度开销过大。
 */
public class SameTree {
    public static void main(String[] args) {
        main2();
    }

    public static void main2() {
        SameTree obj = new SameTree();
        TreeNode root1 = new TreeNode(0);
        TreeNode root2 = new TreeNode(0);

        obj.buildTree(root1, obj.case1);
        obj.x = 0;
        obj.buildTree(root2, obj.case2);

        obj.printTree(root1);
        System.out.println("====");
        obj.printTree(root2);

//        System.out.println("Build Tree OK.");
        System.out.println(obj.isSameTree2(root1, root2));
    }

    /**
     * 官方解答之一，思路基本上就是同时遍历两棵树，放到同一个递归中处理，这里更简洁，直接返回判断结果。
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeOfficial(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val)
            return isSameTreeOfficial(p.left, q.left) && isSameTreeOfficial(p.right, q.right);
        return false;
    }

    /**
     * 自己想出来的第二个方法，同时遍历两个树，在一个线程中。
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        isSame = true;
        iterateTwoTree(p, q);
        return isSame;
    }

    private void iterateTwoTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return;
        }
        if ((p == null && q != null)
                || (p != null && q == null)) {
            isSame = false;
            return;
        }

        if (p.val != q.val) {
            isSame = false;
        }

        iterateTwoTree(p.left, q.left);
        iterateTwoTree(p.right, q.right);
    }

    private volatile Boolean isSame = true;
    private volatile Object mLock = new Object();

    /**
     * 自己想的方法，在两个线程中同时遍历两颗树，最大的问题是线程切换开销。此方法在 LeetCode 的编辑器上无法正常运行。
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Thread t1 = new Thread(() -> {
            iterateP(p);
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            iterateQ(q);
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return isSame;
    }

    int[] case1 = {1, 2};
    int[] case2 = {1, 2, 3};

    int x = 0;

    public void buildTree(TreeNode node, int[] data) {
        if (node == null) {
            return;
        }
        if (x < data.length) {
            node.left = new TreeNode(data[x++]);
        }
        if (x < data.length) {
            node.right = new TreeNode(data[x++]);
        }
        buildTree(node.left, data);
        buildTree(node.right, data);
    }

    public void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        printTree(node.left);
        System.out.println(node.val);
        printTree(node.right);
    }

    public void iterateP(TreeNode parent) {
        if (parent == null || !isSame) {
            synchronized (mLock) {
                mLock.notifyAll();
            }
            return;
        }

        iterateP(parent.left);
        sum += parent.val;
        complete--;

        synchronized (mLock) {
            mLock.notifyAll();
        }

        synchronized (mLock) {
            try {
                if (isSame)
                    mLock.wait();

                if (sum != 0 || complete != 0) {
                    isSame = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        iterateP(parent.right);
    }

    public void iterateQ(TreeNode parent) {
        if (parent == null || !isSame) {
            synchronized (mLock) {
                mLock.notifyAll();
            }
            return;
        }

        iterateQ(parent.left);
        sum -= parent.val;
        complete++;

        synchronized (mLock) {
            if (sum != 0 || complete != 0) {
                isSame = false;
            }

            mLock.notifyAll();
        }

        synchronized (mLock) {
            try {
                if (isSame)
                    mLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        iterateQ(parent.right);
    }

    volatile int complete = 0;
    volatile int sum = 0;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
