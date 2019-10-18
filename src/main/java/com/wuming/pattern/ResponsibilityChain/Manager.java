package com.wuming.pattern.ResponsibilityChain;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.ResponsibilityChain
 * @Class Manager
 * @Author: WuMing
 * @CreateDate: 2018/7/16 20:24
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Manager extends Permit {
	
	public Manager() {
		super("Manager");
	}
	
	@Override
	public String permitVacation(Vacationer vacationer) {
		if (vacationer.days < 5) {
			return name + "同意"+vacationer.name+"请假"+vacationer.days+"天";
		}
		if (nextPermit != null) {
			
			return nextPermit.permitVacation(vacationer);
		}
		return "请假失败";
	}
}
