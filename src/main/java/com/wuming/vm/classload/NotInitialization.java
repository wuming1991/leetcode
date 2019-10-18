package com.wuming.vm.classload;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.classload
 * @Class NotInitialization
 * @Author: WuMing
 * @CreateDate: 2018/9/18 17:50
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class NotInitialization {
	
	public static void main(String[] args) {
		//System.out.println(SubClass.value);
		SuperClass[] arr=new SuperClass[10];
		System.out.println(arr.getClass().getName());
		System.out.println(ConstClass.HELLO);
	}
}
