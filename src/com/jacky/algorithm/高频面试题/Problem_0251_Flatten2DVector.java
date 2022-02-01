package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0251_Flatten2DVector {

	public static class Vector2D {
		private int[][] matrix;
		private int row;
		private int col;
		private boolean curUse;

		public Vector2D(int[][] v) {
			matrix = v;
			row = 0;
			col = -1;
			curUse = true;
			hasNext();
		}

		public int next() {
			int ans = matrix[row][col];
			curUse = true;
			hasNext();
			return ans;
		}

		public boolean hasNext() {
			if (row == matrix.length) {
				return false;
			}
			if (!curUse) {
				return true;
			}
			// (row，col)用过了
			if (col < matrix[row].length - 1) {
				col++;
			} else {
				col = 0;
				do {
					row++;
				} while (row < matrix.length && matrix[row].length == 0);
			}
			// 新的(row，col)
			if (row != matrix.length) {
				curUse = false;
				return true;
			} else {
				return false;
			}
		}

	}

}
