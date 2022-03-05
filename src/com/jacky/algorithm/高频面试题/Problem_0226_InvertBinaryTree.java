package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 226. 翻转二叉树
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/30
 **/
public class Problem_0226_InvertBinaryTree {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = invertTree(right);
		root.right = invertTree(left);
		return root;
	}


	/**
	 * 这种好理解一点
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree2(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
}
