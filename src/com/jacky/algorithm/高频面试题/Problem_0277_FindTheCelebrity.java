package com.jacky.algorithm.高频面试题;
/**
 * <p>
 * 我们不妨先假定ans为0，然后迭代n个人，如果此时ans认识某个人k(0<=k<n),那么令ans为k；
 * 如何证明若存在名人，则名人必定为ans呢？
 * 我们知道如果存在名人，那么在迭代的过程必定会遇到名人,并且此时ans认识名人，不管ans此时是不是名人，所以此时令ans=名人。在接下来的迭代中，由于名人不认识其他人，则必然不会发生ans值的变更。所以可知若存在名人，则ans必为名人。
 * 得到ans后，我们需要判断ans是不是名人，这个判断过程很简单，就不细说了
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/6
 **/
public class Problem_0277_FindTheCelebrity {

	// 提交时不要提交这个函数，只提交下面的方法
	public static boolean knows(int x, int i) {
		return true;
	}

	public int findCelebrity(int n) {
		int cand = 0;

		// 找到cand,然后cand不是那么就没有人是明星了
		for (int i = 0; i < n; ++i) {
			if (knows(cand, i)) {
				cand = i;
			}
		}

		// 看 cand 之前,是否是cand不认识的人,因为第一个for已经说明了cand - n 是cand不认识的
		for (int i = 0; i < cand; ++i) {
			if (knows(cand, i)) {
				return -1;
			}
		}
		// 到此位置，cand都是不认识所有的人的
        // 然后再验证所有人是否都认识cand
		for (int i = 0; i < n; ++i) {
			if (!knows(i, cand)) {
				return -1;
			}
		}
		return cand;
	}

}
