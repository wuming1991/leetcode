package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class ThatService
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ThatService implements Service {
	
	@Override
	public void doSomething() {
		System.out.println("that do something");
	}
}
