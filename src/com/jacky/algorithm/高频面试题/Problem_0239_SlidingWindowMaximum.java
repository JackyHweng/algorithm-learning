package com.jacky.algorithm.高频面试题;

import java.util.LinkedList;

public class Problem_0239_SlidingWindowMaximum {

	public static int[] maxSlidingWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		// 最大值, 利用双向队列 维持 大 -> 小 的数据状况
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int R = 0; R < arr.length; R++) {
		    // 违反 大 到小的逻辑, 一次弹出
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
				qmax.pollLast();
			}
			qmax.addLast(R);
			// 窗口过期小标
			if (qmax.peekFirst() == R - w) {
				qmax.pollFirst();
			}
			// R 是从0 开始
			if (R >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

}
