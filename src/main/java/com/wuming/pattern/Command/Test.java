package com.wuming.pattern.Command;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Command
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/17 15:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Command close, save, combine;
		close = new CloseCommand(new CloseAction());
		save = new SaveCommand(new SaveAction());
		combine = new CombineCommand();
		((CombineCommand) combine).addCommand(close);
		((CombineCommand) combine).addCommand(save);
		System.out.println("创建按钮,初始化为保存");
		UserDefineButton button = new UserDefineButton(save);
		System.out.println("点击按钮");
		button.clickButtom();
		System.out.println("重新设置为关闭");
		button.setCommand(close);
		System.out.println("点击按钮");
		button.clickButtom();
		System.out.println("重新设置为组合");
		button.setCommand(combine);
		System.out.println("点击按钮");
		button.clickButtom();
	}
}
