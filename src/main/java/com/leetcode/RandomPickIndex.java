package com.leetcode;

import java.util.ArrayList;
import java.util.Random;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class RandomPickIndex
 * @Author: WuMing
 * @CreateDate: 2019/5/17 16:26
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RandomPickIndex {
	
	Random random;
	int[] arr;
	
	public RandomPickIndex(int[] nums) {
		random = new Random();
		arr = nums;
	}
	
	public int pick(int target) {
		int index=-1;
		int count=0;
		for (int i = 0; i < arr.length; i++) {
			if(target==arr[i]){
				count++;
				if(random.nextInt(count)==0){
					index=i;
				}
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		RandomPickIndex pickIndex = new RandomPickIndex(new int[]{1, 2, 3, 3, 3, 4, 4, 5});
		System.out.println(pickIndex.pick(1));
		System.out.println(pickIndex.pick(1));
		System.out.println(pickIndex.pick(1));
		System.out.println(pickIndex.pick(5));
		System.out.println(pickIndex.pick(5));
		System.out.println(pickIndex.pick(5));
		System.out.println(pickIndex.pick(3));
		System.out.println(pickIndex.pick(3));
		System.out.println(pickIndex.pick(3));
		System.out.println(pickIndex.pick(3));
		System.out.println(pickIndex.pick(3));
		
		
	}
}
