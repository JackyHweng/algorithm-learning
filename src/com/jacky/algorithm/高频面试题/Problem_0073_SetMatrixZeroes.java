package com.jacky.algorithm.高频面试题;

/**
 *
 * 57leetcode高频题目全讲九
 * <p>
 *	矩阵置零(类似于炸弹人)
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0073_SetMatrixZeroes {

	/**
	 * 辅助数组
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		boolean[] row = new boolean[n];
		boolean[] col = new boolean[m];
		for(int i = 0 ; i < n ; i++){
			for(int j = 0; j < m ; j++){
				if(matrix[i][j] == 0){
					row[i] = col[j] = true;
				}
			}
		}

		for(int i = 0 ; i < n ; i++){
			for(int j = 0; j < m ; j++){
				if(row[i] || col[j]){
					matrix[i][j] = 0;
				}
			}
		}
	}

	/**
	 * 只需要2个boolean变量
	 * @param matrix
	 */
	public static void setZeroes1(int[][] matrix) {
		boolean row0Zero = false;
		boolean col0Zero = false;
		int i = 0;
		int j = 0;
		for (i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				row0Zero = true;
				break;
			}
		}
		for (i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				col0Zero = true;
				break;
			}
		}
		for (i = 1; i < matrix.length; i++) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (i = 1; i < matrix.length; i++) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if (row0Zero) {
			for (i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}
		if (col0Zero) {
			for (i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

	/**
	 * 只需要一个变量
	 * @param matrix
	 */
	public static void setZeroes2(int[][] matrix) {
		boolean col0 = false;
		int i = 0;
		int j = 0;
		// 先遍历一次，得出信息
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					if (j == 0) {
					    // 如果是第一列只需要修改这个值
						col0 = true;
					} else {
						// 否则利用第一列的信息去维护
						matrix[0][j] = 0;
					}
				}
			}
		}
		// 修改 第0行到第n行，第1列到第n列的数据
		// 注意这里是要从第n行到第0行的遍历
		for (i = matrix.length - 1; i >= 0; i--) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// 看是否需要修改第一列的数据,单独修改第0列的数据
		if (col0) {
			for (i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

}
