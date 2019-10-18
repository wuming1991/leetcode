package com.leetcode;

import java.util.Arrays;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class KthLargest
 * @Author: WuMing
 * @CreateDate: 2019/4/12 10:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class KthLargest {
	
	int[] nums;
	int k;
	int min = Integer.MIN_VALUE;
	
	public KthLargest(int k, int[] nums) {
		this.k = k;
		Arrays.sort(nums);
		this.nums = nums;
	}
	
	
	
	public int add(int val) {
		if (nums.length < 1) {
			min = Math.max(min, val);
			return min;
		}
		int ret;
		if (k == 1) {
			int i = 0;
			while (val > nums[i] && i < nums.length - 1) {
				if (nums[i + 1] >= val) {
					nums[i] = val;
				} else {
					nums[i] = nums[i + 1];
				}
				i++;
			}
			if (val > nums[i]) {
				nums[i] = val;
			}
			ret = nums[0];
		} else {
			if (val <= nums[k - 2]) {
				ret = nums[k - 2];
			} else {
				ret = val;
			}
			if (val > nums[0]) {
				int i = 0;
				while (val > nums[i] && i < nums.length - 1) {
					if (nums[i + 1] >= val) {
						nums[i] = val;
					} else {
						nums[i] = nums[i + 1];
					}
					i++;
				}
				
				ret = Math.min(nums[k - 2], ret);
			}
			
		}
		return ret;
	}
	
	public static void main(String[] args) {
		KthLargest kthLargest = new KthLargest(1, new int[]{});
		kthLargest.add(-3);   // returns 4
		kthLargest.add(-2);   // returns 5
		kthLargest.add(-4);  // returns 5
		kthLargest.add(0);   // returns 8
		kthLargest.add(4);   // returns 8
		
	}
}
