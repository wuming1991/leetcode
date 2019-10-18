package com.wuming.pattern.Composite;

import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Composite
 * @Class PlusExpression
 * @Author: WuMing
 * @CreateDate: 2018/7/10 20:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class PlusExpression implements Expression {
	
	private List<Expression> expressions;
	
	public PlusExpression(List<Expression> expressions) {
		this.expressions = expressions;
	}
	
	@Override
	public void addExpression(Expression e) {
		expressions.add(e);
	}
	
	@Override
	public String print() {
		String result = "";
		for (int i = 0; i < expressions.size(); i++) {
			if (i != expressions.size() - 1) {
				result += expressions.get(i).print() + "+";
			} else {
				result += expressions.get(i).print();
			}
		}
		return "(" + result + ")";
	}
}
