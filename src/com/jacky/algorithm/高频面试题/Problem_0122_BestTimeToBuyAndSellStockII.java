package com.jacky.algorithm.高频面试题;

/**
 * 60leetcode高频题目全讲十二
 * <p>
 * 可以无限次买卖
 *
 * 满足 ： arr[i] - arr[i-1] > 0 ，那么这个就是可以累计到ans去
 * 实质就是分批算 上升的区间
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/4
 **/
public class Problem_0122_BestTimeToBuyAndSellStockII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
		}
		return ans;
	}
	
}
