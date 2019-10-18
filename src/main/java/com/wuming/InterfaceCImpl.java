package com.wuming;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Class InterfaceCImpl
 * @Author: WuMing
 * @CreateDate: 2018/6/29 15:50
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class InterfaceCImpl implements InterfaceA,InterfaceB {
	
	@Override
	public void Aabstract() {
	
	}
	
	@Override
	public void Babstract() {
	
	}
	
	@Override
	public void defaultMethod() {
		InterfaceA.super.defaultMethod();
		InterfaceB.super.defaultMethod();
	}
	
	
}
