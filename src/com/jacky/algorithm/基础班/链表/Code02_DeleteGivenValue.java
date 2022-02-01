package com.jacky.algorithm.基础班.链表;

/**
 * <p>
 * 链表删除给定的值
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/24
 **/
public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 删除给定的值
	 * @param head
	 * @param num
	 * @return
	 */
	public static Node removeValue(Node head, int num) {
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		// head来到 第一个不需要删的位置
        // pre 负责修改指针的值
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

}
