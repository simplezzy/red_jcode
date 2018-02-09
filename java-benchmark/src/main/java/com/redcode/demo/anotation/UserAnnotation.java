package com.redcode.demo.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhiyu.zhou on 2018/1/18.
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    public int id() default 0;
    public String name() default "";
    public int age() default 18;
    public String gender() default "M";
}
