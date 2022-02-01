package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * dp[i][j] 以 i，j为右下角的中正方形区域最大的1的范围
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/30
 **/
public class Problem_0221_MaximalSquare {

	public static int maximalSquare(char[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int[][] dp = new int[N + 1][M + 1];
		int max = 0;
		// 第一行
		for (int i = 0; i < N; i++) {
			if (m[i][0] == '1') {
				dp[i][0] = 1;
				max = 1;
			}
		}
		// 第一列
		for (int j = 1; j < M; j++) {
			if (m[0][j] == '1') {
				dp[0][j] = 1;
				max = 1;
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (m[i][j] == '1') {
					dp[i][j] = Math.min(
							// 左边 上边
							Math.min(dp[i - 1][j],
									dp[i][j - 1]),
							// 左上角
							dp[i - 1][j - 1])
                            // 自身的要算上
							+ 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max * max;

	}

	/**
	 *  简约版
	 * @param matrix
	 * @return
	 */
	public int maximalSquare2(char[][] matrix) {
		int maxSide = 0;
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return maxSide;
		}
		int rows = matrix.length, columns = matrix[0].length;
		int[][] dp = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (matrix[i][j] == '1') {
					if (i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					}
					maxSide = Math.max(maxSide, dp[i][j]);
				}
			}
		}
		int maxSquare = maxSide * maxSide;
		return maxSquare;
	}

}
