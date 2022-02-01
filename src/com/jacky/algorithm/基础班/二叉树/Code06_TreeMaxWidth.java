package com.jacky.algorithm.基础班.二叉树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 二叉树最大宽度
 *
 * 1. 按层遍历
 * 2. 记录每一层的开始和结束 ， 记录最大的层宽度
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code06_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// key 在 哪一层
		HashMap<Node, Integer> levelMap = new HashMap<>();
		levelMap.put(head, 1);
		int curLevel = 1; // 当前正在统计哪一层的宽度
		int curLevelNodes = 0;
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curNodeLevel = levelMap.get(cur);
			if (cur.left != null) {
			    // 左孩子就是下一层
				levelMap.put(cur.left, curNodeLevel + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				// 右孩子就是下一层
				levelMap.put(cur.right, curNodeLevel + 1);
				queue.add(cur.right);
			}

			// 说明当前层还没有遍历完
			if (curNodeLevel == curLevel) {
				// 记录当前层的个数
				curLevelNodes++;
			} else {
				// 结算当前层的个数
				max = Math.max(max, curLevelNodes);
				// 开始下一层
				curLevel++;
				// 当前层的节点数，因为当前时机已经弹出了，所以要设置1
				curLevelNodes = 1;
			}
		}
		// 因为结算时机是新层到来的时候，所以出来还需要统计最后一层的节点
		max = Math.max(max, curLevelNodes);
		return max;
	}

	public static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		Node curEnd = head;  // 当前层,最右节点
		Node nextEnd = null; // 下一层前层,最右节点
		int max = 0;
		int curLevelNodes = 0; // 当前层的节点数
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevelNodes++;
			if (cur == curEnd) { // 结算时机是最右一个节点到来的时候
				max = Math.max(max, curLevelNodes);
				// 准备开始下一层，重置变量
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		return max;
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
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}

}
