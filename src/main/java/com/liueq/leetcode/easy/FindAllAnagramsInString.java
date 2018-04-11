package com.liueq.leetcode.easy;

import java.util.*;

/**
 * 问题描述：给定两个字符串 s, p，找出所有 s 中，包含 p 的 anagram 子串的起始位置。
 *
 * 解：使用 slide window 解决。
 * 如果使用一个定长的 window，那么在数据非常大的时候，每次比较 p 和子串会用到排序再比较，这样性能无法满足要求。
 *
 * 使用 hash 的方式，在 window 每次向右移动的时候，都记录当前 window 的状态变化，每次移动基本无计算量。
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        FindAllAnagramsInString obj = new FindAllAnagramsInString();
        List<Integer> result = obj.findAnagramsMe("abab", "ab");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || "".equals(s)) {
            return Collections.EMPTY_LIST;
        }
        if (p.length() > s.length()) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> counter = new ArrayList<>();
        List<Character> TEMP = new ArrayList<>();
        List<Character> temp = new ArrayList<>();

        boolean fit = false;
        int fitStart = -1;
        int fitEnd = -1;

        for (int i = 0; i < p.length(); i++) {
            TEMP.add(p.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {

            // 目的是为了跳过大量重复且满足的情况
            if (fit) {
                if (fitEnd + 1 < s.length() && s.charAt(fitStart) == s.charAt(fitEnd + 1)) {
                    fitStart++;
                    fitEnd++;
                    counter.add(i);
                    continue;
                }
            }

            temp.clear();
            temp.addAll(TEMP);

            int j;
            for (j = i; j < s.length() && j - i < p.length(); j++) {
                if (temp.contains(s.charAt(j))) {
                    temp.remove((Character) s.charAt(j));
                } else {
                    break;
                }
            }

            if (j - i == p.length() && temp.size() == 0) {
                counter.add(i);
                fit = true;
                fitStart = i;
                fitEnd = i + p.length() - 1;
            } else {
                fit = false;
            }
        }

        return counter;
    }

    public List<Integer> slideWindow(String s, String t) {
        List<Integer> result = new LinkedList<>();

        if (t.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = map.size();

        int begin = 0, end = 0;

        int len = Integer.MAX_VALUE;

        while (end < s.length()) {
            char c = s.charAt(end);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    counter--;
                }
            }

            end++;

            while (counter == 0) {

                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }

                begin++;
            }
        }

        return result;
    }


    /**
     * By NathanNi
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsOfficial(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }
        int[] hash = new int[256];

        // 将 p 按字符分布到 hash 中
        for (char c : p.toCharArray()) {
            hash[c]++;
        }

        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            /*
             right ++ 从0开始，每次 right 递增1
             s.charAt(right)，从 s 中取出当前 right 对应的字符
             hash[] 找到该字符在 hash 数组中对应的位置的值，从0开始，每次递减1
             比较 hash[] 是否大于等于1，如果是减少 count

             在元素进入 slide window 时，必定是满足 p 中的元素才能使 count --
             */
            if (hash[s.charAt(right++)]-- >= 1) {
                count--;
            }

            /*
             当 count == 0 时，认为 right - left 这个长度正好和 p 相同，此时的 left 就是起点
             */
            if (count == 0) {
                list.add(left);
            }

            /*
             right -left == p.lenght()，说明目前 slide window 的大小正好和 p 相同，那么执行到这一步，需要让 left 向前移动了。
             left ++，并且使得 left 对应的 hash[] 也 ++，因为该元素在进入 slide window 时，被 -- 了，再离开时应该还原回去

             在元素离开 slide window 时，必定是满足 p 中的元素才能使 count ++
             */
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) {
                count++;
            }
        }

        return list;
    }

    /**
     * 不使用 slide window 的情况下，直接一个固定的 window。当数据过大的时候，会超时。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsMe(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }
        int p1 = 0, p2 = p1 + p.length();
        char[] template = p.toCharArray();
        Arrays.sort(template);
        boolean lastOk = false;
        while (p2 <= s.length()) {
            if(lastOk){
                if(s.charAt(p2 -1) == s.charAt(p1 -1)){
                    list.add(p1);
                }else{
                    lastOk = false;
                }
            }else {
                char[] target = s.substring(p1, p2).toCharArray();
                Arrays.sort(target);
                if (String.valueOf(template).equals(String.valueOf(target))) {
                    list.add(p1);
                    lastOk = true;
                }else{
                    lastOk = false;
                }

            }
            p1++;
            p2++;
        }

        return list;
    }


    /**
     * 自己默写了一次
     * @param s
     * @param p
     * @return
     */
    private List<Integer> slideWindowMe(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }
        int left = 0, right = 0;
        int[] hash = new int[256];

        for (char c : p.toCharArray()) {
            hash[c]++;
        }

        int count = p.length();
        while (right < s.length()) {
            if (hash[s.charAt(right++)]-- >= 1) {
                count--;
            }

            if (count == 0) {
                list.add(left);
            }

            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) {
                count++;
            }
        }

        return list;
    }
}
