package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * </p>
 *
 * 1. 利用归并排序 和 步长的方式来解决
 * @author: HuangJiaJie
 * @create: 2022/1/5
 **/
public class Problem_0148_SortList {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int v) {
			val = v;
		}
	}

	public static ListNode sortList(ListNode head) {
		int N = 0;
		ListNode cur = head;
		// 计算链表的长度
		while (cur != null) {
			N++;
			cur = cur.next;
		}

		ListNode h = head;
		ListNode teamFirst = head;
		ListNode pre = null;
		// 累加步长
		for (int len = 1; len < N; len <<= 1) {
			while (teamFirst != null) {
				// 返回第一个合并的头和尾， 和第二组的头和尾 ，第二组尾的下一个
				ListNode[] hthtn = hthtn(teamFirst, len);
				//这里返回合并之后的头和尾
				//merge head and merge tail
				ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
				if (h == teamFirst) {
					h = mhmt[0];
					pre = mhmt[1];
				} else {
					pre.next = mhmt[0];
					pre = mhmt[1];
				}
				teamFirst = hthtn[4];
			}
			teamFirst = h;
			pre = null;
		}
		return h;
	}

	/**
	 * h 左组的头 head
     * t 左组的尾 tail
	 * h 右组的尾 head
	 * t 右组的尾 tail
	 * n 右组的下一个 next
	 * @param teamFirst
	 * @param len
	 * @return
	 */
	public static ListNode[] hthtn(ListNode teamFirst, int len) {
		ListNode ls = teamFirst;
		ListNode le = teamFirst;
		ListNode rs = null;
		ListNode re = null;
		ListNode next = null;
		int pass = 0;
		while (teamFirst != null) {
			pass++;
			if (pass <= len) {
				le = teamFirst;
			}
			if (pass == len + 1) {
				rs = teamFirst;
			}
			if (pass > len) {
				re = teamFirst;
			}
			if (pass == (len << 1)) {
				break;
			}
			teamFirst = teamFirst.next;
		}
		le.next = null;
		if (re != null) {
			next = re.next;
			re.next = null;
		}
		return new ListNode[] { ls, le, rs, re, next };
	}

	public static ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re) {
		if (rs == null) {
			return new ListNode[] { ls, le };
		}
		ListNode head = null;
		ListNode pre = null;
		ListNode cur = null;
		ListNode tail = null;
		while (ls != le.next && rs != re.next) {
			if (ls.val <= rs.val) {
				cur = ls;
				ls = ls.next;
			} else {
				cur = rs;
				rs = rs.next;
			}
			if (pre == null) {
				head = cur;
				pre = cur;
			} else {
				pre.next = cur;
				pre = cur;
			}
		}
		if (ls != le.next) {
			while (ls != le.next) {
				pre.next = ls;
				pre = ls;
				tail = ls;
				ls = ls.next;
			}
		} else {
			while (rs != re.next) {
				pre.next = rs;
				pre = rs;
				tail = rs;
				rs = rs.next;
			}
		}
		return new ListNode[] { head, tail };
	}

}
