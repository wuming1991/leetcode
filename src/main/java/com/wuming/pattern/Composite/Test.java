package com.wuming.pattern.Composite;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Composite
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/10 20:45
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Expression plus1, plus2, num1, num2, num3, num4, num5, plus3;
		plus1 = new PlusExpression(new ArrayList<>());
		plus2 = new PlusExpression(new ArrayList<>());
		plus3 = new PlusExpression(new ArrayList<>());
		num1 = new Num(19);
		num2 = new Num(12);
		num3 = new Num(41);
		num4 = new Num(15);
		num5 = new Num(11);
		plus1.addExpression(num1);
		plus1.addExpression(num2);
		plus2.addExpression(num3);
		plus2.addExpression(num4);
		plus2.addExpression(num5);
		plus3.addExpression(plus1);
		plus3.addExpression(plus2);
		System.out.println(plus3.print());
	}
}
