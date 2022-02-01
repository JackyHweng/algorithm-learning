package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 找最小的整数，O(n) 时间 O(1) 空间
 * L 代表 [0..L-1]上的每一个位置的值都放了 i + 1,(假设现在L = 5， 那么 0 - 4 位置上放的数 是 1,2,3,4,5,否则L 不能往右走)
 * R 代表 没有看过的数，最好预期下，及可能大的最小整数,(假设现在R 是 10 ，数组为0-9，现在假设0-9都是筹齐了，但是我们假设第10的位置是)
 *  虽然代码比较少，但是思路很复杂
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0041_FirstMissingPositive {
    public static int firstMissingPositive(int[] arr) {
		int l = 0;
		// 注意这里指向的一个越界的位置
		int r = arr.length;
		// r - arr.length 是垃圾区
		while (l < r) {
			if (arr[l] == l + 1) {
				l++;
			} else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
				//  arr[l] > r 最好预期在变差
				// arr[l] <= l 期望的是 1-l ，但是出现了l小，所以期望就变成了 1-l-1了
				// (假设 1-4 在 0-3出现了，但是到4位置出现了1，我原本期望的是1-10，但是出现了一个重复的数，减少了一个位置，那么最好期望就变成了 1- 9了)
				// arr[arr[l] - 1] == arr[l] 原本 i 位置的上的数应该再 i-1上的位置，但是i-1上的数应景是arr[l]了，所以将这个数放到垃圾区
				swap(arr,l,--r);
			} else {

				// 潜台词 L 位置上的是x，应该放到 x-1 上的位置，但是x-1没有超过R，期望没有减少
				// 预期没有变差
				swap(arr, l, arr[l] - 1);
			}
		}
		return l + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
