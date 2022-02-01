package com.jacky.algorithm.高频面试题;

import java.util.*;

/**
 * <p>
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 *
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 * </p>
 *
 * 二叉树的递归套路
 *
 * 1. 抢 X 节点 ，子树最大值(不抢X左子树为头的，不抢X右子树为头的)  = a
 * 2. 不抢 X 节点 ，子树最大值(抢不抢X左子树为头的最大值，抢和不抢X右子树为头的最大值)  = b
 * 3. Math.max(a,b);
 * @author: HuangJiaJie
 * @create: 2022/1/30
 **/
public class Problem_0337_HouseRobberIII {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int rob(TreeNode root) {
		Info info = process(root);
		return Math.max(info.no, info.yes);
	}

	public static class Info {
		public int no;  // 整个子树，在不抢头节点的情况下，获得的最好收益
		public int yes; // 整个子树，在抢头节点的情况下，获得的最好收益

		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		// 不抢x的情况下，最好收
		int no = Math.max(leftInfo.no, leftInfo.yes) + Math.max(rightInfo.no, rightInfo.yes);
		// 抢x的情况下，最好收益
		int yes = x.val + leftInfo.no + rightInfo.no;
		return new Info(no, yes);
	}


	public int rob2(TreeNode root) {
		int[] rootStatus = dfs(root);
		return Math.max(rootStatus[0], rootStatus[1]);
	}

	public int[] dfs(TreeNode node) {
		if (node == null) {
			return new int[]{0, 0};
		}
		int[] l = dfs(node.left);
		int[] r = dfs(node.right);
		int selected = node.val + l[1] + r[1];
		int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
		return new int[]{selected, notSelected};
	}


	public static void main(String[] args) {
		String s1 = "this apple is sweet";
		String s2 = "this apple is sour";
		final String[] strings = uncommonFromSentences(s1, s2);
		System.out.println(strings);
	}
	public static String[] uncommonFromSentences(String s1, String s2) {
		List<String> ans = new ArrayList<>();
		String[] a1 = s1.split(" ");
		String[] a2 = s2.split(" ");
		Map<String,Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();

		for(String s : a1){
			map.put(s,map.getOrDefault(s, 0)+1);
		}

		for(String s: a1){
			set.add(s);
		}

		map.forEach((key,value) ->{
			if(value == 1){
				if(!set.contains(key)){
					ans.add(key);
				}
			}
		});
		return ans.toArray(new String[ans.size()]);
	}

}
