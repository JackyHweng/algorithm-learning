package com.jacky.algorithm.高频面试题;

/**
 * 58leetcode高频题目全讲十
 * <p>
 *  给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * </p>
 *
 * 1. 中序遍历结果集是升序的，那么这课树就是搜索二叉树
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0098_ValidateBinarySearchTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	/**
	 * 递归模式
	 * @param root
	 * @return
	 */
	public boolean isValidBST2(TreeNode root) {
		return process(root,Long.MIN_VALUE,Long.MAX_VALUE);
	}

	public boolean process(TreeNode root,long lower, long upper){
		if(root == null){
			return true;
		}

		if(root.val <= lower || root.val >= upper){
			return false;
		}

		return process(root.left,lower,root.val) && process(root.right,root.val,upper);
	}
	/**
	 * Mirror 遍历
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		TreeNode cur = root;
		TreeNode mostRight = null;
		Integer pre = null;
		boolean ans = true;
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
			if (pre != null && pre >= cur.val) {
				ans = false;
			}
			pre = cur.val;
			cur = cur.right;
		}
		return ans;
	}

}
