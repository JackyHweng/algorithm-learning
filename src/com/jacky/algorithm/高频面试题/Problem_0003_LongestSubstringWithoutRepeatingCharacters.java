package com.jacky.algorithm.高频面试题;

import java.util.HashMap;

/**
 * <p>
 * 子串，子序列经典解法： 假设以i位置结尾的情况下答案是什么, 求所有可能的结尾
 * 以i位置结尾情况下，往左推，推到最大的长度就是这个i位置的结果，求所有i位置的结果最大值就是答案了
 *
 * 注意的点：
 * 1) 上次i位置出现的字符的最近的位置 , 如 当前 17 位置 结果是a， 上次a出现的位置是 13
 * 2）以i-1位置结尾的字符往左推最大可以推多远？ 16位置结尾的情况下最远推动的位置为11
 * 那么结果就是条件一 和 条件二 的结果的 最小值 就是答案了 ,也就是说 答案就是在 13 - 17 ， 11 -13 中选最小，也就是 11-13
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/23
 **/
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		// s.chatAt(i)
		char[] str = s.toCharArray();
		// map (a, ?) (b, ?)
		// a, 17
		// map[97] = 17
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		// 收集答案
		int len = 0;
		int pre = -1; // i-1位置结尾的情况下，往左推，推不动的位置是谁
		int cur = 0; // 当前位置推不动的长度
		for (int i = 0; i != str.length; i++) {
			// i位置结尾的情况下，往左推，推不动的位置是谁
			// pre (i-1信息) -> pre(i 结尾信息)
			// 为什么是Max，因为是下标，这里求的是最近的结果，所以是Max
			pre = Math.max(pre, map[str[i]]);
			// 计算当前位置推不动的长度
			cur = i - pre;
			len = Math.max(len, cur);
			// 记录当前位置的字符出现的位置
			map[str[i]] = i;
		}
		return len;
	}


	/**
	 * 滑动窗口版本
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring2(String s) {
		int[] map = new int[256];
		for(int i = 0 ; i < 256; i++){
			map[i] = -1;
		}

		int left = -1;
		int ans = 0;
		for(int i = 0 ; i < s.length(); i++){
			// 当前字符最近出现位置
			left = Math.max(map[s.charAt(i)],left);
			map[s.charAt(i)] = i;
			ans = Math.max(ans,i - left);
		}

		return ans;
	}

}
