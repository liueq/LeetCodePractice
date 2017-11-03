package com.liueq.leetcode.easy;

/**
 * 问题描述：移除一个链表中的重复项目。
 *
 * 解：有两种方法
 *
 * 1 照搬数组的解决方法，对于链表来说虽然可用，但是不够简洁，也没有利用到链表的特性。
 * 2 使用链表特性，超级简洁。
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args){
        RemoveDuplicatesFromSortedList obj = new RemoveDuplicatesFromSortedList();
        ListNode head = obj.createTestCase();
        obj.deleteDuplicates(head);
        obj.print(head);
    }

    private ListNode createTestCase(){
        ListNode head = new ListNode(1);
        ListNode cursor = head;
        int[] array = {1, 1, 2, 2, 3, 3, 3, 4, 5, 44, 44};
        for(int i = 0; i < array.length; i++){
            cursor.next = new ListNode(array[i]);
            cursor = cursor.next;
        }

        return head;
    }

    private void print(ListNode head){
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 使用类似于处理 array 的方法，用了 i，j 两个游标，当 j != j.next 的时候，同时移动 i，j。
     *
     * 此方法在数据结构为数组的时候还不错，但是对于链表，为了计算其长度还需要额外遍历一次。
     *
     * 并不适合链表。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head){
        ListNode i = head;
        ListNode j = head;

        if(head == null || head.next == null){
            return head;
        }

        int len = 1;

        while(j.next != null){
            if(j.val == j.next.val){
                j = j.next;
            }else{
                i.next = j.next;
                i = i.next;
                j = j.next;
                len++;
            }
        }

        j = head;

        while(len != 1){
            j = j.next;
            len--;
        }

        j.next = null;

        return head;
    }

    /**
     * 基于链表特性的超简洁方法，当 c == c.next 的时候，直接跳过 c.next，使用 c.next = c.next.next。
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesOfficial(ListNode head){
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
}
