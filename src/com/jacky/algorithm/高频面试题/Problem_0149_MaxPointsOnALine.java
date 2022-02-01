package com.jacky.algorithm.高频面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0149_MaxPointsOnALine {

	public static int maxPoints(int[][] points) {
		if (points == null) {
			return 0;
		}
		if (points.length <= 2) {
			return points.length;
		}
		// Map<String, Integer>   "3_5"   6
		// 3 / 5    4
		// 3 / 7    10
		// 3 / 17   11
		// 5 / 7    9
		// 5 / 9    3
		// 3 :    (  5 , 4    7, 10,      17 ,  11      )
		// 5 :    (  7 , 9    9, 3  )
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			map.clear();
			int samePosition = 1;
			int sameX = 0;
			int sameY = 0;
			int line = 0; // 哪个斜率压中的点最多，把最多的点的数量，赋值给line
			for (int j = i + 1; j < points.length; j++) {
				int x = points[j][0] - points[i][0];
				int y = points[j][1] - points[i][1];
				if (x == 0 && y == 0) {
					samePosition++;
				} else if (x == 0) {
					sameX++;
				} else if (y == 0) {
					sameY++;
				} else { // 有斜率
					int gcd = gcd(x, y);
					x /= gcd;
					y /= gcd;
					if (!map.containsKey(x)) {
						map.put(x, new HashMap<Integer, Integer>());
					}
					if (!map.get(x).containsKey(y)) {
						map.get(x).put(y, 0);
					}
					map.get(x).put(y, map.get(x).get(y) + 1);
					line = Math.max(line, map.get(x).get(y));
				}
			}
			result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePosition);
		}
		return result;
	}

	// 保证初始调用的时候，a和b不等于0
	// O(1)
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}
