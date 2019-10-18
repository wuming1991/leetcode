package com.leetcode;

import java.lang.reflect.Array;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import javax.lang.model.element.VariableElement;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class TopVotedCandidate
 * @Author: WuMing
 * @CreateDate: 2019/5/27 17:08
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class TopVotedCandidate {
	
	
	int[] vote;
	int[] time;
	
	public TopVotedCandidate(int[] persons, int[] times) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int max = 0;
		vote = new int[times.length];
		time = times;
		int[] count = new int[times.length];
		for (int i = 0; i < times.length; i++) {
			int p = persons[i];
			count[p]++;
			if (count[p] >= max) {
				max = count[p];
				vote[i] = p;
			} else {
				vote[i] = vote[i - 1];
			}
		}
	}
	
	public int q(int t) {
		int i = Arrays.binarySearch(time, t);
		if(i<0){
			i=-(i+1)-1;
		}
		return vote[i];
	}
}
