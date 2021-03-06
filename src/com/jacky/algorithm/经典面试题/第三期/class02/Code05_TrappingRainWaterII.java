package com.jacky.algorithm.经典面试题.第三期.class02;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 19经典面试题目三
 *
 * <p>
 * 如果给你一个二维数组，每一个值表示这一块地形的高度，
 * 求整块地形能装下多少水。
 * </p>
 *
 * 大流程：
 * 1. 准备一个小根堆(记录值，行列信息),还有记录当前节点是否进入堆的记录
 * 2. 将边缘的值加入到小跟堆中
 * 3. 弹出小跟堆的值，更新最大值（其实就是确定当前湖的短板,出海口）, 然后结算当前点的水量( max = Math.max(max, cur.value)更新max, 结算 )
 * 4. 再以当前位置的上下左右没有进入过小跟堆的点进入小跟堆
 *
 * https://leetcode-cn.com/problems/trapping-rain-water-ii/
 *
 * @author: HuangJiaJie
 * @create: 2022/1/31
 **/
public class Code05_TrappingRainWaterII {

	public static class Node {
		public int value;
		public int row;
		public int col;

		public Node(int v, int r, int c) {
			value = v;
			row = r;
			col = c;
		}

	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value - o2.value;
		}

	}

	public static int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
			return 0;
		}
		int N = heightMap.length;
		int M = heightMap[0].length;
		// isEnter[i][j] == true  之前进过
		//  isEnter[i][j] == false 之前没进过
		boolean[][] isEnter = new boolean[N][M];
		// 小根堆
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());


		// 放入小跟堆的时候结算 水量 所以先把四周放入小跟堆，因为四周肯定是没有水量可以结算的
		// 将四周的边缘位置放入 小根堆
		for (int col = 0; col < M - 1; col++) {
			isEnter[0][col] = true;
			heap.add(new Node(heightMap[0][col], 0, col));
		}
		for (int row = 0; row < N - 1; row++) {
			isEnter[row][M - 1] = true;
			heap.add(new Node(heightMap[row][M - 1], row, M - 1));
		}
		for (int col = M - 1; col > 0; col--) {
			isEnter[N - 1][col] = true;
			heap.add(new Node(heightMap[N - 1][col], N - 1, col));
		}
		for (int row = N - 1; row > 0; row--) {
			isEnter[row][0] = true;
			heap.add(new Node(heightMap[row][0], row, 0));
		}
		
		
		int water = 0; // 每个位置的水量，累加到water上去
		int max = 0; // 每个node在弹出的时候，如果value更大，更新max，否则max的值维持不变
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			// 是否更新，更新就是换湖,结算当前湖! 结算当前湖! 结算当前湖!
			max = Math.max(max, cur.value);
			int r = cur.row;
			int c = cur.col;
			// 上方
			if (r > 0 && !isEnter[r - 1][c]) { // 如果有上面的位置并且上面位置没进过堆
				water += Math.max(0, max - heightMap[r - 1][c]);
				isEnter[r - 1][c] = true;
				heap.add(new Node(heightMap[r - 1][c], r - 1, c));
			}
			if (r < N - 1 && !isEnter[r + 1][c]) {
				water += Math.max(0, max - heightMap[r + 1][c]);
				isEnter[r + 1][c] = true;
				heap.add(new Node(heightMap[r + 1][c], r + 1, c));
			}
			if (c > 0 && !isEnter[r][c - 1]) {
				water += Math.max(0, max - heightMap[r][c - 1]);
				isEnter[r][c - 1] = true;
				heap.add(new Node(heightMap[r][c - 1], r, c - 1));
			}
			if (c < M - 1 && !isEnter[r][c + 1]) {
				water += Math.max(0, max - heightMap[r][c + 1]);
				isEnter[r][c + 1] = true;
				heap.add(new Node(heightMap[r][c + 1], r, c + 1));
			}
		}
		return water;
	}


	// Dijkstra

}
