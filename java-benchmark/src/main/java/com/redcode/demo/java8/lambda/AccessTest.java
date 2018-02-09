package com.redcode.demo.java8.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
public class AccessTest {

    private static List<Friend> ds_friends = Arrays.asList(
            new Friend("张A", "boy", 185.5, 65, 65, 8888.8),
            new Friend("Lily", "girl", 175.5, 70, 87, 5555.5),
            new Friend("李B", "boy", 170.0, 90, 75, 6666.6),
            new Friend("Lucy", "girl", 155.5, 95, 85, 11111.1),
            new Friend("王C", "boy", 175.5, 75, 85, 7777.7),
            new Friend("张A", "boy", 185.5, 95, 95, 33333.3),
            new Friend("HanMeimei", "girl", 160.5, 90, 90, 9999.9),
            new Friend("赵D", "boy", 160.5, 99, 95, 22222.2),
            new Friend("polly", "girl", 165.5, 85, 80, 33333.3),
            new Friend("Tom", "boy", 165.5, 85, 93, 44444.4),
            new Friend("Lintao", "boy", 170.5, 95, 92, 10000),
            new Friend("Lintao", "boy", 180.5, 95, 88, 10000)
    );

   public static void main(String[] args) {

       Arrays.asList("1","2","3","4").stream()
               .map(Long::parseLong)
               .filter(v -> v % 2 == 0)
               .forEach(System.out::println);

       // test2();
       //test3();
       //test4();
       test5();
      // test6();

   }
    //使用匿名内部类作为参数传递
    public static void test0() {
        Comparator<Double> comparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o1, o2);
            }
        };
        TreeSet<Double> treeSet = new TreeSet<>(comparator);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, everyone!");
            }
        };

        Runnable runl = () -> System.out.println("Hello, everyone!");
    }

    //Lambda表达式作为参数传递
    public static void test1() {
       Comparator<Double> comparator = (x, y) -> Double.compare(x, y);
       TreeSet<Double> treeSet = new TreeSet<>(comparator);
    }

    //需求： 选朋友
    public static List<Friend> filterFriendByHeight(List<Friend> friends) {
        List<Friend> list = new ArrayList<>();
        for(Friend friend : friends) {
            if(friend.getHeight() > 170.0) {
                list.add(friend);
            }
        }
        return list;
    }

    //按照身高选
    public static void test2() {
        List<Friend> list = filterFriendByHeight(ds_friends);
        for(Friend friend : list) {
            System.out.println(friend);
        }
    }

    //需求：颜值大于80
    public static List<Friend> filterFriendByFacepower(List<Friend> friends) {

        List<Friend> list = new ArrayList<>();
        for(Friend friend : friends) {
            if(friend.getFacepower() >80) {
                list.add(friend);
            }
        }
        return list;
    }

    //优化方式一：  策略设计模式
    public static List<Friend> filterFriend(List<Friend> friends, MyOption<Friend> myOption) {
       List<Friend> list = new ArrayList<>();
       for (Friend friend : friends) {
           if(myOption.test(friend)) {
               list.add(friend);
           }
       }
       return list;
    }

    public static void test3() {

       List<Friend> list1 = filterFriend(ds_friends, new FilterFriendForHeight());
       for (Friend friend : list1) {
           System.out.println(friend);
       }

        System.out.println("------------------------------------------------");
        List<Friend> list2 = filterFriend(ds_friends, new FilterFriendForFacepower());
        for (Friend friend : list2) {
            System.out.println(friend);
        }
    }

    //优化方式2：匿名内部类
    public static void test4() {
       List<Friend> list = filterFriend(ds_friends, new MyOption<Friend>() {
           @Override
           public boolean test(Friend friend) {
               return friend.getSalary() > 20000;
           }
       });

       for (Friend friend : list) {
           System.out.println(friend);
       }
    }

    //优化方式3：Lambda表达式
    public static void test5() {

       List<Friend> list = filterFriend(ds_friends, (f) -> f.getNature() >= 85 && f.getFacepower() > 90);
       list.forEach(System.out::println);
       System.out.println("------------------------------------------------");

       List<Friend> list1 = filterFriend(ds_friends, (f) -> f.getFacepower() >= 90);
       list1.forEach(System.out::println);
    }


    //优化方式4：Stream API
    public static void test6() {
       ds_friends.stream().filter((f) -> f.getFacepower() >= 95 && f.getNature() >= 80 && f.getSex().equals("girl"))
                          .filter(f -> f.getName().startsWith("L"))
                          .forEach(System.out::println);

       System.out.println("----------------------------------------------");
       ds_friends.stream().map(Friend::getName)
                          .sorted().limit(3).skip(2).distinct()
                          .forEach(System.out::println);

        System.out.println("----------------------------------------------");
        Map<Boolean, List<Friend>> group = ds_friends.stream().collect(Collectors.groupingBy(f -> f.getSex().equals("boy")));
        System.out.println(group);
        group.get(true).stream().filter(f -> f.getNature() >= 90).map(f->f.getName()).skip(1).forEach(System.out::println);
    }

}
