package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 581. 最短无序连续子数组
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/11/24
 **/
public class Problem_581 {

    public int findUnsortedSubarray2(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int l = 0, r = len - 1;

        for(int i = 0;  i < len; i++){
            if(max <= nums[i])
                max = nums[i];
            else
                l = i;
        }

        // 注意边界
        for(int j = len - 1; j >= 0; j--){
            if(min >= nums[j])
                min = nums[j];
            else
                r = j;
        }

        if(l == 0 && r == len - 1)
            return 0;
        return l - r + 1;
    }
}
