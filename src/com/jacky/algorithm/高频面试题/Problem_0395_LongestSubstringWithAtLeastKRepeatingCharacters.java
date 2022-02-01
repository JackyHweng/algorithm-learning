package com.jacky.algorithm.高频面试题;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度
 * <p>
 * 1.
 * 2.
 * 3.
 * 4.
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/23
 **/
public class Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters {

	public static int longestSubstring1(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int ans = 0;
		// 这里的思想是 以i 为起点，每次统计最长的
		for (int i = 0; i < N; i++) {
			int[] count = new int[256];
			// 收集到的字符种类
			int collect = 0;
			// 已有多少种字符满足条件
			int satisfy = 0;
			for (int j = i; j < N; j++) {
				if (count[str[j]] == 0) {
					collect++;
				}
				if (count[str[j]] == k - 1) {
					satisfy++;
				}
				count[str[j]]++;
				if (collect == satisfy) {
					ans = Math.max(ans, j - i + 1);
				}
			}
		}
		return ans;
	}

	public static int longestSubstring2(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int max = 0;
		for (int require = 1; require <= 26; require++) {
			// a~z  a~z 出现次数
			// count[0  1  2]  a b c
			// count 是字母的出现的词频
			int[] count = new int[26];
			// 目前窗口内收集了几种字符了
			int collect = 0;
			// 目前窗口内出现次数>=k次的字符，满足了几种
			int satisfy = 0;
			// 窗口右边界
			int R = -1;
			for (int L = 0; L < N; L++) { // L要尝试每一个窗口的最左位置
				// [L..R]  R+1
				// R 没有超边界，收集没有完成 或者 并且下一个收集的字符之前有出现过
				while (R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) {
					R++;
					// 之前没有收集过
					if (count[str[R] - 'a'] == 0) {
						collect++;
					}
					if (count[str[R] - 'a'] == k - 1) {
						satisfy++;
					}
					count[str[R] - 'a']++;
				}
				// [L...R] 窗口内的满足了k钟字符并且不能往右扩了
				if (satisfy == require) {
					// 更新答案
					max = Math.max(max, R - L + 1);
				}
				// L++
				if (count[str[L] - 'a'] == 1) {
					collect--;
				}
				if (count[str[L] - 'a'] == k) {
					satisfy--;
				}
				// L的词频减少
				count[str[L] - 'a']--;
			}
		}
		return max;
	}


	/**
	 *  滑动窗口 第二个版本
	 * @param s
	 * @param k
	 * @return
	 */
	public int longestSubstring4(String s, int k) {
		int ret = 0;
		int n = s.length();
		for (int t = 1; t <= 26; t++) {
			int l = 0, r = 0;
			int[] cnt = new int[26];
			int tot = 0;
			int less = 0;
			while (r < n) {
				cnt[s.charAt(r) - 'a']++;
				if (cnt[s.charAt(r) - 'a'] == 1) {
					tot++;
					less++;
				}
				if (cnt[s.charAt(r) - 'a'] == k) {
					less--;
				}

				while (tot > t) {
					cnt[s.charAt(l) - 'a']--;
					if (cnt[s.charAt(l) - 'a'] == k - 1) {
						less++;
					}
					if (cnt[s.charAt(l) - 'a'] == 0) {
						tot--;
						less--;
					}
					l++;
				}
				if (less == 0) {
					ret = Math.max(ret, r - l + 1);
				}
				r++;
			}
		}
		return ret;
	}

	// 会超时，但是思路的确是正确的
	public static int longestSubstring3(String s, int k) {
		return process(s.toCharArray(), 0, s.length() - 1, k);
	}

	public static int process(char[] str, int L, int R, int k) {
		if (L > R) {
			return 0;
		}
		int[] counts = new int[26];
		for (int i = L; i <= R; i++) {
			counts[str[i] - 'a']++;
		}
		char few = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			if (counts[i] != 0 && min > counts[i]) {
				few = (char) (i + 'a');
				min = counts[i];
			}
		}
		if (min >= k) {
			return R - L + 1;
		}
		int pre = 0;
		int max = Integer.MIN_VALUE;
		for (int i = L; i <= R; i++) {
			if (str[i] == few) {
				max = Math.max(max, process(str, pre, i - 1, k));
				pre = i + 1;
			}
		}
		if (pre != R + 1) {
			max = Math.max(max, process(str, pre, R, k));
		}
		return max;
	}




}
