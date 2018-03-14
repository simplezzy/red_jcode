package com.leetcode.demo.linklist;

/**
 * Created by zhiyu.zhou on 2018/3/14.
 */
public class ReverseLinkList {

    class ListNode {
        int value;
        ListNode next;
        ListNode(int x) {
            value = x;
        }
    }

    /**
     * 非递归
     * @param head
     * @return
     */
    public ListNode reversLinkList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(null != cur) {
            //保存下一个节点
            temp = cur.next;
            //更新next节点
            cur.next = pre;
            //shift pre和cur节点
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 递归-- 翻转head->为首的链表， 然后head变为尾部节点
     */
    public ListNode reverseBy(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode reverseNode = reverseBy(head.next);
        reverseNode.next.next = head;
        head.next = null;
        return reverseNode;
    }
}
