package com.jacky.algorithm.经典面试题.第三期.class02;

/**
 * <p>
 * 给定一个有序数组arr，给定一个正数aim
 *
 * 1）返回累加和为aim的，所有不同二元组
 * 2）返回累加和为aim的，所有不同三元组
 *
 * </p>
 *
 *
 * 这道题和下标没有关系 !!
 *
 * 二元组流程：
 * 1.双指针
 * 2. arr[l] + arr[r] > aim , r--
 * 3. arr[l] + arr[r] < aim , l++
 * 4. arr[l] + arr[r] = aim , arr[l-1] != arr[l]  才收集答案(其实就是剪枝), l++ 或者 r-- 都可以
 *
 * 三元组流程：
 * 1. 查询i位置的值，查询 和为 aim - i 的二元组的问题
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code06_PrintUniquePairAndTriad {

	/**
	 *  目标和 不同的二元组问题
	 * @param arr
	 * @param k
	 */
	public static void printUniquePair(int[] arr, int k) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < k) {
				left++;
			} else if (arr[left] + arr[right] > k) {
				right--;
			} else { // L   +   R   =   aim
				if (left == 0 || arr[left - 1] != arr[left]) {
					System.out.println(arr[left] + "," + arr[right]);
				}
				left++;
				right--;
			}
		}
	}

	/**
	 *  目标和 不同的三元组问题
	 * @param arr
	 * @param k
	 */
	public static void printUniqueTriad(int[] arr, int k) {
		if (arr == null || arr.length < 3) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			if (i == 0 || arr[i] != arr[i - 1]) {
				printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
			}
		}
	}

	public static void printRest(int[] arr, int f, int l, int r, int k) {
		while (l < r) {
			if (arr[l] + arr[r] < k) {
				l++;
			} else if (arr[l] + arr[r] > k) {
				r--;
			} else {
				if (l == f + 1 || arr[l - 1] != arr[l]) {
					System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
				}
				l++;
				r--;
			}
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int sum = 10;
		int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
		printArray(arr1);
		System.out.println("====");
		printUniquePair(arr1, sum);
		System.out.println("====");
		printUniqueTriad(arr1, sum);

	}

}
