package com.leetcode2;

import java.util.LinkedList;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class Fancy1
 * @Author: WuMing
 * @CreateDate: 2020/10/20 9:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Fancy1 {//超时
	int[][] num;
	int[] ops;
	int ni=0,oi=0;
	public Fancy1() {
		num=new int[100000][2];
		ops=new int[100000];
	}
	
	public void append(int val) {
		num[ni][0]=val;
		num[ni][1]=oi;
		ni++;
	}
	
	public void addAll(int inc) {
		ops[oi]=-inc;
		oi++;
	}
	
	public void multAll(int m) {
		if(m>1){
			ops[oi]=m;oi++;
		}
	}
	
	public int getIndex(int idx) {
		if(idx>=ni){
			return -1;
		}
		long ret=num[idx][0];
		int cur = num[idx][1];
		for (int i = cur; i < oi; i++) {
			if(ops[i]<0){
				ret-=ops[i];
			}else{
				ret*=ops[i];
			}
			ret%=1000000007;
		}
		num[idx][0]=(int)ret;
		num[idx][1]=oi;
		return (int)ret;
	}
	
	public static void main(String[] args) {
		Fancy1 t = new Fancy1();
		t.append(2);
		t.addAll(3);
		t.append(7);
		t.multAll(2);
		t.getIndex(0);
	}
}
