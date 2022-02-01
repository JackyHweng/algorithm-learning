package com.jacky.algorithm.基础班.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 	1. 其实就是宽度优先遍历，用队列
 	2. 可以通过设置flag变量的方式，来发现某一层的结束（看题目）
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code03_LevelTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void level(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
		    // 弹出就打印
			Node cur = queue.poll();
			System.out.println(cur.value);
			// 左进去队列
			if (cur.left != null) {
				queue.add(cur.left);
			}
			// 右节点 进入队列
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		level(head);
		System.out.println("========");
	}

}
