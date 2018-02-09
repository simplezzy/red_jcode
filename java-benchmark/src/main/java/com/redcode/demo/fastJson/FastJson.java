package com.redcode.demo.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyu.zhou on 2017/9/10.
 */
public class FastJson {

    public static void main(String[] args) {

        /**
         * List<Object> 转 JsonString
         */
        List<ReceiverInfo> receiverInfoList = new ArrayList<>();
        receiverInfoList.add(new ReceiverInfo("123","zzy", "主管"));
        receiverInfoList.add(new ReceiverInfo("567","heolo", "经理"));
        String jsoninfos = JSON.toJSONString(receiverInfoList);

        String str = "[123,456,789]";
        List<String> liststrs = JSONObject.parseArray(str, String.class);
        System.out.println("json test:" + liststrs);
        /**
         * JsonString 转 List<Object>
         */
       // String jsonstr = "[ReceiverInfo(receiverId=123456, receiverName=江泽明, receiverJob=主管)]";
        List<ReceiverInfo> list = JSONObject.parseArray(jsoninfos, ReceiverInfo.class);
        System.out.println("object:" + list);

        //String jsonlist = "[ReceiverInfo(receiverId=123456, receiverName=江泽明, receiverJob=主管), ReceiverInfo(receiverId=123456, receiverName=江泽明, receiverJob=主管)]";
        List<ReceiverInfo> lists = JSONObject.parseArray(jsoninfos, ReceiverInfo.class);
        System.out.println("object list" + lists);


    }
}
