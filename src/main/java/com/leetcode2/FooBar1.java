package com.leetcode2;

import java.util.concurrent.Semaphore;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/18 17:34
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class FooBar1 {
	private int n;
	Semaphore f,b;
	public FooBar1(int n) {
		this.n = n;
		f=new Semaphore(1);
		b=new Semaphore(0);
	}
	
	public void foo(Runnable printFoo) throws InterruptedException {
		
		for (int i = 0; i < n; i++) {
			f.acquire();
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			b.release();
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		
		for (int i = 0; i < n; i++) {
			b.acquire();
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			f.release();
		}
	}
}
