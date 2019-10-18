package com.wuming.vm.unit13;

import java.util.Vector;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.unit13
 * @Class VectorTest
 * @Author: WuMing
 * @CreateDate: 2018/12/26 19:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class VectorTest {
	
	private static Vector<Integer> vector = new Vector<>();
	
	public static void main(String[] args) {
		while (true) {
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}
			Thread removeThread = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							vector.remove(i);
						}
					}
					
				}
			});
			Thread printThread = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							System.out.println(vector.get(i));
						}
					}
					
				}
			});
			removeThread.start();
			printThread.start();
			while (Thread.activeCount() > 20) {
				;
			}
		}
	}
}
