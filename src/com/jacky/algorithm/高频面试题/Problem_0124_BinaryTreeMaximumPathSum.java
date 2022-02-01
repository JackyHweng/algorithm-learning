package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/4
 **/
public class Problem_0124_BinaryTreeMaximumPathSum {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return process(root).maxPathSum;
	}

	public static class Info {
		public int maxPathSum;
		public int maxPathSumFromHead;

		public Info(int path, int head) {
			maxPathSum = path;
			maxPathSumFromHead = head;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return null;
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		// 分析6种可能性
		// 1. 左树最大
		// 2. 右树最大
		// 3. x 为最大
		// 4. x 往左扎
		// 5. x 往右扎
		// 6. x 往左右扎
		int p1 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p1 = leftInfo.maxPathSum;
		}
		int p2 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p2 = rightInfo.maxPathSum;
		}
		int p3 = x.val;
		int p4 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p4 = x.val + leftInfo.maxPathSumFromHead;
		}
		int p5 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p5 = x.val + rightInfo.maxPathSumFromHead;
		}
		int p6 = Integer.MIN_VALUE;
		if (leftInfo != null && rightInfo != null) {
			p6 = x.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead;
		}
		int maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
		int maxSumFromHead = Math.max(p3, Math.max(p4, p5));
		return new Info(maxSum, maxSumFromHead);
	}

}
