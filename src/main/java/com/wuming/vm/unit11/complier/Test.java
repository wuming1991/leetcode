package com.wuming.vm.unit11.complier;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.unit11.complier
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/12/11 15:24
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static final int Num = 150000;
	
	public static void main(String[] args) {
		for (int i = 0; i < Num; i++) {
			calcSum();
		}
	}
	
	public static long calcSum() {
		long sum = 0;
		for (int i = 0; i <= 100; i++) {
			sum += doubleValue(i);
		}
		return sum;
		
	}
	
	public static int  doubleValue(int i) {
		for (int j = 0; j < 10000; j++);
		return i*2;
	}
}
