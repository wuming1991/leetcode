package com.base;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ProjectName: leetcode
 * @Package: com.base
 * @Class TreeNode
 * @Author: WuMing
 * @CreateDate: 2020/9/24 9:54
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class TreeNode {
	
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int x) {
		val = x;
	}
	
	public static TreeNode toTree(String s) {
		String[] value = s.split(",");
		int len = value.length;
		if (len == 0) {
			return null;
		}
		TreeNode ret = new TreeNode(Integer.parseInt(value[0])), t;
		ArrayList<TreeNode> cur = new ArrayList<>(), next;
		cur.add(ret);
		int idx = 1;
		while (idx < len) {
			next = new ArrayList<>();
			for (TreeNode node : cur) {
				if (idx == len) {
					break;
				}
				if (!"null".equals(value[idx])) {
					t = new TreeNode(Integer.parseInt(value[idx]));
					node.left = t;
					next.add(t);
				}
				idx++;
				if (idx == len) {
					break;
				}
				if (!"null".equals(value[idx])) {
					t = new TreeNode(Integer.parseInt(value[idx]));
					node.right = t;
					next.add(t);
				}
				idx++;
			}
			cur = next;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] ints = new int[9];
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			int x=scanner.nextInt();
			ints[i]=x;
		}
		for (int i = 0; i < 9; i++) {
			System.out.println(ints[i]);
		}
	}
}
