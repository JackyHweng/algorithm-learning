package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * 59leetcode高频题目全讲十一
 * <p>
 * 118. 杨辉三角
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/7
 **/
public class Problem_0118_PascalTriangle {

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			ans.add(new ArrayList<>());
			ans.get(i).add(1);
		}
		for (int i = 1; i < numRows; i++) {
			for (int j = 1; j < i; j++) {
				ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
			}
			ans.get(i).add(1);
		}
		return ans;
	}

}
