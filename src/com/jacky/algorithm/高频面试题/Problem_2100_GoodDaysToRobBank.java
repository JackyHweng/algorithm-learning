package com.jacky.algorithm.高频面试题;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 你和一群强盗准备打劫银行。给你一个下标从 0开始的整数数组security，其中security[i]是第 i天执勤警卫的数量。日子从 0开始编号。同时给你一个整数time。
 *
 * 如果第 i天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 *
 * 第 i天前和后都分别至少有 time天。
 * 第 i天前连续 time天警卫数目都是非递增的。
 * 第 i天后连续 time天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
 * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 *
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0开始）。返回的日子可以 任意顺序排列。
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/6
 **/
public class Problem_2100_GoodDaysToRobBank {


    /**
     * 辅助数组 一个记录
     *  left 从左到右 i 位置 连续递减的的个数
     *  right 从右到左 i 位置 连续递减的的个数
     * @param nums
     * @param time
     * @return
     */
    public List<Integer> goodDaysToRobBank(int[] nums, int time) {
        List<Integer> ans = new ArrayList<>();
        if(nums == null || nums.length ==0){
            return ans;
        }
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for(int i = 1; i < n; i++){
            if(nums[i] <= nums[i-1]){
                left[i] = left[i-1] +1;
            }
            if(nums[n - i -1] <= nums[n - i]){
                right[n - i - 1] = right[n -i] +1;
            }
        }
        for(int i = time; i < n - time ; i++){
            if(left[i] >= time && right[i] >= time){
                ans.add(i);
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] security = new int[]{5,3,3,3,5,6,2};
        int time = 2;
        final List<Integer> ans = new Problem_2100_GoodDaysToRobBank().goodDaysToRobBank(security, time);
        System.out.println(ans);
    }
}
