package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class FreqStack
 * @Author: WuMing
 * @CreateDate: 2019/12/4 11:35
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class FreqStack {
	
	HashMap<Integer, LinkedList<Integer>> map;
	int idx;
	PriorityQueue<LinkedList<Integer>> queue;
	
	public FreqStack() {
		idx = -1;
		queue = new PriorityQueue<>((a, b) -> {
			if (a.size() != b.size()) {
				return b.size() - a.size();
			} else {
				return b.getLast() - a.getLast();
			}
		});
		map = new HashMap<>();
	}
	
	public void push(int x) {
		LinkedList<Integer> list;
		idx++;
		if (!map.containsKey(x)) {
			list = new LinkedList<>();
			list.add(x);
			list.add(idx);
			map.put(x, list);
			queue.add(list);
		} else {
			list = map.get(x);
			queue.remove(list);
			list.add(idx);
			queue.add(list);
		}
		
	}
	
	public int pop() {
		LinkedList<Integer> list = queue.poll();
		list.removeLast();
		if (list.size() > 1) {
			queue.add(list);
		}
		return list.getFirst();
	}
	
	public static void main(String[] args) {
		FreqStack stack = new FreqStack();
		stack.push(5);
		stack.push(7);
		stack.push(5);
		stack.push(7);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
