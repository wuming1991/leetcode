package com.wuming.vm.classload.init;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.classload.init
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/9/21 15:42
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	static {
		i = 1;
		//System.out.println(i);
	}
	
	static int i = 0;
	
	public static void main(String[] args) {
		System.out.println(i);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread() + "start");
				new DeadLoopClass();
				System.out.println(Thread.currentThread() + "run over");
			}
		};
		Thread thread = new Thread(runnable);
		Thread thread1 = new Thread(runnable);
		thread.start();
		thread1.start();
	}
	
	static class DeadLoopClass {
		
		static {
			if (true) {
				System.out.println(Thread.currentThread() + "init DeadLoopClass");
				while (true) {
				
				}
			}
		}
	}
}
