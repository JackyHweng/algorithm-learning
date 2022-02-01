package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 *
 * 1. 可以通过对字符排序，然后对比
 * 2. 利用词频的出现的次数来判断
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0242_ValidAnagram {

	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] tar = t.toCharArray();
		int[] count = new int[256];
		for (char cha : str) {
			count[cha]++;
		}
		for (char cha : tar) {
			if (--count[cha] < 0) {
				return false;
			}
		}
		return true;
	}

}
