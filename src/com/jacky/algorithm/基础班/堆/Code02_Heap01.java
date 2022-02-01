package com.jacky.algorithm.基础班.堆;

/**
 * <p>
 * 1）堆结构就是用数组实现的完全二叉树结构
 * 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 * 4）堆结构的heapInsert与heapify操作
 * 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code02_Heap01 {

	public static class MyMaxHeap {
	    // 父节点 ； i-1/2
		// 左 ： 2*i + 1
		// 右 ： 2*i + 2
		private int[] heap;
		private final int limit;
		private int heapSize;

		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void push(int value) {
			if (heapSize == limit) {
				throw new RuntimeException("heap is full");
			}
			heap[heapSize] = value;
			heapInsert(heap, heapSize++);
		}

		public int pop() {
			int ans = heap[0];
			// 堆根值 无效了
			swap(heap, 0, --heapSize);
			// 调整堆的状态
			heapify(heap, 0, heapSize);
			return ans;
		}

		// 当前数是否比父节点的值要大, 如果是交换
		private void heapInsert(int[] arr, int index) {
			// 当前数是否比父节点的值要大, 或者 index == 0 就是来到堆的根
			while (arr[index] > arr[(index - 1) / 2]) {
				swap(arr, index, (index - 1) / 2);
				// 来到父节点的位置
				index = (index - 1) / 2;
			}
		}

		// 从 index 位置 ，往下看，不断的下沉
		// 停的条件： 当前的节点的嗨知不在比当前节点大 或者 没有孩子了
		private void heapify(int[] arr, int index, int heapSize) {
			// 左节点
			int left = index * 2 + 1;
			while (left < heapSize) {  // 潜台词： 是否有孩子
				// 左右2个孩子中哪一个最大，就赋值给largest
				// 选择右孩子的条件：
                // 1. 有右孩子
				// 2. 右孩子的值比左孩子大
				int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

				// 左右孩子的最大值和 父节点的值，取最大
				largest = arr[largest] > arr[index] ? largest : index;
				// 不用往下沉
				if (largest == index) {
					break;
				}
				// 往下沉的逻辑
				swap(arr, largest, index);
				index = largest;
				left = index * 2 + 1;
			}
		}

		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}

	}

	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;

		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}

		public int pop() {
			int maxIndex = 0;
			for (int i = 1; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}

	}

	public static void main(String[] args) {
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MyMaxHeap my = new MyMaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");

	}

}
