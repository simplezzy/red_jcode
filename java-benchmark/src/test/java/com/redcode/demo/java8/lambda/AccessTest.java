package com.redcode.demo.java8.lambda;

import junit.framework.TestCase;

import java.util.*;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
public class AccessTest extends TestCase {

    List<Friend> ds_friends = Arrays.asList(
            new Friend("张A", "boy", 185.5, 80, 65, 8888.8),
            new Friend("Lily", "girl", 175.5, 80, 45, 5555.5),
            new Friend("李B", "boy", 170.0, 90, 75, 6666.6),
            new Friend("Lucy", "girl", 155.5, 95, 85, 11111.1),
            new Friend("王C", "boy", 175.5, 75, 85, 7777.7),
            new Friend("HanMeimei", "girl", 160.5, 90, 90, 9999.9),
            new Friend("赵D", "boy", 160.5, 99, 95, 22222.2),
            new Friend("polly", "girl", 165.5, 85, 80, 33333.3),
            new Friend("Tom", "boy", 165.5, 85, 93, 44444.4)
    );

    //内部匿名函数
    public void testTest0() throws Exception {
        Comparator<Double> comparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o1, o2);
            }
        };

        TreeSet<Double> treeSet = new TreeSet<>(comparator);
    }

    //lambda 表达式
    public void testTest1() throws Exception {
        Comparator<Double> comparator = (x, y) -> Double.compare(x, y);
        TreeSet<Double> treeSet = new TreeSet<>(comparator);
    }

    //需求： 选朋友
    public List<Friend> filterFriend(List<Friend> friends) {

        List<Friend> list = new ArrayList<>();
        for(Friend friend : list) {
            if(friend.getHeight() > 170.0) {
                list.add(friend);
            }
        }
        return list;
    }

    public void testTest2() throws Exception {
        List<Friend> list = filterFriend(ds_friends);

        for(Friend friend : list) {
            System.out.println(friend);
        }
    }



}