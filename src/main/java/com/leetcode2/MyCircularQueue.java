package com.leetcode2;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class MyCircularQueue
 * @Author: WuMing
 * @CreateDate: 2020/1/10 16:59
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MyCircularQueue {
	
	int[] queue;
	int len;
	int head = 0, tail = 0;
	boolean flag = true;
	
	/**
	 * Initialize your data structure here. Set the size of the queue to be k.
	 */
	public MyCircularQueue(int k) {
		len = k;
		queue = new int[len];
	}
	
	/**
	 * Insert an element into the circular queue. Return true if the operation is successful.
	 */
	public boolean enQueue(int value) {
		int tar = (tail + 1) % len;
		if (tail == head) {
			if (flag) {
				queue[tail] = value;
				tail = tar;
				flag = false;
				return true;
			}
			return false;
		} else {
			queue[tail] = value;
			tail = tar;
			return true;
		}
	}
	
	/**
	 * Delete an element from the circular queue. Return true if the operation is successful.
	 */
	public boolean deQueue() {
		if (flag) {
			return false;
		} else {
			head = (head + 1) % len;
			flag = head == tail;
			return true;
		}
	}
	
	/**
	 * Get the front item from the queue.
	 */
	public int Front() {
		if(flag){
			return -1;
		}else{
			return queue[head];
		}
	}
	
	/**
	 * Get the last item from the queue.
	 */
	public int Rear() {
		if(flag){
			return -1;
		}else{
			if(tail==0){
				return queue[len-1];
			}
			return queue[tail-1];
		}
	}
	
	/**
	 * Checks whether the circular queue is empty or not.
	 */
	public boolean isEmpty() {
		return flag;
	}
	
	/**
	 * Checks whether the circular queue is full or not.
	 */
	public boolean isFull() {
		return (tail + 1) % len == head;
	}
	
	public static void main(String[] args) {
		MyCircularQueue circularQueue = new MyCircularQueue(3);
		System.out.println(circularQueue.enQueue(1));
		
		System.out.println(circularQueue.enQueue(2));
		
		System.out.println(circularQueue.enQueue(3));
		
		System.out.println(circularQueue.enQueue(4));
		
		System.out.println(circularQueue.Rear());
		
		System.out.println(circularQueue.isFull());
		
		System.out.println(circularQueue.deQueue());
		
		System.out.println(circularQueue.enQueue(4));
		
		System.out.println(circularQueue.Rear());
		
		
	}
}
