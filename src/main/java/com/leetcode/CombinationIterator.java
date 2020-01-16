package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class CombinationIterator
 * @Author: WuMing
 * @CreateDate: 2019/12/16 10:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class CombinationIterator {
	
	String characters;
	int len;
	int[] mem;
	int combinationLength;
	
	public CombinationIterator(String characters, int combinationLength) {
		this.characters = characters;
		len = characters.length();
		mem = new int[combinationLength];
		this.combinationLength = combinationLength;
		for (int i = 0; i < combinationLength; i++) {
			mem[i] = i;
		}
		mem[combinationLength - 1] = combinationLength - 2;
	}
	
	public String next() {
		int length = len - 1;
		int i = combinationLength - 1;
		for (; i >= 0; i--, length--) {
			if (mem[i] < length) {
				break;
			}
		}
		mem[i]++;
		i++;
		for (; i < combinationLength; i++) {
			mem[i] = mem[i-1] + 1;
		}
		StringBuffer buffer = new StringBuffer();
		for (int x : mem) {
			buffer.append(characters.charAt(x));
		}
		return buffer.toString();
	}
	
	public boolean hasNext() {
		int length = len - 1;
		for (int i = combinationLength - 1; i >= 0; i--, length--) {
			if (mem[i] < length) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		CombinationIterator iterator = new CombinationIterator("abc", 2);
		System.out.println(iterator.next());
		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		System.out.println(iterator.hasNext());
	}
}
