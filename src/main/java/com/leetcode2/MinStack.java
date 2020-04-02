package com.leetcode2;

import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class MinStack
 * @Author: WuMing
 * @CreateDate: 2020/3/27 10:24
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MinStack {
	
	Stack<Integer> stack;
	Stack<Integer> min;
	
	public MinStack() {
		stack = new Stack();
		min = new Stack<>();
	}
	
	public void push(int x) {
		stack.push(x);
		if(min.isEmpty()||x<=min.peek()){
			min.push(x);
		}
	}
	
	public void pop() {
		Integer pop = stack.pop();
		if(min.peek().equals(pop)){
			min.pop();
		}
	}
	
	public int top() {
		return stack.peek();
	}
	
	public int getMin() {
		return min.peek();
	}
	
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
	}
}
