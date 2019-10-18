package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class Client
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:17
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Client {
	
	BusinessDelegate delegate;
	
	public Client(BusinessDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void doTask(){
		delegate.doTask();
	}
}
