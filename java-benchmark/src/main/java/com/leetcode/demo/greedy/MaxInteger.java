package com.leetcode.demo.greedy;

/**
 * Created by zhiyu.zhou on 2018/3/15.
 */

/**
 * Q:设有n个正整数，将它们连接成一排，组成一个最大的多位整数
 * S:n=3时，3个整数13，312，343，连成的最大整数为34331213。
 * A:把整数转换成字符串，然后在比较a+b和b+a，如果a+b>=b+a，就把a排在b的前面，反之则把a排在b的后面
 */
public class MaxInteger {

    class Solution {
        public String maxNum(int[] nums) {
            if(nums == null || nums.length <= 0) {
                throw new IllegalArgumentException("invalid input");
            }
            String result = "";
            for(int i = 0; i < nums.length; i++) {
                String num1 = nums[i] + "";
                for(int j = i + 1; j < nums.length; j++) {
                    String num2 = nums[j] + "";
                    if((num1 + num2).compareTo((num2 + num1)) < 0) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                    }
                }
            }
            for (int i = 0; i < nums.length; i++) {
                result += nums[i];
            }
            return result;
        }
    }

}
