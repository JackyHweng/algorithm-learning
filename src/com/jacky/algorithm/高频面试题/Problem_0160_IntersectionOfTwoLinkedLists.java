package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 两个无环链表的相交节点
 * </p>
 *
 *
 * 1. 如果2个链表的最后一个节点不相同，肯定不想交
 * 2. 求链表1 长度 和 链表2 的长度 ，较长的链表 先走 2个链表的差值 ( l1 100  , l2 80 , 可以让链表先走 20 步，那么肯定可以求出)
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0160_IntersectionOfTwoLinkedLists {

	public class ListNode {
		int val;
		ListNode next;
	}

	public static ListNode getIntersectionNode(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		ListNode cur1 = head1;
		ListNode cur2 = head2;
		int n = 0;
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		// cur1  end1
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		// cur2 end2
		if (cur1 != cur2) {
			return null;
		}

		cur1 = n > 0 ? head1 : head2; // 谁是长链表，谁把头节点，给cur1赋值
		cur2 = cur1 == head1 ? head2 : head1;

		n = Math.abs(n);
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		// 肯定会跳出
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

}
