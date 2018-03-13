package com.leetcode.demo.missingnum;
import static java.lang.Math.abs;

/**
 * Created by zhiyu.zhou on 2018/3/8.
 */
public class MissingNumbers {

    public static void main(String[] args) {
        int array[] = {1,2,3,5};
        System.out.println(findMissingNumbers(array));
    }

    public static int findMissingNumbers(int[] array) {
        if(array == null || array.length <= 1) {
            throw new IllegalArgumentException("invalid input!");
        }

        int missedNum = 0;
        for(int i = 0; i < array.length; i++) {
            int num = array[abs(array[i]) - 1];
            if( num > 0) {
                array[abs(array[i]) - 1] = -num;
            }
        }

        for(int i = 0; i < array.length; i++) {
            if(array[i] > 0) {
                missedNum = array[i];
            }
        }
        return missedNum;
    }
}
