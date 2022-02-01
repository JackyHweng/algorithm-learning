package com.jacky.algorithm.高频面试题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]
 * ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 *
 * </p>
 *
 *
 * 贪心
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0253_MeetingRoomsII {

	public static int minMeetingRooms(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		Arrays.sort(lines, new StartComparator());
		PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			while (!heap.isEmpty() && heap.peek().end <= lines[i].start) {
				heap.poll();
			}
			heap.add(lines[i]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	public static class Line {
		public int start;
		public int end;

		public Line(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static class StartComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}

	}

	public static class EndComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.end - o2.end;
		}

	}

}
