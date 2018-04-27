package com.liueq.leetcode.easy;

/**
 * 问题描述：判断一个字符串是否可以全部由其子串组成
 * exp
 * abab -> true
 * abc -> false
 * aabbaabb -> true
 * <p>
 * 解：官方提供了一个巧妙地方法，见下面解释。
 * 我自己的解决方法：假设存在这样的子串，长度从 len / 2 ~ 1。那么用该长度把原始字符串均分，每一份都应该等于该子串。只要遍历完后满足，就认为成立。
 * 值得注意的：
 * 1. 必须是从 len / 2 -> 1 ，而不能从1开始，因为只要小粒度的子串满足，那么大粒度也必定满足，所以从大到小能够节省时间。
 * 2. 子串必须被原始字符串整除，否则肯定不满足
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        RepeatedSubstringPattern obj = new RepeatedSubstringPattern();
        String a = "abab";
        String b = "aba";
        String c = "abcabcabcabc";
        String d = "abac";
        String e = "aabaaba";
//        System.out.println(obj.repeatedSubstringPattern(a));
//        System.out.println(obj.repeatedSubstringPattern(b));
//        System.out.println(obj.repeatedSubstringPattern(c));
//        System.out.println(obj.repeatedSubstringPattern(d));
        System.out.println(obj.repeatedSubstringPattern(e));
    }

    public boolean repeatedSubstringPattern(String s) {
        boolean isRepeated = false;
        int len = s.length() / 2;
        while (len > 0) {
            if (s.length() % len != 0) {
                len--;
                continue;
            }
            isRepeated = true;
            String subString = s.substring(0, len); // substring 方法是前开后闭
            for (int i = len; i + len <= s.length(); i = i + len) {
                if (!subString.equals(s.substring(i, i + len))) {
                    isRepeated = false;
                }
            }

            if (isRepeated) {
                break;
            }

            len--;
        }

        return isRepeated;
    }

    /**
     * 重复一次原始字符串，去掉头尾的一个字符，如果此时的合并后字符串仍然包含原始字符串，那么就是满足该条件的。
     */
    public boolean repeatedSubstringPatternOfficial1(String s) {
        StringBuffer sb = new StringBuffer(s + s);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        return sb.indexOf(s) != -1;
    }
}
