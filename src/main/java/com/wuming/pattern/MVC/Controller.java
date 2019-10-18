package com.wuming.pattern.MVC;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.MVC
 * @Class Controller
 * @Author: WuMing
 * @CreateDate: 2018/7/23 20:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void updateModel(Model model) {
		this.model = model;
	}
	
	public void showview() {
		view.show(model);
	}
}
