package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class NumArray2
 * @Author: WuMing
 * @CreateDate: 2019/9/6 16:39
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class NumArray2 {
	
	int len;
	int[][] base;
	int[] nums;
	
	public NumArray2(int[] nums) {
		this.nums = nums;
		int length = nums.length;
		this.len = (int) Math.sqrt(length) + 1;
		base = new int[len][len];
		int idx = 0;
		for (int i = 0; i < len && idx < length; i++) {
			base[i][0] = nums[idx++];
			for (int j = 1; j < len && idx < length; j++) {
				base[i][j] = base[i][j - 1] + nums[idx++];
			}
		}
	}
	
	public void update(int i, int val) {
		int x = nums[i] - val;
		nums[i] = val;
		int cur = i / len;
		int j = i % len;
		for (; j < len; j++) {
			base[cur][j] -= x;
		}
	}
	
	public int sumRange(int i, int j) {
		int u = i / len;
		int d = j / len;
		int l = i % len;
		int r = j % len;
		int ret = 0;
		for (int k = u; k < d; k++) {
			ret += base[k][len - 1];
		}
		if (l > 0) {
			ret -= base[u][l - 1];
		}
		ret += base[d][r];
		
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(6 & -6);
		NumArray2 a = new NumArray2(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
		a.sumRange(1, 7);
		a.update(2, 2);
		a.sumRange(1, 4);
	}
}
