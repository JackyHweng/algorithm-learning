package com.jacky.algorithm.高频面试题;

import java.util.Arrays;

/**
 * <p>
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 *
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 *
 * </p>
 *
 * 1. 找到数组的中位数
 * 2. 荷兰国旗将 数组按照中位数排序
 * 3. 使用完美洗牌求出结果(区分数组为奇数个和偶数个的情况)
 * @author: HuangJiaJie
 * @create: 2022/1/27
 **/
public class Problem_0324_WiggleSortII {



	public void wiggleSort3(int[] nums) {
		int[] help = nums.clone(); //不能写成int[] help = nums,排序后两个数组都改变
		Arrays.sort(help);
		int N = nums.length;
		//比如123456
		for (int i = 1; i < nums.length; i += 2) {
			nums[i] = help[--N]; //遍历完成后 x 6 x 5 x 4
		}
		for (int i = 0; i < nums.length; i += 2) {
			nums[i] = help[--N]; //便利完成后 3 6 2 5 1 4
		}
	}

	public  void wiggleSort2(int[] nums) {
		int[]bucket=new int[5001];
		for(int num:nums)bucket[num]++;
		int len=nums.length;
		int small,big;//穿插数字时的上界
		//总长度为奇数时，“小 大 小 大 小”边界左右都为较小的数；
		//总长度为偶数时，“小 大 小 大”边界左为较小的数，边界右为较大的数
		if((len&1)==1){
			small=len-1;
			big=len-2;
		}else{
			small=len-2;
			big=len-1;
		}
		int j=5000; //从后往前，将桶中数字穿插到数组中，后界为j
		//桶中大的数字在后面，小的数字在前面，所以先取出较大的数字，再取出较小的数字
		//先将桶中的较大的数字穿插放在nums中
		for(int i=1;i<=big;i+=2){
			while (bucket[j]==0)j--;//找到不为0的桶
			nums[i]=j;
			bucket[j]--;
		}
		//再将桶中的较小的数字穿插放在nums中
		for(int i=0;i<=small;i+=2){
			while (bucket[j]==0)j--;//找到不为0的桶
			nums[i]=j;
			bucket[j]--;
		}
	}

	// 时间复杂度O(N)，额外空间复杂度O(1)
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
		int N = nums.length;
		findIndexNum(nums, 0, nums.length - 1, N / 2);
		if ((N & 1) == 0) {
			shuffle(nums, 0, nums.length - 1);
			reverse(nums, 0, nums.length - 1);
		} else {
			shuffle(nums, 1, nums.length - 1);
		}
	}

	public static int findIndexNum(int[] arr, int L, int R, int index) {
		int pivot = 0;
		int[] range = null;
		while (L < R) {
			pivot = arr[L + (int) (Math.random() * (R - L + 1))];
			range = partition(arr, L, R, pivot);
			if (index >= range[0] && index <= range[1]) {
				return arr[index];
			} else if (index < range[0]) {
				R = range[0] - 1;
			} else {
				L = range[1] + 1;
			}
		}
		return arr[L];
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void shuffle(int[] nums, int l, int r) {
		while (r - l + 1 > 0) {
			int lenAndOne = r - l + 2;
			int bloom = 3;
			int k = 1;
			while (bloom <= lenAndOne / 3) {
				bloom *= 3;
				k++;
			}
			int m = (bloom - 1) / 2;
			int mid = (l + r) / 2;
			rotate(nums, l + m, mid, mid + m);
			cycles(nums, l - 1, bloom, k);
			l = l + bloom - 1;
		}
	}

	public static void cycles(int[] nums, int base, int bloom, int k) {
		for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
			int next = (2 * trigger) % bloom;
			int cur = next;
			int record = nums[next + base];
			int tmp = 0;
			nums[next + base] = nums[trigger + base];
			while (cur != trigger) {
				next = (2 * cur) % bloom;
				tmp = nums[next + base];
				nums[next + base] = record;
				cur = next;
				record = tmp;
			}
		}
	}

	public static void rotate(int[] arr, int l, int m, int r) {
		reverse(arr, l, m);
		reverse(arr, m + 1, r);
		reverse(arr, l, r);
	}

	public static void reverse(int[] arr, int l, int r) {
		while (l < r) {
			swap(arr, l++, r--);
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
