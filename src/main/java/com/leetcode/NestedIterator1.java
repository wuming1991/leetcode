package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class NestedIterator1
 * @Author: WuMing
 * @CreateDate: 2019/5/22 18:23
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class NestedIterator1 {
	
	LinkedList<Integer> list;
	
	public NestedIterator1(List<NestedInteger> nestedList) {
		list = new LinkedList<>();
		helper(nestedList);
	}
	
	private void helper(List<NestedInteger> nestedList) {
		for (NestedInteger nested : nestedList) {
			if (nested.isInteger()) {
				list.add(nested.getInteger());
			} else {
				helper(nested.getList());
			}
		}
	}
	
	public Integer next() {
	return 	list.removeFirst();
	}
	
	public boolean hasNext() {
		return !list.isEmpty();
	}
}
