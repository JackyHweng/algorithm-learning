package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 *
 * 算法流程：
 * 特判，对于数组长度 nn，如果数组为 nullnull 或者数组长度小于 33，返回 [][]。
 * 对数组进行排序。
 * 遍历排序后数组：
 * 若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
 * 对于重复元素：跳过，避免出现重复解
 * 令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
 * 当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
 * 若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
 * 若和小于 00，说明 nums[L]nums[L] 太小，LL 右移
 *
 *
 * @author: HuangJiaJie
 * @create: 2022/2/10
 **/
public class Problem_0015_3Sum {

	public static List<List<Integer>> threeSum1(int[] nums) {
	    // 先将数组有序
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		// 第一个数选了i位置的数
		for (int i = 0; i < nums.length - 2; i++) {
			// 看当前的数是不是和之前的数相同,第一个数肯定没有左边的数
			if (i == 0 || nums[i - 1] != nums[i]) {
				// 将问题抛给二元组问题解决, 注意点 : i + 1
				List<List<Integer>> nexts = twoSum1(nums, i + 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					cur.add(0, nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	// nums已经有序了
	// nums[begin......]范围上，找到累加和为target的所有二元组
	public static List<List<Integer>> twoSum1(int[] nums, int begin, int target) {
		int L = begin;
		int R = nums.length - 1;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				// 看当前的数是不是和之前的数相同
				if (L == begin || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				// 这里的L++，需要等上面的计算玩了才可以移动
				L++;
			}
		}
		return ans;
	}

	public static List<List<Integer>> threeSum2(int[] nums) {
		Arrays.sort(nums);
		int N = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = N - 1; i > 1; i--) {
			if (i == N - 1 || nums[i] != nums[i + 1]) {
				List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					cur.add(nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
		int L = 0;
		int R = end;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				if (L == 0 || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				L++;
			}
		}
		return ans;
	}


	/**
	 * 排序 + 双指针
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum3(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		//排序
		Arrays.sort(nums);
		//双指针
		int len = nums.length;
		for(int i = 0;i < len;++i) {
			if(nums[i] > 0) return lists;

			if(i >0 && nums[i] == nums[i-1]) continue;

			int curr = nums[i];
			// 接下来利用双指针
			int L = i+1, R = len-1;
			while (L < R) {
				int tmp = curr + nums[L] + nums[R];
				if(tmp == 0) {
					List<Integer> list = new ArrayList<>();
					list.add(curr);
					list.add(nums[L]);
					list.add(nums[R]);
					lists.add(list);
					// ** 过滤重复的
					while(L < R && nums[L+1] == nums[L]) ++L;
					while (L < R && nums[R-1] == nums[R]) --R;
					++L;
					--R;
				} else if(tmp < 0) {
					++L;
				} else {
					--R;
				}
			}
		}
		return lists;
	}


}
