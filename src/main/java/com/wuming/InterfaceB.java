package com.wuming;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Interface InterfaceB
 * @Author: WuMing
 * @CreateDate: 2018/6/29 15:46
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface InterfaceB {
	void Babstract();
	default void defaultMethod(){
		System.out.println("B");
	}
}
