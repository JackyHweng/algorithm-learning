package com.jacky.algorithm.高频面试题;

public class Problem_0918_MaxSubarraySumCircular {

    public int maxSubarraySumCircular(int[] nums) {
        int maxVal = nums[0],minVal = nums[0],preMin = 0,preMax = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]);
            maxVal = Math.max(maxVal, preMax);
            preMin = Math.min(preMin+nums[i],nums[i]);
            minVal = Math.min(minVal,preMin);
            sum += nums[i];
        }
        return Math.max(maxVal,sum-minVal==0?maxVal:sum-minVal);
    }
}
