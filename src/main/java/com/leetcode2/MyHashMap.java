package com.leetcode2;

import com.wuming.pattern.FlyWeight.Tree;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class MyHashMap
 * @Author: WuMing
 * @CreateDate: 2020/9/16 19:04
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class MyHashMap {
	/** Initialize your data structure here. */
	class TreeNode{
		TreeNode[] child;
		int val;
		
		public TreeNode() {
			this.child = new TreeNode[10];
			this.val = -1;
		}
	}
	TreeNode root;
	public MyHashMap() {
		  root = new TreeNode();
	}
	
	/** value will always be non-negative. */
	public void put(int key, int value) {
		TreeNode cur = this.root;
		int t;
		while (key>0){
			t=key%10;
			if(cur.child[t]==null){
				cur.child[t]=new TreeNode();
			}
			cur=cur.child[t];key/=10;
		}
		cur.val=value;
	}
	
	/** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	public int get(int key) {
		TreeNode cur = this.root;int t;
		while (key>0){
			t=key%10;
			if(cur.child[t]==null){
				return -1;
			}
			cur=cur.child[t];key/=10;
		}
		return cur.val;
	}
	
	/** Removes the mapping of the specified value key if this map contains a mapping for the key */
	public void remove(int key) {
		TreeNode cur = this.root;int t;
		while (key>0){
			t=key%10;
			if(cur.child[t]==null){
				return;
			}
			cur=cur.child[t];key/=10;
		}
		cur.val=-1;
	}
}
