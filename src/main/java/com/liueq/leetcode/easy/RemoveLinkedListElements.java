package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.ListNode;

/**
 * 问题描述：删除链表节点
 *
 * 解：
 * 1 双指针循环
 * 2 递归
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        RemoveLinkedListElements obj = new RemoveLinkedListElements();
        ListNode head = new ListNode(1);
        obj.removeElements(head, 1);
    }

    /**
     * 双指针遍历链表法，保证previous，这样可以随时删除p
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode tmpHead = new ListNode(-1);
        tmpHead.next = head;
        ListNode p = head;
        ListNode previous = tmpHead;
        while (p != null) {
            if (p.val == val) {
                if (p.next == null) {
                    previous.next = null;
                } else {
                    previous.next = p.next;
                }
            } else {
                previous = previous.next;
            }

            p = p.next;
        }

        return tmpHead.next;
    }

    /**
     *  递归删除
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

}
