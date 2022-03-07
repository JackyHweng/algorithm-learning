package com.jacky.algorithm.高频面试题;

/**
 * 57leetcode高频题目全讲九
 * <p>
 * 荷兰国旗问题
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: c
 **/
public class Problem_0075_SortColors {
	public static void sortColors(int[] nums) {
		int less = -1;
		int more = nums.length;
		int index = 0;
		while (index < more) {
			if (nums[index] == 1) {
				index++;
			} else if (nums[index] == 0) {
				swap(nums, index++, ++less);
			} else {
				swap(nums, index, --more);
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
