package com.liueq.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题描述：计算帕斯卡三角第k(n)次方层的数组。
 * <p>
 * 解：该问题的难点在于不能使用额外空间，需要在O(k)的空间复杂度内完成。
 * 下面提供了3中解决方法，主要思路是一致的：
 * <p>
 * 1, 3, 3, 1
 * , 1, 3, 3, 1
 * 1, 4, 6, 4, 1
 * <p>
 * 不同的方法对于如何能够不使用额外空间各有技巧：
 * 1 使用头尾，两个元素作为交换空间，使得 j = j + j-1 得以实现。
 * 2 每次从数组前添加元素[1]，绕过 j = j + j-1 转为 j = j + j+1，从而无需考虑 j-1 无法保存的问题。
 * 3 从数组最后开始处理，虽然使用了 j = j + j-1，但是当前的j无需保存，所以不需要额外交换空间。
 */
public class PascalTriangle2 {
    public static void main(String[] args) {

    }

    /**
     * 自己想出来的，较为繁琐，并且没有利用到 List 这个条件
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        Integer[] array = new Integer[rowIndex];

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    array[0] = 1;
                } else if (j == i - 1) {
                    array[j] = 1;
                } else {
                    int firstTemp = 0; // firstTemp 保存的是 j - 1
                    int lastTemp = rowIndex - 1; // lastTemp 保存的是当前的j，因为 j 计算过后，会改变，无法直接复制给 firstTemp，所以需要中转一下。
                    array[lastTemp] = array[j];
                    array[j] = array[j] + array[firstTemp];
                    array[firstTemp] = array[lastTemp];
                }
            }
        }

        array[0] = 1;
        array[rowIndex - 1] = 1;
        return Arrays.asList(array);
    }

    /**
     * 非常巧妙的方法，外层循环，每次从 list 头添加一个 [1] 的元素。从而将 j = j + j-1改为了 j = j + j+1 的形式。
     * 由于不需要使用 j-1，也不存在 j 在赋值之后，下一次循环无法获得 j-1的问题。
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowOfficial1(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if (rowIndex < 0) {
            return list;
        }

        for (int i = 0; i <= rowIndex; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }

        return list;
    }

    /**
     * 同样巧妙，从后向前，及时当前 j 被覆盖，由于下一次循环不需要上一次循环的j，所以也可以达到不用额外空间置换。
     * <p>
     * 1, 3, 3, 1
     * , 1, 3, 3, 1
     * 1, 4, 6, 4, 1
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowOfficial2(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            list.add(1); //外循环从最后增加1
            for (int j = list.size() - 2; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }

        return list;
    }
}
