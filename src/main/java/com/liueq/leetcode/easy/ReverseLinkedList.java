package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.ListNode;

/**
 * 问题描述：反转单向链表
 *
 * 解：
 * 1 循环，从链表头插入达到反转的效果
 * 2 递归，关键是对于中间状态的定一个 head.next.next = head, head.next = null
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public ListNode reverseList(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode node = head;
        while (node.next != null) {
            ListNode p = node.next;
            node.next = node.next.next;
            p.next = fakeHead.next;
            fakeHead.next = p;
        }

        return fakeHead.next;
    }

}
