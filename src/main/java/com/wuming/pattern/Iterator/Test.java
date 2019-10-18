package com.wuming.pattern.Iterator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Iterator
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/12 21:02
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		NameList nameList = new NameList();
		nameList.names.add("111");
		nameList.names.add("1121");
		Iterator iterator = nameList.getIterator();
		while (iterator.hasNext()) {
			System.out.println((String) iterator.next());
			
		}
	}
}
