package com.jacky.algorithm.基础班.暴力递归和动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字符串的全部排列
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/26
 **/
public class Code03_PrintAllPermutations {

	public static ArrayList<String> permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		return res;
	}

	// str[i...] i和之后的位置的字符都有可能来到i位置
    // i 终止位置
	public static void process(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
		}

		// i 没有到终止位置，i... 都可以来到i位置
		for (int j = i; j < str.length; j++) {
			swap(str, i, j);
			process(str, i + 1, res);
			// 恢复现场, process 执行完了回溯需要恢复线程
			swap(str, i, j);
		}
	}

	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process2(chs, 0, res);
		return res;
	}

	public static void process2(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
		}
		// 分支限界，可以杀死之前走做的路, 和记忆化搜索是不一样的
		boolean[] visit = new boolean[26]; // visit[0 1 .. 25]
		for (int j = i; j < str.length; j++) {
			if (!visit[str[j] - 'a']) {
				visit[str[j] - 'a'] = true;
				swap(str, i, j);
				process2(str, i + 1, res);
				swap(str, i, j);
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutation(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutationNoRepeat(s);
		for (String str : ans2) {
			System.out.println(str);
		}

	}

}
