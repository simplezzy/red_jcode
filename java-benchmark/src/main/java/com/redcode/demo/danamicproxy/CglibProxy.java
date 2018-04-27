package com.redcode.demo.danamicproxy;

/**
 * Created by zhiyu.zhou on 2018/4/27.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 没有接口的class,需要采用CGlib实现动态代理
 * 1、采用非常底层的字节码技术，为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用,并注入横切逻辑
 * 2、性能比较：动态代理对象性能比JDK高，创建代理对象要慢，单例的对象，因为无需频繁创建对象，用CGLib合适
 * 3、由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    //设置需要创建子类的类
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    //实现MethodInterceptor接口方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理");
        return result;
    }
}
