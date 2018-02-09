package com.redcode.demo.anotation;

/**
 * Created by zhiyu.zhou on 2018/1/18.
 */
public class AnnotationTest {

    @UserAnnotation(age = 20, gender = "F", id = 2018, name = "Hello")
    private Object object;

    public static void main(String[] args) throws Exception {
        //Filed filedObj = AnnotationTest.class.getField("object");
    }
}
