package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class FreqStack1
 * @Author: WuMing
 * @CreateDate: 2019/12/4 12:07
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class FreqStack1 {
	
	int max = 0;
	Map<Integer, Integer> map;
	Map<Integer, Stack<Integer>> freqMap;
	
	public FreqStack1() {
		map = new HashMap<>();
		freqMap = new HashMap<>();
	}
	
	public void push(int x) {
		Integer freq = map.getOrDefault(x, 0);
		max = Math.max(max, freq + 1);
		map.put(x, freq + 1);
		Stack<Integer> stack = freqMap.getOrDefault(freq + 1, new Stack<>());
		stack.push(x);
		freqMap.put(freq + 1, stack);
	}
	
	
	
	public int pop() {
		Stack<Integer> stack = freqMap.get(max);
		Integer ret = stack.pop();
		if (stack.size() == 0) {
			max--;
		}
		map.put(ret, map.get(ret) - 1);
		return ret;
	}
	
	public static void main(String[] args) {
		FreqStack1 stack = new FreqStack1();
		stack.push(4);
		stack.push(0);
		stack.push(9);
		stack.push(3);
		stack.push(4);
		stack.push(2);
		System.out.println(stack.pop());
		stack.push(6);
		System.out.println(stack.pop());
		stack.push(1);
		System.out.println(stack.pop());
		stack.push(1);
		System.out.println(stack.pop());
		stack.push(4);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}
}
