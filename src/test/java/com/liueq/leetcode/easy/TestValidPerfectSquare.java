package com.liueq.leetcode.easy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestValidPerfectSquare {

    ValidPerfectSquare target;
    int[] testCase;
    String[][] testResult;

    @Before
    public void setUp() throws Exception {
        target = new ValidPerfectSquare();

        testCase = new int[]{
                2,
                22,
                222,
                2222,
                22222,
        };

        testResult = new String[][]{
                {"2"},
                {"22"},
                {"2", "22"},
                {"22", "22"},
                {"2", "22", "22"},
        };
    }

    @Test
    public void testConvertIntoArray() {
        for (int i = 0; i < testCase.length; i++) {
            List<String> result = target.convertIntoArray(testCase[i]);
            String[] array = result.toArray(new String[]{});

            String[] standard = testResult[i];
            for (int j = 0; j < array.length && j < standard.length; j++) {
                Assert.assertEquals(array[j], standard[j]);
            }
        }

        assert true;
    }
}
