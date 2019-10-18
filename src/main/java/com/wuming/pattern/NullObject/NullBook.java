package com.wuming.pattern.NullObject;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.NullObject
 * @Class NullBook
 * @Author: WuMing
 * @CreateDate: 2018/7/19 10:43
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class NullBook implements Book {
	
	@Override
	public int getId() {
		return -1;
	}
	
	@Override
	public void read() {
		System.out.println("no book found");
	}
	
	@Override
	public boolean isNull() {
		return true;
	}
}
