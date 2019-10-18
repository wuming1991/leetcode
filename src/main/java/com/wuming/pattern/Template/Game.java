package com.wuming.pattern.Template;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Template
 * @Class Game
 * @Author: WuMing
 * @CreateDate: 2018/7/19 17:22
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class Game {
	
	abstract void initialize();
	
	abstract void startPlay();
	
	abstract void endPlay();
	
	public final void play() {
		initialize();
		startPlay();
		endPlay();
	}
}
