package com.jacky.algorithm.高频面试题;

public class Problem_1380_LuckyNumbers {

    public boolean threeConsecutiveOdds(int[] arr) {
        int r = -1;
        int c = 0;
        for(int i = 0; i < arr.length - 3; i++){
            while( r + 1 < arr.length &&  (r + 1 - i  < 3)){
                r++;
                if((arr[r] & 1) == 1){
                    c++;
                }
            }
            if(c == 3){
                return true;
            }
            if((arr[i] & 1) == 1){
                c--;
            }
        }

        return false;
    }
}
