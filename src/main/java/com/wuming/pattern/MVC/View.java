package com.wuming.pattern.MVC;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.MVC
 * @Class View
 * @Author: WuMing
 * @CreateDate: 2018/7/23 20:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class View {
	
	public void show(Model model) {
		System.out.println(model.id + " " + model.name);
	}
}
