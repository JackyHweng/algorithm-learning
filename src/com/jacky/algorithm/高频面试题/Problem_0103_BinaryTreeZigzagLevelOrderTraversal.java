package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 59leetcode高频题目全讲十一
 * <p>
 * 103. 二叉树的锯齿形层序遍历
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	/**
	 * bfs
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {

		List<List<Integer>> ans = new ArrayList<>();
		if(root == null){
			return ans;
		}

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		int size = 0;
		boolean isHead = true;
		while(!queue.isEmpty()){
			size = queue.size();
			List<Integer> curLevel = new ArrayList<>();
			for(int i = 0 ; i < size; i++){
				TreeNode cur = isHead ? queue.pollFirst():queue.pollLast();
				curLevel.add(cur.val);
				if(isHead){
					if(cur.left != null){
						queue.addLast(cur.left);
					}
					if(cur.right != null){
						queue.addLast(cur.right);
					}
				}else{
					// 注意这里是反向的，所以先放右节点，在放入做节点
					if(cur.right != null){
						queue.addFirst(cur.right);
					}
					if(cur.left != null){
						queue.addFirst(cur.left);
					}
				}
			}

			isHead = !isHead;
			ans.add(curLevel);
		}

		return ans;
	}

	/**
	 * morior
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		LinkedList<TreeNode> deque = new LinkedList<>();
		deque.add(root);
		int size = 0;
		boolean isHead = true;
		while (!deque.isEmpty()) {
			size = deque.size();
			List<Integer> curLevel = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = isHead ? deque.pollFirst() : deque.pollLast();
				curLevel.add(cur.val);
				if(isHead) {
					if (cur.left != null) {
						deque.addLast(cur.left);
					}
					if (cur.right != null) {
						deque.addLast(cur.right);
					}
				}else {
					if (cur.right != null) {
						deque.addFirst(cur.right);
					}
					if (cur.left != null) {
						deque.addFirst(cur.left);
					}
				}
			}
			ans.add(curLevel);
			isHead = !isHead;
		}
		return ans;
	}

}
