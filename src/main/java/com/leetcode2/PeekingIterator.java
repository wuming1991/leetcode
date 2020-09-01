package com.leetcode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/24 17:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class PeekingIterator  implements Iterator<Integer> {
	Iterator<Integer> iterator;
	Integer buffer=null;
	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator=iterator;
	}
	
	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if(buffer==null){
			buffer=iterator.next();
		}
		return buffer;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if(buffer==null){
			return iterator.next();
		}else{
			int ret=buffer;
			buffer=null;
			return ret;
		}
	}
	
	@Override
	public boolean hasNext() {
		return buffer!=null||iterator.hasNext();
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
		PeekingIterator iterator = new PeekingIterator(list.iterator());
		int c=1;
		while (iterator.hasNext()){
			for (int i = 0; i < c; i++) {
				System.out.println("peek"+iterator.peek());
			}
			System.out.println("next"+iterator.next());
			c++;
		}
		
	}
}
