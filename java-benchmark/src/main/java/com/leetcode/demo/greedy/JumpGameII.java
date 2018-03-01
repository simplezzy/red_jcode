package com.leetcode.demo.greedy;

/**
 * Created by zhiyu.zhou on 2018/3/1.
 */

/**
 * 给定一整数数组，数组元素表示每次可以跳跃的最大距离。然后初始位置在数组的第一个元素，以最少的步数到达最后元素。
 * A={2，3，1，1，4},初始位置0出为2，最终跳的最少次数是2.
 *    即先从0跳到1位置，此时1位置是3，正好可以一次跳到结束位置，加起来正好两次是最小次数了。
 * S:贪心算法：引入reach变量表示可以能到达的最远处，这也就是全局最优解
 *            当遍历到i时，局部最优解即次局部下最远可达位置A[i]+i，此时全局的最优解reach和A[i]+i的最大值，即reach=max(reach,A[i]+i)
 */
public class JumpGameII {

    public static void main(String[] args) {
        int a[] = {2,3,1,1,4};
        System.out.println("jumpStep:" + jumpStep(a));
    }

    public static int jumpStep(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int curReach = 0;  //当前能跳到的最大位置
        int lastReach = 0;  //上一跳能达到的最远距离
        int step = 0;

        for(int i = 0; i < nums.length; i++) {
            //无法向前继续则返回
            if(i > curReach) {
                return -1;
            }
            //需要进行下次跳跃，则更新lastReach 和 step
            if(i > lastReach) {
                lastReach = curReach;
                ++step;
            }
            // 记录当前能达到的最远距离
            curReach = Math.max(curReach, i + nums[i]);
        }

        return step;
    }
}
