package com.leetcode2;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Interface Master
 * @Author: WuMing
 * @CreateDate: 2020/9/24 17:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Master {
	String s;
	
	public Master(String s) {
		this.s = s;
	}
	
	public int guess(String word){
		int ret=0;
		for (int i = 0; i < 6; i++) {
			if(s.charAt(i)==word.charAt(i)){
				ret++;
			}
		}
		return ret==0?-1:ret;
	}
}
