package com.leetcode2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WuMing
 * @CreateDate: 2020/7/10 16:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Cashier {
	int mod,discount,count;
	Map<Integer,Integer> map=new HashMap<>();
	public Cashier(int n, int discount, int[] products, int[] prices) {
		count=0;
		mod=n;
		this.discount=discount;
		int len = products.length;
		for (int i = 0; i < len; i++) {
			map.put(products[i],prices[i]);
		}
	}
	
	public double getBill(int[] product, int[] amount) {
		double ret=0;
		int len = product.length;
		for (int i = 0; i < len; i++) {
			ret+=map.get(product[i])*amount[i];
		}
		count++;
		if(count%mod==0){
			return ret-(ret*discount)/100;
		}
		return ret;
	}
}
