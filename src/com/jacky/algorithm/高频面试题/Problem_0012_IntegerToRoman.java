package com.jacky.algorithm.高频面试题;

public class Problem_0012_IntegerToRoman {

	public static String intToRoman(int num) {
		/**
		 *   0 1 2 3 4 5 6 7 8 9
		 *   0 10 20 30 40 50 60 70 80 90
		 *   0 100 200 300 400 500 600 700 800 900
		 *   0 1000 2000 3000
		 */
		String[][] c = { 
				{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "", "M", "MM", "MMM" } };
		StringBuilder roman = new StringBuilder();
		roman
		.append(c[3][num / 1000 % 10])
		.append(c[2][num / 100 % 10])
		.append(c[1][num / 10 % 10])
		.append(c[0][num % 10]);
		return roman.toString();
	}

}
