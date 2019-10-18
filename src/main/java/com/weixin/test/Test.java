package com.weixin.test;

/**
 * @ProjectName: study
 * @Package: com.weixin.test
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2019/1/4 17:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test extends Thread {
	private static ThreadTest threadTest;
	public static void main(String[] args) {
		 threadTest = new ThreadTest(10000);
		for (int i = 0; i < 5; i++) {
			new Test().start();
		}
	}
	
	@Override
	public void run() {
		int i=threadTest.getCount();
		while (i>0){
			System.out.println(Thread.currentThread().getName() + "count=" + i);
			i=threadTest.getCount();
		}
	}
}
