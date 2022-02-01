package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 条件分类
 *  1. 在【L-R】范围上，取一点M， 如果 [L]  [M]  [R] 上的数都不相等，那么就可以2分
 *  2. 如果都相等，那么将L 往 M 一步一步走二分，如果 L -> M 还是相等，那么就从 M+1 - R 上进行二分
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0033_SearchInRotatedSortedArray {

	public static int search(int[] arr, int num) {
		int L = 0;
		int R = arr.length - 1;
		int M = 0;
		while (L <= R) {
			M = (L + R) / 2;
			if (arr[M] == num) {
				return M;
			}
			// arr[M] != num
			if (arr[L] == arr[M] && arr[M] == arr[R]) {
				while (L != M && arr[L] == arr[M]) {
					L++;
				}
				// L和M没撞上，[L]!=[M] L,.....M 都是一种数，并且数不是target
				if (L == M) {
					L = M + 1;
					continue;
				}
			}
			// arr[M] != num
			// [L] [M] [R] 不都一样的情况
			if (arr[L] != arr[M]) {
				if (arr[M] > arr[L]) { // 说明 L-M 是有序的
					if (num >= arr[L] && num < arr[M]) {
						R = M - 1;
					} else {
						L = M + 1;
					}
				} else { //  [L]  >  [M], 说明 M -R 是有序的
					if (num > arr[M] && num <= arr[R]) {
						L = M + 1;
					} else {
						R = M - 1;
					}
				}
			} else { // [L] === [M] ->  [M]!=[R]
				if (arr[M] < arr[R]) {
					if (num > arr[M] && num <= arr[R]) {
						L = M + 1;
					} else {
						R = M - 1;
					}
				} else {
					if (num >= arr[L] && num < arr[M]) {
						R = M - 1;
					} else {
						L = M + 1;
					}
				}
			}
		}
		return -1;
	}

}
