package com.redcode.demo.javacompare;

/**
 * Created by zhiyu.zhou on 2018/4/27.
 */

/**
 * equals and "=="
 * 1、"==" 比较的是栈内存中存放的对象的堆内存地址；判断的是两个对象的地址是否相等，是否指向的是同一个对象
 * 2、"equals" 比较两个对象的内容是否相等，如果不覆盖，调用的是Object类中的方法，而Object中的equals方法返回的却是==的判断。
 *     equals源码定义的equals与==是等效的（Object类中的equals没什么区别），不同的原因就在于有些类（像String、Integer等类）
 *     对equals进行了重写，但是没有对equals进行重写的类（比如我们自己写的类）就只能从Object类中继承equals方法，
 *     其equals方法与==就也是等效的，除非我们在此类中重写equals。
 *
 *    特别地：String s="abce" 放在常量池中；只要值相等,任何多个引用都指向同一对象
 *
 */
public class EqualsAnd {

    public static void main(String[] args) {
        String a = new String("abc");  //a 为一个引用
        String b = new String("abc");  //b 为另一个引用
        String aa = "abc";  //放在常量池中
        String bb = "abc";  //从常量池中查找
        if(aa == bb) { //true
            System.out.println("aa==bb");
        }
        if(a == b) {//false
            System.out.println("a==b");
        }
        if(a.equals(b)) { //true
            System.out.println("aEQSb");
        }

    }
}
