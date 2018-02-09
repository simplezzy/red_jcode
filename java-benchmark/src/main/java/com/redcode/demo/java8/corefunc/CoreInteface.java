package com.redcode.demo.java8.corefunc;

/**
 * Created by Erichou on 9/16/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 * Consumer<T>:  消费型接口
 *    void accept(T t)
 *
 * Supplier<T>:  供给型接口
 *    T get();
 *
 * Function<T, R>:  函数型接口
 *    R apply(T, R)
 *
 * Predicate<T>:  断言型接口
 *
 *    boolean test(T t)
 */

public class CoreInteface {

    public static void main(String[] args) {
        System.out.println("Hello four core function interface");

    }

    //Consumer<T> 消费性接口
    public static void test1() {
        happy(1000, (m) -> System.out.println("happy:" + m));
    }

    public static void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //Supplier<T> 供给型接口

    public static void test2() {
        List<Integer> list = getNumList(10, () -> (int)(Math.random()*100));

    }

    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //function<T> 函数型接口
    public static void test3() {
        String newStr = strHandler("\t\t HHHHJJ  ", (str) -> str.trim());
    }
    public static String strHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }

    //predicate<T> 断言型接口
    public static void test4() {
        List<String > list = Arrays.asList("");
        List<String> newList = filterStr(list, (s) -> s.length() > 3);
    }

    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> list1 = new ArrayList<>();
        for(String str : list) {
            if(predicate.test(str)) {
                list1.add(str);
            }
        }
        return list1;
    }
}
