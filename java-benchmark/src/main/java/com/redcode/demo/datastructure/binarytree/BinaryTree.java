package com.redcode.demo.datastructure.binarytree;

import java.util.LinkedList;

/**
 * Created by zhiyu.zhou on 2018/2/5.
 */
public class BinaryTree {

    //1、求二叉树最大深度
    int maxDepth(TreeNode node) {
        if(null == node) {
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    //2、二叉树第k层节点个数
    int numNodesFromKLevel(TreeNode node, int k) {
        if(null == node || k < 1) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }
        return numNodesFromKLevel(node.left, k - 1) + numNodesFromKLevel(node.right, k - 1);
    }

    //3、二叉树的层次遍历/相当于广度优先搜索，使用队列（深度优先搜索的话，使用栈）

    /**
     * 1、根节点为空、返回
     * 2、根节点不为null,则加入队列
     * 3、从队列中拿出节点，当左右节点不为null时，分别把左右节点加入到队列中
     */
    public void levelIterator(TreeNode root) {
        if(null == root) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode curNode = null;
        while (!list.isEmpty()) {
            curNode = list.poll();  //获取节点并删除
            System.out.println(curNode.value);
            if(null != curNode.left) {
                list.add(curNode.left);
            }
            if(null != curNode.right) {
                list.add(curNode.right);
            }
        }
    }



}
