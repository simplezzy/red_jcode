package com.redcode.demo.javauc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhiyu.zhou on 2018/1/24.
 */
public class ThreadServiceExcutor {

    public static void main(String args[]) {

        while (true) {
            singleThreadExecutor();
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           System.out.println("当前线程已处理完毕");
        }
    }


    public static void singleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Thread(() -> System.out.println(Thread.currentThread().getName() + "正在执行")));
        pool.shutdown();
    }
}
