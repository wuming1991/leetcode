package com.wuming.vm;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm
 * @Class ThreadMonitoring
 * @Author: WuMing
 * @CreateDate: 2018/8/15 17:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DeadLock implements Runnable {
	
	int a, b;
	
	public  DeadLock(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void run() {
		synchronized (Integer.valueOf(a)) {
			synchronized (Integer.valueOf(b)) {
				System.out.println(a + b);
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			
			new Thread(new DeadLock(1, 2)).start();
			new Thread(new DeadLock(2, 1)).start();
		}
	}
}
