package com.jacky.algorithm.高频面试题;

import java.util.*;

public class Problem_0039_CombinationSum {

	/*
	 * 两个方法都能通过， 测试时想用哪个方法，就把函数名改成combinationSum
	 */

	public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
		return process1(candidates, candidates.length - 1, target);
	}

	// 想凑出来的和叫target
	// arr[0...index]所有的数，自由选择，每一种数任意个，(index+1....) 不用管
	// 凑出target，所有的方案，返回，List<List<Integer>>
	public static List<List<Integer>> process1(int[] arr, int index, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		if (target == 0) { // index == -1
			ans.add(new ArrayList<>());
			return ans;
		}
		// target != 0 
		if (index == -1) {
			return ans;
		}
		// 当前的数可以使用多少个
		for (int zhang = 0; zhang * arr[index] <= target; zhang++) {
			// 已经决定了使用zhang个，当前数
			List<List<Integer>> preLists = process1(arr, index - 1, target - (zhang * arr[index]));
			for (List<Integer> pre : preLists) {
				for (int i = 0; i < zhang; i++) {
					pre.add(arr[index]);
				}
				ans.add(pre);
			}
		}
		return ans;
	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map = new HashMap<>();
		return process2(candidates, candidates.length - 1, target, map);
	}

	public static List<List<Integer>> process2(int[] arr, int index, int target,
			HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map) {
		if (map.containsKey(index) && map.get(index).containsKey(target)) {
			return copy(map.get(index).get(target));
		}
		List<List<Integer>> ans = new ArrayList<>();
		if (target == 0) {
			ans.add(new ArrayList<>());
			setAns(index, target, ans, map);
			return copy(map.get(index).get(target));
		}
		if (index == -1) {
			setAns(index, target, ans, map);
			return copy(map.get(index).get(target));
		}
		for (int zhang = 0; zhang * arr[index] <= target; zhang++) {
			List<List<Integer>> preLists = process2(arr, index - 1, target - (zhang * arr[index]), map);
			for (List<Integer> pre : preLists) {
				for (int i = 0; i < zhang; i++) {
					pre.add(arr[index]);
				}
				ans.add(pre);
			}
		}
		setAns(index, target, ans, map);
		return copy(map.get(index).get(target));
	}

	public static void setAns(int index, int target, List<List<Integer>> ans,
			HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map) {
		if (!map.containsKey(index)) {
			map.put(index, new HashMap<>());
		}
		if (!map.get(index).containsKey(target)) {
			map.get(index).put(target, ans);
		}
	}

	public static List<List<Integer>> copy(List<List<Integer>> lists) {
		List<List<Integer>> ans = new ArrayList<>();
		for (List<Integer> cur : lists) {
			ArrayList<Integer> n = new ArrayList<>();
			for (Integer num : cur) {
				n.add(num);
			}
			ans.add(n);
		}
		return ans;
	}


	public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
		int len = candidates.length;
		List<List<Integer>> res = new ArrayList<>();
		if (len == 0) {
			return res;
		}
		// 剪枝前提 数组要排序
		Arrays.sort(candidates);


		Deque<Integer> path = new ArrayDeque<>();
		dfs(candidates, 0, len, target, path, res);
		return res;
	}

	public static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
		// 剪枝的情况下是这里是不会触发的
		if (target < 0) {
			return;
		}
		if (target == 0) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = begin; i < len; i++) {

			if(target - candidates[i] < 0){
				break;
			}
			path.addLast(candidates[i]);
			System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

			dfs(candidates, i, len, target - candidates[i], path, res);

			path.removeLast();
			System.out.println("递归之后 => " + path);

		}
	}

	public static void main(String[] args) {
		int[] candidates = new int[]{2, 3, 6, 7};
		int target = 7;
		List<List<Integer>> res = combinationSum3(candidates, target);
		System.out.println("输出 => " + res);
	}


}
