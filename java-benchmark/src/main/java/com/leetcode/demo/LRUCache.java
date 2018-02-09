package com.leetcode.demo;

/**
 * Created by zhiyu.zhou on 2018/1/7.
 */
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。
 * 实现 1：链表保存缓存数据
 *        a.新数据插入到链表头部
 *        b.每当缓存命中，则数据移到链表头部
 *        c.当链表满的时候，将尾部淘汰
 *        【命中率】：当存在热点数据时，LRU的效率很好，但偶发性的、周期性的批量操作会导致LRU命中率急剧下降，缓存污染情况比较严重。
 *        【复杂度】：实现简单
 *        【代价】：命中时需要遍历链表，找到命中的数据块索引，然后需要将数据移到头部。
 *
 *      2:LRU-K
 *        LRU-K中的K代表最近使用的次数，因此LRU可以认为是LRU-1。LRU-K的主要目的是为了解决LRU算法“缓存污染”的问题，
 *        其核心思想是将“最近使用过1次”的判断标准扩展为“最近使用过K次”。
 *        相比LRU，LRU-K需要多维护一个队列，用于记录所有缓存数据被访问的历史
 *        只有当数据的访问次数达到K次的时候，才将数据放入缓存。当需要淘汰数据时，LRU-K会淘汰第K次访问时间距当前时间最大的数据。
 *        a. 数据第一次被访问，加入到访问历史列表；
 *        b. 如果数据在访问历史列表里后没有达到K次访问，则按照一定规则（FIFO，LRU）淘汰；
 *        c. 当访问历史队列中的数据访问次数达到K次后，将数据索引从历史队列删除，将数据移到缓存队列中，并缓存此数据，缓存队列重新按照时间排序；
 *        d. 缓存数据队列中被再次访问后，重新排序；
 *        e. 需要淘汰数据时，淘汰缓存队列中排在末尾的数据，即：淘汰“倒数第K次访问离现在最久”的数据
 *        LRU-K具有LRU的优点，同时能够避免LRU的缺点,适应性差，需要大量的数据访问才能将历史访问记录清除掉。
 *        【命中率】：LRU-K降低了“缓存污染”带来的问题，命中率比LRU要高。
 *        【复杂度】：LRU-K队列是一个优先级队列，算法复杂度和代价比较高
 *        【代价】：由于LRU-K还需要记录那些被访问过、但还没有放入缓存的对象，因此内存消耗会比LRU要多；当数据量很大的时候，内存消耗会比较可观。
 *                 基于时间进行排序（可以需要淘汰时再排序，也可以即时排序），CPU消耗比LRU要高
 *
 *      3:2Q
 *        类似于LRU-2，不同点在于2Q将LRU-2算法中的访问历史队列（注意这不是缓存数据的）改为一个FIFO缓存队列，即：2Q算法有两个缓存队列，一个是FIFO队列，一个是LRU队列。
 *        a. 新访问的数据插入到FIFO队列；
 *        b. 如果数据在FIFO队列中一直没有被再次访问，则最终按照FIFO规则淘汰;
 *        c. 如果数据在FIFO队列中被再次访问，则将数据移到LRU队列头部；
 *        d. 如果数据在LRU队列再次被访问，则将数据移到LRU队列头部；
 *        e. LRU队列淘汰末尾的数据。
 *        【命中率】：2Q算法的命中率要高于LRU。
 *        【复杂度】：需要两个队列，但两个队列本身都比较简单。
 *        【代价】：FIFO和LRU的代价之和。内存消耗也比较接近，但对于最后缓存的数据来说，2Q会减少一次从原始存储读取数据或者计算数据的操作。
 *
 *      4:Multi Queue（MQ）
 *        a.将数据划分为多个队列，不同的队列具有不同的访问优先级，其核心思想是：优先缓存访问次数多的数据。
 *        b. 详细的算法结构图如下，Q0，Q1....Qk代表不同的优先级队列，Q-history代表从缓存中淘汰数据，但记录了数据的索引和引用次数的队列：
 */

/**
 * 基于双链表的LRU实现
 */
public class LRUCache {

    ConcurrentHashMap hashMap = null;

    class CacheNode {
        CacheNode prev; //前一节点
        CacheNode next; //后一节点
        Object key;    //key
        Object value;  //value

        CacheNode() {
        }
    }

    private int cacheSize; //缓存大小

    private Hashtable nodes;  //缓存容器

    private int currentSize;  //当前缓存对象数量

    private CacheNode head;  //(双链表) 表头

    private CacheNode tail;  //(双链表) 表尾

    public LRUCache(int i) {
        currentSize = 0;
        cacheSize = i;
        nodes = new Hashtable(i);
    }

    /**
     * 获取缓存命中对象
     */
     public Object get(Object key) {
         CacheNode node = (CacheNode) nodes.get(key);
         if(node != null) {
             moveToHead(node);
             return node.value;
         } else {
             return null;
         }
     }
    /**
     * 添加缓存
     */
      public void put(Object key, Object value) {
          CacheNode node = (CacheNode) nodes.get(key);

          if(null == node) {
              //判断缓存容量
              if(currentSize >= cacheSize) {
                  //最少使用的删除
                  if(null != tail) {
                      nodes.remove(tail.key);
                  }
                  //?
                  removeLast();
              } else {
                  currentSize++;
              }
          }
          //不为null,更新
          node.value = value;
          node.key = key;
          //最新使用的放在表头
          moveToHead(node);
          nodes.put(key, node);
      }
    /**
     * 删除缓存
     */
    public Object remove(Object key) {
        CacheNode node = (CacheNode) nodes.get(key);
        if(node != null) {
            if(node.prev != null) {
                node.prev.next = node.next;
            }
            if(node.next != null) {
                node.next.prev = node.prev;
            }
            if(tail == node) {
                tail = node.prev;
            }
            if(head == node) {
                head = head.next;
            }
        }
        return node;
    }
    /**
     * 清除缓存
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * 移动到链表表头，表示该节点是最新使用过的
     * @param node
     */
    private void moveToHead(CacheNode node) {
        if(node == head) {
            return;
        }
        if(node.prev != null) {
            node.prev.next = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
        if(tail == node) {
            tail = node.prev;
        }
        if(head != null) {
            node.next = head;
            head.prev = node;
        }
        head = node;
        node.prev = null;
        if(tail == null) {
            tail = head;
        }

    }

    /**
     * 删除链表尾部节点：最少使用的缓存对象
     */
    private void removeLast() {
        //链表尾不为null,则将尾部指向null,删除尾节点
        if(tail != null) {
            if(tail.prev != null) {
                tail.prev.next = null;
            } else {
                head = null;
            }
            tail = tail.prev;
        }
    }
}
