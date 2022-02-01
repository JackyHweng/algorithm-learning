package com.jacky.algorithm.基础班.二叉树递归套路;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 派对的最大快乐值
 *
 * 员工信息的定义如下:
 * class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 *
 *
 * 派对的最大快乐值
 *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 *  树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 *  叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 *
 *
 *  派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 *
 *
 * 假设 x 有3个节点 ，a,b,c
 * 分类讨论：
 * 1、 假设X来了，那么最大的happy 就是 x 的happy + a,b,c 不来的子树最大的happy
 * 2、 假设X不来了，那么最大的happy 就是 0 + Math.max(a来， a不来) + Math.max(b来， b不来) + Math.max(c来， c不来)
 * 因为给a 发请帖，a不一定会来,所以需要算 a来 和 a不来 happy 的最大值
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2021/12/25
 **/
public class Code09_MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}

	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	public static int process1(Employee cur, boolean up) {
		if (up) {
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else {
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}

	public static int maxHappy2(Employee boss) {
		if (boss == null) {
			return 0;
		}
		Info all = process2(boss);
		return Math.max(all.yes, all.no);
	}

	public static class Info {
		public int yes;
		public int no;

		public Info(int y, int n) {
			yes = y;
			no = n;
		}
	}

	public static Info process2(Employee x) {
		if (x.nexts.isEmpty()) {
			return new Info(x.happy, 0);
		}
		int yes = x.happy;
		int no = 0;
		for (Employee next : x.nexts) {
			Info nextInfo = process2(next);
			yes += nextInfo.no;
			no += Math.max(nextInfo.yes, nextInfo.no);
		}
		return new Info(yes, no);
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
