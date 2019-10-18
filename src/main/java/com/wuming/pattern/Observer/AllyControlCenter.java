package com.wuming.pattern.Observer;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Observer
 * @Class AllyControlCenter
 * @Author: WuMing
 * @CreateDate: 2018/7/18 18:29
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class AllyControlCenter {
	
	protected String allyName; //战队名称
	protected ArrayList<Observer> players = new ArrayList<Observer>(); //定义一个集合用于存储战队成员
	
	public String getAllyName() {
		return allyName;
	}
	
	public void setAllyName(String allyName) {
		if (allyName != null) {
			System.out.println(this.allyName + "更名为" + allyName);
		}
		this.allyName = allyName;
		
	}
	
	public void addPlayer(Observer observer) {
		players.add(observer);
		System.out.println(observer.getName() + "加入" + allyName);
	}
	
	public void removePlayer(Observer observer) {
		players.remove(observer);
		System.out.println(observer.getName() + "退出" + allyName);
	}
	
	public abstract void notifyObserver(String name);
}
