package com.leetcode2;

import com.sun.javafx.sg.prism.web.NGWebView;
import java.util.Stack;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/30 9:03
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class CQueue {
	Stack<Integer> a,b;
	public CQueue() {
		 a = new Stack<>();
		 b = new Stack<>();
	}
	
	public void appendTail(int value) {
		a.push(value);
	}
	
	public int deleteHead() {
		while (!a.isEmpty()) {
			b.push(a.pop());
		}
		int ret = b.isEmpty()?-1:b.pop();
		while (!b.isEmpty()) {
			a.push(b.pop());
		}
		return ret;
	}
	
	public static void main(String[] args) {
		CQueue cQueue = new CQueue();
		cQueue.appendTail(1);
		System.out.println(cQueue.deleteHead());
		System.out.println(cQueue.deleteHead());
	}
}
