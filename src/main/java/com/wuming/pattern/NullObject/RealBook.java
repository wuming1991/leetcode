package com.wuming.pattern.NullObject;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.NullObject
 * @Class RealBook
 * @Author: WuMing
 * @CreateDate: 2018/7/19 10:43
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class RealBook implements Book {
	
	int id;
	String name;
	String data;
	
	public RealBook(int id, String name, String data) {
		this.id = id;
		this.name = name;
		this.data = data;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void read() {
		System.out.println("read " + name + " data");
	}
	
	@Override
	public boolean isNull() {
		return false;
	}
}
