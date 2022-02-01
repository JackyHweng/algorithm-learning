package com.jacky.algorithm.高频面试题;

public class Problem_0029_DivideTwoIntegers {

	public static int add(int a, int b) {
		int sum = a;
		while (b != 0) {
			// 无进位相加
			sum = a ^ b;
			// 2数相加的 进位信息
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

	// 相反数 取反 + 1
	public static int negNum(int n) {
		return add(~n, 1);
	}

	public static int minus(int a, int b) {
		return add(a, negNum(b));
	}

	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = add(res, a);
			}
			a <<= 1;
			b >>>= 1;
		}
		return res;
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}

	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i > negNum(1); i = minus(i, 1)) {
			if ((x >> i) >= y) {
				res |= (1 << i);
				x = minus(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	public static int divide(int dividend, int divisor) {
		if (divisor == Integer.MIN_VALUE) {
			return dividend == Integer.MIN_VALUE ? 1 : 0;
		}
		// 除数不是系统最小
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == negNum(1)) {
				return Integer.MAX_VALUE;
			}
			int res = div(add(dividend, 1), divisor);
			return add(res, div(minus(dividend, multi(res, divisor)), divisor));
		}
		// dividend不是系统最小，divisor也不是系统最小
		return div(dividend, divisor);
	}
	// div(a,b) a和b都不能是系统最小

	// 现场福利函数
	public static String printNumBinary(int num) {
		StringBuilder builder = new StringBuilder();
		for (int i = 31; i >= 0; i--) {
			builder.append(((num >> i) & 1) == 0 ? '0' : '1');
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		int num = -1;
		System.out.println(printNumBinary(num));
	}


	/**
	 * 解题思路：这题是除法，所以先普及下除法术语
	 * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
	 * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
	 * 进而推导得出：商×除数+余数=被除数。
	 *
	 * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
	 *
	 * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
	 *
	 * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
	 *
	 * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
	 *
	 * 我们可以以100/3为例
	 *
	 * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
	 *
	 * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
	 *
	 * 所以一共是减去了33个3，所以商就是33
	 *
	 * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
	 *
	 */
	public int divide2(int dividend, int divisor) {
		if (dividend == 0) {
			return 0;
		}
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		boolean negative;
		negative = (dividend ^ divisor) <0;//用异或来计算是否符号相异
		long t = Math.abs((long) dividend);
		long d= Math.abs((long) divisor);
		int result = 0;
		for (int i=31; i>=0;i--) {
			if ((t>>i)>=d) {//找出足够大的数2^n*divisor
				result+=1<<i;//将结果加上2^n
				t-=d<<i;//将被除数减去2^n*divisor
			}
		}
		return negative ? -result : result;//符号相异取反
	}
}
