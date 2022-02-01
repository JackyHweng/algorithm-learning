package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 链表中删除某一个节点
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0237_DeleteNodeInLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
