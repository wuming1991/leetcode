package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MyCalendarTwo2
 * @Author: WuMing
 * @CreateDate: 2019/10/2 11:25
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MyCalendarTwo2 {
	
	int[][] s = new int[1000][2];
	int si = 0;
	int[][] d = new int[1000][2];
	int di = 0;
	
	public MyCalendarTwo2() {
	
	}
	
	public boolean book(int start, int end) {
		for (int i = 0; i < di; i++) {
			if (start >= d[i][1] || end <= d[i][0]) {
				continue;
			} else {
				return false;
			}
		}
		for (int i = 0; i < si; i++) {
			if (start >= s[i][1] || end <= s[i][0]) {
				continue;
			} else {
				d[di][0]=Math.max(start,s[i][0]);
				d[di][1]=Math.min(end,s[i][1]);
				di++;
			}
		}
		s[si][0]=start;
		s[si][1]=end;
		si++;
		return true;
	}
	
	
}
