package com.jacky.algorithm.基础班.二叉树;

/**
 * <p>
 * 递归打印 二叉树
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code01_RecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 递归序 , 也就是说 递归遍历二叉树都访问这个head 节点3次
	 * @param head
	 */
	public static void f(Node head) {
		// 这是第一次经过head
		if (head == null) {
			return;
		}
		f(head.left);
		// 当递归完左子树后，第二次访问head
		f(head.right);
		// 当递归完右子树后，第三次访问head
	}

	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		pre(head.left);
		pre(head.right);
	}

	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.println(head.value);
		in(head.right);
	}

	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos(head);
		System.out.println("========");

	}

}
