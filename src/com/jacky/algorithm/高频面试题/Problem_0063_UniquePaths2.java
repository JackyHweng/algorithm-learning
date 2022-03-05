package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * TODO
 * </p>
 *
 * 递归思路：
 * 假设我们定义到达右下角的走法数为 f(m, n)f(m,n), 因为右下角只能由它上方或者左方的格子走过去，因此可以很容易的写出递归求解式，即 f(m, n) = f(m - 1, n) + f(m, n - 1)f(m,n)=f(m−1,n)+f(m,n−1)，最后加上递归终止条件，SO EASY 看起来大功告成啦！
 *
 * 然而事情并木有结束～ 因为这样自底向上的递归会存在大量的重复计算，所以我们将其改写为在二维数组中自顶向下的递推即可，即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]dp[i,j]=dp[i−1,j]+dp[i,j−1]。
 *
 * 1、状态定义：
 * dp[i][j]dp[i][j] 表示走到格子 (i, j)(i,j) 的方法数。
 *
 * 2、状态转移：
 * 如果网格 (i, j)(i,j) 上有障碍物，则 dp[i][j]dp[i][j] 值为 00，表示走到该格子的方法数为 00；
 * 否则网格 (i, j)(i,j) 可以从网格 (i - 1, j)(i−1,j) 或者 网格 (i, j - 1)(i,j−1) 走过来，因此走到该格子的方法数为走到网格 (i - 1, j)(i−1,j) 和网格 (i, j - 1)(i,j−1) 的方法数之和，即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]dp[i,j]=dp[i−1,j]+dp[i,j−1]。
 *
 * 状态转移方程如下：
 *
 * dp[i][j] = \begin{cases} dp[i - 1, j] + dp[i, j - 1] & & {(i, j) 上无障碍物} \\ 0 & & {(i, j) 上有障碍物} \end{cases}
 * dp[i][j]={
 * dp[i−1,j]+dp[i,j−1]
 * 0
 * ​
 *
 * ​
 *
 * (i,j)上无障碍物
 * (i,j)上有障碍物
 * ​
 *
 *
 * 3、初始条件
 * 第 1 列的格子只有从其上边格子走过去这一种走法，因此初始化 dp[i][0] 值为 1，存在障碍物时为 0；
 *
 * 第 1 行的格子只有从其左边格子走过去这一种走法，因此初始化 dp[0][j] 值为 1，存在障碍物时为 0。
 *
 * @author: HuangJiaJie
 * @create: 2022/2/19
 **/
public class Problem_0063_UniquePaths2 {


    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int n= obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < m && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0,1},{0,0}};
        uniquePathsWithObstacles(arr);
    }
}
