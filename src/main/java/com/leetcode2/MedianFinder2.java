package com.leetcode2;

import java.util.PriorityQueue;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/30 12:31
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MedianFinder2 {
	PriorityQueue<Integer> small,big;
	public MedianFinder2() {
		  small = new PriorityQueue<>((a, b) -> (a - b));
		  big = new PriorityQueue<>((a, b) -> (b-a));
	}
	
	public void addNum(int num) {
		if(small.isEmpty()||num<small.peek()||big.isEmpty()||num<big.peek()){
			small.add(num);
			if(small.size()==big.size()+2){
				big.add(small.poll());
			}
		}else {
			 big.add(num);
			 if(big.size()>small.size()){
			 	small.add(big.poll());
			 }
		}
	}
	
	public double findMedian() {
		if(small.isEmpty()){
			return -1;
		}
		if(small.size()==big.size()){
			return (double)(small.peek()+big.peek())/2;
		}else{
			return small.peek();
		}
	}
}
