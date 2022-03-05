package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 翻转整数
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/21
 **/
public class Problem_0007_ReverseInteger {

	public static int reverse(int x) {
		boolean neg = ((x >>> 31) & 1) == 1;
		x = neg ? x : -x;
		// 系统最小 / 10
		int m = Integer.MIN_VALUE / 10;
		// 系统最小 % 10
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
		    // 检测溢出
			if (res < m || (res == m && x % 10 < o)) {
				return 0;
			}
			// res 获取 x 上的个位数
			res = res * 10 + x % 10;
			// x 去掉个位数
			x /= 10;
		}
		return neg ? res : Math.abs(res);
	}


	public int reverse2(int x) {
		int rev = 0;
		while (x != 0) {
			if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			x /= 10;
			rev = rev * 10 + digit;
		}
		return rev;
	}

	public static void main(String[] args) {
//		-2147483648
		System.out.println(Integer.MIN_VALUE);
//		2147483647
		System.out.println(Integer.MAX_VALUE);
	}

}
