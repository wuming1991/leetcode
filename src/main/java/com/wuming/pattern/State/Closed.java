package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Closed
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:08
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Closed implements State {
	
	Door door;
	
	public Closed(Door door) {
		this.door = door;
	}
	
	@Override
	public void click() {
		door.setState(new Openning(door));
	}
	
	@Override
	public void complete() {
		System.out.println("error operate");
	}
	
	@Override
	public void timeout() {
		System.out.println("do nothing");
	}
}
