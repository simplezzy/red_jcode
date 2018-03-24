package com.leetcode.demo.sortfind;

/**
 * Created by zhiyu.zhou on 2018/3/23.
 */

/**
 * Q:在N个乱序的数组中找第K大的数
 * S: 利用快排的思想，从数组arr中随机找出一个元素X，把数组分成两部分arr_a和arr_b。 O(n)
 *    arr_a中的元素比x大，arr_b中的元素比x小。
 *    1.arr_a中的元素小于K，则arr_b中第k-arr_a.length个元素即为第K大数。
      2.arr_a中的元素大于等于K，则返回arr_a中第K大数
 */
public class FindKthMaxNum {

    public static void main(String[] args) {
        int[] arr={3,1,2,5,4,7,6};
        System.out.println("Kth num:" + findKthNum(arr, 3, 0, arr.length - 1));
    }

    public static int partition(int[] arr, int low, int high) {
        int temp = arr[low];
        while(low < high) {
            while (arr[high] <= temp && high > low) {
                high--;
            }
            arr[low] = arr[high];
            while (arr[low] >= temp && high > low) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = temp;
        return high;
    }

    public static int findKthNum(int[] arr, int k, int low, int high) {
        int temp = partition(arr, low, high);
        if(temp == k - 1) {
            return arr[temp];
        } else if (temp > k - 1) {
            return findKthNum(arr, k, low, temp - 1);
        } else {
            return findKthNum(arr, k - temp, temp + 1, high);
        }
    }
}
