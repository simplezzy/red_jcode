package com.redcat.tools.transthreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhiyu.zhou on 2018/3/6.
 */
public class TtlRunnable {

    public static void main(String[] args) {

        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();
        parent.set("my-local-thread-value");

        Runnable task = null; //new Task("1");
        Runnable ttlRunable = com.alibaba.ttl.TtlRunnable.get(task);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(ttlRunable);

        //Task中可以读取，值是"my-local-thread-value"
        String value = parent.get();
    }
}
