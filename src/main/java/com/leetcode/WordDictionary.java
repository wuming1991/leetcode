package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class WordDictionary
 * @Author: WuMing
 * @CreateDate: 2019/8/30 11:29
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class WordDictionary {
	
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
	
	/**
	 * Initialize your data structure here.
	 */
	public WordDictionary() {
		this.root = new Tire('0');
	}
	
	/**
	 * Adds a word into the data structure.
	 */
	public void addWord(String word) {
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
	 * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		char[] array = word.toCharArray();
		return searchTree(root, array, 0);
	}
	
	private boolean searchTree(Tire r, char[] array, int i) {
		if (array[i] == '.') {
			for (char j = 'a'; j <='z' ; j++) {
				Tire next = r.next[j - 'a'];
				if(next!=null){
					if(i == array.length - 1){
						if(next.isword){
							return true;
						}
					}else{
						if(searchTree(next, array, i + 1)){
							return true;
						}
					}
				}
			}
			return false;
		} else {
			Tire next = r.next[array[i] - 'a'];
			if (next == null) {
				return false;
			}
			if (i == array.length - 1) {
				return next.isword;
			} else {
				return searchTree(next, array, i + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		WordDictionary dict = new WordDictionary();
		dict.addWord("abc");
		System.out.println(dict.search("abc"));
		System.out.println(dict.search("a.c"));
		System.out.println(dict.search("abc."));
	}
}
