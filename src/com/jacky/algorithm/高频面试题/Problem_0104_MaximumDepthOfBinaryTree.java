package com.jacky.algorithm.高频面试题;

import java.util.LinkedList;

/**
 *
 * 59leetcode高频题目全讲十一
 * <p>
 * 104. 二叉树的最大深度
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0104_MaximumDepthOfBinaryTree {

	/*
	 * 注意最小高度比这个复杂，要额外小心判断空
	 * */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}


	public int maxDepth2(TreeNode root) {
		if(root == null){
			return 0;
		}
		int ans = 0;
		LinkedList<TreeNode> queue = new LinkedList<>();

		queue.addLast(root);
		int size =0;
		while(!queue.isEmpty()){
			size = queue.size();
			while(size > 0){
				TreeNode node = queue.pollFirst();
				if(node.left != null){
					queue.addLast(node.left);
				}
				if(node.right != null){
					queue.addLast(node.right);
				}
				size--;
			}
			ans+=1;
		}
		return ans;
	}

}
