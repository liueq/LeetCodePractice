package com.liueq.leetcode.easy

class MaxConsecutiveOnes

/**
 * 问题描述：在一个只包含0，1的数组中，找出相邻1最大的长度。
 *
 * 解：直接遍历，遇到1累加，遇到0清零，每次累加判断是否大于max，最后返回max。
 */
fun main(args: Array<String>) {
    findMaxConsecutiveOnes(intArrayOf(1, 1, 0, 1, 1, 1)).apply { print(this) }
    findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1)).apply { print(this) }
    findMaxConsecutiveOnes(intArrayOf(0, 1)).apply { print(this) }
}

fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var max = 0
    var counter = 0
    for (i in 0 until nums.size) {
        if (nums[i] == 0) {
            counter = 0
        } else {
            counter++
        }
        max = if (counter > max) counter else max
    }
    return max
}