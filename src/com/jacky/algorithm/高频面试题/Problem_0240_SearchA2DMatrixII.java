package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0240_SearchA2DMatrixII {

	public static boolean searchMatrix(int[][] m, int target) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		int N = m.length;
		int M = m[0].length;
		int row = 0;
		int col = M - 1;
		while (row < N && col >= 0) {
		    // 下面不可能，往左走
			if (m[row][col] > target) {
				col--;
			} else if (m[row][col] < target) {
				//
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

}
