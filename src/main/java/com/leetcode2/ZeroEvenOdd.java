package com.leetcode2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/18 16:41
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class ZeroEvenOdd {
	
	private int n;
	private Semaphore zero, even, odd;
	
	public ZeroEvenOdd(int n) {
		this.n = n;
		zero = new Semaphore(1);
		even = new Semaphore(0);
		odd = new Semaphore(0);
	}
	
	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(/*IntConsumer printNumber*/) throws InterruptedException {
		for (int i = 1; i <= n; i++) {
			zero.acquire();
			System.out.println(0);
			//printNumber.accept(0);
			if ((i & 1) == 1) {
				odd.release();
			} else {
				even.release();
			}
		}
		
	}
	
	public void even(/*IntConsumer printNumber*/) throws InterruptedException {
		
		for (int i = 2; i <= n; i+=2) {
			even.acquire();
			System.out.println(i);
			//printNumber.accept(i);
			zero.release();
		}
		
	}
	
	public void odd(/*IntConsumer printNumber*/) throws InterruptedException {
		for (int i = 1; i <= n; i+=2) {
			odd.acquire();
			System.out.println(i);
			//printNumber.accept(i);
			zero.release();
		}
		
	}
	
	public static void main(String[] args) {
		ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
		Runnable zero = new Runnable() {
			
			@Override
			public void run() {
				try {
					zeroEvenOdd.zero();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};Runnable odd = new Runnable() {
			
			@Override
			public void run() {
				try {
					zeroEvenOdd.odd();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};Runnable even = new Runnable() {
			
			@Override
			public void run() {
				try {
					zeroEvenOdd.even();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread zt = new Thread(zero);
		Thread ot = new Thread(odd);
		Thread et = new Thread(even);
		zt.start();
		ot.start();
		et.start();
	}
}
