package com.redcode.demo.jcompare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class ObjectSortApp
{
    public static void main( String[] args )
    {

        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        System.out.println( cities );
//        stringSort(cities);
        StringSortBy8(cities);
    }


    public static void stringSort(List<String> cities) {


        //忽略大小写  jdk7 使用Collection.sort()接受List和最后的Comparator
        Collections.sort(cities, new Comparator<String>() {

            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        System.out.println( cities );
    }

    //jdk8 有新的 List.sort() 用于接受Comparator
    public static void StringSortBy8(List<String> cities) {
        // Comparator.naturalOrder() （返回首先排序大写字母的比较器）
        cities.sort(Comparator.naturalOrder());

        //返回不区分大小写的比较器
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(cities.toString());
    }
}
