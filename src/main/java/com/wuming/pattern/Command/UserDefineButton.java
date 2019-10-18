package com.wuming.pattern.Command;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Command
 * @Class UserDefineButton
 * @Author: WuMing
 * @CreateDate: 2018/7/17 15:34
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class UserDefineButton {
	Command command;
	
	public UserDefineButton(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void clickButtom(){
		command.execute();
	}
}
