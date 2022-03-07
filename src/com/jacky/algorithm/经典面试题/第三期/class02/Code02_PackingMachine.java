package com.jacky.algorithm.经典面试题.第三期.class02;

import java.util.Arrays;

/**
 * <p>
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打 包机上，
 * 放到每个机器上的这些物品数量有多有少，
 * 由于物品数量不相同，需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。
 * 每个物品重量太大、 每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动最 小轮数的前提下，
 * 使每个机器上的物品数量相等。如果不能使每个机器上的物品相同， 返回-1。
 * 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后:
 *
 * 第一轮:1    0 <- 5 => 1 1 4
 * 第二轮:1 <- 1 <- 4 => 2 1 3
 * 第三轮:2    1 <- 3 => 2 2 2
 * 移动了3轮，每个机器上的物品相等，所以返回3
 * 例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1
 * </p>
 *
 * 517. 超级洗衣机  https://leetcode-cn.com/problems/super-washing-machines/
 * 类似问题：网络阻塞问题
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code02_PackingMachine {

	// 每一轮都 每一台机器都可以往左或往右移动至多一个
	public static int MinOps(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int size = arr.length;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += arr[i];
		}

		// 根本不可能
		if (sum % size != 0) {
			return -1;
		}
		// 每一个机器需要的衣服数量
		int avg = sum / size;
		int leftSum = 0;
		int ans = 0;
		// 每个位置都求各自的
		for (int i = 0; i < arr.length; i++) {
			// i号机器，是中间机器，左(0~i-1) i 右(i+1~N-1)
			// 负 需要输入     正需要输出 
			int leftRest = leftSum - i * avg; // a-b
			// 负 需要输入     正需要输出 
			// c - d
			int rightRest =  (sum - leftSum - arr[i]) -  (size - i - 1) * avg;
			// i 2左右两边都缺 至少需要 Math.abs(leftRest) + Math.abs(rightRest)
			if (leftRest < 0 && rightRest < 0) {
				ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
			} else {
				// 只有一边缺 取最大缺的那个为最坏的情况
				ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
			}
			leftSum += arr[i];
		}
		return ans;
	}


	/**
	 * 版本二
	 * @param ms
	 * @return
	 */
	public int findMinMoves2(int[] ms) {
		int n = ms.length;
		int sum = 0;
		for (int i : ms) sum += i;
		if (sum % n != 0) return -1;
		int t = sum / n;
		int ls = 0, rs = sum;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			rs -= ms[i];
			int a = Math.max(t * i - ls, 0);
			int b = Math.max((n - i - 1) * t - rs, 0);
			ans = Math.max(ans, a + b);
			ls += ms[i];
		}
		return ans;
	}


	public int findMinMoves(int[] machines) {
		int tot = Arrays.stream(machines).sum();
		int n = machines.length;
		if (tot % n != 0) {
			return -1;
		}
		int avg = tot / n;
		int ans = 0, sum = 0;
		for (int num : machines) {
			num -= avg;
			sum += num;
			// Math.abs(sum) i 往左右2边最坏情况
			// num i 往左右2边最坏情况
			ans = Math.max(ans, Math.max(Math.abs(sum), num));
		}
		return ans;
	}

	public static void main(String[] args) {

	}

}
