package com.liueq.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 问题描述：查看一个单向链表是否有圈（不一定是首尾）
 *
 * 解：
 *
 * 1 使用HashTable进行去重复。
 * 2 使用 slow fast 追踪法，只要 fast能够追上 slow，那么说明有圈。
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        LinkedListCycle obj = new LinkedListCycle();
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(0);
        n2.next = n3;
        ListNode n4 = new ListNode(-4);
        n3.next = n4;
//        boolean cycle = obj.hasCycle(n1);

        ListNode reversed = obj.reverseList2(n1);
        obj.printList(reversed);
    }

    /**
     * 定义两个游标，fast 和 slow，让fast 在 slow 之前跑，每次跑2格。
     * 如果没有圈，那么 fast 会直接跑到链表结尾；
     * 如果有圈，那么 fast 会绕一圈回来后追上 slow，由此判断；
     * <p>
     * 时间复杂度，如果没有圈，那么 O(n)
     * 如果有圈，fast 需要多跑 k，那么 O(n + k)
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 利用 HashTable 来去重复。
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> hash = new HashSet<>();
        while (head.next != null) {
            if (hash.contains(head)) {
                return true;
            } else {
                hash.add(head);
            }
            head = head.next;
        }

        return false;
    }

    /**
     * 自己未能解决
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode cursor = head;
        while (true) {
            if (cursor.next == null) {
                return false;
            } else if (cursor == cursor.next.next) {
                return true;
            }

            cursor.next = cursor.next.next;
        }
    }

    /**
     * 反转链表，将A链表从头顺序获取，从B链表，从头开始插入，这样得到的B就是A的反转。
     *
     * @param head
     * @return
     */
    private ListNode reverseList1(ListNode head) {
        ListNode newHead = new ListNode(-1);
        ListNode originHead = new ListNode(-1);
        originHead.next = head;

        ListNode newC = newHead;
        while (originHead.next != null) {
            newC = newHead.next;
            newHead.next = originHead.next;
            originHead.next = originHead.next.next;
            newHead.next.next = newC;
        }

        return newHead.next;
    }

    /**
     * 反转链表，不断从原有head之后取node，插入到一个新的链表头中，也就是自己前边。
     *
     * @param head
     * @return
     */
    private ListNode reverseList2(ListNode head) {
        ListNode n = head;
        ListNode trueHead = new ListNode(-1);
        trueHead.next = n;
        while (n.next != null) {
            // 链表头的删除
            ListNode trans = n.next;
            n.next = n.next.next;

            //链表头插入
            ListNode temp = trueHead.next;
            trueHead.next = trans;
            trueHead.next.next = temp;
        }

        return trueHead.next;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
