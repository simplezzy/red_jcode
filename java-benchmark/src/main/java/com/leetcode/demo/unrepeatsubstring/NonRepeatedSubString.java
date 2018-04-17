package com.leetcode.demo.unrepeatsubstring;

/**
 * Created by zhiyu.zhou on 2018/4/17.
 */
public class NonRepeatedSubString {

    public static void main(String[] args) {
        String input = "asdfggrbbnrnnacdfdddwesffghjkl";
        System.out.println("subString:" + findNonRepeatedSubString(input));
    }

    public static String findNonRepeatedSubString(String str) {
        if(null == str) {
            return null;
        }

        char[] charArray = str.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i < 256; i++) {
            map[i] = -1;
        }

        int start = -1;
        int updatedStart = start;
        int maxLen = 0;
        for(int i = 0; i < charArray.length; i++) {
            if(map[charArray[i]] > start) {
                start = map[charArray[i]];
            }
            if(i - start > maxLen) {
                maxLen = i - start;
                updatedStart = start;
            }
            map[charArray[i]] = i;
        }

        return str.substring(updatedStart + 1, updatedStart + maxLen + 1);
    }
}
