package com.wuming;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Interface InterfaceA
 * @Author: WuMing
 * @CreateDate: 2018/6/29 15:46
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface  InterfaceA<T> {
	void Aabstract();
	void Babstract();
	default void defaultMethod(){
		System.out.println("A");
	}
	default void defaultMethod2(){
		System.out.println("A");
	}
}
