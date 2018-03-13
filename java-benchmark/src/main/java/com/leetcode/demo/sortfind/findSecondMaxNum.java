package com.leetcode.demo.sortfind;

/**
 * Created by zhiyu.zhou on 2018/3/13.
 */
public class findSecondMaxNum {

    public static void main(String[] args) {

    }

    static class Solution {
        /**
         * M1: 排序找到倒数第二个数  O(nlogn)
         * M2: 设置最大max 和max_sec 遍历  O(n)
         * @param nums
         * @return
         */
        public static int findSecondMaxNum(int[] nums) {
            if(nums == null || nums.length <= 2) {
                throw new IllegalArgumentException("invalid input");
            }
            int max = nums[0];
            int max_sec = -1;
            for(int i = 1; i < nums.length; i++) {
                if(max < nums[i]) {
                    max_sec = max;
                    max = nums[i];
                }
                if(max > nums[i] && nums[i] > max_sec) {
                    max_sec = nums[i];
                }
            }
            return max_sec;
        }
    }
}
