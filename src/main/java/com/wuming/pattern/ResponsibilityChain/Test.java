package com.wuming.pattern.ResponsibilityChain;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.ResponsibilityChain
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/16 20:30
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Permit groupLeader, manager, ceo;
		groupLeader = new GroupLeader();
		manager = new Manager();
		ceo = new CEO();
		// 建立责任链
		groupLeader.setNextPermit(manager);
		manager.setNextPermit(ceo);
		
		Vacationer v1 = new Vacationer("张三", 1);
		Vacationer v2 = new Vacationer("李四", 4);
		Vacationer v3 = new Vacationer("王五", 7);
		Vacationer v4 = new Vacationer("赵六", 11);
		
		System.out.println(groupLeader.permitVacation(v1));
		System.out.println(groupLeader.permitVacation(v2));
		System.out.println(groupLeader.permitVacation(v3));
		System.out.println(groupLeader.permitVacation(v4));
		
	}
}
