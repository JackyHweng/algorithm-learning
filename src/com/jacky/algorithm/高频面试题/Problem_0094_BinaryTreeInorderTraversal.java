package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * 58leetcode高频题目全讲十
 * <p>
 * mirror 遍历
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0094_BinaryTreeInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		TreeNode cur = root;
		TreeNode mostRight = null;
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
			ans.add(cur.val);
			cur = cur.right;
		}
		return ans;
	}

}
