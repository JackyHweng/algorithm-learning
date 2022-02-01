package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 	给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素
 *
 *
 *
 * 1. 一次删除掉不同的2个数，最终剩下来的就是答案
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0169_MajorityElement {

	public static int majorityElement(int[] nums) {
		int cand = 0;
		int HP = 0;
		for (int i = 0; i < nums.length; i++) {
			if (HP == 0) {
				cand = nums[i];
				HP = 1;
			} else if (nums[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		return cand;
	}

}
