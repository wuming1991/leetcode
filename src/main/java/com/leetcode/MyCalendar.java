package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MyCalendar
 * @Author: WuMing
 * @CreateDate: 2019/5/24 11:30
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MyCalendar {
	
	int[][] calender;
	int index = 0;
	
	public MyCalendar() {
		calender = new int[100][2];
	}
	
	public boolean book(int start, int end) {
		int i = 0;
		for (; i < index; i++) {
			if (start > calender[i][1] && end < calender[i][0]) {
				return false;
			}
		}
		
		calender[i][0] = start;
		calender[i][1] = end;
		index++;
		return true;
		
		
	}
}
