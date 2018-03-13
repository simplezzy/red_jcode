package com.leetcode.demo.missingnum;

import java.util.Arrays;

/**
 * Created by zhiyu.zhou on 2018/3/12.
 */
public class missingNumber {

    class Solution {
        /**
         * M1:求和思路 O(n) ---O(1)
         * @param nums
         * @return
         */
        public int missingNumber(int[] nums) {
            if(null == nums) {
                throw new IllegalArgumentException("invalid input");
            }
            int size = nums.length;
            if(size < 1) {
                return 0;
            }
            int sum = 0;
            for(int i = 0; i < size; i++) {
                sum += nums[i];
            }
            return size*(size + 1)/2 - sum;
        }

        /**
         * M2:空间标记法 O(n) -- O(n)
         * @param nums
         * @return
         */
        public int missingNumberByMark(int[] nums) {
            if(null == nums) {
                throw new IllegalArgumentException("invalid input");
            }
            int size = nums.length;
            if(size < 1) {
                return 0;
            }
            boolean[] mark = new boolean[size + 1];
            for(int i = 0; i < mark.length; i++) {
                mark[i] = false;
            }
            for(int i = 0; i < size; i++) {
                mark[nums[i]] = true;
            }
            int i = 0;
            for( ; i < mark.length; i++) {
                if(!mark[i]) {
                    return i;
                }
            }
            return i;
        }

        /**
         * M3.先排序后对应  O(nlgn)  --- O(1)
         *    效率优化：多线程并发排序
         * @param nums
         * @return
         */
        public int missingNumberBySort(int[] nums) {
            if(nums == null) {
                throw new IllegalArgumentException("invalid input");
            }
            int size = nums.length;
            if(size < 1) {
                return 0;
            }
            Arrays.sort(nums);
            for(int i = 0; i < size; i++) {
                if(nums[i] != i) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * M4:异或处理法  O(n)  --- O(1)
         *    两个相同的数异或为0，将数组中数据和0到n异或，剩下的数一定为那个未出现的数
         * @param nums
         * @return
         */
        public int missingNumberByNOR(int[] nums) {
            if(nums == null) {
                throw new IllegalArgumentException("invalid input");
            }
            int size = nums.length;
            if(size < 1) {
                return 0;
            }

            int n = 0;
            for (int i = 0; i < size; i++) {
                n = n^(i + 1)^nums[i];
            }
            return n;
        }

        /**
         * M5:先排序，后二分查找  O(nlogn)  --- O(1)
         * @param nums
         * @return
         */
        public int missingNumberBySortFind(int[] nums) {
            if(nums == null) {
                throw new IllegalArgumentException("invalid input");
            }
            int size = nums.length;
            if(size < 1) {
                return 0;
            }
            Arrays.sort(nums);
            int low = 0;
            int high = size;
            int mid;
            while(low < high) {
                mid = low + ((high - low) >> 1);
                if(nums[mid] - nums[0] == mid) {
                    low = mid + 1;
                } else if(nums[mid] - nums[0] > mid) {
                    high = mid;
                }
            }
            return high + nums[0];
        }
    }
}
