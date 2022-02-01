package com.jacky.algorithm.高频面试题;

/**
 * 63leetcode高频题目全讲十五
 * <p>
 *
 *  给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 1. 异或满足交换律和结合律
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 *
 * 任何数和 00 做异或运算，结果仍然是原来的数，即 a ^ 0=a 。
 * 任何数和其自身做异或运算，结果是 0，即 a ^ a=0
 * 异或运算满足交换律和结合律
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0136_SingleNumber {

	public static int singleNumber(int[] nums) {
		int eor = 0;
		for (int num : nums) {
			eor ^= num;
		}
		return eor;
	}

}
