package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 相关基础 卡特兰数
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0022_GenerateParentheses {

	public static List<String> generateParenthesis(int n) {
		// 沿途的决策
		char[] path = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process(path, 0, 0, n, ans);
		return ans;
	}

	// 依次在path上填写决定
	// ( ( ) ) ( )....
	// 0 1 2 3 4 5
	// path[0...index-1]决定已经做完了
	// index位置上，( )
	// leftMinusRight 左括号做过的决策
    // leftRest 多少个左括号可以决定的
	public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
		if (index == path.length) {
			ans.add(String.valueOf(path));
		} else {
			// 可以剩余可以做左括号的数量
			if (leftRest > 0) {
				path[index] = '(';
				process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
			}
			// 这么可以做右括号的决定，左括号 - 括号的数量大于0
			if (leftMinusRight > 0) {
				path[index] = ')';
				process(path, index + 1, leftMinusRight - 1, leftRest, ans);
			}
		}
	}

	// 不剪枝的做法
	public static List<String> generateParenthesis2(int n) {
		char[] path = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process2(path, 0, ans);
		return ans;
	}

	/**
	 * 不剪枝的版本
	 * @param path
	 * @param index
	 * @param ans
	 */
	public static void process2(char[] path, int index, List<String> ans) {
		if (index == path.length) {
			if (isValid(path)) {
				ans.add(String.valueOf(path));
			}
		} else {
			path[index] = '(';
			process2(path, index + 1, ans);
			path[index] = ')';
			process2(path, index + 1, ans);
		}
	}

	public static boolean isValid(char[] path) {
		int count = 0;
		for (char cha : path) {
			if (cha == '(') {
				count++;
			} else {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

}
