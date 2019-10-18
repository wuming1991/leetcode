package com.wuming.pattern.Observer;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Observer
 * @Class RealAllyControlCenter
 * @Author: WuMing
 * @CreateDate: 2018/7/18 18:32
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class RealAllyControlCenter extends AllyControlCenter {
	
	public RealAllyControlCenter(String name) {
		allyName = name;
		System.out.println(allyName + "成立");
	}
	
	@Override
	public void notifyObserver(String name) {
		System.out.println(name + "发出求救信号!");
		for (Observer player : players) {
			if (!player.getName().equals(name)) {
				player.help();
			}
		}
	}
}
