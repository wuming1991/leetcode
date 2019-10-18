package com.leetcode;

import java.util.Random;
import jodd.util.BinarySearch;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class RandPointGetInRectangle
 * @Author: WuMing
 * @CreateDate: 2019/9/27 8:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RandPointGetInRectangle {
	
	int[][] rects;
	Random random;
	int[][] ints;
	int sum;
	
	public RandPointGetInRectangle(int[][] rects) {
		this.rects = rects;
		int len = rects.length;
		ints = new int[len][2];
		sum = -1;
		for (int i = 0; i < len; i++) {
			ints[i][0] = ++sum;
			int[] rect = rects[i];
			sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
			ints[i][1] = sum;
		}
		random = new Random();
	}
	
	public int[] pick() {
		int pick = random.nextInt(sum);
		int l = 0, r = ints.length - 1, m;
		while (l < r) {
			m = (l + r) / 2;
			if (ints[m][0] > pick) {
				r = m - 1;
			} else if (ints[m][1] < pick) {
				l = m + 1;
			} else {
				l = m;
				break;
			}
		}
		int[] rect = rects[l];
		int x1 = rect[0];
		int y1 = rect[1];
		int x2 = rect[2];
		int y2 = rect[3];
		return new int[]{x1 + random.nextInt(x2 - x1 + 1), y1 + random.nextInt(y2 - y1 + 1)};
	}
	
	
	public static void main(String[] args) {
		RandPointGetInRectangle get = new RandPointGetInRectangle(
			new int[][]{{-2, -2, -1, -1}, {1, 0, 3, 0}});
		int[] pick = get.pick();
		System.out.println(pick[0] + " " + pick[1]);
		for (int i = 0; i < 100; i++) {
			pick = get.pick();
			System.out.println(pick[0] + " " + pick[1]);
		}
	}
}
