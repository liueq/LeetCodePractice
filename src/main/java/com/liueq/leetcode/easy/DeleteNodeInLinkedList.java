package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.ListNode;

/**
 * 问题描述：删除链表中的一个节点，不包括最后一个。
 *
 * 解：此问题表面上看就是一个链表删除节点，但是实际上，问题并没有给出 head。因此不能够删除当前的节点。
 * 而是需要将下一个节点的值赋值给当前节点，然后删除下一个节点。由于不包含最后一个节点，所以该方法有效。
 */
public class DeleteNodeInLinkedList {
    public static void main(String[] args) {

    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
