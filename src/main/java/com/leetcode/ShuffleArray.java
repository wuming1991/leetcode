package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class ShuffleArray
 * @Author: WuMing
 * @CreateDate: 2019/5/17 16:02
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ShuffleArray {
	
	int[] old;
	int[] temp;
	
	public ShuffleArray(int[] nums) {
		old = nums;
		temp = new int[old.length];
		for (int i = 0; i < old.length; i++) {
			temp[i] = old[i];
		}
	}
	
	public int[] reset() {
		return old;
	}
	
	public int[] shuffle() {
		for (int i = 0; i < temp.length; i++) {
			int k = (int) (Math.random() * (temp.length - i) + i);
			int l=temp[k];
			temp[k]=temp[i];
			temp[i]=l;
		}
		return temp;
	}
}
