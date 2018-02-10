package com.leetcode.demo.summax;

/**
 * Created by zhiyu.zhou on 2018/2/10.
 */

/**
 * Q:数组中连续区间的和最大(最大和的子数组)
 *   input: array[] int
 *   output: max int
 * S：[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 * M：1、动态规划
 */
public class SubArraySumMax {

    public static void main(String[] args) {
       int[] array = {-2, 2, -3, 4, -1, 2, 1, -5, 3};
       System.out.println("online M1:" + maxSum(array));
    }

    public static int maxSum(int[] array) {
        if(array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        int max = 0;
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            if(sum < 0) {
                sum = 0;
            }
            sum += array[i];
            if(sum > max) {
                max = sum;
            }

        }
        return max;
    }
}
