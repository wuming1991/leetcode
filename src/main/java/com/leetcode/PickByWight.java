package com.leetcode;

import java.util.Random;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class PickByWight
 * @Author: WuMing
 * @CreateDate: 2019/9/29 8:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class PickByWight {
	
	int[][] wight;
	int total;
	Random random;
	
	public PickByWight(int[] w) {
		int len = w.length;
		wight = new int[len][2];
		total = 0;
		for (int i = 0; i < len; i++) {
			
			wight[i][0] = total;
			total += w[i];
			wight[i][1] = total-1;
			random = new Random();
		}
	}
	
	public int pickIndex() {
		int i = random.nextInt(total);
		int l = 0, r = wight.length - 1, m;
		while (l <= r) {
			m = (l + r) / 2;
			if (wight[m][0] > i) {
				r = m - 1;
			} else if (wight[m][1] < i) {
				l = m + 1;
			} else {
				l = m;
				break;
			}
		}
		return l;
	}
	
	public static void main(String[] args) {
		PickByWight pickByWight = new PickByWight(new int[]{3, 14, 1, 17});
		for (int i = 0; i < 100; i++) {
			
			System.out.println(pickByWight.pickIndex());
		}
		System.out.println(pickByWight.pickIndex());
		System.out.println(pickByWight.pickIndex());
		System.out.println(pickByWight.pickIndex());
		System.out.println(pickByWight.pickIndex());
	}
}
