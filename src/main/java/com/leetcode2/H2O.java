package com.leetcode2;

import java.util.concurrent.Semaphore;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/18 18:01
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class H2O {
	Semaphore h,o;
	public H2O() {
		h=new Semaphore(2);
		o=new Semaphore(0);
	}
	
	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		h.acquire(1);
		// releaseHydrogen.run() outputs "H". Do not change or remove this line.
		releaseHydrogen.run();
		o.release();
	}
	
	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		// releaseOxygen.run() outputs "O". Do not change or remove this line.
		o.acquire(2);
		releaseOxygen.run();
		h.release(2);
	}
}
