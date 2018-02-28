package com.leetcode.demo.segment;

/**
 * Created by zhiyu.zhou on 2018/2/27.
 */

/**
 * Q: 找出重合最长的线段
 *    如线段A（1,5）、B（2,8）、C（3,9），则B和C的重合长度最长，为5.返回(3,8)
 * S: 动态规划 max= {sn-1, sn-1 + segment[n-1]}
 *    1、线段end值排序
 *    2、递归出Sn-1的最大重合线段
 */
public class MaxCoincideSegment {

    public static void main(String[] args) {

    }

    //排序后 a.end < b.end
    private Segment commonSegement(Segment s1, Segment s2) {
        Segment commonSegment = null;
        if(s1.end < s2.start) {  //no common segment
           commonSegment.start = 0;
           commonSegment.end = 0;
        } else {
            commonSegment.start = s2.start;
            commonSegment.end = s1.end;
        }
        return commonSegment;
    }

    private int findMaxCoincideSegment(Segment[] segments) {
        if(null == segments || segments.length <= 0) {
            return -1;
        }
        if(segments.length == 1) {
            return segments[0].end - segments[0].start;
        } else if(segments.length == 2) {
            Segment commonSegment = commonSegement(segments[0], segments[1]);
            return commonSegment.end - commonSegment.start;
        } else {
            Segment maxSegment = null;
            int currentMaxSegment = 0;
            findMaxCoincideSegment(segments);
            for (int i = 0; i < segments.length - 1; i++) {
                maxSegment = commonSegement(segments[i], segments[segments.length - 1]);
                if(maxSegment.end - maxSegment.start > currentMaxSegment) {
                    currentMaxSegment = maxSegment.end - maxSegment.start;

                }
            }

            return maxSegment.end - maxSegment.start;
        }
    }

    class Segment {
        private int start;

        private int end;
    }
}
