package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class Student
 * @Author: WuMing
 * @CreateDate: 2018/7/19 19:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Student implements Person {
	
	String name;
	int paper;
	
	public Student(String name, int paper) {
		this.name = name;
		this.paper = paper;
	}
	
	@Override
	public void accept(PersonVisitor visitor) {
		visitor.visit(this);
	}
}
