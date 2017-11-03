package com.liueq.leetcode.easy;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 描述：判断是个包含'(', ')', '[', ']', '{', '}' 是否是回文
 *
 * 方法：直接用栈解决，官方答案里利用了入栈的小技巧，简化了比较过程。
 */
public class ValidParentheses {

    public static void main(String[] args){
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid("[][][]"));
        System.out.println(vp.isValid("[[{{(())}}]]"));
        System.out.println(vp.isValid(""));
        System.out.println(vp.isValid("["));
        System.out.println(vp.isValid("}"));
    }

    /**
     * 用栈解决，注意几种特殊情况即可。
     * @param s
     * @return
     */
    public boolean isValid(String s){
        LinkedList<Character> stack = new LinkedList<>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.size() == 0){
                    return false;
                }
                char pop = stack.pop();
                if(!match(pop, c)){
                    return false;
                }
            }
        }

        if(stack.size() == 0){
            return true;
        }else {
            return false;
        }
    }

    private boolean match(char a, char b){
        if(a == '(' && b == ')'){
            return true;
        }else if(a == '[' && b == ']'){
            return true;
        }else if(a == '{' && b == '}'){
            return true;
        }
        return false;
    }

    /**
     * 直接用 Stack，在入栈的时候使用了点小技巧，当遇到前半部分的时候，直接入栈后半部分，这样在遇到后半部分的时候，直接出栈一个字符，判断是否相等即可，就节省了匹配判断。
     * @param s
     * @return
     */
    private boolean isValid1(String s){
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            }else {
                if(stack.isEmpty()){
                    return  false;
                }else if(stack.pop() != c){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
