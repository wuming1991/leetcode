package com.leetcode;

import java.util.HashMap;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class SnapshotArray
 * @Author: WuMing
 * @CreateDate: 2019/11/8 9:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class SnapshotArray {
	
	HashMap<Integer, HashMap<Integer, Integer>> base;
	HashMap<Integer, Integer> cur;
	int version;
	
	public SnapshotArray(int length) {
		base = new HashMap<>();
		cur = new HashMap<>();
		version = 0;
	}
	
	public void set(int index, int val) {
		cur.put(index, val);
	}
	
	public int snap() {
		if(cur.size()>0){
			base.put(version, cur);
			cur = new HashMap<>();
		}
		version++;
		return version - 1;
	}
	
	public int get(int index, int snap_id) {
		for (int i = snap_id; i >=0 ; i--) {
			HashMap<Integer, Integer> map = base.get(i);
			if(map!=null){
				Integer integer = map.get(index);
				if(integer!=null){
					return integer;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		SnapshotArray array = new SnapshotArray(1);
		array.set(0, 15);
		array.snap();
		array.snap();
		array.snap();
		array.get(0, 2);
		array.snap();
		array.snap();
		array.get(0, 2);
	}
}
