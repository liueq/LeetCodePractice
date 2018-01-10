package com.liueq.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：给出一个 pattern，判断一个以空格隔开的字符串是否是与该 pattern 相符合
 *
 * "aab" vs "good good bad" 符合
 * "abca" vs "one two three one" 符合
 * "abba" vs "one two two three" 不符合
 *
 * 解：似乎只能使用 HashMap，记录映射关系
 */
public class WordPattern {
    public static void main(String[] args) {
        WordPattern pattern = new WordPattern();
        String p = "aab";
        String str = "good one one";
        System.out.println(pattern.wordPattern(p, str));
    }

    /**
     * 使用 HashMap 来记录映射
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        char[] p = pattern.toCharArray();
        String[] s = str.split(" ");

        if (p.length != s.length) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < p.length; i++) {
            String assume = map.get(p[i]);
            if (assume == null) {
                // 如果 key 不存在于 map 中时，其 value 也必须不存在，否则就会造成两个 key 有同一个 value
                if(map.values().contains(s[i])){
                    return false;
                }else{
                    map.put(p[i], s[i]);
                }
            } else {
                if (!assume.equals(s[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
