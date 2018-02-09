package com.leetcode.demo;

/**
 * Created by zhiyu.zhou on 2017/10/24.
 */
public class Max {

 /*   // find the prime  between 1 to 1000;
    public static void main(String[] args) {
        printPrime(20171024);
    }
    public static void  printPrime(int n){

        for(int i = 2; i < n ; i++){

            int count = 0;

            for(int j = 2 ; j<=i; j++){

                if(i%j==0){
                    count++;
                }
                if(j==i & count == 1){
                    System.out.print(i+" ");
                }
                if(count > 1){
                    break;
                }
            }


        }

    }*/

    public static void main(String[] args){
        int n = 20171024;
        int tmp = 0;
        for (; n > 1; n--) {
            for(int i=2; i<n; i++){
                if(n % i == 0){
                    tmp = i;
                    break;
                }
            }

            if(tmp >2 && tmp <=n){
                System.out.println("最大的素数是: " + n);
                return;
            }
        }

    }

}
