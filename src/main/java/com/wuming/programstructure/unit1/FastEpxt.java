package com.wuming.programstructure.unit1;

/**
 * @ProjectName: study
 * @Package: com.wuming.programstructure.unit1
 * @Class FastEpxt
 * @Author: WuMing
 * @CreateDate: 2019/1/17 11:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class FastEpxt {
	
	public static void main(String[] args) {
	
		int i1 = fast_epxt(3, 3, 7, 1);
		System.out.println(i1);
		
		double[] list=new double[10];
		list[0]=0;
		list[1]=1;
		for (int i = 2; i < 10; i++) {
			list[i]=list[i-1]+list[i-2];
		}
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}
		double s=0;
		for (int i = 2; i < list.length; i++) {
			s=s+list[i-1]/list[i];
		}
		System.out.println(s / 8);
	}
	
	public static int fast_epxt(int result, int a, int n, int up) {
		if (n == up) {
			return result;
		} else if (2 * up < n) {
			return fast_epxt(result*result, a, n, 2 * up);
		}else{
			return result*fast_epxt(a,a,(n-up),1);
		}
	}
}
