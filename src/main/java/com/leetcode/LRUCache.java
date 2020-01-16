package com.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class LRUCache
 * @Author: WuMing
 * @CreateDate: 2019/12/26 9:32
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class LRUCache {
	
	int capacity;
	Map<Integer, Node> map;
	Node head, tail;
	int cur = 0;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
	}
	
	public int get(int key) {
		Node node = map.get(key);
		if (node == null) {
			return -1;
		}
		if (node.right == null) {
		
		} else if (node.left != null) {
			node.left.right = node.right;
			node.right.left = node.left;
			tail.right = node;
			node.left = tail;
			tail = node;
			tail.right = null;
		} else {
			head = node.right;
			head.left = null;
			tail.right = node;
			node.left = tail;
			tail = node;
			tail.right = null;
		}
		return node.val;
	}
	
	public void put(int key, int value) {
		if (cur == 0) {
			head = new Node(key, value);
			tail = head;
			map.put(key, head);
			cur++;
		} else if (cur < capacity) {
			Node node = map.get(key);
			if (node != null) {
				get(node.key);
				node.val=value;
			} else {
				node = new Node(key, value);
				cur++;
				tail.right = node;
				node.left = tail;
				tail = node;
			}
			map.put(key, node);
			
		} else {
			Node node = map.get(key);
			if (node == null) {
				map.remove(head.key);
				head = head.right;
				node = new Node(key, value);
				if (head == null) {
					head = node;
					tail = node;
				} else {
					head.left = null;
					tail.right = node;
					node.left = tail;
					tail = node;
					tail.right = null;
				}
			} else {
				get(node.key);
				node.val = value;
			}
			map.put(key, node);
		}
		
	}
	
	class Node {
		
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
		
		int key;
		int val;
		Node right;
		Node left;
		
	}
	
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(10);
		lruCache.put(10, 13);
		lruCache.put(3, 17);
		lruCache.put(6, 11);
		lruCache.put(10, 5);
		lruCache.put(9, 10);
		lruCache.get(13);
		lruCache.put(2, 19);
		lruCache.get(2);
		lruCache.get(3);
		lruCache.put(5, 25);
		lruCache.get(8);
		lruCache.put(9, 22);
		lruCache.put(5, 5);
		lruCache.put(1, 30);
		lruCache.get(11);
		lruCache.put(9, 12);
		lruCache.get(7);
		lruCache.get(5);
		lruCache.get(8);
		lruCache.get(9);
		lruCache.put(4, 30);
		lruCache.put(9, 3);
		lruCache.get(9);
		lruCache.get(10);
		lruCache.get(10);
		lruCache.put(6, 14);
		lruCache.put(3, 1);
		lruCache.get(3);
		lruCache.put(10, 11);
		lruCache.get(8);
		lruCache.put(2, 14);
		lruCache.get(1);
		lruCache.get(5);
		lruCache.get(4);
		lruCache.put(11, 4);
		lruCache.put(12, 24);
		lruCache.put(5, 18);
		lruCache.get(13);
		lruCache.put(7, 23);
		lruCache.get(8);
		lruCache.get(12);
		lruCache.put(3, 27);
		lruCache.put(2, 12);
		lruCache.get(5);
		lruCache.put(2, 9);
		lruCache.put(13, 4);
		lruCache.put(8, 18);
		lruCache.put(1, 7);
		lruCache.get(6);
		lruCache.put(9, 29);
		lruCache.put(8, 21);
		lruCache.get(5);
		lruCache.put(6, 30);
		lruCache.put(1, 12);
		lruCache.get(10);
		lruCache.put(4, 15);
		lruCache.put(7, 22);
		lruCache.put(11, 26);
		lruCache.put(8, 17);
		lruCache.put(9, 29);
		lruCache.get(5);
		lruCache.put(3, 4);
		lruCache.put(11, 30);
		lruCache.get(12);
		lruCache.put(4, 29);
		lruCache.get(3);
		lruCache.get(9);
		lruCache.get(6);
		lruCache.put(3, 4);
		lruCache.get(1);
		lruCache.get(10);
		lruCache.put(3, 29);
		lruCache.put(10, 28);
		lruCache.put(1, 20);
		lruCache.put(11, 13);
		lruCache.get(3);
		lruCache.put(3, 12);
		lruCache.put(3, 8);
		lruCache.put(10, 9);
		lruCache.put(3, 26);
		lruCache.get(8);
		lruCache.get(7);
		lruCache.get(5);
		lruCache.put(13, 17);
		lruCache.put(2, 27);
		lruCache.put(11, 15);
		lruCache.get(12);
		lruCache.put(9, 19);
		lruCache.put(2, 15);
		lruCache.put(3, 16);
		lruCache.get(1);
		lruCache.put(12, 17);
		lruCache.put(9, 1);
		lruCache.put(6, 19);
		lruCache.get(4);
		lruCache.get(5);
		lruCache.get(5);
		lruCache.put(8, 1);
		lruCache.put(11, 7);
		lruCache.put(5, 2);
		lruCache.put(9, 28);
		lruCache.get(1);
		lruCache.put(2, 2);
		lruCache.put(7, 4);
		lruCache.put(4, 22);
		lruCache.put(7, 24);
		lruCache.put(9, 26);
		lruCache.put(13, 28);
		lruCache.put(11, 26);
	}
}
