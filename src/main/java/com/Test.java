package com;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @ProjectName: study
 * @Package: com
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2019/6/20 14:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("input your name:");
		String name = scanner.nextLine();
		System.out.println("input your first num");
		int i = scanner.nextInt();
		System.out.println("input your last num");
		int i1 = scanner.nextInt();
		float i2=(float)(i1-i)/i;
		System.out.println(name + ":first:" + i + "last:" + i1+"rate:"+new BigDecimal(i2).setScale(2,BigDecimal.ROUND_DOWN));
		
	}
}
