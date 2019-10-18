package com.wuming.pattern.FlyWeight;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.FlyWeight
 * @Class TreeFactory
 * @Author: WuMing
 * @CreateDate: 2018/7/12 18:45
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public enum TreeFactory {
	TreeFactory(new ConcurrentHashMap<>());
	private Map<String, Tree> map;
	
	TreeFactory(Map<String, Tree> map) {
		this.map = map;
	}
	
	Tree getTree(String type) {
		Tree tree = map.get(type);
		if (tree == null) {
			tree = TreeCreator.getTree(type);
			map.put(type, tree);
		}
		return tree;
	}
}
