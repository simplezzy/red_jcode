package com.leetcode.demo.ksum;

/**
 * Created by zhiyu.zhou on 2018/3/27.
 */

import java.util.*;

/**
 * Q: input: int nums[] int target
 *    output: int[] result
 */
public class TwoSum {

    /**
     * M1: 暴力搜索 O(n2)
     * @param nums
     * @param target
     * @return
     */
    public int[] towSumM1(int[] nums, int target) {
        if(null == nums || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length - 1; j++) {
                if(nums[i] == target - nums[j]) {
                    result[0] = nums[i];
                    result[1] = nums[j];
                    break;
                }
            }
        }
        return result;
    }

    /**
     * M2:HashMap O(n) O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] towSumM2(int[] nums, int target) {
        if (null == nums || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(nums[i])) {
                int index = hashMap.get(nums[i]);
                result[0] = nums[i];
                result[1] = nums[index];
                break;
            } else {
                hashMap.put(target - nums[i], i);
            }
        }
        return result;
    }

    /**
     * 排序 + 双指针 O(nlgn)
     * @param nums
     * @param target
     * @return
     */
    public int[] towSumM3(int[] nums, int target) {
        if (null == nums || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        int[] result = new int[2];
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length -1;
        while(low < high) {
            if(nums[low] + nums[high] == target) {
                result[0] = nums[low];
                result[1] = nums[high];
                break;
            } else if (nums[low] + nums[high] > target) {
                high--;
            } else {
                low++;
            }
        }
        return result;
    }

    /**
     * 递归处理,也需要排序
     * @param nums
     * @param target
     * @return
     */
    List<List<Integer>> res;
    public int[] towSumM4(int[] nums, int target) {
        if (null == nums || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        int[] result = new int[2];
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        Arrays.sort(nums);
        helper(nums, target, 0, temp);
        return result;
    }

    private void helper(int[] nums, int target, int index, List<Integer> temp) {
        if(target < 0) {
            return;
        } else if(target == 0) {
            List<Integer> comb = new LinkedList<>(temp);
            if(comb.size() == 2) {
                res.add(comb);
            }
        } else {
            for(int i = index; i < nums.length; i++) {
                temp.add(nums[i]);
                helper(nums, target - nums[i], i + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
