package com.wuming.pattern.Observer;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Observer
 * @Interface Observer
 * @Author: WuMing
 * @CreateDate: 2018/7/18 18:28
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface Observer {
	
	String getName();
	
	void setName(String name);
	
	void help(); //声明支援盟友方法
	
	void beAttacked(AllyControlCenter acc); //声明遭受攻击方法
}
