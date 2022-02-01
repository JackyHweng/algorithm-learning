package com.jacky.algorithm.高频面试题;

import java.util.*;

/**
 * <p>
 *
 * 回溯的本质:
 * https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
 *
 * ①画出递归树，找到状态变量(回溯函数的参数)，这一步非常重要※
 * ②根据题意，确立结束条件
 * ③找准选择列表(与函数参数相关),与第一步紧密关联※
 * ④判断是否需要剪枝
 * ⑤作出选择，递归调用，进入下一层
 * ⑥撤销选择
 *
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/26
 **/
public class Problem_0077_Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        process(n, k, 1, path, res);
        return res;
    }

    private void process(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
        // 剪枝
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            process(n, k, i + 1, path, res);
            path.removeLast();
        }
    }
}
