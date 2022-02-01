package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 洗乱array
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/29
 **/
public class Problem_0384_ShuffleAnArray {

	class Solution {
		private int[] origin;
		private int[] shuffle;
		private int N;

		public Solution(int[] nums) {
			origin = nums;
			N = nums.length;
			shuffle = new int[N];
			shuffle = origin.clone();
//			System.arraycopy(origin,0,shuffle,0, origin.length);
//			for (int i = 0; i < N; i++) {
//				shuffle[i] = origin[i];
//			}
		}

		public int[] reset() {
			return origin;
		}

		/**
		 * 随机获取 0 -n 的位置上的位置，和n交换
		 * @return
		 */
		public int[] shuffle() {
		    // 注意边界
			for (int i = N - 1; i >= 0; i--) {
				// Math.random得到是 [0,i) 的范围
				int r = (int) (Math.random() * (i + 1));
				int tmp = shuffle[r];
				shuffle[r] = shuffle[i];
				shuffle[i] = tmp;
			}
			return shuffle;
		}
	}

}
