package com.jacky.algorithm.高频面试题;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 合并k个有序列表
 * 1. 小根堆
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0023_MergeKSortedLists {

	public static class ListNode {
		public int val;
		public ListNode next;
	}


	/**
	 * 小根堆比较器
	 */
	public static class ListNodeComparator implements Comparator<ListNode> {

		@Override
		public int compare(ListNode o1, ListNode o2) {
			return o1.val - o2.val;
		}

	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
		// 加入和弹出 都是 O(logn) 时间复杂度的
		PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(lists[i]);
			}
		}
		if (heap.isEmpty()) {
			return null;
		}
		// 弹出所有头结点最小的元素
		ListNode head = heap.poll();
		ListNode pre = head;
		// 将最小节点的下一个元素放入堆
		if (pre.next != null) {
			heap.add(pre.next);
		}
		// 开始弹出逻辑
		while (!heap.isEmpty()) {
			// 弹出当前节点，追加到链表中
			ListNode cur = heap.poll();
			pre.next = cur;
			pre = cur;
			// 将当前节点的下一个入堆
			if (cur.next != null) {
				heap.add(cur.next);
			}
		}
		return head;
	}

}
