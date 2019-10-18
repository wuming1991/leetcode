package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class BusinessDelegate
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class BusinessDelegate {
	
	ServiceLookup lookup = new ServiceLookup();
	String type;
	Service service;
	
	public void setService(String type) {
		this.type = type;
	}
	
	public void doTask() {
		service = lookup.getService(type);
		service.doSomething();
	}
}
