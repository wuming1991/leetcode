package com.wuming.pattern.ResponsibilityChain;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.ResponsibilityChain
 * @Interface Permit
 * @Author: WuMing
 * @CreateDate: 2018/7/16 20:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public abstract class Permit {
	
	String name;
	Permit nextPermit;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Permit getNextPermit() {
		return nextPermit;
	}
	
	public void setNextPermit(Permit nextPermit) {
		this.nextPermit = nextPermit;
	}
	
	public Permit(String name) {
		this.name = name;
	}
	
	public abstract String permitVacation(Vacationer vacationer);
}
