package com.jacky.algorithm.高频面试题;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/2/28
 **/
public class Problem_0020_ValidParentheses {

	public static boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] str = s.toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
//		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length; i++) {
			char cha = str[i];
			if (cha == '(' || cha == '[' || cha == '{') {
				stack.addFirst(cha == '(' ? ')' : (cha == '[' ? ']' : '}'));
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char last = stack.pollFirst();
				if (cha != last) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

}
