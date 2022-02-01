package com.jacky.algorithm.高频面试题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 *
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false
 *
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * </p>
 *
 * 1. 拓扑排序
 * 2. 找到入度为0的节点， 删除入度为0的节点
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0207_CourseSchedule {

	// 一个node，就是一个课程
	public static class Node {
		// name是课程的编号
		public int name;
		// in是课程的入度
		public int in;
		// 邻居(可以到达的节点)
		public ArrayList<Node> nexts;

		public Node(int n) {
			name = n;
			in = 0;
			nexts = new ArrayList<>();
		}
	}

	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0) {
			return true;
		}
		// 建立拓扑图
		HashMap<Integer, Node> nodes = new HashMap<>();
		for (int[] arr : prerequisites) {
			int to = arr[0];
			int from = arr[1];
			if (!nodes.containsKey(to)) {
				nodes.put(to, new Node(to));
			}
			if (!nodes.containsKey(from)) {
				nodes.put(from, new Node(from));
			}
			Node t = nodes.get(to);
			Node f = nodes.get(from);
			f.nexts.add(t);
			t.in++;
		}

		// 获取整个拓扑中的节点数
		int needPrerequisiteNums = nodes.size();
		// 从入度为0的开始统计
		Queue<Node> zeroInQueue = new LinkedList<>();
		for (Node node : nodes.values()) {
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}

		int count = 0;
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			// 弹出说明可以完成一门课
			count++;
			for (Node next : cur.nexts) {
			    // 说明消除了他入度
				if (--next.in == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		// 能否完成所有的科目
		return count == needPrerequisiteNums;
	}

}
