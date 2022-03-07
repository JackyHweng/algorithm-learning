package com.jacky.algorithm.高频面试题;

/**
 * 57leetcode高频题目全讲九
 * <p>
 * 开方 (需要注意Integer.MIN_VALUE 的问题)
 * </p>
 *
 * 1 二分一直找左边界
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0069_SqrtX {

	// x一定非负，输入可以保证
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		if (x < 3) {
			return 1;
		}
		// 需要注意Integer.MIN_VALUE 的问题
		long ans = 1;
		long L = 1;
		long R = x;
		long M = 0;
		while (L <= R) {
			M = (L + R) / 2;
			if (M * M <= x) {
				ans = M;
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return (int) ans;
	}

	public int mySqrt2(int x) {
		int l = 0, r = x, ans = -1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if ((long) mid * mid <= x) {
			    // 找到最近的
				ans = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return ans;
	}
}
