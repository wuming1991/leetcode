package com.wuming.pattern.Composite;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Composite
 * @Interface Expression
 * @Author: WuMing
 * @CreateDate: 2018/7/10 20:39
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface Expression {
	void addExpression(Expression e);
	String print();
}
