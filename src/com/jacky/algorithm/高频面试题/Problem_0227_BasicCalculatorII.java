package com.jacky.algorithm.高频面试题;

import java.util.LinkedList;

/**
 * <p>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 *
 * 67leetcode高频题目全讲十九
 * </p>
 *
 *
 * 1. 准备一个栈
 * 1.1. 要么遇到数字
 * 1.2. 要么遇到)
 * 1.3. 要么遇到 空格
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0227_BasicCalculatorII {

	public static int calculate(String s) {
		char[] str = s.toCharArray();
		LinkedList<String> list = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		builder.setLength(0);
		for (int i = 0; i < str.length; i++) {
			if (str[i] != ' ') {
				if (str[i] >= '0' && str[i] <= '9') {
					builder.append(str[i]);
				} else {
					handleStack(list, builder.toString(), str[i]);
					builder.setLength(0);
				}
			}
		}
		handleStack(list, builder.toString(), ' ');
		return computeStack(list);
	}

	public static void handleStack(LinkedList<String> list, String str, char op) {
		if (list.isEmpty() || (list.peekLast().equals("+") || list.peekLast().equals("-"))) {
			list.addLast(str);
		} else {
			int num = Integer.valueOf(str);
			String preOp = list.pollLast();
			int preNum = Integer.valueOf(list.pollLast());
			if (preOp.equals("*")) {
				list.addLast(String.valueOf(preNum * num));
			} else {
				list.addLast(String.valueOf(preNum / num));
			}
		}
		list.addLast(String.valueOf(op));
	}

	public static int computeStack(LinkedList<String> list) {
		int ans = Integer.valueOf(list.pollFirst());
		while (list.size() != 1) {
			String op = list.pollFirst();
			int cur = Integer.valueOf(list.pollFirst());
			if (op.equals("+")) {
				ans += cur;
			} else {
				ans -= cur;
			}
		}
		return ans;
	}

}
