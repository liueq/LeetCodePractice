package com.liueq.leetcode.easy

/**
 * 问题描述：将一个由字母，数字，短横线组成的字符串，转换成每组 k 个字符，以短横线分割的字符串。第一个字符串可以不满足4个，短横线不能在开头。
 *
 * 解：使用一个 StringBuffer，不断从头插入，每第 K 个就插入一个短横线。
 */

class LicenseKeyFormatting

fun main(args: Array<String>) {
    var s1 = "5F3Z-2e-9-w"
    var s2 = "2-5g-3-J"
    licenseKeyFormating(s1, 4).apply { print(this) }
    println()
    licenseKeyFormating(s2, 2).apply { print(this) }
}

fun licenseKeyFormating(S: String, K: Int): String? {
    var S = S.toUpperCase()
    var buffer = StringBuffer()
    var counter = 0
    for (i in (S.length - 1).downTo(0)) {
        if (S[i] != '-') {
            if (counter == K) {
                buffer.insert(0, '-')
                counter = 0
            }
            buffer.insert(0, S[i])
            counter++
        }
    }

    return buffer.toString()
}