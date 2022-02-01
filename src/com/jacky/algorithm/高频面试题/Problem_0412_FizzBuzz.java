package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

public class Problem_0412_FizzBuzz {

	public static List<String> fizzBuzz(int n) {
		ArrayList<String> ans = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			ans.add(i % 15  == 0 ? "FizzBuzz" : i % 5 == 0 ? "Buzz" : i % 3 == 0 ? "Fizz" : String.valueOf(i));
//			if (i % 15 == 0) {
//				ans.add("FizzBuzz");
//			} else if (i % 5 == 0) {
//				ans.add("Buzz");
//			} else if (i % 3 == 0) {
//				ans.add("Fizz");
//			} else {
//				ans.add(String.valueOf(i));
//			}
		}
		return ans;
	}

}
