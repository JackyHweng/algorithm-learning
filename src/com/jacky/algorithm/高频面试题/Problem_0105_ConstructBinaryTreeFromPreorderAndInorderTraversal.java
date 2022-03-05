package com.jacky.algorithm.高频面试题;

import java.util.HashMap;

/**
 * <p>
 * TODO
 * </p>
 *
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * 首先根据 preorder 找到根节点是 3
 *
 * 然后根据根节点将 inorder 分成左子树和右子树
 * 左子树
 * inorder [9]
 *
 * 右子树
 * inorder [15,20,7]
 *
 * 把相应的前序遍历的数组也加进来
 * 左子树
 * preorder[9]
 * inorder [9]
 *
 * 右子树
 * preorder[20 15 7]
 * inorder [15,20,7]
 *
 * 现在我们只需要构造左子树和右子树即可，成功把大问题化成了小问题
 * 然后重复上边的步骤继续划分，直到 preorder 和 inorder 都为空，返回 null 即可
 *
 * @author: HuangJiaJie
 * @create: 2022/2/20
 **/
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
		if (L1 > R1) {
			return null;
		}
		TreeNode head = new TreeNode(pre[L1]);
		if (L1 == R1) {
			return head;
		}
		int findIndex = map.get(pre[L1]);
		head.left = f(pre, L1 + 1, L1 + findIndex - L2, in, L2, findIndex - 1, map);
		head.right = f(pre, L1 + findIndex - L2 + 1, R1, in, findIndex + 1, R2, map);
		return head;
	}

}
