package com.wuming.pattern.Interpreter;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Interpreter
 * @Class Node
 * @Author: WuMing
 * @CreateDate: 2018/7/17 18:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class Node {
	
	public abstract void interpret(Context text); //声明一个方法用于解释语句
	
	public abstract void execute(); //声明一个方法用于执行标记对应的命令
}
