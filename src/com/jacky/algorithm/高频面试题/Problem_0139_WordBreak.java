package com.jacky.algorithm.高频面试题;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 大逻辑：
 *
 * aaaabaab [a,aa,aab,b]
 *
 * 1 以a 开头 aaabaab 可以划分多少中分解数
 * 2 以aa 开头 aabaab 可以划分多少中分解数
 * 3 以aab 开头 ,是没有的
 * 4 以b 开头 ,是没有的
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0139_WordBreak {

	public static boolean wordBreak(String s, List<String> wordDict) {
		Node root = new Node();
		for (String str : wordDict) {
			char[] chs = str.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		boolean[] dp = new boolean[N + 1];
		dp[N] = true;
		for (int i = N - 1; i >= 0; i--) {
			Node cur = root;
			for (int end = i; end < N; end++) {
				int path = str[end] - 'a';
				if (cur.nexts[path] == null) {
					break;
				}
				cur = cur.nexts[path];
				if (cur.end && dp[end + 1]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[0];
	}

	public static boolean wordBreak2(String s, List<String> wordDict) {
		return process(s, 0, new HashSet<>(wordDict)) != 0;
	}

	// s[0......index-1]这一段，已经分解过了，不用在操心
	// s[index.....] 这一段字符串，能够被分解的方法数，返回
	public static int process(String s, int index, HashSet<String> wordDict) {
		if (index == s.length()) {
			// 能够到达最后一个位置说明说明之前哟一个答案
			return 1;
		}
		// index没到最后
		// index...index
		// index...index+1
		// index....index+2
		// index....end
		int ways = 0;
		for (int end = index; end < s.length(); end++) {
			// s[index....end]
            // end 是开区间
			String pre = s.substring(index, end + 1);
			if (wordDict.contains(pre)) {
				ways += process(s, end + 1, wordDict);
			}
		}
		return ways;
	}

	public static boolean wordBreak3(String s, List<String> wordDict) {
		HashSet<String> set = new HashSet<>(wordDict);
		int N = s.length();
		int[] dp = new int[N + 1];
		// dp[i] = process(s, i, set)的返回值
		dp[N] = 1;
		for (int index = N - 1; index >= 0; index--) {
			int ways = 0;
			for (int end = index; end < s.length(); end++) {
				// s[index....end]
				String pre = s.substring(index, end + 1);
				if (set.contains(pre)) {
					ways += dp[end + 1];
				}
			}
			dp[index] = ways;
		}
		return dp[0] != 0;
	}

	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	public static boolean wordBreak4(String s, List<String> wordDict) {
		Node root = new Node();
		// 构建前缀树
		for (String str : wordDict) {
			char[] chs = str.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}

		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			Node cur = root;
			for (int end = i; end < N; end++) {
				cur = cur.nexts[str[end] - 'a'];
				if (cur == null) {
					break;
				}
				if (cur.end) {
					dp[i] += dp[end + 1];
				}
			}
		}
		return dp[0] != 0;
	}

}
