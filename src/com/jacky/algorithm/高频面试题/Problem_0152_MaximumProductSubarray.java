package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
 * </p>
 *
 * 1. 一个数组中子数组的数目是 N^2 个的
 *
 * 对于数组的最值问题可以利用 以i位置结尾的思想来解题，从其中获取最符合的答案 本质就是利用动态规划来加速
 *
 *
 * 分情况
 *
 * 1. 以i位置结尾的
 * 2. 不以i位置结尾的
 * 2.1  [i-1] 上最大累乘积 * [i]      -1 2 4 6 （4 的最大累乘积为8 ，那么6的最大乘积就是 6 * 8 = 68）
 * 2.2. [i-1] 上最小累乘积 * [i]      2 -3 -6 （3 的最大累乘积为2 最小的乘积为 -6 ，那么6的最大乘积就是应该是 -6 * -6 = 36 和 2 中的最大值）
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0152_MaximumProductSubarray {

	public static int maxProduct(int[] nums) {
		int ans = nums[0];
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			// 每一步都是要 分析3个条件的最值问题
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			// 变量滚动
			min = curmin;
			max = curmax;
			ans  = Math.max(ans, max);
		}
		return ans;
	}

}
