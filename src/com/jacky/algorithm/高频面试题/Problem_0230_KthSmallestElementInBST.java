package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
 *
 *
 * 中序遍历第k个
 * 这里用的是 Morris遍历
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0230_KthSmallestElementInBST {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int kthSmallest(TreeNode head, int k) {
		if (head == null) {
			return -1;
		}
		TreeNode cur = head;
		TreeNode mostRight = null;
		int index = 1;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (index++ == k) {
				return cur.val;
			}
			cur = cur.right;
		}
		return -1;
	}

}
