package com.jacky.algorithm.高频面试题;

/**
 * 65leetcode高频题目全讲十七
 * <p>
 *  注意这里不是 26进制
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0171_ExcelSheetColumnNumber {

	// 这道题反过来也要会写
	public static int titleToNumber(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			ans = ans * 26 + (str[i] - 'A') + 1;
		}
		return ans;
	}

}
