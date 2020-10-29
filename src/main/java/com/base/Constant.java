package com.base;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/4 18:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Constant {
	
	public static int[][] ds_8 = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
		{1, 1}};
	public static int[][] ds_4 = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	public static int mod=1000000007;
	
	public static int[][] stringToArr(String s){
		s=s.substring(0,s.length()-1);
		String p="\\[";
		s=s.replaceAll(p,"");
		String[] rows = s.split("],");
		int len = rows.length,h;
		int[][] ret = new int[len][];
		for (int i = 0; i < len; i++) {
			String[] cols = rows[i].split(",");
			  h = cols.length;
			ret[i]=new int[h];
			for (int j = 0; j < h; j++) {
				ret[i][j]=Integer.parseInt(cols[j]);
			}
		}
		return ret;
	}
}
