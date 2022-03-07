package com.jacky.algorithm.高频面试题;


/**
 * 60leetcode高频题目全讲十二
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
		    // doneOnceMinusBuyMax 是完成一次买卖再加上买入价格的 ，所以直接加上i的
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);

			//做完一次的最好收入doneOnceMax（就是股票问题1的问题）
			min = Math.min(min, prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			// [1,2,2,8,1,10,9,9,3] 假设需要求i的完成一次交易 + 买入最好收益
			// 第一种 可以i位置买入时机无关， 1买入 8卖出 1买入 所以3的买入最好收益 以8的为主  c1= （ 8 - 1  - 1）
			// 第二种，和i的买入时机有关，1 买入 10 卖出，那么只能3买入 c2 = ( 10 - 1 - 3)
			// 这里有2中可能性，Math.max(c1,c2) 的最大值
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
