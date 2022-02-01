package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 算法原型 ： 判断链表是否有环
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0287_FindTheDuplicateNumber {

	public static int findDuplicate(int[] nums) {
		if (nums == null || nums.length < 2) {
			return -1;
		}
		int slow = nums[0];
		int fast = nums[nums[0]];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0;
		while (slow != fast) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}

}
