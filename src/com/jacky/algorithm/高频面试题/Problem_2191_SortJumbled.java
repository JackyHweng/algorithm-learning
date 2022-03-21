package com.jacky.algorithm.高频面试题;

import java.util.*;

public class Problem_2191_SortJumbled {

    public class Node{
        private int origin;
        private int num;
        private int order;

        public Node(int origin, int num, int order) {
            this.origin = origin;
            this.num = num;
            this.order = order;
        }

    }

    public class NumComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            if(o1.num == o2.num){
                return o1.order = o2.order;
            }else{
                return o1.num - o2.num;
            }
        }
    }
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int[] ans = new int[nums.length];
        PriorityQueue<Node> heap = new PriorityQueue<>(new NumComparator());

        for(int i = 0 ; i < nums.length; i++){
            Node n = new Node(nums[i],convert(mapping,nums[i]),i);
            heap.add(n);
        }

        int index = 0;
        while(!heap.isEmpty()){
            ans[index++] = heap.poll().origin;
        }
        return  ans;
    }


    public int convert(int[] mapping, int val) {
        int digit = 1;
        int ret = 0;
        if (val <= 9) {
            return mapping[val];
        }
        while (val > 0) {
            ret += mapping[val % 10] * digit;
            digit *= 10;
            val /= 10;
        }
        return ret;
    }


    public static void main(String[] args) {

        /**
         * mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
         * 输出：[338,38,991]
         */
        final Problem_2191_SortJumbled problem_2191_sortJumbled = new Problem_2191_SortJumbled();
        int[] mapping = {8,9,4,0,2,1,3,5,7,6};
        int[] nums = {991,338,38};
        final int[] ans = problem_2191_sortJumbled.sortJumbled(mapping, nums);
        System.out.println(Arrays.toString(ans));
    }
}
