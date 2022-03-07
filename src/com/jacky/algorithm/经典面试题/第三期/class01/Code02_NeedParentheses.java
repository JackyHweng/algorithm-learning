package com.jacky.algorithm.经典面试题.第三期.class01;

/**
 * <p>
 * 括号有效配对是指：
 *
 * 1）任何一个左括号都能找到和其正确配对的右括号
 *
 * 2）任何一个右括号都能找到和其正确配对的左括号
 *
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 *
 * 问题一：怎么判断一个括号字符串有效？
 *
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/31
 **/
public class Code02_NeedParentheses {


	public static boolean valid(String str){
		char[] chars = str.toCharArray();
		int count = 0;
		for(int i = 0; i < chars.length ; i++){
			count += chars[i] == '(' ? 1: -1;
			if(count < 0){
				return false;
			}
		}
		return true;
	}

	public static int needParentheses(String str) {
		int t = 0;
		int needSolveRight = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				t++;
			} else { // 遇到的是')'
				if (t == 0) {
					needSolveRight++;
				} else {
					t--;
				}
			}
		}
		return t + needSolveRight;
	}

	public static void main(String args[]) {

	}

}
