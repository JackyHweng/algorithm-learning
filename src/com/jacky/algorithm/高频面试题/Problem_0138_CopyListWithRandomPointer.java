package com.jacky.algorithm.高频面试题;

/**
 * <p>
 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，
 其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

 返回复制链表的头节点。

 用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：

 val：一个表示Node.val的整数。
 random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 你的代码 只 接受原链表的头节点 head 作为传入参数。


 方案一：
 1. 利用Map 遍历 链表 key:value = 旧节点：新节点(a,a2)
 2. 然后再遍历一次，将新的节点按照旧节点的链接方式链接起来

 方案二:
 1. 遍历链接，遇到一个节点将克隆的节点生成在节点后面
  a -> a1 -> b -> b1 -> c -> c1 -> null
 * </p>
 *
 *
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0138_CopyListWithRandomPointer {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	public static Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// 1 -> 2 -> 3 -> null
		// 1 -> 1' -> 2 -> 2' -> 3 -> 3'
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.val);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node copy = null;
		// 1 1' 2 2' 3 3'
		// 依次设置 1' 2' 3' random指针
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		// 老 新 混在一起，next方向上，random正确
		// next方向上，把新老链表分离
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

}
