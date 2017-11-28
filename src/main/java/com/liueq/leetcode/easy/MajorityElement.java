package com.liueq.leetcode.easy;

import java.util.HashMap;
import java.util.Random;

/**
 * 问题描述：在一个数组中，找到出现次数超过 n / 2 的成员。
 * <p>
 * 解：
 * 1 利用 HashTable，插入判断
 * 2 先排序，然后获取中间值
 * 3 利用 Random 随机获取
 */
public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement obj = new MajorityElement();
        int[] nums = {2};
        int i = obj.majorityElement2(nums);
        System.out.println(i);
    }

    /**
     * 利用Hash Table，插入时判断是否已经达到 length / 2 个数量。
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int val = map.get(nums[i]);
                if (val >= nums.length / 2) {
                    return nums[i];
                }
                map.put(nums[i], ++val);
            } else {
                map.put(nums[i], 1);
            }
        }

        return -1;
    }

    /**
     * 先排序，然后取数组最终间的值，简单判断后，就能够得出 majority element。
     * <p>
     * 该方法时间复杂度达不到要求。
     * <p>
     * 更新：其实该方法是可以的，也是标准答案之一。关键在于 majority element 的定义。必须是大于 n /2 ，无论数组长度是奇数
     * 还是偶数，length / 2 都是 majority ，就不需要更详细的逻辑了。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

        if (nums.length % 2 == 0) {
            //偶数
            int low = nums[0];
            int high = nums[nums.length - 1];
            int mid1 = nums[nums.length / 2 - 1];
            int mid2 = nums[nums.length / 2];

            if (low == mid1) {
                return low;
            } else if (mid2 == high) {
                return high;
            } else {
                return mid2;
            }
        } else {
            int mid = nums[nums.length / 2];
            return mid;
        }
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int i = low;
        int j = high + 1;
        int flag = nums[low];
        while (true) {
            while (nums[++i] <= flag) {
                if (i == high) {
                    break;
                }
            }
            while (nums[--j] >= flag) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        nums[low] = nums[j];
        nums[j] = flag;

        quickSort(nums, low, j - 1);
        quickSort(nums, j + 1, high);
    }

    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 随机数法，随机找一个 element ，然后遍历数组，判断是否是 majority element。如果不是，随机下一个。
     * <p>
     * 该算法的时间复杂度最坏是无限，但是实际应用中，应该是线性，计算方式太复杂了。
     *
     * @param nums
     * @return
     */
    public int majorityElementOfficial(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }
}
