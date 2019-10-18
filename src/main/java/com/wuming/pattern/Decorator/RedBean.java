package com.wuming.pattern.Decorator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Decorator
 * @Class RedBean
 * @Author: WuMing
 * @CreateDate: 2018/7/11 11:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class RedBean implements Decorator {
	
	Coffee coffee;
	
	public RedBean(Coffee coffee) {
		this.coffee = coffee;
	}
	
	@Override
	public String getName() {
		return coffee.getName() + "+RedBean";
	}
	
	@Override
	public Double getCost() {
		return coffee.getCost() + 2.1;
	}
}
