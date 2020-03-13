package com.leetcode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test1
 * @Author: WuMing
 * @CreateDate: 2020/1/16 11:07
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test1 {
	
	static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	public int minSetSize(int[] arr) {
		int[] count = new int[100001];
		for (int i : arr) {
			count[i]++;
		}
		int len = arr.length;
		int ret = 1, t = 0;
		Arrays.sort(count);
		for (int i = count.length - 1; i >= 0; i--) {
			t += count[i];
			if (t >= len / 2) {
				return ret;
			}
			ret++;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Test1 test = new Test1();
		test.largestMultipleOfThree(new int[]{8,6,7,1,0});
		test.isPossible(new int[]{5, 8});
		test.validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1});
		//test.arrayRankTransform(new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12, 12, 12, 12, 12});
	}
	//1363
	public String largestMultipleOfThree(int[] digits) {
		int[] count = new int[10];
		int sum = 0;
		int c = 0;
		for (int digit : digits) {
			sum += digit;
			count[digit]++;
			if (digit > 0) {
				c++;
			}
		}
		if (sum % 3 != 0) {
			int i = 1;
			for (i = 1; i <= c; i++) {
				if (largestMultipleOfThreeHelper(count, 3-sum % 3, i)) {
					break;
				}
			}
			if (i > c) {
				if (count[0] > 0) {
					return "0";
				}
				return "";
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 9; i > 0; i--) {
			while (count[i] > 0) {
				buffer.append(i);count[i]--;
			}
		}
		if (count[0] > 0) {
			if (buffer.length() > 0) {
				while (count[0] > 0) {
					buffer.append(0);
					count[0]--;
				}
			} else {
				buffer.append(0);
			}
		}
		return buffer.toString();
	}
	
	private boolean largestMultipleOfThreeHelper(int[] count, int sum, int l) {
		if(l==0){
			return sum%3==0;
		}
		for (int j = 1; j <= 9; j++) {
			if(count[j]>0){
				count[j]--;
				if(largestMultipleOfThreeHelper(count,sum+j,l-1)){
					return true;
				}
				count[j]++;
			}
		}
		return false;
	}
	
	public int[] sortByBits(int[] arr) {
		int len = arr.length, t, c;
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			c = 0;
			t = arr[i];
			ints[i][0] = t;
			while (t > 0) {
				c += t & 1;
				t >>= 1;
			}
			ints[i][1] = c;
		}
		Arrays.sort(ints, (a, b) -> {
			if (a[1] == b[1]) {
				return a[0] - b[0];
			} else {
				return a[1] - b[1];
			}
		});
		for (int i = 0; i < len; i++) {
			arr[i] = ints[i][0];
		}
		return arr;
	}
	
	//1354
	public boolean isPossible(int[] target) {
		if (target.length < 2) {
			return target[0] == 1;
		}
		TreeSet<Integer> set = new TreeSet<>();
		int total = 0;
		for (int i : target) {
			set.add(i);
			total += i;
		}
		Integer last = set.last();
		while (last > 1) {
			set.remove(last);
			int other = total - last;
			int me = (last) % other;
			total -= (last / other) * other;
			
			if (1 == other) {
				return true;
			} else if (me == 0 || me == last) {
				return false;
			}
			if (me != 1 && set.contains(me)) {
				return false;
			}
			set.add(me);
			last = set.last();
		}
		return true;
	}
	
	//1361
	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		boolean[] flag = new boolean[n];
		//flag[0] = true;
		if (!validateBinaryTreeNodesHelper(0, leftChild, rightChild, flag)) {
			return false;
		}
		int root = 0, count = 0;
		
		for (int i = 0; i < n; i++) {
			if (flag[i]) {
				count++;
			} else if (leftChild[i] == root) {
				flag[i] = true;
				root = i;
				if (!validateBinaryTreeNodesHelper(rightChild[i], leftChild, rightChild, flag)) {
					return false;
				}
				i = 0;
				count = 1;
			} else if (rightChild[i] == root) {
				flag[i] = true;
				root = i;
				if (!validateBinaryTreeNodesHelper(leftChild[i], leftChild, rightChild, flag)) {
					return false;
				}
				i = 0;
				count = 1;
			}
		}
		return count == n;
	}
	
	private boolean validateBinaryTreeNodesHelper(int i, int[] leftChild, int[] rightChild,
		boolean[] flag) {
		if (i < 0) {
			return true;
		}
		if (flag[i]) {
			return false;
		}
		flag[i] = true;
		return validateBinaryTreeNodesHelper(leftChild[i], leftChild, rightChild, flag) &&
			validateBinaryTreeNodesHelper(rightChild[i], leftChild, rightChild, flag);
		
	}
	
	int deep = 0, deepSum = 0;
	
	public int deepestLeavesSum(TreeNode root) {
		deepestLeavesSumHelper(root, 0);
		return deepSum;
	}
	
	private void deepestLeavesSumHelper(TreeNode root, int i) {
		if (root == null) {
			return;
		}
		deepestLeavesSumHelper(root.left, i + 1);
		deepestLeavesSumHelper(root.right, i + 1);
		if (i > deep) {
			deep = i;
			deepSum = root.val;
		} else if (i == deep) {
			deepSum += root.val;
		}
	}
	
	public int[] arrayRankTransform(int[] arr) {
		int len = arr.length;
		int[][] copy = new int[len][2];
		for (int i = 0; i < len; i++) {
			copy[i][0] = arr[i];
			copy[i][1] = i;
		}
		Arrays.sort(copy, (a, b) -> (a[0] - b[0]));
		int rank = 1;
		int[] count = new int[arr.length];
		count[0] = 1;
		for (int i = 1; i < len; i++) {
			if (copy[i][0] == copy[i - 1][0]) {
				count[i] = rank;
			} else {
				rank++;
				count[i] = rank;
			}
		}
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[copy[i][1]] = count[i];
		}
		return ret;
	}
	
	
	public int minTaps1(int n, int[] ranges) {
		int[] mem = new int[n];
		int min, max;
		for (int i = 0; i < n; i++) {
			mem[i] = i;
		}
		for (int i = n; i >= 0; i--) {
			if (ranges[i] > 0) {
				min = Math.max(0, i - ranges[i]);
				max = i + ranges[i];
				for (int j = min; j < n && j < max; j++) {
					if (max > mem[j] + ranges[mem[j]]) {
						mem[j] = i;
					}
				}
			}
		}
		int cur = 0, next = 0, count = 0;
		while (cur < n) {
			next = mem[cur] + ranges[mem[cur]];
			if (next == cur) {
				return -1;
			}
			cur = next;
			count++;
		}
		return count;
		
	}
	
	public int minTaps(int n, int[] ranges) {
		return minTapsHelper(0, n, ranges, 0);
	}
	
	private int minTapsHelper(int x, int n, int[] ranges, int cur) {
		int max = x;
		int t = cur;
		if (x >= n) {
			return 0;
		}
		for (int i = cur; i <= n; i++) {
			if (ranges[i] != 0 && i - ranges[i] <= x && i + ranges[i] > max) {
				max = i + ranges[i];
				cur = i;
			}
		}
		if (t == cur) {
			return -1;
		}
		return 1 + minTapsHelper(max, n, ranges, cur);
	}
	
	//1324
	public List<String> printVertically(String s) {
		String[] split = s.split(" ");
		int max = 0;
		int len = split.length;
		int[] length = new int[len];
		int[] mlength = new int[len];
		for (int i = len - 1; i >= 0; i--) {
			length[i] = split[i].length();
			max = Math.max(max, split[i].length());
			mlength[i] = max;
		}
		ArrayList<String> ret = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		String t;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < len; j++) {
				t = split[j];
				if (mlength[j] <= i) {
					break;
				} else if (length[j] <= i) {
					buffer.append(' ');
				} else {
					buffer.append(t.charAt(i));
				}
			}
			ret.add(buffer.toString());
			buffer.setLength(0);
		}
		return ret;
	}
	
	//1325
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (removeLeafNodesHelper(root, target) && root.val == target) {
			return null;
		}
		return root;
	}
	
	private boolean removeLeafNodesHelper(TreeNode root, int target) {
		if (root == null) {
			return true;
		}
		boolean left = removeLeafNodesHelper(root.left, target);
		boolean right = removeLeafNodesHelper(root.right, target);
		if (left && right) {
			root.left = null;
			root.right = null;
			return root.val == target;
		} else if (left) {
			root.left = null;
		} else if (right) {
			root.right = null;
		}
		return false;
	}
	
	//1323
	public int maximum69Number(int num) {
		int t = num, mod = 1000;
		while (t > 0) {
			if (t / mod == 6) {
				return num + 3 * mod;
			}
			t %= mod;
			mod /= 10;
		}
		return num;
		
	}
	
	//928
	public int minMalwareSpread(int[][] graph, int[] initial) {
		TreeSet<Integer> set = new TreeSet<>();
		int len = graph.length;
		List<Integer>[] lists = new List[len];
		for (int i = 0; i < len; i++) {
			lists[i] = new ArrayList();
		}
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (graph[i][j] > 0) {
					lists[i].add(j);
					lists[j].add(i);
				}
			}
		}
		HashSet<Integer> cur = new HashSet<>(), next;
		for (int i : initial) {
			cur.add(i);
		}
		while (!cur.isEmpty()) {
			set.addAll(cur);
			next = new HashSet<>();
			for (Integer x : cur) {
				for (Integer n : lists[x]) {
					if (!set.contains(n)) {
						next.add(n);
					}
				}
			}
			if (next.size() == 1) {
				for (Integer integer : next) {
					int ret = -1;
					for (Integer a : lists[integer]) {
						if (set.contains(a)) {
							if (ret >= 0) {
								return integer;
							}
							ret = a;
						}
					}
					return ret;
				}
			}
			cur = next;
		}
		for (Integer integer : set) {
			return integer;
		}
		return 0;
	}
	
	int minCameraCover = 0;
	
	//968
	public int minCameraCover(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (minCameraCoverHelper(root) == 2) {
			return minCameraCover + 1;
		}
		return minCameraCover;
	}
	
	private int minCameraCoverHelper(TreeNode root) {
		if (root == null) {
			return 1;
		}
		int left = minCameraCoverHelper(root.left);
		int right = minCameraCoverHelper(root.right);
		if (left == 2 || right == 2) {//要求当前节点必须加
			minCameraCover++;
			return 0;
		} else if (left == 0 || right == 0) {//当前节点可被监控,不需要
			return 1;
		} else {//当前节点不加,但是要求父节点加
			return 2;
		}
	}
	
	//996
	public int numSquarefulPerms(int[] A) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		HashMap<Integer, Integer> count = new HashMap<>();
		int len = A.length;
		for (int i : A) {
			count.put(i, count.getOrDefault(i, 0) + 1);
			map.put(i, new HashSet<>());
		}
		for (int i = 0; i < len; i++) {
			Set<Integer> list = map.get(A[i]);
			for (int j = i + 1; j < len; j++) {
				if (list.contains(A[j])) {
					continue;
				}
				if (checkSquare(A[i] + A[j])) {
					list.add(A[j]);
					map.get(A[j]).add(A[i]);
				}
			}
			if (list.size() == 0) {
				return 0;
			}
		}
		int ret = 0;
		for (Entry<Integer, Set<Integer>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Set<Integer> value = entry.getValue();
			count.put(key, count.get(key) - 1);
			for (Integer x : value) {
				if (count.get(x) > 0) {
					ret += numSquarefulPermsHelper(map, count, x, len - 1);
				}
			}
			count.put(key, count.get(key) + 1);
			
		}
		return ret;
	}
	
	private int numSquarefulPermsHelper(HashMap<Integer, Set<Integer>> map,
		HashMap<Integer, Integer> count, int x, int len) {
		if (len == 1) {
			return 1;
		}
		int ret = 0;
		Set<Integer> set = map.get(x);
		count.put(x, count.get(x) - 1);
		for (Integer next : set) {
			if (count.get(next) > 0) {
				ret += numSquarefulPermsHelper(map, count, next, len - 1);
			}
		}
		count.put(x, count.get(x) + 1);
		return ret;
	}
	
	private boolean checkSquare(int i) {
		int l = 0, r = i, m;
		while (l <= r) {
			m = (l + r) >> 1;
			if (m * m > i) {
				r = m - 1;
			} else if (m * m < i) {
				l = m + 1;
			} else {
				return true;
			}
		}
		return false;
	}
	
	//1163
	public String lastSubstring(String s) {
		int begin = 0;
		int len = s.length();
		for (int i = 1; i < len; i++) {
			int j = 0;
			for (j = 0; j + i < len; j++) {
				if (s.charAt(i + j) > s.charAt(begin + j)) {
					begin = i;
					break;
				} else if (s.charAt(i + j) < s.charAt(begin + j)) {
					break;
				}
			}
			if (j + i == len) {
				return s.substring(begin);
			}
		}
		return s.substring(begin);
	}
	
	//1106
	char[] exp;
	
	public boolean parseBoolExpr(String expression) {
		exp = expression.toCharArray();
		return parseBoolExprHelper(0, exp.length - 1);
	}
	
	private boolean parseBoolExprHelper(int l, int r) {
		if (exp[l] == '(') {
			l++;
			r--;
		}
		char c = exp[l];
		if (l == r) {
			return c == 't';
		}
		if (c == '!') {
			l++;
			return !parseBoolExprHelper(l, r);
		}
		int count = 0;
		int i = l + 2;
		boolean ret = true;
		if (c == '&') {
			l += 2;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				} else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret && parseBoolExprHelper(l, i - 1);
					if (!ret) {
						break;
					}
					l = i + 1;
				} else if (exp[i] == ')') {
					count--;
				}
				i++;
				
			}
		} else {
			l += 2;
			ret = false;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				} else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret || parseBoolExprHelper(l, i - 1);
					if (ret) {
						break;
					}
					l = i + 1;
				} else if (exp[i] == ')') {
					count--;
				}
				i++;
			}
		}
		return ret;
	}
	
	
}
