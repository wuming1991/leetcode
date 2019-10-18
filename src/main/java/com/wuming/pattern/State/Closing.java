package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Closing
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Closing implements State {
	
	Door door;
	
	public Closing(Door door) {
		this.door = door;
	}
	
	@Override
	public void click() {
		door.setState(new Openning(door));
	}
	
	@Override
	public void complete() {
		door.setState(new Closed(door));
	}
	
	@Override
	public void timeout() {
		System.out.println("do nothing");
	}
}
