package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Door
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:06
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Door {
	
	State state;
	
	
	
	public void click() {
		state.click();
		System.out.println("door is " + state.getClass().getSimpleName());
	}
	
	public void complete() {
		state.complete();
		System.out.println("door is " + state.getClass().getSimpleName());
	}
	
	public void timeout() {
		state.timeout();
		System.out.println("door is " + state.getClass().getSimpleName());
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
}
