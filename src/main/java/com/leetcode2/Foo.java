package com.leetcode2;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/18 17:46
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Foo {
	private   volatile int cur=1;
	
	public Foo() {
	
	}
	
	public void first(Runnable printFirst) throws InterruptedException {
		
		// printFirst.run() outputs "first". Do not change or remove this line.
		printFirst.run();
		cur++;
	}
	
	public void second(Runnable printSecond) throws InterruptedException {
		
		// printSecond.run() outputs "second". Do not change or remove this line.
		while (cur!=2){
		
		}
		printSecond.run();
		cur++;
	}
	
	public void third(Runnable printThird) throws InterruptedException {
		while (cur!=3){
		
		}
		// printThird.run() outputs "third". Do not change or remove this line.
		printThird.run();
	}
}
