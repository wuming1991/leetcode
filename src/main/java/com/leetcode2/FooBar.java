package com.leetcode2;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class FooBar
 * @Author: WuMing
 * @CreateDate: 2020/1/9 11:19
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class FooBar {
	
	private int n;
	CountDownLatch fooLatch, barLatch;
	
	public FooBar(int n) {
		this.n = n;
		fooLatch = new CountDownLatch(0);
		barLatch = new CountDownLatch(1);
	}
	
	public void foo(Runnable printFoo) throws InterruptedException {
		
		for (int i = 0; i < n; i++) {
			fooLatch.await();
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			barLatch.countDown();
			fooLatch = new CountDownLatch(1);
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		
		for (int i = 0; i < n; i++) {
			barLatch.await();
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			fooLatch.countDown();
			barLatch = new CountDownLatch(1);
		}
	}
}
