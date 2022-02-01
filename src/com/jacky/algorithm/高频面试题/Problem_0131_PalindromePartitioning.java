package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串
 *
 * 解决:
 * 1. 利用 dp[L][R] 表示str L 到 R 是否为回文, 应该满足 dp[L] == [R] 并且 [L+1...R-1] 是回文
 * 2. 利用生成的dp 加速深度优先遍历 dfs
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0131_PalindromePartitioning {

	public static List<List<String>> partition(String s) {
		// dp[L][R] -> 是不是回文
		boolean[][] dp = getdp(s.toCharArray());
		LinkedList<String> path = new LinkedList<>();
		List<List<String>> ans = new ArrayList<>();
		process(s, 0, path, dp, ans);
		return ans;
	}

	public static boolean[][] getdp(char[] str) {
		int N = str.length;
		boolean[][] dp = new boolean[N][N];
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = true;
			dp[i][i + 1] = str[i] == str[i + 1];
		}
		dp[N - 1][N - 1] = true;
		for (int j = 2; j < N; j++) {
			int row = 0;
			int col = j;
			while (row < N && col < N) {
				dp[row][col] = str[row] == str[col] && dp[row + 1][col - 1];
				row++;
				col++;
			}
		}
		return dp;
	}

	// s 字符串
	// s[0...index-1] 已经做过的决定，放入了path中
	// 在index开始做属于这个位置的决定，
	// index == s.len  path之前做的决定（一种分割方法），放进总答案ans里
	public static void process(String s, int index, LinkedList<String> path, 
			boolean[][] dp, List<List<String>> ans) {
		if (index == s.length()) {
			ans.add(copy(path));
		} else {
			for (int end = index; end < s.length(); end++) {
				// index..index   
				// index..index+1
				// index..index+2
				// index..end
				if (dp[index][end]) {
					path.addLast(s.substring(index, end + 1));
					process(s, end + 1, path, dp, ans);
					// 开始下次分支之前需要将path回复现场
					path.pollLast();
				}
			}
		}
	}

	public static List<String> copy(List<String> path) {
		List<String> ans = new ArrayList<>();
		for (String p : path) {
			ans.add(p);
		}
		return ans;
	}

}
