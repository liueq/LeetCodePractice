package com.liueq.leetcode.easy;

/**
 * 问题描述：使用一个数组来表示数字，然后进行+1
 *
 * 解：主要考虑进位情况，从最后开始向前，不需要进位为终止条件
 */
public class PlusOne {

    public static void main(String[] args){

        PlusOne po = new PlusOne();
        int[] testCase = {9};
        int[] sum = po.plusOne(testCase);
        for(int i : sum){
            System.out.println(i);
        }
    }

    public int[] plusOne(int[] digits){
        boolean plus = true;
        for(int i = digits.length - 1; i >= 0; i--){
            if(plus){
                int sum = digits[i] + 1;
                if(sum > 9){
                    digits[i] = 0;
                    plus = true;
                }else{
                    digits[i] += 1;
                    plus = false;
                }
            }else {
                break;
            }
        }

        if(plus){
            int[] extDigits = new int[digits.length + 1];
            extDigits[0] = 1;
            for(int i = 0; i < digits.length; i++){
                extDigits[i + 1] = digits[i];
            }

            return extDigits;
        }

        return digits;
    }

    public int[] plusOneOfficial(int[] digits) {

        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
}
