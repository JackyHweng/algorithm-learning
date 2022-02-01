package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 字符串中的第一个唯一字符
 * </p>
 *
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 *
 * @author: HuangJiaJie
 * @create: 2022/1/29
 **/
public class Problem_0387_FirstUniqueCharacterInString {

	public int firstUniqChar(String s) {
		char[] str = s.toCharArray();
		int N = str.length;
		// 小写字母的数量
		int count[] = new int[26];


		// 可以写在一个for语句呢？
		// 可以的，但是里面压8条语句和2个for每个for压4条语句是差不多的
		for (int i = 0; i < N; i++) {
			count[str[i] - 'a']++;
		}

		for (int i = 0; i < N; i++) {
			if (count[str[i] - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}

}
