package com.wuming.pattern.FlyWeight;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.FlyWeight
 * @Interface Tree
 * @Author: WuMing
 * @CreateDate: 2018/7/12 18:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class Tree {
	
	abstract String getColor();
	
	public void display(Location l, Size s) {
		System.out
			.println("this tree is " + getColor() + ",location is (" + l.x + "," + l.y + "),size"
				+ " is " + s.x);
	}
}
