package com.jacky.algorithm.高频面试题;



/**
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/22
 **/
public class Problem_0011_ContainerWithMostWater {

	public static int maxArea(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			// 计算最优解
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			// 谁小结算小
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

	public int maxArea2(int[] height) {
		int l =  0, r = height.length - 1, res = 0;
		while(l < r) {
			res = height[l] < height[r] ?
					Math.max(res, (r - l) * height[l++]):
					Math.max(res, (r - l) * height[r--]);
		}
		return res;
	}


}
