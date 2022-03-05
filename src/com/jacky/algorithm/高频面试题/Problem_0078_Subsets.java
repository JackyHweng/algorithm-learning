package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * 递归的本质:
 * https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
 *
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/26
 **/
public class Problem_0078_Subsets {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		process(nums, 0, path, ans);
		return ans;
	}

	// 当前来到index位置，做决定，1）不要当前位置的数   2）要当前位置的数
	// 如果要当前位置的数，把该数字，放入到path中去
	// 如果不要当前位置的数，不把该数字，放入到path中去
	public static void process(int nums[], int index, LinkedList<Integer> path, List<List<Integer>> ans) {
		if (index == nums.length) {
			ans.add(copy(path));
		} else {
		    // 做决定 , 不要当前位置的数
			process(nums, index + 1, path, ans);

			// 做决定, 要当前位置的数
			path.addLast(nums[index]);
			process(nums, index + 1, path, ans);

			// 恢复现场
			path.removeLast();
		}
	}

	public static ArrayList<Integer> copy(LinkedList<Integer> path) {
		ArrayList<Integer> ans = new ArrayList<>();
		for (Integer num : path) {
			ans.add(num);
		}
		return ans;
	}


	class Solution {
		List<List<Integer>> ans = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		public List<List<Integer>> subsets(int[] nums) {
			dfs(nums,0);
			return ans;
		}

		private void dfs(int[] nums,int index){
			if(index == nums.length){
				ans.add(new ArrayList<>(path));
			}else{
				// not select
				dfs(nums,index+1);
				// select
				path.addLast(nums[index]);
				dfs(nums,index+1);
				// recover
				path.removeLast();
			}
		}
	}

}
