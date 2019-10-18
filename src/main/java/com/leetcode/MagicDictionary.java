package com.leetcode;

import jdk.nashorn.internal.ir.ReturnNode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MagicDictionary
 * @Author: WuMing
 * @CreateDate: 2019/5/15 11:43
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MagicDictionary {
	
	class TrieNode {
		
		TrieNode[] children;
		boolean flag;
		
		public TrieNode() {
			this.children = new TrieNode[26];
		}
	}
	
	TrieNode root;
	char[] chars;
	
	public MagicDictionary() {
		root = new TrieNode();
		chars = new char[26];
		for (char i = 'a'; i <= 'z'; i++) {
			chars[i - 'a'] = i;
		}
	}
	
	public void buildDict(String[] dict) {
		for (String s : dict) {
			TrieNode p = root;
			for (char c : s.toCharArray()) {
				if (p.children[c - 'a'] == null) {
					p.children[c - 'a'] = new TrieNode();
				}
				p = p.children[c - 'a'];
			}
			p.flag = true;
		}
	}
	
	public boolean search(String word) {
		char[] array = word.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (searchHelper(root, array, 0, i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean searchHelper(TrieNode p, char[] array, int from, int index) {
		int i = from;
		for (; i < index; i++) {
			if (p.children[array[i] - 'a'] != null) {
				p = p.children[array[i] - 'a'];
			} else {
				return false;
			}
		}
		if (i == array.length) {
			return p.flag;
		} else {
			char c = array[i];
			for (int j = 0; j < chars.length; j++) {
				if (chars[j] != c && p.children[chars[j] - 'a'] != null) {
					if (searchHelper(p.children[chars[j] - 'a'], array, index + 1, array.length)) {
						return true;
					}
				}
			}
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		MagicDictionary m = new MagicDictionary();
		m.buildDict(new String[]{"a","b","ab","abc","abcabacbababdbadbfaejfoiawfjaojfaojefaowjfoawjfoawj","abcdefghijawefe","aefawoifjowajfowafjeoawjfaow","cba","cas","aaewfawi","babcda","bcd","awefj"});
		m.search("a");
		m.search("b");
		m.search("c");
		m.search("d");
		m.search("ab");
		m.search("ba");
		m.search("abc");
		m.search("cba");
		m.search("abb");
		m.search("aa");
		m.search("bb");
		m.search("abcd");
		m.search("abcabacbababdbadbfaejfoiawfjaojfaojefaowjfoawjfoawj");
		
	}
	
}
