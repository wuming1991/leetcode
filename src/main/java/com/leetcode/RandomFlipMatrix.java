package com.leetcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class RandomFlipMatrix
 * @Author: WuMing
 * @CreateDate: 2019/9/27 9:53
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RandomFlipMatrix {
	
	int n_rows;
	int n_cols;
	Random random;
	int last;
	Tire root;
	
	static class Tire {
		
		int val;
		int beforeHave;
		Tire left;
		Tire right;
	}
	
	public RandomFlipMatrix(int n_rows, int n_cols) {
		this.n_cols = n_cols;
		this.n_rows = n_rows;
		root = null;
		last = n_rows * n_cols;
		random = new Random();
	}
	
	public int[] flip() {
		if (last == 0) {
			return null;
		}
		int p = random.nextInt(last);
		int val;
		if (root == null) {
			root = new Tire();
			root.val = p;
			root.beforeHave = p;
			val = p;
		} else {
			val = InsertTree(root, p);
		}
		last--;
		return new int[]{val / n_cols, val%n_cols};
	}
	
	private int InsertTree(Tire root, int p) {
		if (root.beforeHave > p) {
			root.beforeHave--;
			minusOne(root.right);
			if (root.left == null) {
				Tire left = new Tire();
				root.left = left;
				left.beforeHave = p;
				left.val = root.val - (root.beforeHave - p + 1);
				return left.val;
			}
			return InsertTree(root.left, p);
		} else {
			if (root.right == null) {
				Tire right = new Tire();
				root.right = right;
				right.beforeHave = p;
				right.val = root.val + (p - root.beforeHave + 1);
				return right.val;
			}
			return InsertTree(root.right, p);
		}
	}
	
	public Tire getRoot() {
		return root;
	}
	
	public void setRoot(Tire root) {
		this.root = root;
	}
	
	private void minusOne(Tire root) {
		if (root != null) {
			root.beforeHave--;
			minusOne(root.left);
			minusOne(root.right);
		}
	}
	
	public void reset() {
		root = null;
		last = n_rows * n_cols;
	}
	
	public static void main(String[] args) {
		RandomFlipMatrix test = new RandomFlipMatrix(2, 1000);
		int[] flip;
		for (int i = 0; i < 5; i++) {
			flip = test.flip();
			System.out.println(flip[0] + " " + flip[1]);
		}
	}
}
