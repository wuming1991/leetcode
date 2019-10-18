package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MapSum
 * @Author: WuMing
 * @CreateDate: 2019/5/15 16:42
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MapSum {
	
	class TreNode {
		
		int sum;
		Map<Character, TreNode> children;
		
		public TreNode() {
			//sum = 0;
			this.children = new HashMap<>();
		}
	}
	
	TreNode root;
	HashMap<String, Integer> map;
	
	public MapSum() {
		map = new HashMap<>();
		root = new TreNode();
	}
	
	public void insert(String key, int val) {
		int delta = val - map.getOrDefault(key, 0);
		map.put(key,val);
		TreNode p = root;
		for (char c : key.toCharArray()) {
			p.children.putIfAbsent(c, new TreNode());
			p = p.children.get(c);
			p.sum = p.sum + delta;
		}
	}
	
	public int sum(String prefix) {
		TreNode p = root;
		for (char c : prefix.toCharArray()) {
			p = p.children.get(c);
			if (p == null) {
				return 0;
			}
		}
		return p.sum;
	}
	
	public static void main(String[] args) {
		MapSum mapSum = new MapSum();
		mapSum.insert("aa", 3);
		mapSum.sum("a");
		mapSum.insert("aa", 2);
		mapSum.sum("a");
		mapSum.insert("aa", 2);
		mapSum.sum("a");
		
	}
}
