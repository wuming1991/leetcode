package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test14
 * @Author: WuMing
 * @CreateDate: 2019/12/3 10:42
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test14 {
	
	public static void main(String[] args) {
		Test14 test = new Test14();
		test.suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"},
			"mouse");
	}
	
	class TireNode {
		
		TireNode[] next;
		boolean isword;
		String word;
		
		public TireNode(boolean isword) {
			this.isword = isword;
			next = new TireNode[26];
		}
	}
	
	
	
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TireNode root = new TireNode(false);
		char x = searchWord.charAt(0);
		for (String product : products) {
			if(product.charAt(0)==x){
				buildTree(root, product);
			}
		}
		TireNode cur = root;
		ArrayList<List<String>> ret = new ArrayList<>();
		for (char c : searchWord.toCharArray()) {
			if (cur != null) {
				cur = cur.next[c - 'a'];
			}
			ret.add(SearchTree(cur, new ArrayList<String>()));
		}
		return ret;
	}
	
	private List<String> SearchTree(TireNode cur, List<String> list) {
		if(cur==null){
			return list;
		}
		if (cur.isword) {
			list.add(cur.word);
			if (list.size() == 3) {
				return list;
			}
		}
		for (TireNode next : cur.next) {
			if (next != null) {
				SearchTree(next, list);
				if (list.size() == 3) {
					return list;
				}
			}
		}
		return list;
	}
	
	private void buildTree(TireNode t, String product) {
		for (char c : product.toCharArray()) {
			if (t.next[c - 'a'] == null) {
				t.next[c - 'a'] = new TireNode(false);
			}
			t = t.next[c - 'a'];
		}
		t.isword = true;
		t.word = product;
	}
}
