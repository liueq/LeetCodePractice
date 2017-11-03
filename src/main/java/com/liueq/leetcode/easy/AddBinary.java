package com.liueq.leetcode.easy;

/**
 * 问题描述：将两个二进制数字的字符串相加，输出一个二进制字符串结果。
 *
 * 解：按位相加，一些小技巧可以精简代码。
 */
public class AddBinary {

    public static void main(String[] args){
        AddBinary obj = new AddBinary();
        String a = "0";
        String b = "0";
        System.out.println(obj.addBinary(a, b));
    }

    /**
     * 思路是从后向前遍历，两个 char 转位 int 后相加 再转 char，记录下是否进位。
     *
     * 写的太复杂了。
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b){
        char[] charsA, charsB;
        if(a.length() > b.length()){
            charsA = a.toCharArray();
            charsB = b.toCharArray();
        }else{
            charsA = b.toCharArray();
            charsB = a.toCharArray();
        }

        int div = charsA.length - charsB.length;
        boolean plus = false;

        for(int i = charsA.length - 1; i >= 0; i--){
            int x, y;
            x = Character.getNumericValue(charsA[i]);
            if(i - div >=0){
                y = Character.getNumericValue(charsB[i - div]);
            }else{
                y = 0;
            }

            int z = x + y;

            if(plus){
                z++;
            }
            if(z == 3){
               plus = true;
               charsA[i] = '1';
            }else if(z == 2){
                plus = true;
                charsA[i] = '0';
            }else{
                plus = false;
                charsA[i] = String.valueOf(z).charAt(0);
            }
        }

        if(plus){
            return "1" + String.valueOf(charsA);
        }else{
            return String.valueOf(charsA);
        }
    }

    /**
     * 基本思想还是各个位相加，巧妙的地方在于，直接用 char - '0'，得到的差值就是对应的数字。
     * 使用除法得到是否需要进位，使用取余得到该位上应该保留的数字。
     * @param a
     * @param b
     * @return
     */
    public String addBinaryOfficial(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry; //进位部分
            if (j >= 0)
                sum += b.charAt(j--) - '0'; //直接用 char 减， '1' - '0' = 1，这点是不变的。
            if (i >= 0)
                sum += a.charAt(i--) - '0';
            sb.append(sum % 2);//得到留在该位上的数组
            carry = sum / 2;//得到是否进位
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }
}
