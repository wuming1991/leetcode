package com.leetcode2;

import com.leetcode.MapSum;
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
	
	LinkedList<Integer> queue, max;
	
	public MaxQueue() {
		queue = new LinkedList<>();
		max = new LinkedList<>();
	}
	
	public int max_value() {
		if (max.isEmpty()) {
			return -1;
		}
		return max.getFirst();
	}
	
	public void push_back(int value) {
		queue.add(value);
		while (!max.isEmpty() && max.getLast() < value) {
			max.removeLast();
		}
		max.add(value);
	}
	
	public int pop_front() {
		if (queue.isEmpty()) {
			return -1;
		}
		Integer x = queue.removeFirst();
		if (x.equals(max.getFirst())) {
			max.removeFirst();
		}
		return x;
	}
	
	public static void main(String[] args) {
		MaxQueue queue = new MaxQueue();
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		queue.push_back(46);
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		queue.push_back(868);
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		queue.push_back(525);
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		queue.push_back(123);
		queue.push_back(646);
		System.out.println(queue.max_value());
		queue.push_back(229);
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		queue.push_back(871);
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		queue.push_back(285);
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		queue.push_back(45);
		queue.push_back(140);
		queue.push_back(873);
		queue.push_back(545);
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		queue.push_back(561);
		queue.push_back(237);
		System.out.println(queue.pop_front());
		queue.push_back(633);
		queue.push_back(98);
		queue.push_back(806);
		queue.push_back(717);
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		queue.push_back(186);
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		queue.push_back(268);
		System.out.println(queue.pop_front());
		queue.push_back(29);
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		queue.push_back(866);
		System.out.println(queue.pop_front());
		queue.push_back(239);
		queue.push_back(3);
		queue.push_back(850);
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.max_value());
		System.out.println(queue.pop_front());
		queue.push_back(310);
		System.out.println(queue.pop_front());
		queue.push_back(674);
		queue.push_back(770);
		System.out.println(queue.pop_front());
		queue.push_back(525);
		System.out.println(queue.pop_front());
		queue.push_back(425);
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		queue.push_back(720);
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		System.out.println(queue.pop_front());
		queue.push_back(373);
		queue.push_back(411);
		System.out.println(queue.max_value());
		queue.push_back(831);
		System.out.println(queue.pop_front());
		queue.push_back(765);
		queue.push_back(701);
		System.out.println(queue.pop_front());
		
		
	}
}
