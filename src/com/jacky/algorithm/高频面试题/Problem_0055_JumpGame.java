package com.jacky.algorithm.高频面试题;

/**
 *
 * 57leetcode高频题目全讲九
 * <p>
 *
 * </p>
 *
 * 1.
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0055_JumpGame {


	public static boolean canJump(int[] nums) {
		if (nums == null || nums.length < 2) {
			return true;
		}
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
//			if (max >= nums.length - 1) {
//				return true;
//			}
			// 不能调到下一步
			if (i > max) {
				return false;
			}
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}

}
