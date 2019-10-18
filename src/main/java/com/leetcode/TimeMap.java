package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class TimeMap
 * @Author: WuMing
 * @CreateDate: 2019/5/16 15:51
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class TimeMap {
	
	
	Map<String, TreeMap<Integer, String>> map;
	
	public TimeMap() {
		map = new HashMap<>();
	}
	
	public void set(String key, String value, int timestamp) {
		if (this.map.get(key) == null) {
			TreeMap<Integer, String> tree = new TreeMap<>();
			this.map.put(key, tree);
		}
			this.map.get(key).put(timestamp, value);
		
	}
	
	
	public String get(String key, int timestamp) {
		if (map.get(key) == null) {
			return "";
		} else {
			TreeMap<Integer, String> tree = this.map.get(key);
			Integer time = tree.floorKey(timestamp);
			return time == null ? "" : tree.get(time);
		}
	}
	
	public static void main(String[] args) {
		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1);
		timeMap.get("foo", 1);
		timeMap.get("foo", 3);
		timeMap.set("foo", "bar2", 4);
		timeMap.get("foo", 4);
		timeMap.get("foo", 5);
	}
}
