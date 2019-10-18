package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		BusinessDelegate businessDelegate = new BusinessDelegate();
		businessDelegate.setService("this");
		Client client = new Client(businessDelegate);
		client.doTask();
		businessDelegate.setService("that");
		client.doTask();
	}
}
