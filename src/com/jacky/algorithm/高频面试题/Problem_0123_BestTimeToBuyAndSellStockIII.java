package com.jacky.algorithm.高频面试题;


/**
 * <p>
 * 只能做2次交易
 * 需要在i位置做完2次交易，并且最后一次交易是在i位置的
 *
 * 0 到 i 位置，记录 0 到 i-1 位置做完一次交易并且减去一个买入交易的最优值
 * 比如 1，3，6，7，2，1，5，i ， 做完一次交易就是（1买入，7卖出），然后再选择以i位置为第二次卖出的位置的最优买入点（ 1 ）
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/4
 **/
public class Problem_0123_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		// 做完一次交易后 减去买入加个的最优, 为什么是 -prices[0], 以0 位置买入 ，0位置卖出，再以0位置作为新的买入点，prices[0] - prices[0] - prices[0]
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;// 0 : [0] - [0]
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			min = Math.min(min, prices[i]);
			//做完一次
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
