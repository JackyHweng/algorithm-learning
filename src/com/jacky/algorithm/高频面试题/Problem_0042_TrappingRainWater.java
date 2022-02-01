package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 接雨水问题
 * 1） i 位置上可以接多少水？而i位置上可以装多少水是有左侧最大值和右侧最大值决定的
 *  水量 [i] = Math.min(左边最大， 右边最大) - [i]
 *  为了防止i 大于 左侧最大值和右侧最大值
 *  水量 [i] = Math.max(0,Math.min(左边最大， 右边最大) - [i])
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0042_TrappingRainWater {

	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
