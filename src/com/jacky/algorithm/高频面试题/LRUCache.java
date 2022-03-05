package com.jacky.algorithm.高频面试题;

import java.util.HashMap;
import java.util.Map;

/*
 * 在leetcode上提交时，把文字替换成下面的代码
 * 然后把类名、构造方法名从Problem_0146_LRUCache改为LRUCache即可
 */

/**
 * <p>
 * LRU
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    /**
     * 改为 LRUCache
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
//            throw new RuntimeException("data not found");
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        printList();
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }

        printList();
    }

    /**
     * 头插法
     * @param node
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        // 有虚拟头和尾
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private void printList(){
        DLinkedNode cur = head;
        StringBuffer sb = new StringBuffer();
        while(cur != null){
            if(cur == head){
                sb.append( "head ->" );
            }else if(cur == tail){
                sb.append("tail");
            }else{
                sb.append(cur.value + "->" );
            }
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        final LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,2);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,2);
        lruCache.put(4,2);
        System.out.println(lruCache.get(1));
    }
}
