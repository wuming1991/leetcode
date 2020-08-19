package com.leetcode2;

import java.util.Stack;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/8 19:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class BrowserHistory {
	Stack<String> back, forward;
	public BrowserHistory(String homepage) {
		back = new Stack<>();
		forward = new Stack<>();
		back.push(homepage);
	}
	public void visit(String url) {
		back.push(url);
		forward.clear();
	}
	public String back(int steps) {
		while (back.size()>1 && steps > 0) {
			forward.push(back.pop());
			steps--;
		}
		return back.peek();
	}
	public String forward(int steps) {
		while (!forward.isEmpty() && steps > 0) {
			back.push(forward.pop());steps--;
		}
		return back.peek();
	}
	
	public static void main(String[] args) {
		BrowserHistory history = new BrowserHistory("lee");
		history.visit("a");
		history.visit("b");
		history.visit("c");
		history.back(1);
		history.back(1);
		history.forward(1);
		history.visit("d");
		history.forward(2);
		history.back(2);
		history.back(7);
	}
}
