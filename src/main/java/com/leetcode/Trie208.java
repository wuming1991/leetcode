package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Trie208
 * @Author: WuMing
 * @CreateDate: 2019/8/29 17:06
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Trie208 {
	
	class Tire {
		char c;
		Tire[] next;
		boolean isword;
		public Tire(char c) {
			this.c = c;
			this.next = new Tire[26];
			isword = false;
		}
	}
	
	Tire root;
	public Trie208() {
		root = new Tire('0');
	}
	
	public void insert(String word) {
		
		char[] array = word.toCharArray();
		buildTree(root, array, 0);
	}
	
	private void buildTree(Tire r, char[] array, int i) {
		if (r.next[array[i] - 'a'] == null) {
			r.next[array[i] - 'a'] = new Tire(array[i]);
		}
		if (i == array.length - 1) {
			r.next[array[i] - 'a'].isword = true;
		} else {
			buildTree(r.next[array[i] - 'a'], array, i + 1);
		}
	}
	
	/**
	 * Returns if the word is in the trie.
	 */
	public boolean search(String word) {
		char[] array = word.toCharArray();
		return searchTire(root, array, 0);
	}
	
	private boolean searchTire(Tire r, char[] array, int i) {
		Tire next = r.next[array[i] - 'a'];
		if (next == null) {
			return false;
		}
		if (i == array.length - 1) {
			return next.isword;
		} else {
			return searchTire(next, array, i + 1);
		}
	}
	
	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		char[] array = prefix.toCharArray();
		return startsWithTire(root, array, 0);
	}
	
	private boolean startsWithTire(Tire r, char[] array, int i) {
		Tire next = r.next[array[i] - 'a'];
		if (next == null) {
			return false;
		}
		if (i == array.length - 1) {
			return true;
		} else {
			return startsWithTire(next, array, i + 1);
		}
	}
	
	public static void main(String[] args) {
		Trie208 trie = new Trie208();
		trie.insert("apple");
		trie.search("apple");   // returns true
		trie.search("app");     // returns false
		trie.startsWith("app"); // returns true
		trie.insert("app");
		trie.search("app");     // returns true
	}
}
