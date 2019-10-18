package com.wuming.pattern.FlyWeight;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.FlyWeight
 * @Class TreeCreator
 * @Author: WuMing
 * @CreateDate: 2018/7/12 19:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class TreeCreator {
	
	public static Tree getTree(String type) {
		if ("green".equals(type)) {
			return new GreenTree();
		}
		return null;
	}
}
