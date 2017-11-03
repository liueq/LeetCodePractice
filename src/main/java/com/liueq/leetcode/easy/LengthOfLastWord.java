package com.liueq.leetcode.easy;

/**
 * 问题描述： 在一个字母和空格组成的字符串中，找出最后一个词的长度
 *
 * 解：从字符串最后开始扫描，排除最后的空格，然后直到遇到一个空格的距离就是最后一个单词的长度
 */
public class LengthOfLastWord {

    public static void main(String[] args){
        LengthOfLastWord algorathm = new LengthOfLastWord();
        System.out.println(algorathm.lengthOfLastWord("Hello World"));
    }

    public int lengthOfLastWord(String s){
        if(s == ""){
            return 0;
        }
        String[] splits = s.split(" ");
        if(splits.length >= 1){
            return splits[splits.length - 1].length();
        }else{
            return 0;
        }
    }

    public int lengthOfLastWord2(String s){
        if(s.equals("")){
            return 0;
        }

        int tail = s.length() - 1;
        int length = 0;
        while (tail >= 0 && s.charAt(tail) == ' ') {
            tail--;
        }
        while (tail >= 0 && s.charAt(tail) != ' '){
            tail--;
            length++;
        }

        return length;
    }
}
