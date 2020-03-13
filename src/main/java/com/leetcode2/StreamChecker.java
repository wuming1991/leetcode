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
					cur = cur.child[t];
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
		if(cur<0){
			return false;
		}
		Node node = root.child[arr[cur]];
		if (node != null) {
			return node.flag || search(node, cur - 1);
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		StreamChecker streamChecker = new StreamChecker(new String[]{"ab","ba","aaab","abab","baa"});
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('b');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('b');
		streamChecker.query('b');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('b');
		streamChecker.query('a');
		streamChecker.query('a');
		streamChecker.query('a');
		
	}
}
