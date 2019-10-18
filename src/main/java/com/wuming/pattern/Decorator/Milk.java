package com.wuming.pattern.Decorator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Decorator
 * @Class Milk
 * @Author: WuMing
 * @CreateDate: 2018/7/11 11:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Milk implements Decorator {
	
	Coffee coffee;
	
	public Milk(Coffee coffee) {
		this.coffee = coffee;
	}
	
	@Override
	public String getName() {
		return coffee.getName() + "+Milk";
	}
	
	@Override
	public Double getCost() {
		return coffee.getCost() + 1.5;
	}
}
