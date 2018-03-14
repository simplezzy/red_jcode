package com.leetcode.demo.repeatnum;

/**
 * Created by zhiyu.zhou on 2018/3/14.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 有序数组重复数字最多的数字与次数 O(n)
 * [1,2,2,3,3,3,3,3,4,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6]
 */
public class MaxCountRepeatNum {

    public Map<Integer, Integer> findMaxCountRepeatNum(int[] nums) {
        if(null == nums || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        int maxCount = 0;
        int maxNum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; ) {
            int count = 1;
            int j = i + 1;
            for(; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    ++count;
                }
                if(maxCount < count) {
                    maxCount = count;
                    maxNum = nums[i];
                }
            }
            i = j;
        }

        map.put(maxNum, maxCount);
        return map;
    }

}
