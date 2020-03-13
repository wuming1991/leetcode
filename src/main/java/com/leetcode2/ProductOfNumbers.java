package com.leetcode2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class ProductOfNumbers
 * @Author: WuMing
 * @CreateDate: 2020/3/12 11:37
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class ProductOfNumbers {
	int length = 0;
	ArrayList<Integer> list;
	
	public ProductOfNumbers() {
		list = new ArrayList<>();
	}
	
	public void add(int num) {
		if(num==0){
			length=0;
			list=new ArrayList<>();
		}else{
			if(length==0){
				list.add(num);
			}else{
				list.add(list.get(length-1)*num);
			}
			length++;
		}
	}
	
	public int getProduct(int k) {
		if(k>length){
			return 0;
		}else if(k==length){
			return list.get(length-1);
		}else{
			return list.get(length-1)/list.get(length-k-1);
		}
	}
	
	public static void main(String[] args) {
		ProductOfNumbers product = new ProductOfNumbers();
		product.add(2);
		product.add(3);
		System.out.println(product.getProduct(1));
		System.out.println(product.getProduct(2));
		product.add(4);
		System.out.println(product.getProduct(1));
		System.out.println(product.getProduct(2));
		product.add(0);
		System.out.println(product.getProduct(1));
		System.out.println(product.getProduct(2));product.add(1);
		System.out.println(product.getProduct(1));
		System.out.println(product.getProduct(2));
	}
}
