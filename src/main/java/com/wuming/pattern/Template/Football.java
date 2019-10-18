package com.wuming.pattern.Template;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Template
 * @Class Football
 * @Author: WuMing
 * @CreateDate: 2018/7/19 17:25
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Football extends Game {
	
	@Override
	void initialize() {
		System.out.println("初始化" + this.getClass().getSimpleName() + "游戏");
	}
	
	@Override
	void startPlay() {
		System.out.println("开始玩" + this.getClass().getSimpleName() + "游戏");
	}
	
	@Override
	void endPlay() {
		System.out.println("关闭" + this.getClass().getSimpleName() + "游戏");
	}
}
