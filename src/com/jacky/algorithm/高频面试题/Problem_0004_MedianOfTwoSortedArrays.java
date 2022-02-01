package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 有序数组的中位数
 * </p>
 *
 * 解题思路:
 *
 * 这里拆分了2个算法原型
 * findKthNum() 第一个是给定有序数组a1,a2 返回合并后第k小的数
 *
 * getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2)
 * 数组A和B是有序且长度一样的，返回 上中位数
 *
 * @author: HuangJiaJie
 * @create: 2021/12/21
 **/
public class Problem_0004_MedianOfTwoSortedArrays {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		boolean even = (size & 1) == 0;
		if (nums1.length != 0 && nums2.length != 0) {
			if (even) {
				return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
			} else {
				return findKthNum(nums1, nums2, size / 2 + 1);
			}
		} else if (nums1.length != 0) {
			if (even) {
				return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
			} else {
				return nums1[size / 2];
			}
		} else if (nums2.length != 0) {
			if (even) {
				return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
			} else {
				return nums2[size / 2];
			}
		} else {
			return 0;
		}
	}

	/**
	 *
	 * @param arr1 有序数组,不等长
	 * @param arr2 有序数组,不等长
	 * @param kth 合并数组最小的第k个数
	 * @return
	 */
	public static int findKthNum(int[] arr1, int[] arr2, int kth) {
		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;
		if (kth <= s) {
			return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
		}
		// 大于最长的
		if (kth > l) {
			if (shorts[kth - l - 1] >= longs[l - 1]) {
				return shorts[kth - l - 1];
			}
			if (longs[kth - s - 1] >= shorts[s - 1]) {
				return longs[kth - s - 1];
			}
			return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
		}
		// 第2段
		// 大于 短数组长度，小于 长数组长度的
		if (longs[kth - s - 1] >= shorts[s - 1]) {
			return longs[kth - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
	}

	/**
	 * 算法原型
	 * 给定2个长度一样的数组，返回其中数组a,[s1,e1]范围的和数组a2上[s2,e2]范围上的   上中位数
	 */
	public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		while (s1 < e1) {
			// 获取上中位数
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;
			// 如果2者的上中位数的值相等，返回
			if (A[mid1] == B[mid2]) {
				return A[mid1];
			}
			if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
				if (A[mid1] > B[mid2]) {
					if (B[mid2] >= A[mid1 - 1]) {
						return B[mid2];
					}
					e1 = mid1 - 1;
					s2 = mid2 + 1;
				} else { // A[mid1] < B[mid2]
					if (A[mid1] >= B[mid2 - 1]) {
						return A[mid1];
					}
					e2 = mid2 - 1;
					s1 = mid1 + 1;
				}
			} else { // 偶数长度
				if (A[mid1] > B[mid2]) {
					e1 = mid1;
					s2 = mid2 + 1;
				} else {
					e2 = mid2;
					s1 = mid1 + 1;
				}
			}
		}
		return Math.min(A[s1], B[s2]);
	}

	/**
	 * 算法原型
	 * 给定2个长度一样的数组，返回其中数组a,[s1,e1]范围的和数组a2上[s2,e2]范围上的   上中位数
	 */
	public static int getUpMedian2(int[] A, int s1, int e1, int[] B, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		int offset = 0;
		while(s1 < e1){
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;
			offset = ((e1 - s1 + 1) & 1) ^1;
			if(A[mid1] > B[mid2]){
				e1 = mid1;
				s2 = mid2 + offset;
			}else if(A[mid1] < B[mid2]){
				s1 = mid1 + offset;
				e2 = mid2;
			}else {
				return A[mid1];
			}
		}

		return Math.min(A[s1],B[s2]);
	}

	}
