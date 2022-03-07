package com.jacky.algorithm.高频面试题;

/**
 * 58leetcode高频题目全讲十
 * <p>
 * 101. 对称二叉树
 * </p>
 *
 *
 * 1. 如果将一个树翻转一下和原来是一样的，那么这课树就是一颗对称二叉树
 * 2. 也即是说，如果一个节点相等，并且它的左子树和它的右子树相等，它的右子树和它的左子树相等
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0101_SymmetricTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	// 一棵树是原始树  head1
	// 另一棵是翻面树  head2
	public static boolean isMirror(TreeNode head1, TreeNode head2) {
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1 != null && head2 != null) {
			return head1.val == head2.val 
					&& isMirror(head1.left, head2.right) 
					&& isMirror(head1.right, head2.left);
		}
		// 一个为空，一个不为空  false
		return false;
	}

}
