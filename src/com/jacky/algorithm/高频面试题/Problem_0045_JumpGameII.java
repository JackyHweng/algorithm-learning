package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * [3,2,1,,4,2,1,0,3,1,2]
 * step
 * cur
 * next
 *
 * 1) i > cur , step步不足以到i，所以需要step++；如果step + 1， cur = next
 * 2) i <= cur , step 步可以到i，此时看next是否可以更远
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Problem_0045_JumpGameII {

	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 当前最少跳几步能到i位置
		int step = 0;
		// 跳的步数不超过step 情况下，最右可以到哪里？
		int cur = 0;
		// 跳的步数不超过step + 1 情况下，最右
		int next = arr[0];
		for (int i = 1; i < arr.length; i++) {
//            if(next >= arr.length - 1){
//                return step + 1;
//            }
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

}
