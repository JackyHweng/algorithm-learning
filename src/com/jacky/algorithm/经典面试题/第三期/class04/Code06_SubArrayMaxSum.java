package com.jacky.algorithm.经典面试题.第三期.class04;

/**
 * <p>
 * 给定一个数组arr，返回子数组的最大累加和。
 * </p>
 *
 *
 * 流程:
 *
 * 需要 2个变量 cur, max
 *
 * 1. 从左往右遍历，将当前数累加到cur
 * 2. 然后将cur和max 比较，如果 cur 是大于 max 的
 * 3. 重点：如果此时cur 是小于0 的 ，将cur 重置成0
 *
 *
 * ps : 为什么 cur 如果是小于0就要重置成0呢？
 *
 * 证明：
 * 1、 如果数组都是正数
 * 2、 如果数组都是负数, 肯定是支持的，因为全是负数，每次累加的时候否不需要前面的(只需要max包含到最大值就可以了)
 * 3、 如果数组有正数也有负数,假设数组中有一个最长最大的子数组 arr[i]
 *
 * 也就是说 数组 arr[0.... N] , 如果答案是 arr[ i ... j] 的话那么这个子数组的累加和也是大于0的，那么 arr[ k ... i-1] 的累加和肯定不会大于0（如果是大于0的话，那么肯定会包含在答案里面）
 *
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code06_SubArrayMaxSum {

	public static int maxSum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i != arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			// cur 其实算的是 k...i-1 上的累加和,k <= i
			// 如果累加和是小于0的话，那么这个子数组肯定是不会包含在答案中的
			// 所以重置cur
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
		System.out.println(maxSum(arr1));

		int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
		System.out.println(maxSum(arr2));

		int[] arr3 = { -2, -3, -5, -1 };
		System.out.println(maxSum(arr3));

	}

}
