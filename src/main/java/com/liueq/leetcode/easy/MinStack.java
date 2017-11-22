package com.liueq.leetcode.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * 问题描述： 设计一个 stack，满足常用的栈操作外，还需要支持一个 getMin 方法。
 *
 * 解：此问题的关键在于 getMin 的实现。
 *
 * 1. 目前是采用排序后，取得最小值。
 * 因此，排序的时机很重要，只有一个当pop 出当前的 min 后，再重新排序，这样能使得大部分情况下，排序次数最少。
 *
 * 2. 也可以再维护一个有序的 stack，每次getMin 就pop即可。
 *
 * 3. 由于MinStack 没有要求实现 size() 方法，意味着只要规定的方法中，返回正确的值，stack 里边到底有什么东西并不重要。
 * 因此下边这个方法就采用了当push 一个新的最小值时，先push 旧最小值，然后push 新的值。pop的时候，只要最小值等于 pop，那么
 * 多pop一次，第二次pop 出来的就是倒数第二小的值。此方法会在stack 中插入历次的最小值，如果在插入新的最小值时。
 *
 * class MinStack {
 *  int min = Integer.MAX_VALUE;
 *  Stack<Integer> stack = new Stack<Integer>();
 *  public void push(int x) {
 *      // only push the old minimum value when the current
 *      // minimum value changes after pushing the new value x
 *      if(x <= min){
 *          stack.push(min);
 *          min =x;
 *      }
 *      stack.push(x);
 *  }
 * <p>
 *  public void pop() {
 *      // if pop operation could result in the changing of the current minimum value,
 *      // pop twice and change the current minimum value to the last minimum value.
 *      if(stack.pop() == min) min=stack.pop();
 *  }
 * <p>
 *  public int top() {
 *      return stack.peek();
 *  }
 * <p>
 *  public int getMin() {
 *      return min;
 *  }
 * }
 *
 * 4. 可以维护一个链表，Node 存有当前的最小值，这样pop后，每次都能更新最小值。但是此方法的本质还是用多余的空间来维护了一min数组。
 */
public class MinStack {
    public static void main(String[] args) {

    }

    Stack<Integer> mStack;
    int mMin;

    public MinStack() {
        mStack = new Stack<>();
        mMin = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x < mMin) {
            mMin = x;
        }
        mStack.push(x);
    }

    public void pop() {
        int pop = mStack.pop();
        //只有在 pop 出 min 的时候，才需要重新排序，获得 min
        if (pop == mMin) {
            sortToMin();
        }
    }

    public int top() {
        return mStack.peek();
    }

    public int getMin() {
        return mMin;
    }

    private void sortToMin() {
        if (mStack.size() == 0) {
            mMin = Integer.MAX_VALUE;
        } else {
            Integer[] array = new Integer[mStack.size()];
            mStack.copyInto(array);
            Arrays.sort(array);
            mMin = array[0];
        }
    }
}
