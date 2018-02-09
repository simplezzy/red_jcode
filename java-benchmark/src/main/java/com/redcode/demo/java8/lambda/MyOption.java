package com.redcode.demo.java8.lambda;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
@FunctionalInterface
public interface MyOption<T> {

    boolean test(T t);
}
