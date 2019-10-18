package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class RLEIterator
 * @Author: WuMing
 * @CreateDate: 2019/5/17 15:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RLEIterator {
	
	int index;
	int[] arr;
	
	public RLEIterator(int[] A) {
		arr = A;
		index = 0;
	}
	
	public int next(int n) {
		for (int i = index; i < arr.length; i += 2) {
			if(arr[i]<n){
				n-=arr[i];
				index+=2;
			}else{
				arr[i]-=n;
				index=i;
				return arr[i+1];
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		RLEIterator iterator = new RLEIterator(new int[]{635,606,576,391,370,981,36,21,961,185,124,210,801,937,22,426,101,260,221,647,350,180,504,39,101,989,199,358,732,839,919,169,673,967,58,676,846,342,250,597,442,174,472,410,569,509,311,357,838,251});
		iterator.next(5039);
		iterator.next(62);
		iterator.next(3640);
		iterator.next(671);
		iterator.next(67);
		iterator.next(395);
		iterator.next(262);
		iterator.next(839);
		iterator.next(74);
		iterator.next(43);
		iterator.next(42);
		iterator.next(77);
		iterator.next(13);
		iterator.next(6);
		iterator.next(3);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		iterator.next(1);
		
		
	}
}
