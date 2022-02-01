package com.jacky.algorithm.高频面试题;

public class Problem_0328_OddEvenLinkedList {

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode evenHead = head.next;
		ListNode odd = head, even = evenHead;
		while (even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}

	// 提交时不要提交这个类
	public static class ListNode {
		int val;
		ListNode next;
	}

	public ListNode oddEvenList2(ListNode head) {
		ListNode firstOdd = null;
		ListNode firstEven = null;
		ListNode odd = null;
		ListNode even = null;
		ListNode next = null;
		int count = 1;
		while (head != null) {
			next = head.next;
			head.next = null;
			if ((count & 1) == 1) {
				firstOdd = firstOdd == null ? head : firstOdd;
				if (odd != null) {
					odd.next = head;
				}
				odd = head;
			} else {
				firstEven = firstEven == null ? head : firstEven;
				if (even != null) {
					even.next = head;
				}
				even = head;
			}
			count++;
			head = next;
		}
		if (odd != null) {
			odd.next = firstEven;
		}
		return firstOdd != null ? firstOdd : firstEven;
	}

}
