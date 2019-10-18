package com.wuming.pattern.MVC;

import com.wuming.pattern.Visitor.Student;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.MVC
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/23 20:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Model model = new Model(1, "aaa");
		View view = new View();
		Controller controller = new Controller(model, view);
		controller.showview();
		controller.updateModel(new Model(2, "bbb"));
		controller.showview();
		
	}
}
