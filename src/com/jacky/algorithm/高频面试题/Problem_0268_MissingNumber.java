package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 缺失最小整数
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0268_MissingNumber {

	public static int missingNumber(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {
			if (arr[l] == l) {
				l++;
			} else if (arr[l] < l || arr[l] >= r || arr[arr[l]] == arr[l]) {
				// 希望  l ... r 范围上每一个位置都 是 无重复的l
				// 无用的情况
                // arr[l] >= r 说明不在期望内
                // 发现重复数
				swap(arr, l, --r);
			} else {
				// 不是垃圾数，将这个数拿过来再判定一下
				swap(arr, l, arr[l]);
			}
		}
		return l;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
