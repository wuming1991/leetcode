package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class Financial
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Financial extends Department {
	
	public Financial(RealMediator realMediator) {
		super(realMediator);
	}
	
	@Override
	public void speak() {
		System.out.println("Financial发言");
		realMediator.communicate(this);
	}
	
	@Override
	public void listen() {
		System.out.println("Financial收到");
	}
}
