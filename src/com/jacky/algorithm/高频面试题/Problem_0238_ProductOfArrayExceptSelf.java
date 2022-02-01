package com.jacky.algorithm.高频面试题;

/**
 * <p>
 *  给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0238_ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {
		int zeros = 0;
		int all = 1;
		for (int num : nums) {
			if (num == 0) {
				zeros++;
			} else {
				all *= num;
			}
		}
		if (zeros > 1) {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = 0;
			}
		} else {
			if (zeros == 0) {
				for (int i = 0; i < nums.length; i++) {
					nums[i] = all / nums[i];
				}
			} else {
				for (int i = 0; i < nums.length; i++) {
					nums[i] = nums[i] == 0 ? all : 0;
				}
			}
		}
		return nums;
	}
	
	public int[] productExceptSelf2(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];
		ans[0] = nums[0];
		for (int i = 1; i < n; i++) {
			ans[i] = ans[i - 1] * nums[i];
		}
		int right = 1;
		for (int i = n - 1; i > 0; i--) {
			ans[i] = ans[i - 1] * right;
			right *= nums[i];
		}
		ans[0] = right;
		return ans;
	}

}
