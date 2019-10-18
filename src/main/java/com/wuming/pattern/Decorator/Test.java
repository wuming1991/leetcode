package com.wuming.pattern.Decorator;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Decorator
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/11 11:36
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Coffee blackCoffee = new BlackCoffee("blackCoffee", 5.8);
		Coffee coffee = new RedBean(new Milk(blackCoffee));
		Coffee c = new Milk(new RedBean(blackCoffee));
		System.out.println(coffee.getName());
		System.out.println(c.getCost());
		
	}
}
