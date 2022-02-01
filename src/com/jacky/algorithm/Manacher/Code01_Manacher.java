package com.jacky.algorithm.Manacher;

/**
 * <p>
 * 最长回文串(对称回文串,如 12321 ， 123321)
 * 解决的问题： 求一个字符串的中的最长回文子串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/11/23
 **/
public class Code01_Manacher {

	public static int manacher(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// "12132" -> "#1#2#1#3#2#"
		char[] str = manacherString(s);
		// 回文半径的大小
		int[] pArr = new int[str.length];
		int C = -1;
		// 讲述中：R代表最右的扩成功的位置
		// coding：最右的扩成功位置的，再下一个位置 , 第一个失败的位置
		int R = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < str.length; i++) { // 0 1 2
			// R第一个违规的位置，i>= R
			// i位置扩出来的答案，i位置扩的区域，至少是多大。
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

			// 1. i在R外
			// 2 i 在R内，i2的的回文半径在[L..R]
			// 3 i 在R内，i2的的回文半径在[L...R]外
			// 4 i 在R内，i2的的回文半径等于L
			// 情况2 和 3 是不会执行的
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			// 看是否刷新R ， C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// 最大回文半径
			max = Math.max(max, pArr[i]);
		}
		// #1#2#2#1#, 回文半径为5 ，1221 为4
		return max - 1;
	}

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	// for test
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			max = Math.max(max, R - L - 1);
		}
		return max / 2;
	}

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			if (manacher(str) != right(str)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
		String s =  "hello";
		System.out.println(s.substring(1, 2));
	}

}
