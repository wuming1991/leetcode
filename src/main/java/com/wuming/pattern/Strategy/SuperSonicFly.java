package com.wuming.pattern.Strategy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Strategy
 * @Class SuperSonicFly
 * @Author: WuMing
 * @CreateDate: 2018/7/19 14:52
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class SuperSonicFly implements Fly {
	
	@Override
	public void fly() {
		System.out.println("超音速飞行");
	}
}
