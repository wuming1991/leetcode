package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class BSTIterator
 * @Author: WuMing
 * @CreateDate: 2019/5/21 17:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class BSTIterator {
	
	LinkedList<Integer> list;
	public BSTIterator(TreeNode root) {
		list = new LinkedList<>();
		if (root != null) {
			BSTIteratorHelper(list, root);
		}
	}
	private void BSTIteratorHelper(LinkedList<Integer> list, TreeNode root) {
		if (root.left != null) {
			BSTIteratorHelper(list, root.left);
		}
		list.addLast(root.val);
		if (root.right != null) {
			BSTIteratorHelper(list, root.right);
		}
	}
	/**
	 * @return the next smallest number
	 */
	public int next() {
		return list.removeFirst();
	}
	
	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		return !list.isEmpty();
	}
}
