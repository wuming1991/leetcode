package com.leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class NestedIterator
 * @Author: WuMing
 * @CreateDate: 2019/5/22 17:55
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class NestedIterator implements Iterator<Integer> {
	
	int index;
	NestedIterator iterator;
	List<NestedInteger> nestedList;
	
	public NestedIterator(List<NestedInteger> nestedList) {
		this.nestedList = nestedList;
		index=0;
	}
	
	@Override
	public Integer next() {
		if(index==nestedList.size()){
			return null;
		}
		NestedInteger nested = nestedList.get(index);
		if (nested.isInteger()) {
			index++;
			return nested.getInteger();
		} else {
			if(iterator==null){
				iterator = new NestedIterator(nested.getList());
			}
			if(iterator.hasNext()){
				return iterator.next();
			}else{
				iterator=null;
				index++;
				return next();
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		if(index==nestedList.size()){
			return false;
		}
		NestedInteger nested = nestedList.get(index);
		if(!nested.isInteger()){
			if(iterator==null){
				iterator=new NestedIterator(nested.getList());
			}
			if(!iterator.hasNext()){
				iterator=null;
				index++;
				return hasNext();
			}else {
				return true;
			}
		}else{
			return true;
		}
	}
}
