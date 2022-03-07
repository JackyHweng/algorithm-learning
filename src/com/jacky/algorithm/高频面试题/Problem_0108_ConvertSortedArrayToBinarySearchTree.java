package com.jacky.algorithm.高频面试题;

/**
 * 59leetcode高频题目全讲十一
 * <p>
 * 108. 将有序数组转换为二叉搜索树
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/7
 **/
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		return process(nums, 0, nums.length - 1);
	}

	public static TreeNode process(int[] nums, int L, int R) {
		if (L > R) {
			return null;
		}
		if (L == R) {
			return new TreeNode(nums[L]);
		}
		int M = (L + R) / 2;
		TreeNode head = new TreeNode(nums[M]);
		head.left = process(nums, L, M - 1);
		head.right = process(nums, M + 1, R);
		return head;
	}

}
