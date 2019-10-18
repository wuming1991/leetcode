package com.wuming.pattern.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Iterator
 * @Class NameList
 * @Author: WuMing
 * @CreateDate: 2018/7/12 20:43
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class NameList implements Container {
	
	List<String> names = new ArrayList<String>();
	
	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}
	
	private class NameIterator implements Iterator {
		
		int index;
		
		@Override
		public boolean hasNext() {
			return index < names.size();
		}
		
		@Override
		public Object next() {
			return names.get(index++);
		}
	}
}
