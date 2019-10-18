package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class Department
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:23
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class Department {
	
	Mediator realMediator;
	
	public Department(Mediator realMediator) {
		this.realMediator = realMediator;
	}
	
	public abstract void speak();
	public abstract void listen();
}
