package com.wuming.vm.unit10;

import com.google.common.collect.ForwardingIterator;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.unit10
 * @Class Box
 * @Author: WuMing
 * @CreateDate: 2018/12/3 11:52
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Box {
	
	public static void main(String[] args){
		Integer a=1;
		Integer b=2;
		Integer c=3;
		Integer d=3;
		Integer e=321;
		Integer f=321;
		Long g= 3L;
		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
	}
}
