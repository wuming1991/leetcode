package com.wuming.pattern.Strategy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Strategy
 * @Class LongDistanceTakeOff
 * @Author: WuMing
 * @CreateDate: 2018/7/19 14:51
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class LongDistanceTakeOff implements TakeOff {
	
	@Override
	public void takeOff() {
		System.out.println("长距离起飞");
	}
}
