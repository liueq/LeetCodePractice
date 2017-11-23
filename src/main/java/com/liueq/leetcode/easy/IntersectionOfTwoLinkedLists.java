package com.liueq.leetcode.easy;

import java.util.HashSet;

/**
 * 问题描述：查看两个链表是否相交汇
 *
 *      a1 - a2
 *              \
 *              c1 - c2 - c3
 * b1 - b2 - b3 /
 *
 * 解：
 *
 * 1 哈希表除重复方式。
 * 2 去掉较长者开头的多余长度的方式。
 * 3 双指针，末尾交叉遍历方式。
 *
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists obj = new IntersectionOfTwoLinkedLists();
        ListNode headA = new ListNode(3);
        ListNode headB = new ListNode(2);
        headB.next = new ListNode(3);
        ListNode intersection = obj.getIntersectionNode2Official(headA, headB);
        System.out.println(intersection == null ? "null" : intersection.val);
    }

    /**
     * 使用HashTable 加入a，然后遍历b，第一个a中包含b的节点就是相交点。
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(n) or O(m)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }

            headB = headB.next;
        }

        return null;
    }

    /**
     * 比较两个链表长度，去掉较长链表的前段超长部分，使得两个链表长度相等。
     * 然后，一次比较两个链表节点，直到遇到相等的节点，该节点就是相交点。
     * <p>
     * 时间复杂度： O(m + n + k)
     * 空间复杂度： O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode itA = headA;
        ListNode itB = headB;
        int lengthA = 0, lengthB = 0;
        while (itA != null) {
            itA = itA.next;
            lengthA++;
        }

        while (itB != null) {
            itB = itB.next;
            lengthB++;
        }

        //使得两个链表长度相等，去掉开头多余的部分
        if (lengthA > lengthB) {
            int diff = lengthA - lengthB;
            for (int i = 0; i < diff; i++) {
                headA = headA.next;
            }
        } else {
            int diff = lengthB - lengthA;
            for (int i = 0; i < diff; i++) {
                headB = headB.next;
            }
        }

        while (headA != headB) {
            if (headA == null) {
                return null;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    /**
     * 按照1的速度遍历a，b。当a到达末尾，移动到b的开头；当b到达末尾，移动到a的开头。直到p1 == p2，那么此时就是相交点。
     * 如果 lastA != lastB，那么说明两个链表无相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2Official(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode itA = headA;
        ListNode itB = headB;

        ListNode lastA = null, lastB = null;

        while (itA != itB) {
            if (itA.next == null) {
                lastA = itA;
                itA = headB;
            } else {
                itA = itA.next;
            }
            if (itB.next == null) {
                lastB = itB;
                itB = headA;
            } else {
                itB = itB.next;
            }

            if (lastA != null && lastB != null && lastA != lastB) {
                return null;
            }
        }

        return itA;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
