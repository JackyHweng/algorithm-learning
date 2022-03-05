package com.jacky.algorithm.高频面试题;

public class Problem_0021_MergeTwoSortedLists {

	public static class ListNode {
		public int val;
		public ListNode next;
	}


	/**
	 *  用这个比较短
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
		ListNode prehead = new ListNode();
		ListNode prev = prehead;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}

		// 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
		prev.next = l1 == null ? l2 : l1;

		return prehead.next;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode head = l1.val <= l2.val ? l1 : l2;
		ListNode pre = head;
		ListNode cur1 = head.next;
		ListNode cur2 = head == l1 ? l2 : l1;
		while (cur1 != null && cur2 != null) {
			if (cur1.val <= cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1 != null ? cur1 : cur2;
		return head;
	}


	// 递归
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}


}
