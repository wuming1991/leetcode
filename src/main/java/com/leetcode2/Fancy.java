package com.leetcode2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class Fancy
 * @Author: WuMing
 * @CreateDate: 2020/10/19 18:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Fancy {//超时
	List<Integer> num;
	List<Integer> idx;
	List<Integer> ops;
	public Fancy() {
		num=new ArrayList<>();
		idx=new ArrayList<>();
		ops=new ArrayList<>();
	}
	
	public void append(int val) {
		num.add(val);
		idx.add(ops.size());
	}
	
	public void addAll(int inc) {
		ops.add(-inc);
	}
	
	public void multAll(int m) {
		ops.add(m);
	}
	
	public int getIndex(int idx) {
		if(idx>=num.size()){
			return -1;
		}
		Integer x = num.get(idx),t;
		long ret=x;
		Integer i = this.idx.get(idx);
		for(;i<ops.size(); i++){
			t=ops.get(i);
			if(t<0){
				ret-=t;
			}else{
				ret*=t;
				ret%=1000000007;
			}
		}
		num.set(idx,(int)ret);
		this.idx.set(idx,ops.size());
		return (int)ret;
	}
}
