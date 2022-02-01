package com.jacky.algorithm.高频面试题;

import java.util.HashMap;

/**
 * <p>
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * </p>
 *
 *
 * 利用分治的思想
 * 先统计AB的组合和，记录在Map中
 * 然后遍历 C D 求出 CD和在ABMap中有没有组合为0的，有的话直接统计
 *
 * @author: HuangJiaJie
 * @create: 2022/1/27
 **/
public class Problem_0454_4SumII {

	public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				sum = A[i] + B[j];
				if (!map.containsKey(sum)) {
					map.put(sum, 1);
				} else {
					map.put(sum, map.get(sum) + 1);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				sum = C[i] + D[j];
				if (map.containsKey(-sum)) {
					ans += map.get(-sum);
				}
			}
		}
		return ans;
	}

	public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
		HashMap<Integer,Integer> m = new HashMap<>();
		for(int a : A){
			for(int  b : B){
				int key  = a + b;
				int sum = m.getOrDefault( key, 0);
				m.put(key,sum+1);
			}
		}
		int ans = 0;
		for (int c : C) {
			for (int d : D) {
				ans += m.getOrDefault(-(c + d),0);
			}
		}
		return ans;
	}

}
