package com.wuming.vm.classload;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.classload
 * @Class ConstClass
 * @Author: WuMing
 * @CreateDate: 2018/9/18 18:56
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ConstClass {
	
	static {
		System.out.println("ConstClass init");
	}
	
	public static final String HELLO = "hello";
}
