package com.wuming.pattern.Decorator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Decorator
 * @Class BlackCoffee
 * @Author: WuMing
 * @CreateDate: 2018/7/11 11:24
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class BlackCoffee implements Coffee {
	
	String name;
	double cost;
	
	public BlackCoffee(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Double getCost() {
		return cost;
	}
}
