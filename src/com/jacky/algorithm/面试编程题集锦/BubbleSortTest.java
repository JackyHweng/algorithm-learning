package com.jacky.algorithm.面试编程题集锦;

import java.util.Arrays;

public class BubbleSortTest {

    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        int n = arr.length;
        for(int e = n  -1 ;e > 0 ; e--){
            for(int i = 0 ; i < e; i++){
                if(arr[e] < arr[i]){
                    swap(arr,e,i);
                }
            }
        }
    }

    public static void swap(int[] arr ,int i , int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args) {
        int[] arr = {2,3,1,5,6,8,9};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
