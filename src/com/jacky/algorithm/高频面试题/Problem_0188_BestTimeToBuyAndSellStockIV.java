package com.jacky.algorithm.高频面试题;

/**
 * 60leetcode高频题目全讲十二
 * <p>
 * 股票买卖,只能买k次
 *
 * 如果k > N/2 的时候，就是股票买卖的问题2的方式
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/4
 **/
public class Problem_0188_BestTimeToBuyAndSellStockIV {

	public static int maxProfit1(int K, int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}

		// k < N/2
		// dp [i][j] = 在 0...i 上 交易不超过j 次最大收益
		// 第一行 0-0 交易多少次都是0
		// 第一列 0-N-1 交易0次都是0

		// 1, i位置不参与交易 以 dp[i-1][j]
		// 2  i位置参加交易 贪心 除了最后一次交易外，不参与其他交易，只考虑最后一次卖出时机即可
		// 2.1 最后一次交易买入时机是i位置，也就是说 i买入， i卖出
		// 2.2 最后的买入时机是 i之前 , dp[k][j-1] + [i] + [k]
		int[][] dp = new int[N][K + 1];
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= K; j++) {
				// 1. [i] 位置不参与  dp[i-1][j] ,比如现在是dp[4][2]
				dp[i][j] = dp[i - 1][j];
				//2. 枚举，能够推高dp[i][j]
				// 在 0..p上做不超过k-1次交易 ，最后一次收益就是 【i】 - 【p】
				// 1. dp[5][1] + dp[5] - dp[5]
				// 1. dp[4][1] + dp[5] - dp[4]
				// 1. dp[3][1] + dp[5] - dp[3]
				// 1. dp[2][1] + dp[5] - dp[2]
				// 1. dp[1][1] + dp[5] - dp[1]
				// 1. dp[0][1] + dp[5] - dp[0]

				// 那么我现在要计算 dp[6][2] = Math.max(dp[5][2],[x][1] + [5] - [x]),那么就会出现重复枚举 dp[5][2] 的过程了，可以使用斜率优化
				for (int p = 0; p <= i; p++) {
					dp[i][j] = Math.max(dp[p][j - 1] + arr[i] - arr[p], dp[i][j]);
				}
			}
		}
		return dp[N - 1][K];
	}

	/**
	 * 优化！！！枚举行为可以用斜率优化
	 * @param K
	 * @param prices
	 * @return
	 */
	public static int maxProfit2(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			int pre = dp[j][0];
			int best = pre - prices[0];
			for (int i = 1; i < N; i++) {
				pre = dp[j - 1][i];
				dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
				best = Math.max(best, pre - prices[i]);
				ans = Math.max(dp[j][i], ans);
			}
		}
		return ans;
	}

	/**
	 * 就是股票问题2，求的就是全部上坡的收益
	 * @param prices
	 * @return
	 */
	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

}
