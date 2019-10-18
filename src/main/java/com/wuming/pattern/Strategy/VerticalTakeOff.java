package com.wuming.pattern.Strategy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Strategy
 * @Class VerticalTakeOff
 * @Author: WuMing
 * @CreateDate: 2018/7/19 14:50
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class VerticalTakeOff implements TakeOff {
	
	@Override
	public void takeOff() {
		System.out.println("垂直起飞");
	}
}
