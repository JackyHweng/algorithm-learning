package com.jacky.algorithm.经典面试题.第三期.class02;

/**
 * <p>
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分。
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的， 左部分最大值减去右部分最大值的绝对值。
 * </p>
 *
 * 1. 首先必须 左右两边都有 值，所以有 N-1 中分割的方法
 *
 * 流程：
 * 1. 找到数组中最大max, max - Math.min(arr[0],arr[N-1]) 就是答案？
 * 为什么？
 *
 * 首先如果max将数组划分为左右部分，那么max肯定是作为左部分或者右部分的最值，然后减去另一部分的最值,（利用了单调性,范围增大，max增大或者不变）
 *
 * 1. 情况一， 如果max 进入了左部分，ans = (max - 右边??),应该竟可能让右边的最大值尽量小(右部分肯定是包含N-1位置的数，那么只包含N-1的数就能保证右部分的最大值最小)
 * 2. 情况二， 如果max 进入了右部分，ans = (max - 左边??),应该竟可能让左边的最大值尽量小(左部分肯定是包含0位置的数，那么只包含0的数就能保证右部分的最大值最小)
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code03_MaxABSBetweenLeftAndRight {

	public static int maxABS1(int[] arr) {
		int res = Integer.MIN_VALUE;
		int maxLeft = 0;
		int maxRight = 0;
		for (int i = 0; i != arr.length - 1; i++) {
			maxLeft = Integer.MIN_VALUE;
			for (int j = 0; j != i + 1; j++) {
				maxLeft = Math.max(arr[j], maxLeft);
			}
			maxRight = Integer.MIN_VALUE;
			for (int j = i + 1; j != arr.length; j++) {
				maxRight = Math.max(arr[j], maxRight);
			}
			res = Math.max(Math.abs(maxLeft - maxRight), res);
		}
		return res;
	}

	public static int maxABS2(int[] arr) {
		int[] lArr = new int[arr.length];
		int[] rArr = new int[arr.length];
		lArr[0] = arr[0];
		rArr[arr.length - 1] = arr[arr.length - 1];
		for (int i = 1; i < arr.length; i++) {
			lArr[i] = Math.max(lArr[i - 1], arr[i]);
		}
		for (int i = arr.length - 2; i > -1; i--) {
			rArr[i] = Math.max(rArr[i + 1], arr[i]);
		}
		int max = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			max = Math.max(max, Math.abs(lArr[i] - rArr[i + 1]));
		}
		return max;
	}

	/**
	 * 这个才是最优解
	 * @param arr
	 * @return
	 */
	public static int maxABS3(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
		}
		return max - Math.min(arr[0], arr[arr.length - 1]);
	}

	public static int[] generateRandomArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i != arr.length; i++) {
			arr[i] = (int) (Math.random() * 1000) - 499;
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = generateRandomArray(200);
		System.out.println(maxABS1(arr));
		System.out.println(maxABS2(arr));
		System.out.println(maxABS3(arr));
	}
}
