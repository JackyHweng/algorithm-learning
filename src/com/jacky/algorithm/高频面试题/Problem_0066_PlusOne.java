package com.jacky.algorithm.高频面试题;

/**
 * 57leetcode高频题目全讲九
 * <p>
 * 当前数加1
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0066_PlusOne {

	public static int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		// 如果全是9，那么必然会走到这里
		int[] ans = new int[n + 1];
		ans[0] = 1;
		return ans;
	}

}
