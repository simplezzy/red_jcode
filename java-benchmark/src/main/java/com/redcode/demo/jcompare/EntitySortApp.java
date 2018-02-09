package com.redcode.demo.jcompare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhiyu.zhou on 2017/8/17.
 */
public class EntitySortApp {

    public static void main(String[] args) {
        List<User> userList =new ArrayList<User>();
        userList.add(new User("mm", 10, "hunan"));
        userList.add(new User("aa", 18, "hunan"));
        userList.add(new User("aa", 18, "shanghai"));
        userList.add(new User("lucy", 9, "nanjing"));
        userList.add(new User("hello", 20, "jinan"));
        userList.add(new User("em", 10, "guangdong"));
        userList.add(new User("em", 12, "guangdong"));
        System.out.println(userList.toString());

//        userSort(userList);
        userSortByCompator(userList);
//        userSortByLamdba(userList);
//        userSortByLamdbaSimple(userList);
//        userSortByLambdaMutil(userList);
//        userSortByLambdaComp(userList);
    }

    //通常排序法 单个变量从小到大
    public static void userSort(List<User> userList) {

        Collections.sort(userList, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(userList);
    }

    //使用Comparator.comparing()
    public static void userSortByCompator(List<User> userList) {

        userList.sort(Comparator.comparing(User::getName));
        System.out.println(userList);
    }
    //使用Lambda的List排序
    public static void userSortByLamdba(List<User> userList) {

        userList.sort((User u1, User u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(userList);
    }


    //使用Lambda的List排序(简化)
    public static void userSortByLamdbaSimple(List<User> userList) {

        userList.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(userList);
    }

    //使用多个条件进行排序，Lambda提供了更复杂的表达式，还可以先对name排序再根据age进行排序
    public static void userSortByLambdaMutil(List<User> userList) {
        Comparator<User> comparator = (u1,u2) -> {
          if (u1.getName().equals(u2.getName())) {
              return Integer.compare(u1.getAge(), u2.getAge());
          }
          return u1.getName().compareTo(u2.getName());
        };
        userList.sort(comparator.reversed());
        System.out.println(userList.toString());
    }

    //使用多个条件进行排序-组合的方式
    //Comparator对这种组合的排序有更优雅实现，从JDK8开始，我们可以使用链式操作进行复合操作来构建更复杂的逻辑：
    public static void userSortByLambdaComp(List<User> userList) {
        userList.sort(Comparator.comparing(User::getName).thenComparing(User::getAge).thenComparing(User::getAddress));
        System.out.println(userList.toString());
    }
}
