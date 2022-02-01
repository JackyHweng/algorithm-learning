package com.jacky.algorithm.高频面试题;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/11/24
 **/
public class Problem_739 {

    // 利用单调递减栈
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        // 这个数组就是记录当前位置距离最近一次比他大的数的位置
        int[] ans = new int[length];
        // 注意的是这个栈只存储数组下标
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

}
