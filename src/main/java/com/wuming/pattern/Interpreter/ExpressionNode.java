package com.wuming.pattern.Interpreter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class ExpressionNode
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ExpressionNode extends Node {
	
	private ArrayList<Node> list = new ArrayList<Node>(); //定义一个集合用于存储多条命令
	
	@Override
	public void interpret(Context context) {
		//循环处理Context中的标记
		while (true) {
			//如果已经没有任何标记，则退出解释
			if (context.currentToken() == null) {
				break;
			}
			//如果标记为END，则不解释END并结束本次解释过程，可以继续之后的解释
			else if (context.currentToken().equals("END")) {
				context.skipToken("END");
				break;
			}
			//如果为其他标记，则解释标记并将其加入命令集合
			else {
				Node commandNode = new CommandNode();
				commandNode.interpret(context);
				list.add(commandNode);
			}
		}
	}
	
	//循环执行命令集合中的每一条命令
	@Override
	public void execute() {
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			((Node) iterator.next()).execute();
		}
	}
}
