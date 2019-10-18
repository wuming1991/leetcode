package com.wuming.pattern.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Command
 * @Class CombineCommand
 * @Author: WuMing
 * @CreateDate: 2018/7/17 15:29
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class CombineCommand implements Command {
	
	List<Command> commands;
	
	public CombineCommand() {
		this.commands = new ArrayList<>();
	}
	
	public void addCommand(Command command) {
		commands.add(command);
	}
	
	@Override
	public void execute() {
		if(commands.size()<1){
			System.out.println("没有命令可执行");
		}
		for (Command command : commands) {
			command.execute();
		}
	}
}
