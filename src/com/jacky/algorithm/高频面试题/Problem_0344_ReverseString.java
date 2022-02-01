package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 字符串逆序
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/27
 **/
public class Problem_0344_ReverseString {

	public void reverseString(char[] s) {
		int l = 0;
		int r = s.length - 1;
		while (l < r) {
			char tmp = s[l];
			s[l++] = s[r];
			s[r--] = tmp;
		}
	}

}
