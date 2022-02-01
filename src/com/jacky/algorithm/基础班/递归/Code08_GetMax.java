package com.jacky.algorithm.基础班.递归;

/**
 * <p>
 * 递归思想体现
 *
 * 求数组arr[L , R] 中的最大值，怎么用递归方法实现
 * 1. 将[L,R] 范围分成左右两半，左[L,mid] 右[mid+1, R]
 * 2. 将左部分求最大值，右部分求最大值
 * 3. [L,R] 范围上的最大值，是Math.max(左边最大值，右边最大值)
 * 注意： 步骤 2 是一个递归过程，当范围上只有一个数，就可以不用递归了
 * </p>
 * 递归实际的过程就是利用系统栈实现的
 * 时间复杂度 O(n) , 为什么？
 * T(N) = aT(N/b) + O(N^d) a,b,d 都是常数
 * T(N) = 2T(N/2) + O(N^0)  a = 2 , b = 2 , d = 0
 * 时间度确定, Master 公式
 * 1. 如果 logba > d 那么复杂度 O(N^logba)
 * 2. 如果 logba < d 那么复杂度 O(N^d)
 * 3. 如果 logba == d 那么复杂度 O(N^d*logN)  归并，堆排都是符合这个条件的
 *	根据以上的公式就可以推断出 下面的程序的时间复杂度就是 O(N)
 *
 *
 * 一定要理解思想，画过程图！！
 * 对于新手来说，把调用的过程画出结构图是必须的，这有利于分析递归
 * 递归并不是玄学，递归底层是利用系统栈来实现的
 * 任何递归函数都一定可以改成非递归
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Code08_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]范围上求最大值
	public static int process(int[] arr, int L, int R) {
		// 这个问题小到什么规模就不需要递归了
		// 当范围上只有一个数，就可以不用递归了
		if (L == R) { // arr[L..R]范围上只有一个数，直接返回，base case
			return arr[L];
		}
		//  L..mid  mid+1...R
		// int mid = (L+R)/2
		int mid = L + ((R - L) >> 1); // 中点

		// T(N/2)
		int leftMax = process(arr, L, mid);
		// T(N/2)
		int rightMax = process(arr, mid + 1, R);
		// 调用了2次 所以 2T(N/2)
		return Math.max(leftMax, rightMax);
	}

}
