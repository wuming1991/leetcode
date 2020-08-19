package com.leetcode2;

import com.leetcode2.Test.TreeNode;
import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Codec
 * @Author: WuMing
 * @CreateDate: 2020/1/20 11:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Codec {
	
	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "[]";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		ArrayList<TreeNode> cur = new ArrayList<>(), next;
		cur.add(root);
		while (!cur.isEmpty()) {
			next = new ArrayList<>();
			for (TreeNode node : cur) {
				if (node == null) {
					buffer.append("null,");
				} else {
					buffer.append(node.val + ",");
					next.add(node.left);
					next.add(node.right);
				}
			}
			cur = next;
		}
		int len = buffer.length();
		while (buffer.indexOf("null,", len - 6) == len - 5) {
			len -= 5;
		}
		buffer.setCharAt(len - 1, ']');
		CharSequence charSequence = buffer.subSequence(0, len);
		System.out.println(charSequence.toString());
		return charSequence.toString();
	}
	
	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		int len = data.length();
		if (len <= 2) {
			return null;
		}
		data = data.substring(1, len - 1);
		String[] node = data.split(",");
		TreeNode root = new TreeNode(Integer.valueOf(node[0]));
		ArrayList<TreeNode> cur = new ArrayList<>(), next;
		cur.add(root);
		int idx = 1;
		String l, r;
		while (idx < node.length) {
			next = new ArrayList<>();
			for (TreeNode x : cur) {
				if(idx >= node.length){
					break;
				}
				l = node[idx];
				r =idx+1 < node.length? node[idx + 1]:"null";
				if (!l.equals("null")) {
					x.left = new TreeNode(Integer.valueOf(l));
					next.add(x.left);
				}
				if (!r.equals("null")) {
					x.right = new TreeNode(Integer.valueOf(r));
					next.add(x.right);
				}
				idx += 2;
			}
			cur = next;
		}
		return root;
	}
	
	public static void main(String[] args) {
		Codec codec = new Codec();
		TreeNode node = codec.deserialize("[1,2]");
		System.out.println(codec.serialize(node));
	}
}
