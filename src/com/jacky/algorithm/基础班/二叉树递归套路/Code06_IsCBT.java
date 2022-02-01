package com.jacky.algorithm.基础班.二叉树递归套路;

import java.util.LinkedList;

/**
 * <p>
 * 6. 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
 *
 *  情况讨论：
 *  1. 满二叉树 无缺口
 *  2. 有缺口 ：
 *  2.1 缺口可能在左树上 左树是完全二叉树，右树是满二叉树 并且 左 的高度比右 大一
 *  2.2 缺口可能在右树上
 *  2.2.1 缺口的左树是否撑满
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code06_IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 宽度优先遍历
	 * @param head
	 * @return
	 */
	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			(leaf && !(l == null && r == null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCBT2(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isCBT;
	}

	// 对 一个树 是否是完全二叉树， 是否是满二叉树  高度
	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			return new Info(true, true, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else {
			if (leftInfo.isCBT && rightInfo.isCBT) {
				if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}
		return new Info(isFull, isCBT, height);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
