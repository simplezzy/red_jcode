package com.redcode.demo.javauc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhiyu.zhou on 2018/3/2.
 */

/**
 * Q:三个线程交替执行，打印ABCABCABC
 */
public class MutilThreadLockDemo {

    private final static int TOTAL_LOOP = 20;

    private final static int LOOP_A = 1;

    private final static int LOOP_B = 1;

    private final static int LOOP_C = 1;

    public static void main(String[] args) {

        MthreadLockDemo lockDemo = new MthreadLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= TOTAL_LOOP; i++) {
                    lockDemo.threadA(i);
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= TOTAL_LOOP; i++) {
                    lockDemo.threadB(i);
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= TOTAL_LOOP; i++) {
                    lockDemo.threadC(i);
                    System.out.println("--------------------");
                }
            }
        }, "C").start();
    }

    static class MthreadLockDemo {

        private int number = 1;
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        private void threadA(int totalLoop) {
            lock.lock();
            try {
                if(number != 1) {
                    conditionA.await();
                }
                for(int i = 0; i < LOOP_A; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i + "---" + totalLoop);
                }
                number = 2;
                conditionB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void threadB(int totalLoop) {
            lock.lock();
            try {
                if(number != 2) {
                    conditionB.await();
                }
                for(int i = 0; i < LOOP_B; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i + "---" + totalLoop);
                }
                number = 3;
                conditionC.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void threadC(int totalLoop) {
            lock.lock();
            try {
                if(number != 3) {
                    conditionC.await();
                }
                for(int i = 0; i < LOOP_C; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i + "---" + totalLoop);
                }
                number = 1;
                conditionA.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
