package com.leetcode2;

import org.w3c.dom.Node;

/**
 * @Author: WuMing
 * @CreateDate: 2020/4/28 18:09
 * @Description: 面试题 16.02. 单词频率
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class WordsFrequency {
	
	static class Node {
		int count;
		Node[] child;
		public Node() {
			this.count = 0;
			this.child = new Node[26];
		}
	}
	
	Node root = new Node();
	
	public WordsFrequency(String[] book) {
		for (String s : book) {
			insertTree(root, s.toCharArray(), 0);
		}
	}
	
	private void insertTree(Node root, char[] arr, int idx) {
		if (idx == arr.length) {
			root.count++;
			return;
		} else {
			int x = arr[idx] - 'a';
			if (root.child[x] == null) {
				root.child[x] = new Node();
			}
			insertTree(root.child[x], arr, idx + 1);
		}
	}
	
	public int get(String word) {
		return searchTree(root, word.toCharArray(), 0);
	}
	
	private int searchTree(Node root, char[] arr, int idx) {
		if (root == null) {
			return 0;
		} else if (idx == arr.length) {
			return root.count;
		} else {
			return searchTree(root.child[arr[idx] - 'a'], arr, idx + 1);
		}
	}
}
