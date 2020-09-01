package com.leetcode2;

import static com.base.Constant.ds_4;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/31 15:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test7 {
	
	public static void main(String[] args) {
		Test7 test = new Test7();
		test.minDays(new int[][]{{0,1,1,0},{0,1,1,0},{0,0,0,0}});
	}
	
	//1568. 使陆地分离的最少天数
	public int minDays(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		int[][] mem = new int[high][len];
		boolean flag = false;
		int sum=0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					sum++;
					if (mem[i][j]==-1) {
						continue;
					} else if (flag) {
						return 0;
					} else {
						flag = true;
						minDaysHelper(i, j, mem, grid,-1);
					}
				}
			}
		}
		if(sum<=2){
			return sum;
		}
		int count=0,ni,nj,val=0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if(grid[i][j] == 1){
					/*count=0;
					for (int[] x : ds_4) {
						ni=i+x[0];
						nj=j+x[1];
						if(ni<0||nj<0||ni>=high||nj>=len||grid[ni][nj]!=1){
							continue;
						}
						count++;
					}
					
					if(count==2){*/flag=false;
						for (int[] x : ds_4) {
							ni=i+x[0];
							nj=j+x[1];
							if(ni<0||nj<0||ni>=high||nj>=len||grid[ni][nj]!=1){
								continue;
							}
							if(flag){
								if(mem[ni][nj]!=val){
									return 1;
								}
							}else{
								val=i*100+j+100;
								mem[i][j]=val;
								minDaysHelper(ni,nj,mem,grid,val);
								flag=true;
							}
						}
					//}
				}
			}
		}
		return 2;
	}
	
	private void minDaysHelper(int i, int j, int[][] mem, int[][] grid,int value) {
		mem[i][j] = value;
		int ni, nj, high = mem.length, len = mem[0].length;
		for (int[] x : ds_4) {
			ni = i + x[0];
			nj = j + x[1];
			if(ni<0||nj<0||ni>=high||nj>=len||grid[ni][nj]!=1||mem[ni][nj]==value){
				continue;
			}
			minDaysHelper(ni,nj,mem,grid,value);
		}
	}
}
