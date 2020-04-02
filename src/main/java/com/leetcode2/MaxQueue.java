package com.leetcode2;

import java.util.LinkedList;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class MaxQueue
 * @Author: WuMing
 * @CreateDate: 2020/4/2 15:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MaxQueue {
	LinkedList<Integer> queue,max;
	public MaxQueue() {
		queue=new LinkedList<>();
		max=new LinkedList<>();
	}
	
	public int max_value() {
		if(max.isEmpty()){
			return -1;
		}
		return max.getLast();
	}
	
	public void push_back(int value) {
		queue.add(value);
		if(max.isEmpty()||value>=max.getLast()){
			max.add(value);
		}
	}
	
	public int pop_front() {
		if(queue.isEmpty()){
			return -1;
		}
		Integer x = queue.removeFirst();
		if(x.equals(max.getFirst())){
			max.removeFirst();
		}
		return x;
	}
}
