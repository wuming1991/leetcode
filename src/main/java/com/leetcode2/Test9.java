package com.leetcode2;

import java.util.Arrays;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class Test9
 * @Author: WuMing
 * @CreateDate: 2020/11/6 16:42
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test9 {
	
	public static void main(String[] args) {
		Test9 test = new Test9();
	}
	//5562. 字符频次唯一的最小删除次数
	public int minDeletions(String s) {
		int[] mem = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			mem[s.charAt(i)-'a']++;
		}
		Arrays.sort(mem);
		int max=Integer.MAX_VALUE;
		int ret=0,t;
		for (int i = 25; i >=0 ; i--) {
			if(max==0){
				ret+=mem[i];
			}else{
				if(mem[i]>=max){
					t=mem[i]-max+1;
					ret+=t;
					mem[i]-=t;
				}
				max=mem[i];
			}
		}
		return ret;
	}
	class CharTree {
		
		public CharTree[] child;
		int val;
		
		public CharTree() {
			this.child = new CharTree[26];
		}
	}
	
	public int numSimilarGroups(String[] A) {
		int len = A.length;
		int[] mem = new int[len];
		for (int i = 0; i < len; i++) {
			mem[i] = i;
		}
		CharTree root = new CharTree();
		insertTree(root, A[0],0);
		for (int i = 1; i < len; i++) {
			numSimilarGroupsHelper(root, A[i], i, mem, 0, 0, '1', '2');
			insertTree(root, A[i], i);
		}
		int ret=0;
		for (int i = 0; i < len; i++) {
			if(mem[i]==i){
				ret++;
			}
		}
		return ret;
	}
	
	private void insertTree(CharTree root, String s,  int val) {
		int x;
		for (int i = 0; i < s.length(); i++) {
			x = s.charAt(i) - 'a';
			if(root.child[x]==null){
				root.child[x]=new CharTree();
			}
			root=root.child[x];
		}
		root.val=val;
	}
	
	
	private void numSimilarGroupsHelper(CharTree root, String s, int id, int[] mem, int idx,
		int step, int a, int b) {
		if(idx==s.length()){
			a=mergeHelper(mem,root.val);
			b=mergeHelper(mem,id);
			if(a>b){
				mem[a]=b;
			}else{
				mem[b]=a;
			}
			return;
		}
		int x = s.charAt(idx) - 'a';
		if (step == 0) {
			CharTree cur;
			for (int i = 0; i < 26; i++) {
				cur = root.child[i];
				if (cur != null) {
					numSimilarGroupsHelper(cur, s, id, mem, idx + 1, step + (i == x ? 0 : 1), x, i);
				}
			}
		} else if (step == 1) {
			if (x == b && root.child[a] != null) {
				numSimilarGroupsHelper(root.child[a], s, id, mem, idx + 1, step + 1, a, b);
			}
			if (root.child[x] != null) {
				numSimilarGroupsHelper(root.child[x], s, id, mem, idx + 1, step, a, b);
			}
		} else {
			if (root.child[x] != null) {
				numSimilarGroupsHelper(root.child[x], s, id, mem, idx + 1, step, a, b);
			}
		}
	}
	
	private int mergeHelper(int[] mem, int val) {
		if(mem[val]==val){
			return val;
		}
		mem[val]=mergeHelper(mem,mem[val]);
		return mem[val];
	}
}
