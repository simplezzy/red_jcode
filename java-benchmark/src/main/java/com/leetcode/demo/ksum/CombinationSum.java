package com.leetcode.demo.ksum;

/**
 * Created by zhiyu.zhou on 2018/3/28.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 可重复  [2, 3, 6, 7] and target 7  ： [[7],[2, 2, 3]]
 * 不可重复：
 *
 * Q:深度优先搜索
 */
public class CombinationSum {

    List<List<Integer>> res;
    public List<List<Integer>> findCombinationSum(int[] nums, int target) {
        if(nums == null || nums.length <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        res = new LinkedList<List<Integer>>();
        List<Integer> temp = new LinkedList<>();
        //排序
        Arrays.sort(nums);
        helper(nums, target, 0, temp);
        return res;
    }

    /**
     * 可重复当前数，
     * @param nums
     * @param target
     * @param index
     * @param temp
     */
    private void helper(int[] nums, int target, int index, List<Integer> temp) {
        if(target < 0) {
            return;
        } else if(target == 0) {
            List<Integer> comb = new LinkedList<>(temp);
            res.add(comb);
        } else {
            for (int i = index; i < nums.length; i++) {
                temp.add(nums[i]);
                helper(nums, target - nums[i], i, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 不可重复取数
     * @param nums
     * @param target
     * @param index
     * @param temp
     */
    private void helper1(int[] nums, int target, int index, List<Integer> temp) {
        if(target < 0) {
            return;
        } else if(target == 0) {
            List<Integer> combOne = new LinkedList<>(temp);
            res.add(combOne);
        } else {
            for (int i = index; i < nums.length; i++) {
                temp.add(nums[i]);
                helper(nums, target - nums[i], i + 1, temp); //i+1 不重复
                temp.remove(temp.size() - 1);
                //跳过重复
                while(i < nums.length - 1 && nums[i] != nums[i + 1]) {
                    i++;
                }
            }
        }
    }
}
