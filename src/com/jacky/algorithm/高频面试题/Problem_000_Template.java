package com.jacky.algorithm.高频面试题;

import java.util.HashMap;
import java.util.Map;

public class Problem_000_Template {

	static class Solution {

		public int findCenter(int[][] edges){
			HashMap<Integer,Integer> map = new HashMap<>();

			for(int[] edge : edges){
				map.put(edge[0],map.getOrDefault(edge[0],0)+1);
				map.put(edge[1],map.getOrDefault(edge[1],0)+1);
			}

			int n = map.size();
			for(Map.Entry<Integer,Integer> entry : map.entrySet()){
				if(entry.getValue() == n -1){
					return entry.getKey();
				}
			}

			return 0;
		}

	}


	public static void main(String[] args) {
		int[][] data =  {{1,2},{2,3},{4,2}};
		final int center = new Solution().findCenter(data);
		System.out.println(center);
	}
}
