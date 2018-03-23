package com.leetcode.demo.linklist;

import java.util.LinkedList;

/**
 * Created by zhiyu.zhou on 2018/3/15.
 */
public class queuestack {

    /**
     * 两个queue实现stack  queue:先进先出  stack:先进后出
     * 非空n-1个元素进空队列,剩下的第n个出队
     */

    class queuesToStack {

        LinkedList<Integer> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();
        //入栈
        public void push(int value) {
            queue1.addLast(value);
        }

        //出栈
        public int pop() {

            if(stackSize() > 0) {
                if(!queue1.isEmpty()) {
                    putN_1ToAnother();
                    return queue1.removeFirst();
                } else {
                    putN_1ToAnother();
                    return queue2.removeFirst();
                }
            } else {
                //栈为空
                return -1;
            }
        }

        public int stackSize() {
            return queue1.size() + queue2.size();
        }

        private void putN_1ToAnother() {
            if(!queue1.isEmpty()) {
                while (queue1.size() > 1) {
                    queue2.addLast(queue1.removeFirst());
                }
            }

            if(!queue2.isEmpty()) {
                while (queue2.size() > 1) {
                    queue1.addLast(queue2.removeFirst());
                }
            }

        }
    }
}
