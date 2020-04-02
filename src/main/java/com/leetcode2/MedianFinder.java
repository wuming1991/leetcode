package com.leetcode2;

import com.leetcode.RandPointGetInRectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class MedianFinder
 * @Author: WuMing
 * @CreateDate: 2020/3/20 13:58
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MedianFinder {
	Map<Integer,Integer> map;
	int count=0;
	public MedianFinder() {
		map=new TreeMap<>();
	}
	
	public void addNum(int num) {
		count++;
		map.put(num,map.getOrDefault(num,0)+1);
	}
	
	public double findMedian() {
		int t;
		if((count&1)==1){
			t=count/2 +1;
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				t-=entry.getValue();
				if(t<=0){
					return entry.getKey();
				}
			}
		}else{
			t=count/2;
			boolean flag=false;
			Integer x=0;
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				t-=entry.getValue();
				if(t<0){
					if(flag){
						return ((double)x+entry.getKey())/2;
					}
					return entry.getKey();
				}else if(t==0){
					flag=true;
					x=entry.getKey();
				}
			}
		}
		return 0;
	}
}
