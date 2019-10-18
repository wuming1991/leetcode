package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class NumArray
 * @Author: WuMing
 * @CreateDate: 2019/4/18 13:35
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class NumArray {
	int[] sum;
	public NumArray(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			nums[i]=nums[i-1]+nums[i];
		}
		sum=nums;
	}
	
	public int sumRange(int i, int j) {
		
		if(i>0){
			return sum[j]-sum[i-1];
		}else{
			return sum[j];
		}
	}
}
