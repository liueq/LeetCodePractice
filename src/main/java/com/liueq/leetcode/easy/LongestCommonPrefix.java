package com.liueq.leetcode.easy;

/**
 * 问题描述：从字符串数组中，找到最长的共同前缀子串
 *
 * 有4种基本解决方法：
 * 1 水平搜索，假设最小字符串为最长子串，在字符串数组种比较，直到满足条件，不满足的时候，假想最小字符串长度减一。
 * 2 垂直搜索，和1类似，只是从最小子串开始，在和数组比较的过程中，慢慢增加，直到不满足子串为止。
 * 3 分而治之，将数组分为前后部分，然后找出各自的最大子串，类似于归并排序
 * 4 二分查找，先找出最短的字符串，然后对该字符串进行二分，如果前半部分是子串，那么继续对后半部分进行二分，如果前半部分不是子串，那么对前半部分进行二分。直到找到最大子串。
 *
 * 另外，还有建立 Trie 的方法，使用树的结构，将数组构建为一个树，子串即为从上至下共同的节点，如果开始有不同，那么树就开始产生分支。
 * 直到分支为止，就是最长的子串。由于要先构建树，这里就不做具体实现了。
 *
 */
public class LongestCommonPrefix {

    public static void main(String[] args){
        String[] input = {"123456", "1234", "1234", "12368y9"};
        LongestCommonPrefix test = new LongestCommonPrefix();
        String output = test.longestCommonPrefix4(input);
        System.out.println(output);
    }

    /**
     * 自己想出来的答案
     *
     * 1 从数组中取第一个字符串作为基准字符串
     * 2 外循环，从基准字符串中不断地取出prefix 子串来和数组中剩下字符串进行匹配，看是否满足共同的 prefix
     * 3 当不满足的时候，认为上一个 prefix 就是满足所有字符串的最大 prefix
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }

        String first = strs[0];
        char[] chars = first.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            for (String str : strs) {
                if(str.length() - 1  < i){
                    return buffer.toString();
                }

                if(str.charAt(i) != cur){
                    return buffer.toString();
                }
            }

            buffer.append(cur);
        }

        return buffer.toString();
    }

    /**
     * 官方解答：Horizontal scanning
     *
     * 1 将第一个字符串假设是最大的子串
     * 2 循环数组，使用 indexOf 来判断该 prefix 是否存在于每一个字符串中，当不存在， prefix 长度减一，直到满足为止
     *
     * 此方法和上边自己想的原理是一样的，只是说这里是从最大子串慢慢缩小范围，而我的方法是从最小子串慢慢扩大范围
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs){
        if(strs.length == 0){
            return "";
        }

        String prefix = strs[0];
        for(int i = 1; i < strs.length; i ++){
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()){
                    return "";
                }
            }
        }

        return prefix;
    }

    /**
     * 官方解答: Vertical scanning
     *
     * 实际上和我自己的答案思路一样
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs){
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * 官方解答: Divide and conquer 分而治之
     *
     * 类似归并排序，将数组分为前后两部分，然后分别递归地找出每一部分地最大子串
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }

        return longestCommonPrefix3(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix3(String[] strs, int l, int r){
        if(l == r){
            return strs[l];
        }else{
            int mid = (l + r) / 2;
            String left = longestCommonPrefix3(strs, l, mid);
            String right = longestCommonPrefix3(strs, mid + 1, r);
            return commonPrefix(left, right);
        }
    }

    private String commonPrefix(String left, String right){
        int length = left.length() < right.length() ? left.length() : right.length();
        for(int i = 0; i < length; i++){
            if(left.charAt(i) != right.charAt(i)){
                return left.substring(0, i);
            }
        }

        return left.substring(0, length - 1);
    }

    /**
     * 官方解答：Binary search 二分查找
     *
     * 1 先找出一个长度最短的字符串，最长子串必定小于等于该字符串
     * 2 对该最短字符串进行二分，判断前半部分时候是通用子串，如果是，那么继续对后半部分进行二分，因为要找最长子串
     *   如果前半部分不是子串，那么对前半部分进行二分。
     * 3 循环2，直到前后只剩一个字符那么该字符（可能包括该字符）之前的字符串就是最长子串。
     * @param strs
     * @return
     */
    public String longestCommonPrefix4(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        String minStr = strs[0];
        for(String s : strs){
            if(minStr.length() > s.length()){
                minStr = s;
            }
        }

        int low = 1; //排除了0 的情况
        int high = minStr.length();

        while(low <= high){
            int mid = (low + high) / 2;
            if(isCommonPrefix(strs, mid)){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return minStr.substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String prefix = strs[0].substring(0, len);
        for(String s : strs){
            if(!s.startsWith(prefix)){
                return false;
            }
        }

        return true;
    }
}
