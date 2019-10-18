package com.leetcode;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class CBTInserter
 * @Author: WuMing
 * @CreateDate: 2019/5/7 17:34
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class CBTInserter {
	
	int level;
	int total;
	TreeNode root;
	
	public CBTInserter(TreeNode root) {
		this.root = root;
		total = countNode(root, 0);
		int curLevelContain=1<<level;
		int num=total-(curLevelContain-1);
		if(num==curLevelContain){
			level++;
		}
	}
	
	private int countNode(TreeNode root, int i) {
		if (root == null) {
			return 0;
		}
		if (level < i) {
			level = i;
		}
		return 1 + countNode(root.left, i + 1) + countNode(root.right, i + 1);
	}
	
	public int insert(int v) {
		total++;
		int curLevelContain=1<<level;
		int num=total-(curLevelContain-1);
		if(num==curLevelContain){
			level++;
		}
		TreeNode cur=root;
		while (curLevelContain>2){
			curLevelContain>>=1;
			if(num<=curLevelContain){
				cur=cur.left;
			}else{
				num-=curLevelContain;
				cur=cur.right;
			}
		}
		if(num==1){
			cur.left=new TreeNode(v);
		}else{
			cur.right=new TreeNode(v);
		}
		
		return cur.val;
	}
	
	public TreeNode get_root() {
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left=new TreeNode(2);
		/*root.left.left=new TreeNode(4);
		root.left.right=new TreeNode(5);
		root.right=new TreeNode(3);
		root.right.left=new TreeNode(6);*/
		CBTInserter inserter = new CBTInserter(root);
		inserter.insert(3);
		inserter.insert(4);
		inserter.insert(5);
		inserter.insert(6);
		inserter.insert(7);
		inserter.insert(8);
		inserter.insert(9);
		inserter.get_root();
	}
}
