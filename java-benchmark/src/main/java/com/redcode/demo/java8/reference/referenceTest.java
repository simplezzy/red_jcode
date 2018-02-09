package com.redcode.demo.java8.reference;

/**
 * Created by Erichou on 9/17/17.
 */

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，可以使用方法引用
 *
 *注意：
 *  1. Lambda体中调用方法列表与返回值类型，要与函数式接口抽象方法的防暑参数列表和返回值 相同
 *  2. 若Lambda参数列表第一个参数式实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 * 主要的三种语法格式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 */

//public class referenceTest {
//
//    public static void main(String[] args) {
//
//        Consumer<String> consumer = (x) -> System.out.println(x);
//
//        //等价于
//        Consumer<String> consumer1 = System.out::println;
//
//        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
//
//        Comparator<Integer> comparator = Integer::compare;
//
//    }
//}　
