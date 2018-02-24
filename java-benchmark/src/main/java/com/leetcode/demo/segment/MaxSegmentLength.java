package com.leetcode.demo.segment;

/**
 * Created by Erichou on 2/24/18.
 */

/**
 * Q: 线段有起点和终点，求这些线段的覆盖长度，重复的部分只计算一次 [2,5] [3,8],[4,6]  --->[2,8]
 * S: 线段细分为单位1的n段，用bool标记每一小段，统计标记为true的长度即可
 */

public class MaxSegmentLength {

    public static void main(String[] args) {

        //A a = new A(); A.B b = a.new B(); //内部类实例化
        SegmentCover segmentCover = new SegmentCover();
        SegmentCover.Segment s1 = segmentCover.new Segment();
        s1.start = 2;
        s1.end = 5;

        SegmentCover.Segment s2 = segmentCover.new Segment();
        s2.start = 8;
        s2.end = 10;

        SegmentCover.Segment s3 = segmentCover.new Segment();
        s3.start = 4;
        s3.end = 7;

        SegmentCover.Segment[] ss = {s1, s2, s3};
        System.out.println(segmentCover.sumLength(ss));
    }

    static class SegmentCover {

        private final static int N = 99999;

        private static boolean[] b = new boolean[N];

        private int sumLength(Segment[] segments) {

            int count = 0;

            int min_start = 0;

            int max_end = 0;

            //remark
            for(int i = 0; i < segments.length; i++) {
                for(int j = segments[i].start; j <= segments[i].end; j++) {
                    b[j] = true;
                }
                //find min start and max end
                if(min_start > segments[i].start) {
                    min_start = segments[i].start;
                }

                if(max_end < segments[i].end) {
                    max_end = segments[i].end;
                }
            }
            for (int i = min_start; i < max_end; i++) {
                if(b[i]) {
                    count++;
                }
            }
            return count;
        }

        class Segment {

            private int start;

            private int end;
        }
    }
}
