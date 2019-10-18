package com.wuming.pattern.NullObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.NullObject
 * @Class BookList
 * @Author: WuMing
 * @CreateDate: 2018/7/19 10:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class BookList {
	
	Map<Integer, Book> map = new ConcurrentHashMap<>(10);
	
	public void add(Book book) {
		map.put(book.getId(), book);
	}
	
	public Book get(int id) {
		Book book = map.get(id);
		if (book == null) {
			book = new NullBook();
		}
		return book;
	}
}
