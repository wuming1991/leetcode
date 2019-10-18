package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class StockSpanner
 * @Author: WuMing
 * @CreateDate: 2019/5/21 17:39
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class StockSpanner {
	
	
	int[] arr;
	int[] span;
	
	int index = -1;
	
	public StockSpanner() {
		arr = new int[10001];
		span = new int[10001];
	}
	
	public int next(int price) {
		this.arr[++index] = price;
		int c = 1;
		for (int k = index - 1; k >= 0;) {
			if(arr[k]<=price){
				c+=span[k];
				k-=span[k];
			}else{
				span[index]=c;
				return c;
			}
		}
		span[index]=c;
		return c;
	}
	
	public static void main(String[] args) {
		StockSpanner spanner = new StockSpanner();
		spanner.next(28);
		spanner.next(14);
		spanner.next(28);
		spanner.next(35);
		spanner.next(46);
	}
}
