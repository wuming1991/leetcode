package com.leetcode2;

import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class MinStack2
 * @Author: WuMing
 * @CreateDate: 2020/3/27 10:39
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MinStack2 {
	Stack<int[]> stack;
	int min=Integer.MAX_VALUE;
	public MinStack2() {
		stack = new Stack();
	}
	
	public void push(int x) {
		min= Math.min(x,min);
		stack.push(new int[]{x,min});
		
	}
	
	public void pop() {
		 stack.pop();
	}
	
	public int top() {
		return stack.peek()[0];
	}
	
	public int getMin() {
		return stack.peek()[1];
	}
}
