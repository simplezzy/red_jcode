package com.leetcode.demo.summax;

/**
 * Created by zhiyu.zhou on 2018/2/10.
 */

/**
 * Q:数组中连续区间的和最大(最大和的子数组)
 *   input: array[] int
 *   output: max int
 * S：[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 * M：1、暴力方法（O(n3), O(1)）--->优化(O(n2), O(1))
 *
 *    2、动态规划
 */
public class SubArraySumMax {

    public static void main(String[] args) {
       int[] array = {-2, 2, -3, 4, -1, 2, 1, -5, 3};
       System.out.println("online M1:" + maxSum(array));
    }

    /**
     * 暴力方法:求出array[i]-array[j]之间的元素和，维护max
     * @param array
     * @return
     */
    public static int maxSum1(int[] array) {
        if(array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        int max = 0;
        int length = array.length;
        int curSum;
        for(int i = 0; i <length; i++) {
            for(int j = i;j < length; j++) {
                curSum = 0;
                for(int k = i; k <= j; k++) {
                    curSum += array[k];
                    if(curSum > max) {
                        max = curSum;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 优化求和
     * @param array
     * @return
     */
    public static int maxSum2(int[] array) {
        if(array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        int max = 0;
        int length = array.length;
        int curSum;
        for(int i = 0; i <length; i++) {
            curSum = 0;
            for (int j = i; j < length; j++) {
                curSum += array[j];
                if(curSum > max) {
                    max = curSum;
                }
            }
        }
        return max;
    }

    /**
     * 动态规划max = {sum[i-1],sum[i-1] + array[i]}
     * @param array
     * @return
     */
    public static int maxSum(int[] array) {
        if(array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        int max = 0;
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            if(sum < 0) {  //将子串和为负数的子串丢掉
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
