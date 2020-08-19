package com.leetcode2;

import com.sun.org.apache.bcel.internal.generic.LNEG;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @Author: WuMing
 * @CreateDate: 2020/5/19 18:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MedianFinder1 {
	
	class ListNode {
		
		ListNode smaller;
		ListNode bigger;
		int val;
		
		public ListNode(int val) {
			this.val = val;
		}
	}
	
	public MedianFinder1() {
	
	}
	
	ListNode mid, head, tail;
	TreeMap<Integer, ListNode> map = new TreeMap<>();
	int length = 0;
	
	public void addNum(int num) {
		if (mid == null) {
			mid = new ListNode(num);
			head = mid;
			tail = mid;
			map.put(num, mid);
		} else {
			Entry<Integer, ListNode> entry = map.ceilingEntry(num);
			ListNode cur = new ListNode(num);
			if (entry == null) {
				map.put(num, cur);
				tail.bigger = cur;
				cur.smaller = tail;
				tail = tail.bigger;
			} else if (entry.getKey() == num) {
				ListNode node = entry.getValue();
				ListNode bigger = node.bigger;
				node.bigger = cur;
				cur.smaller = node;
				if (bigger != null) {
					bigger.smaller = cur;
					cur.bigger = bigger;
				}
				map.put(num, cur);
			} else {
				 entry = map.floorEntry(num);
				 map.put(num,cur);
				 if(entry==null){
				 	cur.bigger=head;
				 	head.smaller=cur;
				 	head=cur;
				 }else{
					 ListNode node = entry.getValue();
					 ListNode bigger = node.bigger;
					 node.bigger = cur;
					 cur.smaller = node;
					 if (bigger != null) {
						 bigger.smaller = cur;
						 cur.bigger = bigger;
					 }
				 }
			}
			if ((length & 1) == 1) {
				if (num < mid.val) {
					mid = mid.smaller;
				}
			} else {
				if (num >= mid.val) {
					mid = mid.bigger;
				}
			}
		}
		length++;
	}
	
	public double findMedian() {
		return (length & 1) == 0 ? (double) (mid.val + mid.bigger.val) / 2 : mid.val;
	}
	
	public static void main(String[] args) {
		MedianFinder1 f = new MedianFinder1();
		f.addNum(6);
		System.out.println(f.findMedian());
		f.addNum(10);
		System.out.println(f.findMedian());
		f.addNum(2);
		System.out.println(f.findMedian());
		f.addNum(6);
		System.out.println(f.findMedian());
		f.addNum(5);
		System.out.println(f.findMedian());
		f.addNum(0);
		System.out.println(f.findMedian());
		f.addNum(6);
		System.out.println(f.findMedian());
		f.addNum(3);
		System.out.println(f.findMedian());
		f.addNum(1);
		System.out.println(f.findMedian());
		f.addNum(0);
		System.out.println(f.findMedian());
		f.addNum(0);
		System.out.println(f.findMedian());
		
	}
}
