package com.liueq.leetcode.easy;

/**
 * 问题描述：将一个 signed int 的数字转换成十六进制表示的字符串
 *
 * 解：
 * 1 对于 2^32 范围内的 signed int，先取模，得到其负数所表示的正数，然后转成16进制。
 * 2 对于计算机，int 总是被以二进制补码的形式被保存。转换成16进制，需要的只是进行2进制到16进制的转换。
 *   使用无符号右移的操作，用 15 进行按位与，每次就能得到一个16进制的位。无需考虑正负数，因为在系统中，已经被
 *   正确的表示为2进制补码。
 *
 */
public class ConvertNumberToHexadecimal {
    public static void main(String[] args){
        ConvertNumberToHexadecimal obj = new ConvertNumberToHexadecimal();
        String result = obj.toHex(8);
        System.out.println(result);
    }

    public String toHexMap(int num) {
        char[] map = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String result = "";
        while (num != 0) {
            result = map[num & 15] + result;
            num = num >> 4;
        }

        return result;
    }

    public String toHex(int num) {
        long mod;
        if (num < 0) {
            mod = Math.floorMod(num, (long) Math.pow(2, 32));
            System.out.println(mod);
        }else{
            mod = num;
        }

        StringBuffer buffer = new StringBuffer();
        while (mod != 0) {
            int remainder = (int) (mod % 16);
            String insert = String.valueOf(remainder);
            switch (remainder) {
                case 10:
                    insert = "a";
                    break;
                case 11:
                    insert = "b";
                    break;
                case 12:
                    insert = "c";
                    break;
                case 13:
                    insert = "d";
                    break;
                case 14:
                    insert = "e";
                    break;
                case 15:
                    insert = "f";
                    break;
            }
            buffer.insert(0, insert);
            mod /= 16;
        }

//        for(int i = 0, len = 8 - buffer.length(); i < len; i++) {
//            buffer.insert(0, "0");
//        }

        if (buffer.toString().equals("")) {
            return "0";
        }else {
            return buffer.toString();
        }
    }

    // 验证2^4 范围内，取模运算的正确性
    private void test1() {
        for(int i = -8; i <= 8; i++) {
            System.out.println(Math.floorMod(i, 16));
        }
    }
}
