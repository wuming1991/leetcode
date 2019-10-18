package com.wuming.pattern.Interpreter;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class LoopCommandNode
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:11
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class LoopCommandNode extends Node {
	
	private int number; //循环次数
	private Node commandNode; //循环语句中的表达式
	
	//解释循环命令
	@Override
	public void interpret(Context context) {
		context.skipToken("LOOP");
		number = context.currentNumber();
		context.nextToken();
		commandNode = new ExpressionNode(); //循环语句中的表达式
		commandNode.interpret(context);
	}
	
	@Override
	public void execute() {
		for (int i = 0; i < number; i++) {
			commandNode.execute();
		}
	}
	
}
