package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 删除倒数第n个节点
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/22
 **/
public class Problem_0019_RemoveNthNodeFromEndofList {

	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	/**
	 * 需要注意 删除头结点
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		// 先数够 n 个节点
		while (cur != null) {
			n--;
			// 多数一个因为需要删除的是当前节点的下一个节点
			if (n == -1) {
				pre = head;
			}
			// 继续往下移动
			if (n < -1) {
				pre = pre.next;
			}
			cur = cur.next;
		}
		//不够，返回头结点
		if (n > 0) {
			return head;
		}
		// 删除的节点刚好是头节点
		if (pre == null) {
			return head.next;
		}
		pre.next = pre.next.next;
		return head;
	}

	/**
	 *
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode dummy = new ListNode(0, head);
		ListNode first = head;
		ListNode second = dummy;
		for (int i = 0; i < n; ++i) {
			first = first.next;
		}
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		ListNode ans = dummy.next;
		return ans;
	}

}
