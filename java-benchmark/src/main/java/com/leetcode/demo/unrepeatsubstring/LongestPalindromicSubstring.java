package com.leetcode.demo.unrepeatsubstring;

/**
 * Created by zhiyu.zhou on 2018/4/18.
 */

/**
 * M：动态规划 dp[j][i] j到i的子串是否是回文串,则长度为i-j+1
 *               true                          j=i;
 *   dp[j][i] =  str[i]==str[j]                i-j=1;
 *               str[i]==str[j]&&dp[j-1][i+1]  i-j>1;
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String str = "abbaabccba";
        System.out.println("the longestPalindromeM1:" + longestPalindrome(str));
        System.out.println("the longestPalindromeM2:" + longestPalindromeM2(str));
    }

    /**
     * M1:动态规划(O(n2)---O(n2))
     * @param str
     * @return
     */
    public static String longestPalindrome(String str) {
        if(str == null || str.length() <= 1) {
            return str;
        }
        char[] strChar = str.toCharArray();
        int length = str.length();
        boolean[][] dp = new boolean[length][length];
        int maxLen = 0;  //保存最长回文子串长度
        int start = 0;   //保存最长回文子串起点
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                dp[i][j] = false;
            }
        }
        for(int i = 0; i < length; ++i) {
            for(int j = 0; j <= i; ++j) {
                if(i - j < 2) {
                    dp[j][i] = (strChar[i] == strChar[j]);
                } else {
                    dp[j][i] = (strChar[i] == strChar[j] && dp[j+1][i-1]);
                }
                if(dp[j][i] && maxLen < i - j + 1) {
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }
        return str.substring(start, start + maxLen);
    }

    /**
     * 暴力搜素O(n3)
     * @param str
     * @return
     */
    public static String longestPalindromeM2(String str) {
        if(null == str || str.length() < 1) {
            return str;
        }
        int maxLen = 0; //记录回文串的最大长度
        int maxStart = 0;  //记录最大串的起始位置
        for(int i = 1; i < str.length(); i++) {
            //i 记录子串长度
            for(int j = 0; j < str.length() - i; j++) {
                //j 记录子串起始位置
                if(isLongestPalindrome(str, i, j) && (i + 1) > maxLen) {
                    maxLen = i + 1;
                    maxStart = j;
                }
            }
        }
        return str.substring(maxStart, maxStart + maxLen);
    }

    private static boolean isLongestPalindrome(String str, int i, int j) {
        if(null == str || str.length() <= 0) {
            return false;
        }
        int left = j;
        int right = j + i;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * M3:中心扩散
     * @param str
     * @return
     */
    String longestStr = "";
    public String longestPalindromeM3(String str) {
        if(null == str || str.length() <= 1) {
            return str;
        }
        for(int i = 0; i < str.length(); i++) {
            //奇数子串
            helper(str, i, 0);
            //子串偶数
            helper(str, i, 1);
        }
        return longestStr;
    }

    private void helper(String str, int i, int offset) {
        int left = i;
        int right = i + offset;
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        //截出当前最长子串
        String curlongestStr = str.substring(left + 1, right);
        if(curlongestStr.length() > longestStr.length()) {
            longestStr = curlongestStr;
        }
    }
}
