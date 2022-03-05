package com.jacky.algorithm.基础班.排序算法.归并排序;

/**
 * <p>
 * merge sort 问题集合：
 * https://leetcode-cn.com/tag/merge-sort/problemset/
 * https://leetcode-cn.com/problems/merge-sorted-array/submissions/
 *
 * 1）整体是递归，左边排好序+右边排好序+merge让整体有序
 * 2）让其整体有序的过程里用了排外序方法
 * 3）利用master公式来求解时间复杂度
 * 4）当然可以用非递归实现
 *
 * 时间复杂度:
 *
 * T(N) = 2*T(N/2) + O(N^1)
 * 根据master可知时间复杂度为O(N*logN)
 * merge过程需要辅助数组，所以额外空间复杂度为O(N)
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 *
 *
 *
 * Partition过程
 *
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Code01_MergeSort {

	// 递归方法实现
	public static void mergeSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	// 请把arr[L..R]排有序
	// l...r N
	// T(N) = 2 * T(N / 2) + O(N)
	// O(N * logN)
	public static void process(int[] arr, int L, int R) {
	    // 这个问题小到什么规模就不需要递归了
		if (L == R) { // base case
			return;
		}
		int mid = L + ((R - L) >> 1);
		// 左边递归,让他有序
		process(arr, L, mid);
		// 右边递归,让他有序
		process(arr, mid + 1, R);
		// 最后开辟一个辅助数组，merge
		merge(arr, L, mid, R);
	}

	public static void merge(int[] arr, int L, int M, int R) {
		// L -> R 有多少个数
		int[] help = new int[R - L + 1];
		int i = 0;
		// 左边的指针
		int p1 = L;
		// 右边的指针
		int p2 = M + 1;
		while (p1 <= M && p2 <= R) {
			// 比较谁小拷贝谁,然后对应的指针往下移动
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 要么p1越界了，要么p2越界了
		// p2 越界
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		// p1 越界
		while (p2 <= R) {
			help[i++] = arr[p2++]
			;
		}
		// 将辅助数组的结果拷贝到 arr
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
	}

	// 非递归方法实现
	public static void mergeSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		// 步长
		int mergeSize = 1;
		while (mergeSize < N) { // log N
			// 当前左组的，第一个位置
			int L = 0;
			while (L < N) {
				if (mergeSize >= N - L) {
					break;
				}
				int M = L + mergeSize - 1;
				int R = M + Math.min(mergeSize, N - M - 1);
				merge(arr, L, M, R);
				// 下一次L开始的位置
				L = R + 1;
			}
			// 防止溢出 （如果当 mergeSize int 无限接近 N 的，乘以2有可能就变成负数了)
			if (mergeSize > N / 2) {
				break;
			}
			// 步长 * 2
			mergeSize <<= 1;
		}
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort1(arr1);
			mergeSort2(arr2);
			if (!isEqual(arr1, arr2)) {
				System.out.println("出错了！");
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
