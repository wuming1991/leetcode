package com.leetcode2;

/**
 * @Author: WuMing
 * @CreateDate: 2020/5/26 19:13
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class CustomStack {
	int[] stack;
	int max,idx;
	public CustomStack(int maxSize) {
		 stack = new int[maxSize];
		 max=maxSize;
		 idx=0;
	}
	
	public void push(int x) {
		if(idx<max){
			stack[idx++]=x;
		}
	}
	
	public int pop() {
		if(idx>0){
			 return stack[--idx];
		}
		return -1;
	}
	
	public void increment(int k, int val) {
		  k= Math.min(k,idx);
		for (int i = 0; i < k; i++) {
			stack[i]+=val;
		}
	}
}
