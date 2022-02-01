package com.jacky.algorithm.KMP;

/**
 * <p>
 * KMP 实际问题是 在字符串s1中求是否存在子串s2，并求出在s1中的位置，没有则返回-1
 * AC 自动机
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/11/23
 **/
public class Code01_KMP {

	public static int getIndexOf(String haystack, String needle) {
		if (haystack == null || needle == null || needle.length() < 1 || haystack.length() < needle.length()) {
			return -1;
		}
		char[] str1 = haystack.toCharArray();
		char[] str2 = needle.toCharArray();
		int x = 0; // str中当前比对到的位置
		int y = 0; // match中当前比对的位置
		// O(M) m <= n
		int[] next = getNextArray(str2); // next[i] match 中i 之前的字符串最长前缀和最长后缀匹配的长度
		// O(N)
		while (x < str1.length && y < str2.length) {
			if (str1[x] == str2[y]) {
				x++;
				y++;
			} else if (next[y] == -1) { // y == 0
				// 最后比对不上，从y=0 和 x+1 开始配置
				x++;
			} else {
				// 能跳就跳，x不动
				y = next[y];
			}
		}
		// 1. X 越界 ， y 没有越界 -1
		// 2. X 没有越界 ， Y 越界 说明匹配上了, 找到合适的开头
		// 3. X 和 Y 都越界 也配置上了,并且就是同时越界匹配上了
		return y == str2.length ? x - y : -1;
	}

	// O(M)

	// 看while 循环 的变量 i , cn
	// i(最大为N)   i-cn(最大为N)
	// i上升    cn-i 上升
	// 不变     上升
	// 上升    不变
    // 所以整体复杂度不会超过 2N
	public static int[] getNextArray(char[] str2) {
		if (str2.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[str2.length];
		next[0] = -1;
		next[1] = 0;

		int i = 2; // 目前在哪个位置上求next数组的值
		int cn = 0; // 当前是哪个位置的值再和 i-1位置的字符比较
		while (i < next.length) {
			if (str2[i - 1] == str2[cn]) { // 配成功的时候
				// next[i] = cn+1
				// i++;
				next[i++] = ++cn;
			} else if (cn > 0) {
				// 往前跳
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
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
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			if (getIndexOf(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
