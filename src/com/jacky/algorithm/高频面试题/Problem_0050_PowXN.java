package com.jacky.algorithm.高频面试题;

/**
 * 55leetcode高频题目全讲七
 * <p>
 * 快速幂
 * 算一个double数的n次方
 * </p>
 *
 * 模拟：假设求 a^75 ，将75求出二进制的值 1001011 ，
 * 可以求出 a^1 ,a^2,a^4,a^8 ,a^16,a^32 那么 res = a^1 * a^2 * a^8 * a^64
 *
 * 流程
 * 1. 将幂数每次求最左的位数，如果是等于1说明当前次幂需要累乘到结果中
 * 2. pow >> 1,继续求下一位的最左的位数，t = t*t
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0050_PowXN {

	/**
	 * 方法二的才是对的
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow1(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		// 系统最小值是不能转正数
        // 如果 |x|  < 1
		if (n == Integer.MIN_VALUE) {
			return (x == 1D || x == -1D) ? 1D : 0;
		}

		int pow = Math.abs(n);
		// 一开始t等于x的一次方
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			// pow 右移
			pow >>= 1;
			t = t * t;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	/**
	 * 这个才是对的 !!
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		// 系统最小值是不能转正数
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		// 由于之前加1了，所以需要补回来
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	public static void main(String[] args) {
		System.out.println("world shut up!");
		int a = Integer.MIN_VALUE;
		int b = -a;
		System.out.println(b);

		System.out.println("==============");

		double test = 1.00000001D;
		int N = Integer.MIN_VALUE;
		System.out.println(test == 1D);
		System.out.println(test + "的" + N + "次方，结果：");
		System.out.println(Math.pow(test, (double) N));
		System.out.println(myPow1(test, N));
		System.out.println(myPow2(test, N));
	}

}
