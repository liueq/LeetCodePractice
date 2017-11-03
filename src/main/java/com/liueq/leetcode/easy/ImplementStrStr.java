package com.liueq.leetcode.easy;


/**
 * 问题描述：查看 needle 是否是 haystack 的子串，如果是，返回第一个子串的位置，如果不是，返回 -1
 *
 * 解答：
 *
 * 1 虽然有多重写法，但是本质上没有区别，都是两层循环，时间复杂度 O(n)2
 * 2 此问题貌似和 KMP 算法有关系，KMP 才是求得子串的最速方法， 此问题并没做出这么高的要求。
 */
public class ImplementStrStr {

    public static void main(String[] args) {

        ImplementStrStr strStr = new ImplementStrStr();
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr.strStr(haystack, needle));

    }

    /**
     * 两层循环遍历， haystack 的增量为1，每次判断 haystack[i] 是否等于 needle[j]，注意一些特殊情况。
     *
     * 特殊情况：
     * 1 当两个字符串都为 "" 时，应该返回0
     * 2 当 haystack 长度小于 needle 时，肯定找不到，直接返回 -1
     * 3 当在比较过程中，如果 haystack 未比较部分的剩余长度小于 needle 时，肯定找不到，直接返回 -1

     * 另外，先转换成数组，比直接用 charAt 效率更高。
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(haystack.equals("") && needle.equals("")){
            return 0;
        }
        char[] hayArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();

        if (needleArray.length > hayArray.length)
            return -1;

        int i = 0, j = 0;
        while (i < hayArray.length) {
            int temp = i;
            j = 0;
            if(hayArray.length - i < needleArray.length){
                return -1;
            }
            while (i < hayArray.length && j < needleArray.length) {
                if (hayArray[i] == needleArray[j]) {
                    i++;
                    j++;
                } else {
                    break;
                }
            }

            if (j == needleArray.length) {
                return i - j;
            }

            i = temp + 1;
        }

        return -1;
    }

    /**
     * 思路同上，但是并未使用数组，速度不如前者
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if (haystack.equals("") && needle.equals("")) {
            return 0;
        }

        if (needle.length() > haystack.length()) {
            return -1;
        }

        int i = 0, j;
        while (i < haystack.length()) {
            int temp = i;
            j = 0;
            if(haystack.length() - i < needle.length()){
                return -1;
            }
            while (i < haystack.length() && j < needle.length()) {
                if (haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                } else {
                    break;
                }
            }

            if (j == needle.length()) {
                return i - j;
            }

            i = temp + 1;
        }

        return -1;
    }

    /**
     * 推荐答案，思路差不多，只是更简洁
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrOffical(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length())
                    return i;
                if (i + j == haystack.length())
                    return -1;
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
            }
        }
    }

    /**
     * 使用了 substring 的一种方式，本质上和前边没有差异
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrOffical2(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; ++i) {
            if (haystack.substring(i,i+l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
