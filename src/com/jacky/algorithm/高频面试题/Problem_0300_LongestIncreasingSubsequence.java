package com.jacky.algorithm.高频面试题;

import java.util.Arrays;

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



	/**
	 * dp
	 * 状态定义：
	 *
	 * dp[i]dp[i] 的值代表 nums 以 nums[i]nums[i] 结尾的最长子序列长度。
	 * 转移方程： 设 j∈[0,i)j∈[0,i)，考虑每轮计算新 dp[i]dp[i] 时，遍历 [0,i)[0,i) 列表区间，做以下判断：
	 *
	 * 当 nums[i] > nums[j]nums[i]>nums[j] 时： nums[i]nums[i] 可以接在 nums[j]nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j] + 1dp[j]+1 ；
	 * 当 nums[i] <= nums[j]nums[i]<=nums[j] 时： nums[i]nums[i] 无法接在 nums[j]nums[j] 之后，此情况上升子序列不成立，跳过。
	 * 上述所有 1. 情况 下计算出的 dp[j] + 1dp[j]+1 的最大值，为直到 ii 的最长上升子序列长度（即 dp[i]dp[i] ）。实现方式为遍历 jj 时，每轮执行 dp[i] = max(dp[i], dp[j] + 1)dp[i]=max(dp[i],dp[j]+1)。
	 * 转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
	 * 初始状态：
	 *
	 * dp[i]dp[i] 所有元素置 11，含义是每个元素都至少可以单独成为子序列，此时长度都为 11。
	 * 返回值：
	 *
	 * 返回 dpdp 列表最大值，即可得到全局最长上升子序列长度。
	 * 复杂度分析：
	 * 时间复杂度 O(N^2)O(N
	 * 2
	 *  ) ： 遍历计算 dpdp 列表需 O(N)O(N)，计算每个 dp[i]dp[i] 需 O(N)O(N)。
	 * 空间复杂度 O(N)O(N) ： dpdp 列表占用线性大小额外空间。
	 *
	 */

	public static int lengthOfLISDP(int[] nums) {
		if(nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int res = 0;
		Arrays.fill(dp, 1);
		for(int i = 0; i < nums.length; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[j] < nums[i]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
	/**
	 * 二分 + 贪心
	 * @param arr
	 * @return
	 */
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
