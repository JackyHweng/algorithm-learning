package com.jacky.algorithm.基础班.暴力递归和动态规划;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

	/**
	 * 翻转一个栈
	 * @param stack
	 */
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = f(stack);
		reverse(stack);
		stack.push(i);
	}

	/**
	 * 将栈中最低的元素拿出来
	 * @param stack
	 * @return
	 */
	public static int f(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
