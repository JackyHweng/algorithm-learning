package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0543_DiameterOfBinaryTree {


  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root);
        return ans - 1;
    }

    public int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);
        ans = Math.max(ans,l + r + 1);
        return Math.max(l , r) + 1;
    }

}
