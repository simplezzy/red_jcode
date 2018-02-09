package com.redcat.demo;

/**
 * Created by zhiyu.zhou on 2018/2/1.
 */

import java.util.Arrays;

/**
 * 进行二分查找的前提:保证数组有序
 */
public class BinarySearch {

    public static void main(String[] args) {
       int[] arr = {8,4,2,11,9};
       Arrays.sort(arr);
       int result = binarySearch(3, arr);
       System.out.println("result:" + result);
    }

    public static int binarySearch(int key, int nums[]) {
        if(nums.length <= 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        if (key < nums[0] || key > nums[nums.length - 1]) {
            return -1;
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if(key == nums[mid]) {
                return mid;
            } else if (key < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
