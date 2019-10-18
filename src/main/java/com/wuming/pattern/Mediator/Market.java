package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class Market
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Market extends Department {
	
	public Market(RealMediator realMediator) {
		super(realMediator);
	}
	
	@Override
	public void speak() {
		System.out.println("Market发言");
		realMediator.communicate(this);
	}
	
	@Override
	public void listen() {
		System.out.println("Market收到");
	}
	
	
}
