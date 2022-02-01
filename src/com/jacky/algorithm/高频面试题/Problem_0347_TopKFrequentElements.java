package com.jacky.algorithm.高频面试题;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <p>
 *
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/1/28
 **/
public class Problem_0347_TopKFrequentElements {

	public static class Node {
		public int num;
		public int count;

		public Node(int k) {
			num = k;
			count = 1;
		}
	}

	public static class CountComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// 小根堆
			return o1.count - o2.count;
		}

	}

	public static int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Node> map = new HashMap<>();
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, new Node(num));
			} else {
				map.get(num).count++;
			}
		}
		PriorityQueue<Node> heap = new PriorityQueue<>(new CountComparator());
		for (Node node : map.values()) {
			if (heap.size() < k || (heap.size() == k && node.count > heap.peek().count)) {
				heap.add(node);
			}
			if (heap.size() > k) {
				heap.poll();
			}
		}

		int[] ans = new int[k];
		int index = 0;
		while (!heap.isEmpty()) {
			ans[index++] = heap.poll().num;
		}
		return ans;
	}



	// 方法二
	public int[] topKFrequent2(int[] nums, int k) {
		Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
		for (int num : nums) {
			occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
		}

		// int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] m, int[] n) {
				return m[1] - n[1];
			}
		});
		for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
			int num = entry.getKey(), count = entry.getValue();
			if (queue.size() == k) {
				if (queue.peek()[1] < count) {
					queue.poll();
					queue.offer(new int[]{num, count});
				}
			} else {
				queue.offer(new int[]{num, count});
			}
		}
		int[] ret = new int[k];
		for (int i = 0; i < k; ++i) {
			ret[i] = queue.poll()[0];
		}
		return ret;
	}


}
