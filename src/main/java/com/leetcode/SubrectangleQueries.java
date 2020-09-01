package com.leetcode;

import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/16 9:05
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class SubrectangleQueries {
	
	class X {
		
		int row1;
		int col1;
		int row2;
		int col2;
		int newValue;
		
		public X(int row1, int col1, int row2, int col2, int newValue) {
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
			this.newValue = newValue;
		}
	}
	
	int[][] rectangle;
	ArrayList<X> list;
	
	public SubrectangleQueries(int[][] rectangle) {
		this.rectangle = rectangle;
		list = new ArrayList<>();
	}
	
	public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
		list.add(new X(row1,col1,row2,col2,newValue));
	}
	
	public int getValue(int row, int col) {
		X cur;
		for (int i = list.size()-1; i >=0; i--) {
			cur=list.get(i);
			if(cur.row1<=row&&cur.col1<=col&&cur.row2>=row&&cur.col2>=col){
				return cur.newValue;
			}
		}
		return rectangle[row][col];
	}
}
