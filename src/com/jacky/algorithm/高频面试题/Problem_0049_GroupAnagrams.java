package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 55leetcode高频题目全讲七
 * <p>
 * 同型词 : 其实 一个字符串 的字符种数和数量相等
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * </p>
 *
 * 流程:
 * 1. 准备一个map，key是放字典序相同的，list 字典序相同的字符集合
 * 2. 遍历数组，求出这个字符的字典序，追加到相同的字典序的list中
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_0049_GroupAnagrams {

	public static List<List<String>> groupAnagrams2(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] chs = str.toCharArray();
			Arrays.sort(chs);
			String key = String.valueOf(chs);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(str);
		}
		List<List<String>> res = new ArrayList<List<String>>();
		for (List<String> list : map.values()) {
			res.add(list);
		}
		return res;
	}

	public static List<List<String>> groupAnagrams1(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			int[] record = new int[26];
			for (char cha : str.toCharArray()) {
				record[cha - 'a']++;
			}
			StringBuilder builder = new StringBuilder();
			for (int value : record) {
				builder.append(String.valueOf(value)).append("_");
			}
			String key = builder.toString();
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(str);
		}
		List<List<String>> res = new ArrayList<List<String>>();
		for (List<String> list : map.values()) {
			res.add(list);
		}
		return res;
	}



}
