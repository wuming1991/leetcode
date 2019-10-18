package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class Teacher
 * @Author: WuMing
 * @CreateDate: 2018/7/19 18:59
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Teacher implements Person {
	
	String name;
	int evaluate;
	int grade;
	int paper;
	
	public Teacher(String name, int evaluate, int grade, int paper) {
		this.name = name;
		this.evaluate = evaluate;
		this.grade = grade;
		this.paper = paper;
	}
	
	@Override
	public void accept(PersonVisitor visitor) {
		visitor.visit(this);
	}
}
