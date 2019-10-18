package com.wuming.pattern.Strategy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Strategy
 * @Interface Plane
 * @Author: WuMing
 * @CreateDate: 2018/7/19 14:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Plane {
	
	TakeOff takeOff;
	Fly fly;
	
	public Plane(TakeOff takeOff, Fly fly) {
		this.takeOff = takeOff;
		this.fly = fly;
	}
	
	public void takeOff() {
		takeOff.takeOff();
	}
	
	public void fly() {
		fly.fly();
	}
	
}
