package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 1. 无进位相加
 * 2. 然后求出进位信息
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/29
 **/
public class Problem_0371_SumOfTwoIntegers {

	public static int getSum(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

	public int getSum2(int a, int b) {
		while (b != 0) {
			// 进位信息
			int carry = (a & b) << 1;
			// 无进位相加的结果
			a = a ^ b;
			// 将 b 复用保存进位信息
			b = carry;
		}
		return a;
	}

}
