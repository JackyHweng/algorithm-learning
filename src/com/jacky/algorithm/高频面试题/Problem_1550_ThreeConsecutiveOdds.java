package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/2/15
 **/
public class Problem_1550_ThreeConsecutiveOdds {

    /**
     * 模拟做法
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> ans = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0){
            return ans;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                boolean isMin = true;
                boolean isMax = true;

                for(int a = 0 ; a < m ; a++){
                    if(matrix[i][j] > matrix[i][a]){
                        isMin = false;
                        break;
                    }
                }

                if(!isMin){
                    continue;
                }

                for(int a = 0 ; a < n ; a++){
                    if(matrix[a][j] > matrix[i][j]){
                        isMax = false;
                        break;
                    }
                }

                if(isMax){
                    ans.add(matrix[i][j]);
                }
            }
        }

        return ans;
    }

    /**
     * 预处理数组
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        int[] maxCol = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }
}
