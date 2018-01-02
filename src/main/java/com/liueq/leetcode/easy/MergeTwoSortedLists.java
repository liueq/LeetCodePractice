package com.liueq.leetcode.easy;

import com.liueq.leetcode.support.ListNode;

/**
 * 描述：合并两个单项链表
 *
 * 主要有两种思路，一种是递归合并，另一种是循环，比较然后合并。
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        MergeTwoSortedLists merge = new MergeTwoSortedLists();
        ListNode callback = merge.mergeTwoLists2(l1, l2);

        while (callback != null) {
            System.out.println(callback.val);
            callback = callback.next;
        }
    }

    /**
     * 自己的答案
     * <p>
     * 主要是需要三个引用，分别追踪：1 已合并部分的链表头；2 未合并的链表1的当前节点；3 未合并的链表2的当前节点
     * <p>
     * 合并步骤：比较未合并链表的头节点，将较小者放入合并链表中，直到其中一个未合并链条没有下一个节点，将剩下的一个链表接入合并链表中。
     * <p>
     * 感觉应该有更简洁的写法，我这样写的只是让我思路更清晰，不然感觉有点混乱
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cursor1 = l1;
        ListNode cursor2 = l2;
        ListNode merged = new ListNode(-1);

        //如果任意一个为空，或者两个都为空，返回恰当的
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        l1 = merged;
        while (cursor1 != null && cursor2 != null) {
            if (cursor1.val > cursor2.val) {
                merged.next = cursor2;
                merged = merged.next;
                cursor2 = cursor2.next;
            } else {
                merged.next = cursor1;
                merged = merged.next;
                cursor1 = cursor1.next;
            }
        }

        if (cursor1 != null) {
            merged.next = cursor1;
        }

        if (cursor2 != null) {
            merged.next = cursor2;
        }

        return l1.next;
    }

    /**
     * 标准答案： 利用递归的方法，将较小者的后续链表合并
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
