package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * </p>
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 1. dp[i] ,相邻不能被盗的最大值
 * 2. dp[i] 不截 i 位置的数，那么最大值就是 dp[i-1]
 * 3. 如果截取i位置的数，那么最大值就是 i + dp[i-2]
 * 4. 最后就是求 2，3 条件的最大值
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0198_HouseRobber {

	public static int rob1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int N = nums.length;
		int[] dp = new int[N];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < N; i++) {
			dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
		}
		return dp[N - 1];
	}

	public static int rob2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int pre2 = nums[0];
		int pre1 = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			int cur = Math.max(pre1, nums[i] + pre2);
			pre2 = pre1;
			pre1 = cur;
		}
		return pre1;
	}

}
