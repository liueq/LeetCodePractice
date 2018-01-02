package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：判断链表是否是回文链表
 *
 * 解： 总之都是翻转链表然后判断
 * 1 翻转后半部分链表，然后判断前后半部分时候相等，使用两个指针遍历，能够一次遍历就找到链表的中点。
 * 2 翻转整个链表，然后判断翻转前后是否相等。需要额外空间。
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        PalindromeLinkedList obj = new PalindromeLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        boolean b = obj.isPalindrome(head);
        System.out.println(b);
    }

    /**
     * 翻转后半部分链表，然后判断前后半部分时候相等，使用两个指针遍历，能够一次遍历就找到链表的中点。
     * @param head
     * @return
     */
    public boolean isPalindromeHalf(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            if (fast.next.next == null) {
                break;
            } else {
                fast = fast.next.next;
            }

            slow = slow.next;
        }

        ListNode revertedHalf = revertList(slow.next);
        while (revertedHalf != null && head != null) {
            if (revertedHalf.val != head.val) {
                return false;
            }
            revertedHalf = revertedHalf.next;
            head = head.next;
        }

        return true;
    }

    /**
     * 翻转整个链表，然后判断翻转前后是否相等。需要额外空间。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();

        ListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            array1.add(p.val);
            p = p.next;
        }

        System.out.println("");

        p = revertList(head);
        while (p != null) {
            System.out.println(p.val);
            array2.add(p.val);
            p = p.next;
        }

        if (array1.size() != array2.size()) {
            System.out.println("array1 = " + array1.size());
            System.out.println("array2 = " + array2.size());
        }
        for(int i = 0; i < array1.size(); i++) {
            int a = array1.get(i);
            int b = array2.get(i);
            if (a != b) {
                return false;
            }
        }

        return true;
    }

    private ListNode revertList(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        ListNode p = head;
        while (p != null) {
            ListNode temp = p.next;
            p.next = fakeHead.next;
            fakeHead.next = p;
            p = temp;
        }

        return fakeHead.next;
    }

}
