package com.leetcode.demo;

/**
 * Created by zhiyu.zhou on 2017/10/27.
 */
public class NthPrime {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("n prime:" + GetNthPrime(10000));
        System.out.println("spent time:" + (System.currentTimeMillis() - start) + "ms");
    }

    public static int GetNthPrime(int n){
        int i = 2, j = 1;
        while (true) {
            j = j + 1;
            if (j > i / j) {
                n--;
                if (n == 0)
                    break;
                j = 1;
            }
            if (i % j == 0) {
                i++;
                j = 1;
            }
        }
        return i;
    }
}
