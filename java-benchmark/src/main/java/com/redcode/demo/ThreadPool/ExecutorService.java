package com.redcode.demo.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zhiyu.zhou on 2018/1/9.
 */
public class ExecutorService {

    public static void main(String[] args) {

        while(true) {

        }
    }

    public void testThread() {
        new Thread(() -> {
            System.out.println("");

        }).start();
    }
    public int testService() {
        // 防止超时
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.execute(() -> {
            System.out.println();
        });

        return 1;
    }

}
