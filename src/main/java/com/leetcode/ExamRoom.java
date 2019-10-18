package com.leetcode;

import java.util.LinkedList;
import java.util.TreeSet;
import javafx.collections.transformation.SortedList;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class ExamRoom
 * @Author: WuMing
 * @CreateDate: 2019/10/15 9:37
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ExamRoom {
	
	int max;
	LinkedList<Integer> list;
	
	public ExamRoom(int N) {
		max = N - 1;
		list = new LinkedList<>();
	}
	
	public int seat() {
		int len = list.size();
		if (len == 0) {
			list.add(0);
			return 0;
		}
		Integer first = list.getFirst();
		Integer last = list.getLast();
		int l = first, r = max + max - last, idx = 0;
		int x = 0 - l, y = list.get(0);
		for (int i = 1; i < len; i++) {
			Integer cur = list.get(i);
			if ((cur - l) / 2 > (y - x) / 2) {
				x = l;
				y = cur;
				idx = i;
			}
			l = cur;
		}
		if ((r - l) / 2 > (y - x) / 2) {
			list.add(max);
			return max;
		} else {
			int ret = (x + y) / 2;
			list.add(idx, ret);
			return ret;
		}
	}
	
	public void leave(int p) {
		boolean b = list.remove((Integer) p);
	}
	
	public static void main(String[] args) {
		ExamRoom room = new ExamRoom(4);
		room.seat();
		room.seat();
		room.leave(0);
		room.leave(3);
		room.seat();
		room.seat();
		room.seat();
		room.seat();
		room.leave(0);
		room.leave(2);
		room.seat();
		room.seat();
		room.leave(1);
		room.seat();
	}
}
