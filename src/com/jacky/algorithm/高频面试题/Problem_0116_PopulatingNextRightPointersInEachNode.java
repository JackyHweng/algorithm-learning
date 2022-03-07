package com.jacky.algorithm.高频面试题;

/**
 * 59leetcode高频题目全讲十一
 * <p>
 * 116. 填充每个节点的下一个右侧节点指针
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/7
 **/
public class Problem_0116_PopulatingNextRightPointersInEachNode {

	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
	}

	/**
	 * 自己实现的队列是使用自己的结构
	 * 但是系统的LinkedList 是再一次封装了Node节点
	 */
	public static class MyQueue {
		public Node head;
		public Node tail;
		public int size;

		public MyQueue() {
			head = null;
			tail = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void offer(Node cur) {
			size++;
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
		}

		public Node poll() {
			size--;
			Node ans = head;
			head = head.next;
			ans.next = null;
			return ans;
		}

	}

	public static Node connect(Node root) {
		if (root == null) {
			return root;
		}
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 第一个弹出的节点
			Node pre = null;
			int size = queue.size;
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				if (pre != null) {
					pre.next = cur;
				}
				pre = cur;
			}
		}
		return root;
	}

}
