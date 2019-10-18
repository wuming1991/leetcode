package com.wuming.pattern.NullObject;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.NullObject
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/19 10:51
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		BookList bookList = new BookList();
		bookList.add(new RealBook(1, "aaa", "......"));
		bookList.add(new RealBook(2, "bbb", "......"));
		Book book = bookList.get(1);
		book.read();
		Book book1 = bookList.get(0);
		book1.read();
		Book book2 = bookList.get(2);
		book2.read();
	}
	
}
