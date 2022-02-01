package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 考点： 剪枝条件的考虑
 *
 * 有效的数独满足以下三个条件：
 *
 * 同一个数字在每一行只能出现一次；
 * 同一个数字在每一列只能出现一次；
 * 同一个数字在每一个小九宫格只能出现一次。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0036_ValidSudoku {

	public static boolean isValidSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10];
		boolean[][] col = new boolean[9][10];
		// 第几个九宫格
		boolean[][] bucket = new boolean[9][10];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int bid = 3 * (i / 3) + (j / 3);
				if (board[i][j] != '.') {
				    // 这里的num 的取值为 0-9 ，所有九宫格的二维数组的空间为 10
					// 如果想改为 9 ，那么这里就要改为 int num = board[i][j] - '1';
					int num = board[i][j] - '0';
					if (row[i][num] || col[j][num] || bucket[bid][num]) {
						return false;
					}
					row[i][num] = true;
					col[j][num] = true;
					bucket[bid][num] = true;
				}
			}
		}
		return true;
	}

}
