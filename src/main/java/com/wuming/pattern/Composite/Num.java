package com.wuming.pattern.Composite;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Composite
 * @Class Num
 * @Author: WuMing
 * @CreateDate: 2018/7/10 20:41
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Num implements Expression {
	
	private int num;
	
	public Num(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public void addExpression(Expression e) {
	
	}
	
	@Override
	public String print() {
		return num + "";
	}
}
