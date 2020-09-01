package com.leetcode2;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/18 18:39
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class FizzBuzz {
	private int n;
	int cur=1;
	private volatile int flag=0;
	public FizzBuzz(int n) {
		this.n = n;
	}
	
	// printFizz.run() outputs "fizz"./3--1
	public void fizz(Runnable printFizz) throws InterruptedException {
		while (cur<=n){
			
			while (flag!=1&&cur<=n){
			
			}
			if(cur<=n)
				printFizz.run();cur++; flag=0;
		}
	}
	
	// printBuzz.run() outputs "buzz"./5--2
	public void buzz(Runnable printBuzz) throws InterruptedException {
		while (cur<=n){
		while (flag!=2&&cur<=n){
		
		}if(cur<=n)
		printBuzz.run();cur++; flag=0;}
	}
	
	// printFizzBuzz.run() outputs "fizzbuzz"./15--3
	public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
		while (cur<=n){
		while (flag!=3&&cur<=n){
		
		}if(cur<=n)
		printFizzBuzz.run();cur++; flag=0;}
	}
	
	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(/*IntConsumer printNumber*/) throws InterruptedException {
		while (cur<=n){
			
			if(cur%15==0){
				flag=3;continue;
			}else if(cur%5==0){
				flag=2; continue;
			}else if(cur%3==0){
				flag=1; continue;
			}
			
			while (flag!=0){
			
			}
			if(cur<=n){
				if(cur%15==0||cur%5==0||cur%3==0){
					continue;
				}
				System.out.println(cur++);
				//printNumber.accept(cur++);
			}
		}
	}
}
