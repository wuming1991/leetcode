package com.wuming.vm.classload;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.classload
 * @Class SubClass
 * @Author: WuMing
 * @CreateDate: 2018/9/18 17:50
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class SubClass extends SuperClass {
	static{
		System.out.println("SubClass init");
	}
}
