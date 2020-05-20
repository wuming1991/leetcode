package com.leetcode2;

/**
 * @Author: WuMing
 * @CreateDate: 2020/5/20 17:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test4 {
	int[][] ds_8 = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	int[][] ds_4 = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	public static void main(String[] args) {
	
	}
	//面试题 08.10. 颜色填充
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if(newColor==image[sr][sc]){
			return image;
		}
		floodFillHelper(image,sr,sc,newColor,image[sr][sc]);
		return image;
	}
	
	private void floodFillHelper(int[][] image, int x, int y, int newColor, int oldColor) {
		image[x][y]=newColor;
		int i,j,high=image.length,len=image[0].length;
		for (int[] d : ds_4) {
			i=x+d[0];
			j=y+d[1];
			if(i<0||j<0||i>=high||j>=len||image[i][j]!=oldColor){
				continue;
			}
			floodFillHelper(image,i,j,newColor,oldColor);
		}
	}
}
