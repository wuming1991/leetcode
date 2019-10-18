package com.wuming.programstructure.unit1;

/**
 * @ProjectName: study
 * @Package: com.wuming.programstructure.unit1
 * @Class Ackermann
 * @Author: WuMing
 * @CreateDate: 2019/1/15 17:58
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Ackermann {
	static int i=0;
	public static int ackermannCal(int x,int y){
		if(y==0){
			return  0;
		}
		if(x==0){
			return 2*y;
		}
		if(y==1){
			return 2;
		}
		System.out.println(i + "---" + x + "---" + y + "---");
		i++;
		return ackermannCal(x-1,ackermannCal(x,y-1));
	}
	
	public static void main(String[] args) {
		System.out.println(ackermannCal(3, 3));
		int k=2;
		for (int j = 1; j < 20; j++) {
			System.out.println(j + "---" + k);
			k*=2;
		}
	}
}
