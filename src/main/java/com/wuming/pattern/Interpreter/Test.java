package com.wuming.pattern.Interpreter;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:12
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		String text = "LOOP 3 PRINT 杨过 SPACE SPACE SPACE PRINT 小龙女 BREAK END PRINT 郭靖 SPACE SPACE PRINT 黄蓉";
		Context context = new Context(text);
		
		Node node = new ExpressionNode();
		node.interpret(context);
		node.execute();
	}
}
