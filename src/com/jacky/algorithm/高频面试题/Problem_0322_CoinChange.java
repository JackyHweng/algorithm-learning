package com.jacky.algorithm.高频面试题;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * 解法：[1,2,5] , 11 块
 * 用动态规划, dp[i][j]  列是 自由使用[0...i]上的钱，j块最少的张数是多少？
 *
 * 分几种情况: 如何使用 [i] 位置上的值
 *
 * 0) 如果使用0张 [i], 那么就要满足 dp[0...i-1][j]
 * 1) 如果使用1张 [i], 那么就要满足 dp[0...i-1][j - i * 1] + 1
 * 2) 如果使用2张 [i], 那么就要满足 dp[0...i-1][j - i * 2] + 2
 * k) 如果使用1张 [i], 那么就要满足 dp[0...i-1][j - i * k] + k
 *
 * @author: HuangJiaJie
 * @create: 2022/1/12
 **/
public class Problem_0322_CoinChange {

	public static int coinChange1(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		}
		int N = coins.length;
		int[][] dp = new int[N + 1][amount + 1];
		for (int col = 1; col <= amount; col++) {
			dp[N][col] = -1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int rest = 0; rest <= amount; rest++) {
				dp[i][rest] = -1;
				if (dp[i + 1][rest] != -1) {
					dp[i][rest] = dp[i + 1][rest];
				}
				if (rest - coins[i] >= 0 && dp[i][rest - coins[i]] != -1) {
					if (dp[i][rest] == -1) {
						dp[i][rest] = dp[i][rest - coins[i]] + 1;
					} else {
						dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - coins[i]] + 1);
					}
				}
			}
		}
		return dp[0][amount];
	}

	public static int coinChange2(int[] coins, int aim) {
		if (coins == null || coins.length == 0 || aim < 0) {
			return -1;
		}
		int N = coins.length;
		int[][] dp = new int[N][aim + 1];
		// dp[i][0] = 0 0列不需要填
		// dp[0][1...] = arr[0]的整数倍，有张数，倍数，其他的格子-1（表示无方案）
		for (int j = 1; j <= aim; j++) {
			if (j % coins[0] != 0) {
				dp[0][j] = -1;
			} else {
				dp[0][j] = j / coins[0];
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= aim; j++) {
				// 这里给一个中间状态
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != -1) {
					dp[i][j] = dp[i - 1][j];
				}
				if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
					// 斜率优化
					dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
				}
				// 如果还是无效，那将用-1来记录
				if (dp[i][j] == Integer.MAX_VALUE) {
					dp[i][j] = -1;
				}
			}
		}
		return dp[N - 1][aim];
	}

	/**
	 * 假设 f(n) 代表要凑齐金额为 n 所要用的最少硬币数量，那么有：
	 *
	 * f(n) = min(f(n - c1), f(n - c2), ... f(n - cn)) + 1
	 * 其中 c1 ~ cn 为硬币的所有面额。
	 *
	 * ----
	 *
	 * 再具体解释一下这个公式吧，例如这个示例：
	 *
	 * 输入: coins = [1, 2, 5], amount = 11
	 * 输出: 3
	 * 解释: 11 = 5 + 5 + 1
	 * 题目求的值为 f(11)，第一次选择硬币时我们有三种选择。
	 *
	 * 假设我们取面额为 1 的硬币，那么接下来需要凑齐的总金额变为 11 - 1 = 10，即 f(11) = f(10) + 1，这里的 +1 就是我们取出的面额为 1 的硬币。
	 *
	 * 同理，如果取面额为 2 或面额为 5 的硬币可以得到：
	 *
	 * f(11) = f(9) + 1
	 * f(11) = f(6) + 1
	 * 所以：
	 *
	 * f(11) = min(f(10), f(9), f(6)) + 1
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange3(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					// dp[i - coins[j]] 不会越界，因为上面判断了
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

}
