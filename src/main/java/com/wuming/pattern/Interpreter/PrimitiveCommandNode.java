package com.wuming.pattern.Interpreter;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class PrimitiveCommandNode
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:11
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class PrimitiveCommandNode extends Node {
	
	private String name;
	private String text;
	
	//解释基本命令
	@Override
	public void interpret(Context context) {
		name = context.currentToken();
		context.skipToken(name);
		if (!name.equals("PRINT") && !name.equals("BREAK") && !name.equals("SPACE")) {
			System.err.println("非法命令！");
		}
		if (name.equals("PRINT")) {
			text = context.currentToken();
			context.nextToken();
		}
	}
	
	@Override
	public void execute() {
		if (name.equals("PRINT")) {
			System.out.print(text);
		} else if (name.equals("SPACE")) {
			System.out.print(" ");
		} else if (name.equals("BREAK")) {
			System.out.println();
		}
	}
}
