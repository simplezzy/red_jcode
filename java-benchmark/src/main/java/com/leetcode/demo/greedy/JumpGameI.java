package com.leetcode.demo.greedy;

/**
 * Created by zhiyu.zhou on 2018/3/1.
 */

/**
 * Q:给定一整数数组，数组元素表示每次可以跳跃的最大距离。然后初始位置在数组的第一个元素，判断能否跳到最后位置。
 *   A = [2,3,1,1,4], return true.
 *   B = [3,2,1,0,4], return false
 * S: 贪心,只需要时刻计算前位置和当前位置所能跳的最远长度,并始终和n作比较就可以:
 *    若在任意pos位置出现maxstep为0的情况,则说明无法继续向前移动,返回false
 *    若在任意pos位置出现maxstep+pos>=n说明可以完成最后一跳,返回true
 */
public class JumpGameI {
    public static void main(String[] args) {

        int a[] = {2,3,1,1,4};

        int b[] = {2,3,1,0,4};
        System.out.println("can finish jump:" + canJump(a, a.length));
        System.out.println("can finish jump:" + canJump(b, b.length));
    }

    public static boolean canJump(int array[], int n) {

        int maxStep = 0;   //维护当前能跳到的最大位置
        for (int i = 0; i < n; i++) {
            // i > maxStep 表示无法到达
            // maxStep >= (n-1), 则可达
            if(i > maxStep || maxStep >= (n-1)) {
                break;
            }
            maxStep = Math.max(maxStep, i + array[i]);
        }
        return maxStep >= (n - 1);
    }
}
