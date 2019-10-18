package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class Development
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:17
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Development extends Department {
	
	public Development(RealMediator realMediator) {
		super(realMediator);
	}
	
	
	@Override
	public void speak() {
		System.out.println("Development发言");
		realMediator.communicate(this);
	}
	
	@Override
	public void listen() {
		System.out.println("Development收到");
	}
}
