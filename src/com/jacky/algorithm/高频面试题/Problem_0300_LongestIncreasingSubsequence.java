package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 最长递增子序列
 * 参考 ： com.jacky.algorithm.followup.IndexTreeTest
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 *
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/27
 **/
public class Problem_0300_LongestIncreasingSubsequence {

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}


	public static void main(String[] args) {

		int[] arr = {2,3,7,101,1};
		System.out.println(lengthOfLIS(arr));
	}

}
