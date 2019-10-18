package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class HonorPrizeVisitor
 * @Author: WuMing
 * @CreateDate: 2018/7/19 19:32
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class HonorPrizeVisitor implements PersonVisitor {
	
	@Override
	public void visit(Teacher teacher) {
		if (teacher.evaluate > 90 || teacher.grade > 90) {
			System.out.println(teacher.name + "老师反馈分" + teacher.evaluate + "学生平均成绩" + teacher.grade
				+ ",恭喜获得优秀奖");
		}
	}
	
	@Override
	public void visit(Student student) {
	
	}
}
