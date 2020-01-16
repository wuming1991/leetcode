package com.leetcode2;

import java.util.Arrays;
import javax.lang.model.element.VariableElement;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class StreamChecker
 * @Author: WuMing
 * @CreateDate: 2020/1/16 16:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
//1032
public class StreamChecker {
	
	class Node {
		
		Node[] child = new Node[26];
		boolean flag = false;
	}
	
	Node root;
	int[] arr = new int[40001];
	int idx = -1;
	
	public StreamChecker(String[] words) {
		root = new Node();
		Node cur;
		int t;
		int length;
		Arrays.sort(words, (a, b) -> (a.length() - b.length()));
		for (String word : words) {
			cur = root;
			length = word.length();
			for (int i = length - 1; i >= 0; i--) {
				t = word.charAt(i) - 'a';
				if (cur.child[t] == null) {
					cur.child[t] = new Node();
				} else if (cur.child[t].flag) {
					break;
				}
				cur = cur.child[t];
			}
			cur.flag = true;
		}
		
	}
	
	public boolean query(char letter) {
		arr[++idx] = letter - 'a';
		return search(root, idx);
	}
	
	private boolean search(Node root, int cur) {
		Node node = root.child[arr[cur]];
		if (node != null) {
			return node.flag || search(node, cur - 1);
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
		streamChecker.query('a');          // 返回 false
		streamChecker.query('b');          // 返回 false
		streamChecker.query('c');          // 返回 false
		streamChecker.query('d');          // 返回 true，因为 'cd' 在字词表中
		streamChecker.query('e');          // 返回 false
		streamChecker.query('f');          // 返回 true，因为 'f' 在字词表中
		streamChecker.query('g');          // 返回 false
		streamChecker.query('h');          // 返回 false
		streamChecker.query('i');          // 返回 false
		streamChecker.query('j');          // 返回 false
		streamChecker.query('k');          // 返回 false
		streamChecker.query('l');
	}
}
