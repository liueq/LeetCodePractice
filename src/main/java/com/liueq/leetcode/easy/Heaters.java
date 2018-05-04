package com.liueq.leetcode.easy;

import java.util.Arrays;

/**
 * 问题描述：给定 houses[] 和 heaters[]，找出一个最小值，使得所有的 houses 至少被一个 heaters 所覆盖。
 * [1, 2, 3] [2] ，radius = 1，即可覆盖 1,2,3
 * 注意 houese 顺序是递增的， 但是可以重复，而 heaters 顺序不定。
 * 当heater 和 house 重合时，所需要的 radius = 0
 * <p>
 * 解：二分查找，对每一个 house，找到其最近的 heater，然后计算距离。最后统计出最大距离即可。
 * 由于不是典型的二分查找，查找的数字不一定在数组中，所以需要稍微修改以便找出最接近的数字
 */
public class Heaters {
    public static void main(String[] args) {
        Heaters obj = new Heaters();
        int[] houses1 = {1, 2, 3};
        int[] heaters1 = {2};
        int[] houses2 = {1, 2, 3, 4};
        int[] heaters2 = {1, 4};
        int[] houses3 = {1};
        int[] heaters3 = {1, 2, 3, 4};
        int[] houses4 = {1, 1, 1, 1, 1, 1, 999, 999, 999, 999};
        int[] heaters4 = {499, 500, 501};
        int[] houses5 = {1, 2, 3, 5, 15};
        int[] heaters5 = {2, 30};
        int[] houses6 = {282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923};
        int[] heaters6 = {823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612};

        System.out.println(obj.findRadiusBinary(houses1, heaters1));
        System.out.println(obj.findRadiusBinary(houses2, heaters2));
        System.out.println(obj.findRadiusBinary(houses3, heaters3));
        System.out.println(obj.findRadiusBinary(houses4, heaters4));
        System.out.println(obj.findRadiusBinary(houses5, heaters5));
        System.out.println(obj.findRadiusBinary(houses6, heaters6));

    }

    /*
        此方法理论上正确，但是时间复杂度在某些情况下不满足，而且逻辑过于复杂
     */
    public int findRadius(int[] houses, int[] heaters) {
        int minR = 0;
        for (int i = 0, j = 0; i < houses.length; ) {
            int r = Math.abs(houses[i] - heaters[j]);
            if (r <= minR) {
                // 当前 i 能够被 j 覆盖，不需要增加 minR
                i++;
            } else {
                // i 无法被 j 覆盖时，从 j + k 中尝试找出比 j 更好的位置，以该位置作为新的基点进行覆盖，至少可以覆盖 i
                int k = 1;
                int localMin = r;
                int localMinJ = j;
                while (j + k < heaters.length) {
                    int rr = Math.abs(houses[i] - heaters[j + k]);
                    if (rr <= localMin) {
                        localMin = rr;
                        localMinJ = j + k;
                    } else {
                        // 已经找不到比 localMinJ 更近的 j 了
                        break;
                    }
                    k++;
                }

                if (localMin <= minR) {
                    // j 无法覆盖 i，而 localMinJ 可以覆盖，那么不需要增加 minR，localMinJ 变成了最合适的 j
                    i++;
                    j = localMinJ;
                    continue;
                }

                // j 和 j + 1 都无法覆盖时,只能增加 minR 了
                minR++;
            }
        }

        return minR;
    }

    public int findRadiusBinary(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int minR = Integer.MIN_VALUE;
        for (int house : houses) {
            int r = binarySearch(house, heaters);
            if (r > minR) {
                minR = r;
            }
        }
        return minR;
    }

    private int binarySearch(int target, int[] heaters) {
        int low = 0, high = heaters.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (heaters[mid] == target) {
                return 0;
            } else if (heaters[mid] > target) {
                high = mid - 1;
            } else if (heaters[mid] < target) {
                low = mid + 1;
            }
        }

        int r = Math.abs(heaters[mid] - target);
        int r1 = Integer.MAX_VALUE;

        // mid 前后都可能是距离 target 最近，所以需要分别处理
        if (high < mid && high >= 0) {
            // 当 high < mid ，说明 target 介于 high 和 mid 之间，判断 high 和 mid 谁更近
            r1 = Math.abs(heaters[high] - target);
        } else if (low > mid && low < heaters.length) {
            // 同理，判断 low 和 mid 谁更近
            r1 = Math.abs(heaters[low] - target);
        }

        return Math.min(r, r1);
    }

    public static int traditionalBinarySearch(int[] array, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < target) {
                low = mid + 1;
            } else if (array[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}

