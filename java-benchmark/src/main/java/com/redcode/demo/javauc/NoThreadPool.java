package com.redcode.demo.javauc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiyu.zhou on 2017/11/1.
 */
public class NoThreadPool {

    public static void main(String[] args) {


    }

    //串行执行任务
    public void singleThreadWebServer() throws Exception {
        ServerSocket socket = new ServerSocket(80);
        while(true) {
            Socket conn = socket.accept();
            //handerRequest(conn);
        }
    }

    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task:" + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task" + taskNum + "执行完毕");
        }
    }

}
