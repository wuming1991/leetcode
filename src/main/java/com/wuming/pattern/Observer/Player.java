package com.wuming.pattern.Observer;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Observer
 * @Class Player
 * @Author: WuMing
 * @CreateDate: 2018/7/18 18:35
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Player implements Observer {
	
	String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void help() {
		System.out.println("兄弟挺住," + this.name + "马上来救你");
	}
	
	@Override
	public void beAttacked(AllyControlCenter acc) {
		acc.notifyObserver(name);
	}
}
