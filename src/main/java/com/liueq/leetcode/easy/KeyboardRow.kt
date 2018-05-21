package com.liueq.leetcode.easy

import java.util.*

/**
 * 问题描述：将 a ~ z 按照键盘上的3行进行分类。只有当一个单词中所有字母都来自同一行，那么该单词被认为是 one line。求一个字符串数组中，这样的 one line word。
 *
 * 解：先将所有字母按照3行分为3类，然后存入 map 中，使用 value 分别是 1， 2， 3. 然后遍历字符串数组，每一个字符串中，依次从 map 中取出，如果都是同一个数字，满足条件。
 */
class KeyboardRow

fun main(args: Array<String>) {
    val array = arrayOf("Hello", "Alaska", "Dad", "Peace")
    for (str in findWords(array)) {
        println(str)
    }
}

fun findWords(words: Array<String>): Array<String> {
    val array1 = "qwertyuiopQWERTYUIOP".toCharArray()
    val array2 = "ASDFGHJKLasdfghjkl".toCharArray()
    val array3 = "ZXCVBNMzxcvbnm".toCharArray()

    var map = HashMap<Char, Int>()
    for (c in array1) {
        map[c] = 1
    }
    for (c in array2) {
        map[c] = 2
    }
    for (c in array3) {
        map[c] = 3
    }

    var array = mutableListOf<String>()

    for (str in words) {
        var oneline = true
        val first = map[str[0]]
        for (i in 1.until(str.length)) {
            if (map[str[i]] != first)
                oneline = false
        }

        if (oneline) {
            array.add(str)
        }
    }


    return array.toTypedArray()
}