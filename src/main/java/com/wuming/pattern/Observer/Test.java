package com.wuming.pattern.Observer;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Observer
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/18 18:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		RealAllyControlCenter rac = new RealAllyControlCenter("金庸群侠小分队");
		Player 杨过 = new Player("杨过");
		Player 郭靖 = new Player("郭靖");
		Player 张无忌 = new Player("张无忌");
		Player 令狐冲 = new Player("令狐冲");
		rac.addPlayer(杨过);
		rac.addPlayer(郭靖);
		rac.addPlayer(张无忌);
		rac.addPlayer(令狐冲);
		令狐冲.beAttacked(rac);
	}
}
