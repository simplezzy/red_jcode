package com.redcode.demo.consisthash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zhiyu.zhou on 2018/3/28.
 */
public class ConsistentHashingWithoutVirtualNode {
    //待加入Hash环的服务器列表
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    //key表示Hash值 value服务器的名
    private static SortedMap sortedMap = new TreeMap();

    //初始化  将所有的服务器放入sortedMap
    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = getHash(servers[i]);
            sortedMap.put(hash, servers[i]);
        }
    }

    /**
     *得到应当路由到的节点
     */
    private static String getNode(String data) {
        int hash = getHash(data);
        //得到所有大于该Hash值的Map
        SortedMap subMap = sortedMap.tailMap(hash);
        Integer i = (Integer) subMap.firstKey();
        return (String) subMap.get(i);
    }


    /**
    使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    */
    private static int getHash(String str)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < 10; i++)  //??
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash ;
        hash ^= hash >> 7;
        hash += hash ;
        hash ^= hash >> 17;
        hash += hash ;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}
