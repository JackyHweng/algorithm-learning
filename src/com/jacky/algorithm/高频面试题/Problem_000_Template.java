package com.jacky.algorithm.高频面试题;

import java.util.*;

public class Problem_000_Template {

	class Solution {

		public String[] uncommonFromSentences(String s1, String s2){
			List<String> ans = new ArrayList<>();
			HashMap<String,Integer> f1 = new HashMap<>();
			insert(s1, f1);
			insert(s2, f1);

			f1.forEach((key,value) ->{
				if(value == 1){
					ans.add(key);
				}
			});

			return ans.toArray(new String[0]);
		}

		private void insert(String s1, HashMap<String, Integer> f1) {
			for (String s : s1.split(" ")) {
				f1.put(s, f1.getOrDefault(s,0)  + 1);
			}
		}

	}
}
