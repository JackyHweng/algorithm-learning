package com.jacky.algorithm.高频面试题;

public class Problem_0070_ClimbingStairs {

	/**
	 * 0 台阶 也是有一种爬法
	 * @param n
	 * @return
	 */
	public int climbStairs4(int n) {

		int[] dp = new int[n +1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i <= n ; i++){
			dp[i] = dp[i -1] + dp[i -2];
		}
		return dp[n];
	}


	public static int climbStairs2(int n){
	    if(n == 1){
	    	return 1;
		}

	    int[] dp = new int[n + 1];
	    dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i < n ;i++){
	    	dp[i] = dp[i-1] + dp[i - 2];
		}
		return dp[n];
	}

	public static int climbStairs3(int n){
		if(n == 1){
			return 1;
		}

		int first = 1;
		int second = 2;
		for(int i = 3; i < n ;i++){
		    int temp = second + first;
		    first = second;
		    second = temp;
		}

		return second;
	}

	public static int climbStairs(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int[][] res = matrixPower(base, n - 2);
		return 2 * res[0][0] + res[1][0];
	}
    
	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		
		// res = 矩阵中的1
		int[][] tmp = m;// 矩阵1次方
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}

	// 两个矩阵乘完之后的结果返回
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}

}

