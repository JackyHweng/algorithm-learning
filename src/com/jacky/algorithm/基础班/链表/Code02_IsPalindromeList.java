package com.jacky.algorithm.基础班.链表;

import java.util.Stack;

/**
 * <p>
 * 判断链表是否为回文链表
 * 核心思路:
 * 方法一
 * 1. 用快慢指针找到中点, 然后将左半部分的节点放到栈中
 * 2. 然后开始弹栈对比
 *
 * 方法二
 * 利用O(1)的有限空间实现
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code02_IsPalindromeList {

	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int data) {
			this.val = data;
		}
	}

	// need n extra space
	public static boolean isPalindrome1(ListNode head) {
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {
			if (head.val != stack.pop().val) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need n/2 extra space
	public static boolean isPalindrome2(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode right = head.next;
		ListNode cur = head;
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		// 找到中点
		Stack<ListNode> stack = new Stack<ListNode>();
		while (right != null) {
			stack.push(right);
			right = right.next;
		}
		while (!stack.isEmpty()) {
			if (head.val != stack.pop().val) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need O(1) extra space
	public static boolean isPalindrome3(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		// slow
		ListNode n1 = head;
		// fast
		ListNode n2 = head;
		while (n2.next != null && n2.next.next != null) { // find mid node
			n1 = n1.next; // n1 -> mid
			n2 = n2.next.next; // n2 -> end
		}
		n2 = n1.next; // n2 -> right part first node
		n1.next = null; // mid.next -> null
		ListNode n3 = null;
		while (n2 != null) { // right part convert
			n3 = n2.next; // n3 -> save next node
			n2.next = n1; // next of right node convert
			n1 = n2; // n1 move
			n2 = n3; // n2 move
		}
		n3 = n1; // n3 -> save last node
		n2 = head;// n2 -> left first node
		boolean res = true;
		while (n1 != null && n2 != null) { // check palindrome
			if (n1.val != n2.val) {
				res = false;
				// 不能返回
				break;
			}
			n1 = n1.next; // left to mid
			n2 = n2.next; // right to mid
		}

		// 恢复链表
		n1 = n3.next;
		n3.next = null;
		while (n1 != null) { // recover list
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}

	public static void printLinkedList(ListNode node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		ListNode head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");


	}

}
