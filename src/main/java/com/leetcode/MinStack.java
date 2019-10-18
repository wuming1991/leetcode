package com.leetcode;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MinStack
 * @Author: WuMing
 * @CreateDate: 2019/4/18 17:05
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MinStack {
	
	ArrayList<Integer> list;
	int topIndex;
	
	public MinStack() {
		list = new ArrayList();
		
		topIndex = -1;
	}
	
	public void push(int x) {
		list.add(x);
		topIndex++;
		
	}
	
	public void pop() {
		if (topIndex < 0) {
			return;
		}
		list.remove(topIndex);
		topIndex--;
	}
	
	public int top() {
		if (topIndex >= 0) {
			return list.get(topIndex);
		} else {
			throw new RuntimeException();
		}
	}
	
	public int getMin() {
		if (topIndex >= 0) {
			int min = Integer.MAX_VALUE;
			for (Integer integer : list) {
				min = Math.min(min, integer);
			}
			return min;
		}else{
			throw new RuntimeException();
		}
	}
	
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		minStack.getMin();
		minStack.pop();
		minStack.top();
		minStack.getMin();
	}
}
