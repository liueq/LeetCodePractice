package com.liueq.leetcode.easy;

/**
 * 问题描述： 求一个字符串中，段落的数量（以空格隔开）。
 *
 * 解：
 * 1 可以使用 split 加正则表达式解决，但是要注意前后空格的情况，以及长度为0的情况。
 * 2 计算前一个字符为空格或者开头，且当前字符串不是空格的出现次数。每个段落的开头必定满足此格式。
 */
public class NumberOfSegmentsInString {
    public static void main(String[] args){
        NumberOfSegmentsInString obj = new NumberOfSegmentsInString();
        String str = "     abcde eree ";
        System.out.println(obj.countSegments(str));
    }

    public int countSegments(String s) {
        int count = 0;
        boolean isSegment = false; //当前i被包括进去后，是否是一个 segment
        for(int i = 0; i < s.length(); i++) {
            if (isSegment) {
                // i 已经是在 segment 中，所以 count 不增加
                if (s.charAt(i) == ' ') {
                    isSegment = false;
                }else {
                    isSegment = true;
                }
            } else {
                // i 没在 segment 中，是否要 count 根据i是否是非空格决定
                if (s.charAt(i) != ' ') {
                    count++;
                    isSegment = true;
                } else {
                    isSegment = false;
                }
            }
        }

        return count;
    }

    public int countSegmentsOfficial(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i ++) {
            if((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ')
                count++;
        }

        return count;
    }

    public int countSegmentsBuiltin(String s) {
        String trimed = s.trim();
        if (trimed.equals("")) {
            return 0;
        } else {
            return trimed.split("\\s+").length;
        }
    }
}
