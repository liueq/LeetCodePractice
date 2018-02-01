package com.liueq.leetcode.easy;

import java.util.*;

/**
 * 问题描述：使用二进制来表示0:00 ~ 11:59 区间的时间。给出一个 n 代表二进制时间中1的个数，求出其可能对应的所有时间
 *
 * 解：
 * 1 先穷举出所有n对应的时间，并保存在一个 map 中，查询时只要搜索即可。
 * 2 分别计算小时和分钟的bit == 1 加起来的个数，当等于n时，把时间添加到列表。本质也是穷举。
 */
public class BinaryWatch {
    public static void main(String[] args) {
        BinaryWatch obj = new BinaryWatch();
        obj.init();
//        List<String> list = obj.readBinaryWatch(1);
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
    }

    public List<String> readBinaryWatchEasy(int num) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 60; j++) {
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    list.add(formatHour(i) + ":" + formatMin(j));
                }
            }
        }

        return list;
    }

    public List<String> readBinaryWatch(int num) {
        return mAllTimes.get(num);
    }

    private void init() {
        for (int hour = 0; hour < 12; hour++) {
            for (int min = 0; min < 60; min++) {
                int bHours = binaryHour(hour);
                int bMins = binaryMin(min);

                int totalPoint = bHours + bMins;
                String formatedTime = formatHour(hour) + ":" + formatMin(min);
//                System.out.println("格式化后的时间 = " + formatedTime);
                if (mAllTimes.keySet().contains(totalPoint)) {
                    mAllTimes.get(totalPoint).add(formatedTime);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(formatedTime);
                    mAllTimes.put(totalPoint, list);
                }
            }
        }

        System.out.println("n 的所有可能性 = " + mAllTimes.keySet().size());
        Iterator<Integer> iter = mAllTimes.keySet().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    private int binaryHour(int hour) {
        return binaryCount(hour);
    }

    private int binaryMin(int min) {
        return binaryCount(min);
    }

    private int binaryCount(int num) {
        String binaryString = Integer.toBinaryString(num);
        int count = 0;
        for(int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    private String formatHour(int hour) {
        return String.valueOf(hour);
    }

    private String formatMin(int min) {
        String str = String.valueOf(min);
        if (str.length() == 1) {
            str = "0" + str;
        }

        return str;
    }

    private Map<Integer, List<String>> mAllTimes = new HashMap<>();
}
