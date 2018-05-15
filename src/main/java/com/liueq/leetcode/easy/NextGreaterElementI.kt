package com.liueq.leetcode.easy

import java.util.*

/**
 * 问题描述：给定两个数组 nums1, nums2。其中 1 是 2的子集。求nums1 每一个element 在 nums2 中的位置，往后最大的数字。如果不存在，用 -1 代替。
 * 比如： [4, 1, 2]
 *       [1, 3, 4, 2]
 *       =
 *       [-1, 3, -1] 因为 4 在 nums2 中往后只有一个 2，不比4大，所以不存在；1在 nums2 中，往后第一个比 1 大的是3，所以返回3。同理。
 *
 * 解：先求出 nums2 每一个 element 往后的第一个更大的值，保存在 map 中。遍历 nums1，从 map 取出即可，如果不存在，说明是 -1
 */
class NextGreaterElementI

fun main(args: Array<String>) {
    nextGreaterElement(intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2)).apply {
        for (i in this) {
            print("$i,")
        }
        println()
    }

    nextGreaterElement(intArrayOf(2, 4), intArrayOf(1, 2, 3, 4)).apply {
        for (i in this) {
            print("$i,")
        }
        println()
    }
}

fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    var map = HashMap<Int, Int>()
    for (i in 0.until(nums2.size)) {
        var nextGreater = -1
        for (j in (i + 1).until(nums2.size)) {
            if (nums2[i] < nums2[j]) {
                nextGreater = nums2[j]
                map[nums2[i]] = nextGreater
                break
            }
        }
    }

    nums1.mapIndexed { index, value ->
        if (map[value] == null)
            nums1[index] = -1
        else
            nums1[index] = map[value]!!
    }

    return nums1
}