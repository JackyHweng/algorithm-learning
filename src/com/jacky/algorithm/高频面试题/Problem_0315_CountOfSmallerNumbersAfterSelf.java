package com.jacky.algorithm.高频面试题;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 归并排序的小和问题改写
 *
 *
 * </p>
 *
 * 方案一：
 * 1. 归并排序
 * 2. 归并的时候。从大到小合并
 * 3. 右组比较大的话，不产生结果。如果左组的数比右组的数大，那么产生小于这个数的结果
 * 4. 如果相等,拷贝右组的数到辅助数组中
 *
 *
 * 方案二： 改写有序表
 *
 * 方案三： 离散化树状数组 (com.jacky.algorithm.followup.FollowIndexTree)
 * 方案四： 线段树?
 *
 *
 * 「树状数组」是一种可以动态维护序列前缀和的数据结构，它的功能是：
 *
 * 单点更新 update(i, v)： 把序列 ii 位置的数加上一个值 vv，在该题中 v = 1v=1
 * 区间查询 query(i)： 查询序列 [1 \cdots i][1⋯i] 区间的区间和，即 ii 位置的前缀和
 *
 * @author: HuangJiaJie
 * @create: 2022/1/12
 **/
public class Problem_0315_CountOfSmallerNumbersAfterSelf {

	public static class Node {
		public int value;
		public int index;

		public Node(int v, int i) {
			// 值
			value = v;
			// 下标
			index = i;
		}
	}

	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null) {
			return ans;
		}
		// 初始化
		for (int i = 0; i < nums.length; i++) {
			ans.add(0);
		}
		if (nums.length < 2) {
			return ans;
		}
		Node[] arr = new Node[nums.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Node(nums[i], i);
		}
		// 改写归并
		process(arr, 0, arr.length - 1, ans);
		return ans;
	}

	public static void process(Node[] arr, int l, int r, List<Integer> ans) {
		if (l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);
		process(arr, l, mid, ans);
		process(arr, mid + 1, r, ans);
		merge(arr, l, mid, r, ans);
	}

	public static void merge(Node[] arr, int l, int m, int r, List<Integer> ans) {
		Node[] help = new Node[r - l + 1];
		int i = help.length - 1;
		int p1 = m;
		int p2 = r;
		while (p1 >= l && p2 >= m + 1) {
			// 计算结果
			if (arr[p1].value > arr[p2].value) {
				ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - m);
			}
			help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
		}
		while (p1 >= l) {
			help[i--] = arr[p1--];
		}
		while (p2 >= m + 1) {
			help[i--] = arr[p2--];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}



	/*
	* 首先[树状数组BIT](https://www.bilibili.com/video/BV1pE41197Qj/)
	* 可以从右往左逆序方式遍历nums
	  并将nums[i]放入对应的桶中，
	  eg[5 2 6 1 1]
	  因为最大值是6，开一个长度为7的桶数组(有一个无用索引0，这里写下标是1开始的)
	  从右往左遍历到1，则++数组[1]，即数组[1 0 0 0 0 0]
	  从右往左遍历到1，则++数组[1]，即数组[2 0 0 0 0 0]
	  从右往左遍历到6，则++数组[6]，即数组[2 0 0 0 0 1]
	  从右往左遍历到2，则++数组[2]，即数组[2 1 0 0 0 1]
	  从右往左遍历到5，则++数组[5]，即数组[2 1 0 0 1 1]
	* 这样通过数组从右往左遍历的过程，因为桶的设计是[1]~[max_element(nums)]
	  则遍历到数字i时，需要++桶数组[i]，
	  那么<i的桶统计了对于数字i在nums中右边小于数字i的个数的统计
	  那么只需要前缀和的方式即可求出当前右侧小于当前数字的个数
	  eg[5 2 6 1 1]
	  从右往左遍历到5，则++数组[5]，即数组[2 1 0 0 1 1]
	  那么小于5的个数就是数组[1]~数组[4]的区间和，即2+1+0+0=3
	* 前缀和+要单点修改，树状数组就可以派上用场
	* 但是有个地方注意，上面设置桶是按nums最大值来设置桶数组的长度
	  但万一nums不是全为正数的数组，或者万一nums最大值是一个很大的数
	  直接按照值=index的方式设置桶是不行的，一会mle，二会有很多空桶没有作用，且增加复杂度
	* 所以采用离散化数组，即按数值从小到大来映射桶
	  即[5 2 6 1 1]有用的数值只有[1 2 5 6]可以设置映射
	  f[1]=1 f[2]=2 f[5]=3 f[6]=4即f[nums中数值]=BIT数组的索引index
	  这样优化空间且不用担心负数的情况
	*/

	// 这个是桶
	private static int[] a;
	private static int[] c;
	public static List<Integer> countSmaller2(int[] nums) {
//		List<Integer> ans = new ArrayList<>();
		int[] ans = new int[nums.length];
		// 离散化
		discretization(nums);
		init(nums.length + 5);
		for(int i = nums.length - 1; i >= 0 ; i--){
			int id = getId(nums[i]);
//			ans.add(query(id - 1));
			ans[i] = (query(id - 1));
			update(id);
		}
//		Collections.reverse(ans);
		return Arrays.stream(ans).boxed().collect(Collectors.toList());
	}

	private static void update(int pos) {
		while (pos < c.length) {
			c[pos] += 1;
			pos += lowBit(pos);
		}
	}

	private static int query(int pos) {
		int ret = 0;
		while (pos > 0) {
			ret += c[pos];
			pos -= lowBit(pos);
		}

		return ret;
	}

	private static int getId(int x) {
		return Arrays.binarySearch(a, x) + 1;
	}

	private static void init(int length) {
		c = new int[length];
		Arrays.fill(c, 0);
	}

	private static int lowBit(int x){
		return x & (-x);
	}

	public static void discretization(int[] arr){
		Set<Integer> set = new HashSet<>();
		for (int a: arr) {
			set.add(a);
		}
		a = new int[set.size()];
		int index = 0;
		for (Integer n : set) {
			a[index++] = n;
		}

		Arrays.sort(a);
	}

	public static void main(String[] args) {
		int[] nums = new int[]{5,2,6,1};
		List<Integer> integers = countSmaller2(nums);
		System.out.println(integers);
	}

}
