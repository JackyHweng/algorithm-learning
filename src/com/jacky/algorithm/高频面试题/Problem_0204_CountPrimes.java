package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 统计所有小于非负整数 n 的质数的数量。
 * </p>
 *
 * 1.
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0204_CountPrimes {

	public static int countPrimes(int n) {
		if (n < 3) {
			return 0;
		}
		//
		boolean[] f = new boolean[n];
		int count = n / 2;
		for (int i = 3; i * i < n; i += 2) {
			if (f[i]) {
				continue;
			}
			for (int j = i * i; j < n; j += 2 * i) {
				if (!f[j]) {
					--count;
					f[j] = true;
				}
			}
		}
		return count;
	}

}
