package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0163_MissingRanges {

	public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> ans = new ArrayList<>();
		for (int num : nums) {
			if (num > lower) {
				ans.add(miss(lower, num - 1));
			}
			if (num == upper) {
				return ans;
			}
			lower = num + 1;
		}

		// 最后还要看当前的lower是否俾upper小
		if (lower <= upper) {
			ans.add(miss(lower, upper));
		}
		return ans;
	}

	// 生成"lower->upper"的字符串，如果lower==upper，只用生成"lower"
	public static String miss(int lower, int upper) {
		String left = String.valueOf(lower);
		String right = "";
		if (upper > lower) {
			right = "->" + String.valueOf(upper);
		}
		return left + right;
	}

}
