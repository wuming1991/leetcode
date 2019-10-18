package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class ThisService
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ThisService implements Service {
	
	@Override
	public void doSomething() {
		System.out.println("this do something");
	}
}
