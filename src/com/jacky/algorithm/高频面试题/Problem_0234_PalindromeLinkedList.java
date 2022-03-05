package com.jacky.algorithm.高频面试题;

public class Problem_0234_PalindromeLinkedList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	/**
	 *  好理解的版本
	 * @param head
	 * @return
	 */
	public boolean isPalindrome2(ListNode head) {
		if (head == null) {
			return true;
		}

		// 找到前半部分链表的尾节点并反转后半部分链表
		ListNode firstHalfEnd = endOfFirstHalf(head);
		ListNode secondHalfStart = reverseList(firstHalfEnd.next);

		// 判断是否回文
		ListNode p1 = head;
		ListNode p2 = secondHalfStart;
		boolean result = true;
		while (result && p2 != null) {
			if (p1.val != p2.val) {
				result = false;
			}
			p1 = p1.next;
			p2 = p2.next;
		}

		// 还原链表并返回结果
		firstHalfEnd.next = reverseList(secondHalfStart);
		return result;
	}

	private ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		return prev;
	}

	private ListNode endOfFirstHalf(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode n1 = head;
		ListNode n2 = head;
		while (n2.next != null && n2.next.next != null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}
		n2 = n1.next;
		n1.next = null;
		ListNode n3 = null;
		while (n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}
		n3 = n1;
		n2 = head;
		boolean res = true;
		while (n1 != null && n2 != null) {
			if (n1.val != n2.val) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		n1 = n3.next;
		n3.next = null;
		while (n1 != null) {
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}

}
