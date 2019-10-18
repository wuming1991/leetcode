package com.weixin.test;

/**
 * @ProjectName: study
 * @Package: com.weixin.test
 * @Class ThreadTest
 * @Author: WuMing
 * @CreateDate: 2019/1/4 17:26
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ThreadTest extends Thread{
	private int count;
	
	public  int getCount() {
		return count--;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public ThreadTest(int count) {
		this.count = count;
	}
	
	
}
