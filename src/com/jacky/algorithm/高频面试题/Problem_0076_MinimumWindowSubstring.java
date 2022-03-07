package com.jacky.algorithm.高频面试题;

/**
 * 57leetcode高频题目全讲九
 *
 * <p>
 * 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * </p>
 *
 * 流程:
 *
 * 1. 准备一张欠债表
 * 2. 利用滑动窗口,L，R（L代表是子串的起始位置），
 * 3. R开始往右滑动，没滑过一个数，将欠债表的欠债数减一，更新总欠债数，当总欠债all数等于0的时候，结算【L...R】的长度
 * 4. 结算完后，继续滑动L，重复2，3，4 步骤
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0076_MinimumWindowSubstring {

	public static String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] target = t.toCharArray();
		int[] map = new int[256];
		for (char cha : target) {
			map[cha]++;
		}
		int all = target.length;
		int L = 0;
		int R = 0;
		// -1(从来没找到过合法的)
		int minLen = -1;
		int ansl = -1;
		int ansr = -1;
		// [L..R)   [0,0)  R
		while (R != str.length) {
			map[str[R]]--;
			if (map[str[R]] >= 0) {
				// 有效还款
				all--;
			}
			// 开始结算
			if (all == 0) {
				// 如果此时L的位置是不属于欠债表的，也就是说L的位置是额外多出的
				// 说明可以外右滑动
				while (map[str[L]] < 0) {
					map[str[L++]]++;
				}
				// 当L互动到==0的时候，说明现在L是在欠债表中的
				// 如果之前没有记录最小长度，或者之前的长度是比此时抓到的答案大，说明此时答案可以更新最小值
				if (minLen == -1 || minLen > R - L + 1) {
					minLen = R - L + 1;
					ansl = L;
					ansr = R;
				}
				//L 继续滑动,看下一个L的位置最短的子串答案
				map[str[L++]]++;
				// 因为此时L是刚好等于0的，如果继续滑，肯定是欠债的
				all++;
			}
			R++;
		}
		return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
	}

}
