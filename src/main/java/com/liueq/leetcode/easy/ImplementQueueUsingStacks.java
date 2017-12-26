package com.liueq.leetcode.easy;

import java.util.Stack;

/**
 * 问题描述：使用栈来实现队列
 *
 * 解：使用两个栈即可，具体有两种方式。如果要追求 push 比 pop 时间复杂度更低，那么就在pop 的时候再将数据移动到另一个栈。
 * 如果是希望 pop 比 push 时间复杂度更低，那么在push 的时候，就先将数据移动。
 */
public class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        for(int i = 0; i < 10; i++) {
            queue.push(i);
        }

        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }

    static class MyQueue{
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            stack1.push(x);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        public int pop() {
            return stack1.pop();
        }

        public int peek() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }
}
