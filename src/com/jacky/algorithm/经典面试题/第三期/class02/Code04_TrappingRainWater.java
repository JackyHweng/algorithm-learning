package com.jacky.algorithm.经典面试题.第三期.class02;


/**
 *
 * 19经典面试题目三
 *
 * <p>
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 * </p>
 *
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 *
 * 流程:
 *
 * 1. 首先将问题拆分为 i位置的水量，那么i位置的水量是和左边的最大值和右边的最大值有关的
 * 2. iwater = Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
 * 3. 左右的边界是不可能是有水量的(因为他们左最大值 和 有最大值)
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code04_TrappingRainWater {

	/*
	 * 给定一个正整数数组arr，把arr想象成一个直方图。返回这个直方图如果装水，能装下几格水？
	 * 
	 */
	/**
	 * 首先将问题拆分为 i位置的水量，那么i位置的水量是和左边的最大值和右边的最大值有关的
	 * iwater = Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
	 * 左右的边界是不可能是有水量的
	 *
	 * @param arr
	 * @return
	 */
	public static int water4(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		// 为什么是从0开始，因为最左的位置是不可能有水量的
		int L = 1;
		int leftMax = arr[0];
		// 为什么是从N -2 开始，因为最右的位置是不可能有水量的
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		// 利用双指针开始通知水量
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

	public static int water1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int water = 0;
		for (int i = 1; i < N - 1; i++) {
			int leftMax = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			int rightMax = Integer.MIN_VALUE;
			for (int j = i + 1; j < N; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			water += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
		}
		return water;
	}


	public static int water2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] leftMaxs = new int[N];
		leftMaxs[0] = arr[0];
		for (int i = 1; i < N; i++) {
			leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
		}

		int[] rightMaxs = new int[N];
		rightMaxs[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
		}
		int water = 0;
		for (int i = 1; i < N - 1; i++) {
			water += Math.max(Math.min(leftMaxs[i - 1], rightMaxs[i + 1]) - arr[i], 0);
		}
		return water;
	}

	/**
	 * 辅助数组的方式
	 * @param arr
	 * @return
	 */
	public static int water3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] rightMaxs = new int[N];
		rightMaxs[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
		}
		int water = 0;
		int leftMax = arr[0];
		for (int i = 1; i < N - 1; i++) {
			water += Math.max(Math.min(leftMax, rightMaxs[i + 1]) - arr[i], 0);
			leftMax = Math.max(leftMax, arr[i]);
		}
		return water;
	}


	// for test
	public static int[] generateRandomArray(int len, int value) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int value = 200;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, value);
			int ans1 = water1(arr);
			int ans2 = water2(arr);
			int ans3 = water3(arr);
			int ans4 = water4(arr);
			if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
