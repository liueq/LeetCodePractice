package com.liueq.leetcode.easy;

/**
 * 问题描述：压缩字符串，将相邻相同字符用数字追加在后边，如果一个字符，那么不追加1.（需要原地解决）
 * exp:
 * aabbc -> a2b2c
 * abc -> abc
 * aaaaaaa -> a7
 * <p>
 * 解：需要思考一下几个点：
 * 1. 压缩后的字符串长度肯定是小于等于原字符串
 * 2. 由于要求 in-place，所以肯定是要多个指针 read, write
 * 3. 除了 read, write ，还需要一个指针 anchor 来标记临界点，即当一个新的字符串出现的位置
 * 4. read 总是往前走，当遇到临界点，那么计算 read - anchor + 1 就是 anchor 所在相同字符子串的长度，进而压缩
 * 5. 还需要注意当 read 走到字符串尾巴时，也是一个临界点。
 */
public class StringCompression {
    public static void main(String[] args) {
        StringCompression obj = new StringCompression();
        char[] case1 = "aabbccc".toCharArray();
        char[] case2 = "a".toCharArray();
        char[] case3 = "abbbbbbbbbbbb".toCharArray();
        char[] case4 = "aaabbcc".toCharArray();

        int result1 = obj.compressOfficial(case1);
        int result2 = obj.compressOfficial(case2);
        int result3 = obj.compressOfficial(case3);
        int result4 = obj.compressOfficial(case4);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }

    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        if (chars.length == 1) {
            return 1;
        }

        int i = 0, j = 0, flag = 0;

        for (; j < chars.length; j++) {
            if (j == chars.length - 1) {
                String diff = String.valueOf(j - flag + 1); // 注意这里计算的是 flag -> j 的距离，flag 是每次相邻两个字符不同的靠前一个位置
                chars[i++] = chars[j]; // 不管是否需要 append 数字，chars[i] 都需要赋值为 chars[j]

                // 只有当 diff 不等于1时，才append 数字
                if (!"1".equals(diff)) {
                    for (int k = 0; k < diff.length(); k++) {
                        chars[i + k] = diff.charAt(k);
                    }

                    // append 数值后，i 需要移动到该数字的后一个位置
                    i = i + diff.length();
                }
                break;
            } else if (chars[j] != chars[j + 1]) {
                String diff = String.valueOf(j - flag + 1);
                chars[i++] = chars[j];
                if (!"1".equals(diff)) {
                    for (int k = 0; k < diff.length(); k++) {
                        chars[i + k] = diff.charAt(k);
                    }
                    i = i + diff.length();
                }

                flag = j + 1; // 每次需要前后字符不同时，更新 flag
            }
        }

        System.out.println(String.valueOf(chars));
        return i;
    }

    public int compressOfficial(char[] chars) {
        int write = 0, anchor = 0;
        for (int read = 0; read < chars.length; read++) {
            // 只有满足这两个条件，才会让 anchor, write 移动
            if (read == chars.length - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[anchor];
                // 当 read > anchor 时，才会是需要追加数字的情况
                // 如果只有一个字符，那么 read == anchor
                if (read > anchor) {
                    for (char c : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }

        return write;
    }
}
