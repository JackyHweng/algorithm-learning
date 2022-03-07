package com.jacky.algorithm.高频面试题;

import java.util.Stack;

/**
 * 58leetcode高频题目全讲十
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * </p>
 *
 * 1. 这个和接雨水问题是不一样的，（接雨水需要知道左边右边比较小的值，不需要知道是否是最近）
 * 2. 但是这个问题需要知道i位置左右两边最近最小的值
 *
 *
 * 流程：
 *
 * 1. 准备一个栈，栈低是最小的，如果遇到比栈顶还小的，弹出栈，结算
 * 2. 注意的是，等于的时候也要弹出，虽然这次算的答案是不对的，但是最后弹出所有栈的时候会结算前面的 (可能性的舍弃)
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0084_LargestRectangleInHistogram {

	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		// 只放下标
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				// 当亲左边比他小的最近的点
				int k = stack.isEmpty() ? -1 : stack.peek();
				// 当前右边比他小的最近的点
				// 结算
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

}
