package com.wuming.pattern.Interpreter;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class CommandNode
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class CommandNode extends Node {
	
	private Node node;
	
	@Override
	public void interpret(Context context) {
		//处理LOOP循环命令
		if (context.currentToken().equals("LOOP")) {
			node = new LoopCommandNode();
			node.interpret(context);
		}
		//处理其他基本命令
		else {
			node = new PrimitiveCommandNode();
			node.interpret(context);
		}
	}
	
	@Override
	public void execute() {
		node.execute();
	}
}
