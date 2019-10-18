package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Openning
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:08
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Openning implements State {
	
	Door door;
	
	public Openning(Door door) {
		this.door = door;
	}
	
	@Override
	public void click() {
		door.setState(new Closing(door));
	}
	
	@Override
	public void complete() {
		door.setState(new Open(door));
	}
	
	@Override
	public void timeout() {
		System.out.println("do nothing");
	}
}
