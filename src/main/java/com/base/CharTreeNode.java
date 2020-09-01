package com.base;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/4 17:41
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class CharTreeNode {
	public CharTreeNode[] children;
	public Boolean flag;
	public String val;
	public CharTreeNode() {
		this.children = new CharTreeNode[26];
	}
	public void insert(String s){
		char[] arr = s.toCharArray();
		CharTreeNode cur=this;
		for (int i = 0; i < arr.length; i++) {
			int x=arr[i]-'a';
			if(cur.children[x]==null){
				cur.children[x]=new CharTreeNode();
			}
			cur=cur.children[x];
		}
	}
}
