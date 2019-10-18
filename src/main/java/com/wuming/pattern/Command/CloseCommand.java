package com.wuming.pattern.Command;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Command
 * @Class OpenCommand
 * @Author: WuMing
 * @CreateDate: 2018/7/17 15:25
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class CloseCommand implements Command {
	
	CloseAction action;
	
	public CloseCommand(CloseAction closeAction) {
		this.action = closeAction;
	}
	
	@Override
	public void execute() {
		action.doAction();
		
	}
}
