package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * </p>
 *
 *
 * 1. 快慢指针, 快指针走到空就没有环
 * 2. 如果快慢指针相遇，让快指针回到头节点，快慢指针一次走一步
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0141_LinkedListCycle {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public static boolean hasCycle(ListNode head) {
		return getFirstLoopNode(head) != null;
	}

	public static ListNode getFirstLoopNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

}
