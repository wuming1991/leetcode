package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Open
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Open implements State {
	Door door;
	
	public Open(Door door) {
		this.door = door;
	}
	@Override
	public void click() {
		door.setState(new StayOpen(door));
	}
	
	@Override
	public void complete() {
		System.out.println("do nothing");
	}
	
	@Override
	public void timeout() {
		door.setState(new Closing(door));
	}
}
