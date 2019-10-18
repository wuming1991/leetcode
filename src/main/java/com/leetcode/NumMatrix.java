package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class NumMatrix
 * @Author: WuMing
 * @CreateDate: 2019/9/5 12:01
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class NumMatrix {
	
	int[][] matrix;
	int row, col;
	
	public NumMatrix(int[][] matrix) {
		this.matrix = matrix;
		row = matrix.length;
		if (row < 1) {
			return;
		}
		col = matrix[0].length;
		for (int i = 1; i < row; i++) {
			matrix[i][0] += matrix[i - 1][0];
		}
		for (int i = 1; i < col; i++) {
			matrix[0][i] += matrix[0][i - 1];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				matrix[i][j] += (matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1]);
			}
		}
	}
	
	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (row2 >= row || col2 >= col) {
			return 0;
		}
		int ret = matrix[row2][col2];
		if (row1 > 0 && col1 > 0) {
			return ret + matrix[row1 - 1][col1 - 1] - matrix[row1 - 1][col2] - matrix[row2][col1
				- 1];
		} else if (row1 > 0) {
			return ret - matrix[row1 - 1][col2];
		} else if (col1 > 0) {
			return ret - matrix[row2][col1 - 1];
		} else {
			return ret;
		}
	}
	
	public static void main(String[] args) {
		NumMatrix numMatrix = new NumMatrix(
			new int[][]{{4, 5}});
		numMatrix.sumRegion(0, 0, 0, 0);
	}
}
