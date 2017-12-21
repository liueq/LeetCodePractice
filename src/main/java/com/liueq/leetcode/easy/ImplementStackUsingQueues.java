package com.liueq.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 问题描述：使用队列来实现栈
 *
 * 解：
 * 1 使用两个队列，在每次插入的时候记录下最后一个作为 top，当要pop的时候，想 q1.size - 1 移动到 q2，这样就移除了最后插入的一个。
 *  插入：
 *      q1: 12 + 3
 *      q2: empty
 *      top = 3
 *  pop：
 *      q1: 3
 *      q2: 12
 *      top = 2
 *
 * 2 使用两个队列，保持其中一个为空，每次都往空的那个队列中插入，然后将另一个非空队列的元素全部移动到前一个队列中。这样保证了每次pop都能够取到最后插入的一个值。
 * push:
 *      q1: 3
 *      q2: 12
 *
 *      q1:312
 *      q2: empty
 *
 * pop:
 *      q1:12
 *      q2:emtpy
 *
 * 3 使用一个队列，每次插入的时候，将队列逆序（通过不断的pop,push实现）
 *
 * push:
 *      q1: 123
 *      q1: 231
 *      q1: 312 逆序完成，这样插入的3就放到了队列的最开始
 */
public class ImplementStackUsingQueues {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < 10; i++) {
            myStack.push(i);
            stack.push(i);
        }

        /*
        for (int i : myStack.getQueue1()) {
            System.out.println(i);
        }

        System.out.println("++++");

        for (int i : myStack.getQueue2()) {
            System.out.println(i);
        }
        */

        for(int i = 0 ; i< 10; i++) {
            System.out.println("myStack = " + myStack.pop() + "\t" + "STACK = " + stack.pop());
        }
    }

    public static class MyStack{

        Queue<Integer> mQueue1 = new LinkedList<>();
        Queue<Integer> mQueue2 = new LinkedList<>();
        boolean mUseQueue1 = true;

        public MyStack() {

        }

        public Queue<Integer> getQueue1() {
            return mQueue1;
        }

        public Queue<Integer> getQueue2() {
            return mQueue2;
        }

        public void push(int x) {
            if (mUseQueue1) {
                mQueue1.add(x);
                transfer(mQueue2, mQueue1);
            } else {
                mQueue2.add(x);
                transfer(mQueue1, mQueue2);
            }
            mUseQueue1 = !mUseQueue1;
        }

        public int pop() {
            if (mUseQueue1) {
                return mQueue2.poll();
            } else {
                return mQueue1.poll();
            }
        }

        public int top() {
            if (mUseQueue1) {
                return mQueue2.peek();
            } else {
                return mQueue1.peek();
            }

        }

        public boolean empty() {
            return mQueue1.size() + mQueue2.size() == 0;
        }

        private void transfer(Queue<Integer> q1, Queue<Integer> q2) {
            q2.addAll(q1);
            q1.clear();
        }
    }
}
