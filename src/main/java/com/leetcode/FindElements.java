package com.leetcode;

import com.leetcode.Test13.TireNode;
import java.util.HashSet;
import javax.lang.model.element.VariableElement;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class FindElements
 * @Author: WuMing
 * @CreateDate: 2019/11/20 16:46
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class FindElements {
	
	HashSet<Integer> set= new HashSet<>();
	public FindElements(TreeNode root) {
		root.val = 0;
		fillTree(root);
		this.set.add(0);
	}
	private void fillTree(TreeNode root) {
		int val = root.val;
		if (root.left != null) {
			root.left.val = val * 2 + 1;
			set.add(val * 2 + 1);
			fillTree(root.left);
		}
		if (root.right != null) {
			root.right.val = val * 2 + 2;
			set.add(val * 2 + 2);
			fillTree(root.right);
		}
	}
	public boolean find(int target) {
		return set.contains(target);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(-1);
		root.left=new TreeNode(-1);
		root.right=new TreeNode(-1);
		root.left.left=new TreeNode(-1);
		root.right.left=new TreeNode(-1);
		root.left.right=new TreeNode(-1);
		root.right.right=new TreeNode(-1);
		FindElements elements = new FindElements(root);
		elements.find(10);
	}
}
