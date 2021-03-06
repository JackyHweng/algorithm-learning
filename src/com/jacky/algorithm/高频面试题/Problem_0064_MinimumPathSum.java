package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 * </p>
 *
 * 经典的动态规划
 * 由于路径的方向只能是向下或向右，因此网格的第一行的每个元素只能从左上角元素开始向右移动到达，
 * 网格的第一列的每个元素只能从左上角元素开始向下移动到达，此时的路径是唯一的，因此每个元素对应的最小路径和即为对应的路径上的数字总和。
 *
 * 对于不在第一行和第一列的元素，可以从其上方相邻元素向下移动一步到达，或者从其左方相邻元素向右移动一步到达，
 * 元素对应的最小路径和等于其上方相邻元素与其左方相邻元素两者对应的最小路径和中的最小值加上当前元素的值。
 * 由于每个元素对应的最小路径和与其相邻元素对应的最小路径和有关，因此可以使用动态规划求解。
 *
 * 创建二维数组 {dp}dp，与原始网格的大小相同，{dp}[i][j]dp[i][j] 表示从左上角出发到 (i,j)(i,j) 位置的最小路径和。
 * 显然，{dp}[0][0]={grid}[0][0]dp[0][0]=grid[0][0]。对于 {dp}dp 中的其余元素，通过以下状态转移方程计算元素值。
 *
 * 当 i>0i>0 且 j=0j=0 时，{dp}[i][0]={dp}[i-1][0]+{grid}[i][0]dp[i][0]=dp[i−1][0]+grid[i][0]。
 *
 * 当 i=0i=0 且 j>0j>0 时，{dp}[0][j]={dp}[0][j-1]+{grid}[0][j]dp[0][j]=dp[0][j−1]+grid[0][j]。
 *
 * 当 i>0i>0 且 j>0j>0 时，{dp}[i][j]=min({dp}[i-1][j],{dp}[i][j-1])+{grid}[i][j]dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
 *
 * 最后得到 {dp}[m-1][n-1]dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
 *
 * @author: HuangJiaJie
 * @create: 2022/1/30
 **/
public class Problem_0064_MinimumPathSum {


	/**
	 * 比较好理解的版本
	 * @param grid
	 * @return
	 */
	public int minPathSum4(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int rows = grid.length, columns = grid[0].length;
		int[][] dp = new int[rows][columns];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < rows; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < columns; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < columns; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[rows - 1][columns - 1];
	}

	public static int minPathSum(int[][] grid) {
		if (grid == null || grid[0] == null) {
			return 0;
		}
		int N = grid.length;
		int M = grid[0].length;
		if (N == 0 || M == 0) {
			return 0;
		}
		// 其实应该是一张二维表，但是用了空间压缩技巧
		int[] dp = new int[M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
			    // 如果是第一行 或者是 第一列 那么就是不需要依赖 左边 和 上边的位置
				if (i == 0 && j == 0) {
					dp[j] = grid[i][j];
				} else {
					dp[j] = Math.min((i > 0 ? dp[j] : Integer.MAX_VALUE), (j > 0 ? dp[j - 1] : Integer.MAX_VALUE))
							+ grid[i][j];
				}

			}
		}
		return dp[M - 1];
	}

	/**
	 * 如果是二维表的写法
	 * @param grid
	 * @return
	 */
	public static int minPathSum2(int[][] grid) {
		if (grid == null || grid[0] == null) {
			return 0;
		}
		int N = grid.length;
		int M = grid[0].length;
		if (N == 0 || M == 0) {
			return 0;
		}
		// 其实应该是一张二维表，但是用了空间压缩技巧
		int[] dp = new int[M];
		// dp数组变成，想象中二维表的第0行数据
		// m : 3 2 1 6 3 5 6 12
		dp[0] = grid[0][0];
		for (int i = 1; i < M; i++) {
			dp[i] = dp[i - 1] + grid[0][i];
		}
		for (int i = 1; i < N; i++) {
			// dp此时是想象中二维表的第i-1行数据
			// 想更新成，想象中二维表的第i行数据
			// dp[0]
			dp[0] = dp[0] + grid[i][0];
			for (int j = 1; j < M; j++) {
				dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
			}
		}
		return dp[M - 1];
	}

}
