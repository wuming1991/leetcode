package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/19 20:03
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		PersonList list = new PersonList();
		list.addPerson(new Student("郭靖", 2));
		list.addPerson(new Student("黄蓉", 3));
		list.addPerson(new Student("杨过", 1));
		list.addPerson(new Teacher("黄药师", 80, 91, 9));
		list.addPerson(new Teacher("梅超风", 91, 80, 11));
		list.addPerson(new Teacher("灭宝", 80, 80, 4));
		list.addPerson(new Teacher("超人", 91, 95, 12));
		
		HonorPrizeVisitor honer = new HonorPrizeVisitor();
		NobelPrizeVisitor nobel = new NobelPrizeVisitor();
		System.out.println("============优秀奖============");
		list.select(honer);
		System.out.println("============科研奖============");
		list.select(nobel);
		String appId = "1234567890";
		System.out.println(
			appId.substring(0, 3) + "***" + appId.subSequence(appId.length() - 3, appId.length()));
	}
}
