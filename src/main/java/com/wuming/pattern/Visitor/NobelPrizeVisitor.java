package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class juror
 * @Author: WuMing
 * @CreateDate: 2018/7/19 19:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class NobelPrizeVisitor implements PersonVisitor {
	
	@Override
	public void visit(Teacher teacher) {
		if (teacher.paper >= 10) {
			System.out.println(teacher.name + "老师发表了" + teacher.paper + "篇论文,恭喜获得科研奖");
		}
	}
	
	@Override
	public void visit(Student student) {
		if (student.paper >= 2) {
			System.out.println(student.name + "同学发表了" + student.paper + "篇论文,恭喜获得科研奖");
		}
	}
}
