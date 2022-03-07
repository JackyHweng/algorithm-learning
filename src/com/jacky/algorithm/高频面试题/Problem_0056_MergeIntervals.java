package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 57leetcode高频题目全讲九
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为
 * intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * </p>
 *
 *
 * 1. 先排序在合并
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0056_MergeIntervals {

	public static class Range {
		public int start;
		public int end;

		public Range(int s, int e) {
			start = s;
			end = e;
		}
	}

	/**
	 * 先排序在合并
	 * @param intervals
	 * @return
	 */
	public int[][] merge2(int[][] intervals) {
		// 先按照区间起始位置排序
		Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
		// 遍历区间
		int[][] res = new int[intervals.length][2];
		int idx = -1;
		for (int[] interval: intervals) {
			// 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
			// 则不合并，直接将当前区间加入结果数组。
			if (idx == -1 || interval[0] > res[idx][1]) {
				res[++idx] = interval;
			} else {
				// 反之将当前区间合并至结果数组的最后区间
				res[idx][1] = Math.max(res[idx][1], interval[1]);
			}
		}
		return Arrays.copyOf(res, idx + 1);
	}

	public static class RangeComparator implements Comparator<Range> {

		@Override
		public int compare(Range o1, Range o2) {
			return o1.start - o2.start;
		}

	}

	// intervals  N * 2
	public static int[][] merge(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0][0];
		}
		Range[] arr = new Range[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			arr[i] = new Range(intervals[i][0], intervals[i][1]);
		}
		Arrays.sort(arr, new RangeComparator());
		ArrayList<Range> ans = new ArrayList<>();
		int s = arr[0].start;
		int e = arr[0].end;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].start > e) {
				ans.add(new Range(s, e));
				s = arr[i].start;
				e = arr[i].end;
			} else {
				e = Math.max(e, arr[i].end);
			}
		}
		ans.add(new Range(s, e));
		return generateMatrix(ans);
	}

	public static int[][] generateMatrix(ArrayList<Range> list) {
		int[][] matrix = new int[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			matrix[i] = new int[] { list.get(i).start, list.get(i).end };
		}
		return matrix;
	}




}
