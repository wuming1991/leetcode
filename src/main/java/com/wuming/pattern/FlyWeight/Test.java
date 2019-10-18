package com.wuming.pattern.FlyWeight;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.FlyWeight
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/12 18:38
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Tree green = TreeFactory.TreeFactory.getTree("green");
		green.display(new Location(2, 3), new Size(3));
	}
}
