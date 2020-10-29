package com.leetcode2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class ThroneInheritance
 * @Author: WuMing
 * @CreateDate: 2020/9/27 10:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class ThroneInheritance {
	
	class TreeNode {
		
		String name;
		boolean alive;
		List<TreeNode> children;
		
		public TreeNode(String name) {
			this.name = name;
			this.alive = true;
			this.children = new ArrayList<>();
		}
	}
	
	TreeNode root;
	HashMap<String,TreeNode> map=new HashMap<>();
	public ThroneInheritance(String kingName) {
		root = new TreeNode(kingName);
		map.put(kingName,root);
	}
	
	public void birth(String parentName, String childName) {
		TreeNode cur = findName(root, parentName);
		if (cur != null) {
			TreeNode e = new TreeNode(childName);
			map.put(childName,e);
			cur.children.add(e);
		}
	}
	
	private TreeNode findName(TreeNode root, String parentName) {
		if(map.containsKey(parentName)){
			return map.get(parentName);
		}
		if (root == null) {
			return null;
		}
		if (root.name.equals(parentName)) {
			return root;
		}
		for (TreeNode child : root.children) {
			TreeNode name = findName(child, parentName);
			if (name != null) {
				return name;
			}
			
		}
		return null;
	}
	
	public void death(String name) {
		if(map.containsKey(name)){
			map.get(name).alive=false;
			map.remove(name);
			return;
		}
		TreeNode cur = findName(root, name);
		cur.alive = false;
	}
	
	public List<String> getInheritanceOrder() {
		ArrayList<String> ret = new ArrayList<>();
		findAll(root, ret);return ret;
	}
	
	private void findAll(TreeNode root, ArrayList<String> ret) {
		if (root == null) {
			return;
		}
		if (root.alive) {
			ret.add(root.name);
		}
		for (TreeNode child : root.children) {
			findAll(child, ret);
		}
	}
	
	public static void main(String[] args) {
		ThroneInheritance x = new ThroneInheritance("king");
		x.birth("king", "logan");
		x.birth("logan", "hosea");
		x.birth("king", "leonard");
		x.death("king");
		x.birth("logan", "carl");
		x.death("hosea");
	}
}
