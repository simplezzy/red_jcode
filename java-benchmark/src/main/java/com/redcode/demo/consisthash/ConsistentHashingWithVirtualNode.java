package com.redcode.demo.consisthash;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zhiyu.zhou on 2018/3/28.
 */
public class ConsistentHashingWithVirtualNode {
    //加入Hash环的服务器节点列表
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    //真实节点列表
    private static List<String> realNodeList = new LinkedList();

    //虚拟节点
    private static SortedMap<Integer, String> virtualNodes = new TreeMap();

    private final static int VIRTUAL_NODES = 3;
    //初始化hash环
    static {
        for(int i = 0; i < servers.length; i++) {
            realNodeList.add(servers[i]);
        }
        for(String str : realNodeList) {
            for(int i = 0; i < VIRTUAL_NODES; i++) {
                String virName = str + "&VN" + i;
                int hash = getHash(virName);
                virtualNodes.put(hash, virName);
            }
        }
    }

    //获取路由的节点
    private static String getNode(String dataKey) {
        int hash = getHash(dataKey);
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode = subMap.get(subMap.firstKey());
        return virtualNode.substring(0, virtualNode.indexOf("&"));
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
