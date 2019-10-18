package com.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class ExamRoom1
 * @Author: WuMing
 * @CreateDate: 2019/10/15 11:02
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ExamRoom1 {
	
	PriorityQueue<int[]> queue;
	int N;
	
	public ExamRoom1(int n) {
		N = n;
		queue = new PriorityQueue<>(
			new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[2] > o2[2]) {
						return -1;
					} else if (o1[2] == o2[2]) {
						return o1[0] - o2[0];
					} else {
						return 1;
					}
				}
			});
		queue.offer(new int[]{-1, n});
	}
	
	public int seat() {
		int[] longest = queue.poll();
		int cur;
		if (longest[0] == -1) {
			cur = 0;
			queue.offer(new int[]{-1, 0, 0});
			queue.offer(new int[]{0, longest[1], longest[1]/2});
		} else if (longest[1] == N) {
			cur = N - 1;
			queue.offer(new int[]{N - 1, N, 0});
			queue.offer(new int[]{longest[0], N - 1,(N - 1 - longest[0])/2});
		} else {
			cur = (longest[1]+longest[2])/2;
			queue.offer(new int[]{longest[0], cur, (cur - longest[0])/2});
			queue.offer(new int[]{cur, longest[1], (longest[1] - cur)/2});
		}
		return cur;
	}
	
	public void leave(int p) {
		ArrayList<int[]> list = new ArrayList<>(queue);
		int[] l = null, r = null;
		for (int[] ints : list) {
			if (ints[0] == p) {
				r = ints;
			} else if (ints[1] == p) {
				l = ints;
			}
			if (l != null && r != null) {
				queue.remove(l);
				queue.remove(r);
				int[] t = new int[3];
				t[0] = l[0];
				t[1] = r[1];
				if (t[0] == -1 || t[1] == N) {
					t[2] = (t[1] - t[0]);
				} else {
					t[2] = (t[1] - t[0])/2;
				}
				queue.offer(t);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		ExamRoom1 room = new ExamRoom1(8);
		room.seat();
		room.seat();
		room.seat();
		room.leave(0);
		room.leave(7);
		room.seat();
		room.seat();
		room.seat();
		room.seat();
		room.seat();
		room.seat();
		room.seat();
		
	}
	
}
