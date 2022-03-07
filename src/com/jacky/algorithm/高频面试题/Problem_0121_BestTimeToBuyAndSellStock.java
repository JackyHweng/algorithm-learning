package com.jacky.algorithm.高频面试题;

/**
 * 60leetcode高频题目全讲十二
 * <p>
 * 只能买卖一次
 * 股票买卖问题
 * 事后诸葛亮
 *
 * 以i位置的时机卖出，那么最大收益就是 arr[i] 减去 0 ~ i-1 位置上的最小值
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/4
 **/
public class Problem_0121_BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// 0...i 最小值
		int min = prices[0];
		int ans = 0;
		for (int i = 0; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}

}
