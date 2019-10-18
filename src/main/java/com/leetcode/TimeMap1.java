package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class TimeMap1
 * @Author: WuMing
 * @CreateDate: 2019/5/16 16:37
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class TimeMap1 {
	
	Map<String, TrieNode> map;
	public TimeMap1() {
		map = new HashMap<>();
	}
	
	public void set(String key, String value, int timestamp) {
		TrieNode trieNode = new TrieNode(timestamp, value);
		if (map.get(key) == null) {
			map.put(key, trieNode);
		} else {
			TrieNode root = map.get(key);
			putNode(root, trieNode);
		}
	}
	
	private void putNode(TrieNode root, TrieNode trieNode) {
		if (root.time > trieNode.time) {
			if (root.left == null) {
				root.left = trieNode;
			} else {
				putNode(root.left, trieNode);
			}
		} else {
			if (root.right == null) {
				root.right = trieNode;
			} else {
				putNode(root.right, trieNode);
			}
		}
	}
	
	public String get(String key, int timestamp) {
		if (map.get(key) == null) {
			return "";
		} else {
			TrieNode root = map.get(key);
			TrieNode dist = getNode(root, timestamp);
			if (dist == null) {
				return "";
			} else {
				return dist.value;
			}
		}
	}
	
	private TrieNode getNode(TrieNode root, int timestamp) {
		if (root == null) {
			return null;
		}
		if (root.time == timestamp) {
			return root;
		}
		if (root.time > timestamp) {
			return getNode(root.left, timestamp);
		} else {
			TrieNode node = getNode(root.right, timestamp);
			if (node == null) {
				return root;
			} else {
				return node;
			}
		}
	}
}

class TrieNode {
	
	int time;
	String value;
	TrieNode left;
	TrieNode right;
	
	public TrieNode(int time, String value) {
		this.time = time;
		this.value = value;
	}
}