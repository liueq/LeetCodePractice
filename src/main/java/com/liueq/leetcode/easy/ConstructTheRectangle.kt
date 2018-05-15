package com.liueq.leetcode.easy

/**
 * 问题描述：给出一个乘积 area，求两个因子 l, w 。保证 l >= w，且 l 和 w 尽可能接近
 *
 * 解：l 从 [area, 1] 开始遍历，直到 l >= w 满足的最后一个，就是 lw 最接近的时候。
 */
class ConstructTheRectangle

fun main(args: Array<String>) {
    constructRectangle(4).apply { println("${this[0]}, ${this[1]}") }
    constructRectangle(7).apply { println("${this[0]}, ${this[1]}") }
}

fun constructRectangle(area: Int): IntArray {
    var l: Int = area
    var w: Int = 1
    var fit: IntArray = intArrayOf(l, w)
    while (l >= 1) {
        if (area % l == 0) {
            w = area / l
            if (l >= w) {
                fit[0] = l
                fit[1] = w
            }
        }
        l--
    }

    return fit
}