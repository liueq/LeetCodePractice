package com.liueq.leetcode.easy;

import org.junit.Test;

public class TestBinarySearch {

    @Test
    public void testBinary() {
        int[] array = {1, 2, 3};
        int index = Heaters.traditionalBinarySearch(array, 0, array.length - 1, 3);
        assert index == 2;
    }
}
