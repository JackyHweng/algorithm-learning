package com.jacky.algorithm.基础班.暴力递归和动态规划;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 打印字符串所有子序列
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/26
 **/
public class Code02_PrintAllSubsquences {

	public static List<String> subs(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process1(str, 0, ans, path);
		return ans;
	}

	/**
	 *  abc  -> abc ab ac bc b c ""
	 * @param str
	 * @param index
	 * @param ans 沿途路径的答案
	 * @param path 之前做出的决定 就是 path
	 */
	public static void process1(char[] str, int index, List<String> ans, String path) {
		// base case
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// 不选择就是追加""
		String no = path;
		process1(str, index + 1, ans, no);

		//  选择
		String yes = path + String.valueOf(str[index]);
		process1(str, index + 1, ans, yes);
	}

	/**
	 *  打印字符串所有子序列,要求不要出现重复的子序列
	 * @param s
	 * @return
	 */
	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		List<String> ans = new ArrayList<>();
		for (String cur : set) {
			ans.add(cur);
		}
		return ans;
	}

	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, set, yes);
	}

	public static void main(String[] args) {
		String test = "aacc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}

}
