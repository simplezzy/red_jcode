package com.leetcode.demo.findKth;

/**
 * Created by zhiyu.zhou on 2018/3/14.
 */
public class FindKthNum {
    /**
     * 分治 + 二分查找  O(lg(m + n))  注意数据有序的条件
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedNum(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("invalid input");
        }
        int len = nums1.length + nums2.length;
        if(len % 2 == 0) {
            return (findKth(nums1, nums2, 0, nums1.length, 0, nums2.length, len/2) +
                    findKth(nums1, nums2, 0, nums1.length, 0, nums2.length, len/2 + 1)) / 2.0f;
        } else {
            return findKth(nums1, nums2, 0, nums1.length, 0, nums2.length, len/2 + 1);
        }
    }

    private double findKth(int[] nums1, int[] nums2, int start1, int len1, int start2, int len2, int k) {
        //回归一般性
        if(len1 > len2) {
            return findKth(nums2, nums1, start2, len2, start1, len1, k);
        }
        //len1 = 0  -->
        if(len1 == 0) {
            return nums2[start2 + k - 1];
        }
        //k == 1
        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        //防止越界，以及k = 0， p = 0 无法前进的情况
        int p = Math.min(Math.max(k / 2, 1), len1);
        int q = k - p ;
        if(nums1[start1 + p - 1] > nums2[start2 + q - 1]) {
            //丢弃q段
            return findKth(nums1, nums2, start1, len1, start2 + q, len2 - q, k - q);
        } else if(nums1[start1 + p - 1] < nums2[start2 + q - 1]) {
            //丢弃p段
            return findKth(nums1, nums2, start1 + p, len1 - p, start2, len2,k - p);
        } else {
            return nums1[start1 + p - 1];
        }
    }
}
