package com.jacky.algorithm.高频面试题;

/**
 * 55leetcode高频题目全讲七
 *
 * <p>
 * 旋转二维数组
 * </p>
 *
 *
 * 流程
 *
 * 1. 将外层的圈 顺时针转90度
 * 2. 将下一层的圈在转90度，直到没有
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0048_RotateImage {

	public static void rotate(int[][] matrix) {
		// matrix.len == matrix[0].len
        // 左上角的点
		int tR = 0;
		int tC = 0;
		// 右下角的点
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		// 有了左上角和右下角就可以确定一个圈了
		// 因为是正方形，如果左上角的行 不能超过 右下角的行
		while (tR < dR) {
		    // 开始处理圈
			// 处理完了， tR++, tC++, dR--, dC--, 其实就是往里面一层处理
			rotateEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	/**
	 * 根据左上角的点和右下角的点，开始打圈
     * 如果是 4 *4 将圈内的数分成3组
	 * 如果是 5 *5 将圈内的数分成4组
	 *
	 * 也就是有n列，那么就有n-1组 数需要处理
	 *
	 */
	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
	    // 需要
		int times = dC - tC;
		int tmp = 0;
		// i 就是组号
		for (int i = 0; i != times; i++) {
		    // 第i组第一个数
			tmp = m[tR][tC + i];
			m[tR][tC + i] = m[dR - i][tC];
			m[dR - i][tC] = m[dR][dC - i];
			m[dR][dC - i] = m[tR + i][dC];
			m[tR + i][dC] = tmp;
		}
	}

}
