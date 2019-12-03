package com.leetcode;

import com.leetcode.MapSum.TreNode;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class FindElements1
 * @Author: WuMing
 * @CreateDate: 2019/11/20 17:07
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class FindElements1 {
	
	TreeNode root;
	
	public FindElements1(TreeNode root) {
		this.root = root;
	}
	
	public boolean find(int target) {
		int[] stack = new int[21];
		int idx = 0;
		while (target > 0) {
			stack[idx++] = target % 2;
			target = (target - 1) / 2;
		}
		
		TreeNode cur = root;
		while (idx > 0) {
			idx--;
			if (cur == null) {
				return false;
			}
			if (stack[idx] == 0) {
				cur = cur.right;
			} else {
				cur = cur.left;
			}
			
		}
		return cur == null;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(-1);
		root.left=new TreeNode(-1);
		root.right=new TreeNode(-1);
		root.left.left=new TreeNode(-1);
		root.right.left=new TreeNode(-1);
		//root.left.right=new TreeNode(-1);
		root.right.right=new TreeNode(-1);
		FindElements1 elements = new FindElements1(root);
		elements.find(4);
	}
}
