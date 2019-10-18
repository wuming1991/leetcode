package com.wuming.pattern.Strategy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Strategy
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/19 16:05
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		SubSonicFly subSonicFly = new SubSonicFly();
		VerticalTakeOff verticalTakeOff = new VerticalTakeOff();
		Plane plane = new Plane(verticalTakeOff, subSonicFly);
		plane.takeOff();
		plane.fly();
		
	}
}
