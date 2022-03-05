package com.jacky.algorithm.高频面试题;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem_0002_AddTwoNumbers {

	// 不要提交这个类描述
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int value) {
			this.val = value;
		}
	}

	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		ListNode c1 = head1;
		ListNode c2 = head2;
		ListNode node = null;
		ListNode pre = null;
		while (c1 != null || c2 != null) {
			n1 = c1 != null ? c1.val : 0;
			n2 = c2 != null ? c2.val : 0;
			// 这是最高位
			n = n1 + n2 + ca;
			// 头插法
			pre = node;
			node = new ListNode(n % 10);
			node.next = pre;
			ca = n / 10;
			c1 = c1 != null ? c1.next : null;
			c2 = c2 != null ? c2.next : null;
		}
		if (ca == 1) {
			pre = node;
			node = new ListNode(1);
			node.next = pre;
		}
		// 最终翻转链表
		return reverseList(node);
	}



	// 翻转列表
	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/**
	 *  逆序 !  逆序 !  逆序 !
	 * l1 = [2,4,3], l2 = [5,6,4]
	 * 双向队列实现
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode c1 = l1;
		ListNode c2 = l2;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		int ca = 0;
		ListNode preHead = new ListNode(-1);
		Deque<ListNode> queue = new ArrayDeque<>();

		while(c1 != null || c2!= null){
			n1 = c1 == null ? 0 : c1.val;
			n2 = c2 == null ? 0 : c2.val;
			n = ca + n1 + n2;
			ca = n / 10 ;
			queue.add(new ListNode(n%10));
			c1 = c1 == null ? null : c1.next;
			c2 = c2 == null ? null : c2.next;
		}

		c1 = preHead;

		while(!queue.isEmpty()){
			ListNode node = queue.poll();
			c1.next = node;
			c1 = c1.next;
		}

		if(ca > 0){
			c1.next = new ListNode(ca);
			c1 = c1.next;
		}

		return preHead.next;
	}

}
