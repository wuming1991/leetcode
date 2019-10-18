package com.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test9
 * @Author: WuMing
 * @CreateDate: 2019/8/23 18:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test9 {
	
	public static void main(String[] args) {
		Test9 test = new Test9();
		
		/*TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.right.right = new TreeNode(7);
		test.widthOfBinaryTree(root);*/
		test.removeComments(
			new String[]{
				"/*Test program */",
				"int main()",
				"{ ",
				"// variable declaration ",
				"int a, b, c;",
				"/* This is a test",
				"   multiline  ",
				"   comment for ",
				"   testing */",
				"a = b + c;",
				"}"});
	}
	
	public List<String> removeComments(String[] source) {
		ArrayList<String> ret = new ArrayList<>();
		int len = source.length;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char[] array = source[i].toCharArray();
			for (int j = 0; j < array.length; j++) {
				if (array[j] == '/') {
					if (j < array.length - 1 && array[j + 1] == '/') {
						break;
					} else if (j < array.length - 1 && array[j + 1] == '*') {
						j += 2;
						while (true) {
							while (j >= array.length-1) {
								j = 0;
								i++;
								array = source[i].toCharArray();
							}
							if (array[j] == '*') {
								if (array[j + 1] == '/') {
									j++;
									break;
								}
							}
							j++;
						}
						continue;
					}
				}
				buffer.append(array[j]);
			}
			if (buffer.length() > 0) {
				ret.add(buffer.toString());
				buffer.setLength(0);
			}
		}
		return ret;
	}
	
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		HashMap<String, List<Integer>> map = new HashMap<>();
		int len = accounts.size();
		for (int i = 0; i < len; i++) {
			List<String> list = accounts.get(i);
			for (int j = 1; j < list.size(); j++) {
				String s = list.get(j);
				if (map.containsKey(s)) {
					map.get(s).add(i);
				} else {
					ArrayList<Integer> l = new ArrayList<>();
					l.add(i);
					map.put(s, l);
				}
			}
		}
		boolean[] visit = new boolean[len];
		ArrayList<List<String>> ret = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			SortedSet<String> set = new TreeSet<>();
			List<String> list = accounts.get(i);
			accountsMergeHelper(set, list, map, visit, accounts);
			ArrayList<String> r = new ArrayList<>();
			r.add(list.get(0));
			r.addAll(set);
			ret.add(r);
		}
		return ret;
	}
	
	private void accountsMergeHelper(SortedSet<String> set, List<String> list,
		HashMap<String, List<Integer>> map, boolean[] visit, List<List<String>> accounts) {
		for (int i = 1; i < list.size(); i++) {
			String email = list.get(i);
			if (set.add(email)) {
				List<Integer> num = map.get(email);
				for (Integer cur : num) {
					if (!visit[cur]) {
						visit[cur] = true;
						List<String> account = accounts.get(cur);
						accountsMergeHelper(set, account, map, visit, accounts);
					}
				}
			}
		}
	}
	
	public int numSubarrayProductLessThanK1(int[] nums, int k) {
		if (k <= 1) {
			return 0;
		}
		int cur = 1, ret = 0, l = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			cur *= nums[i];
			while (cur >= k) {
				cur /= nums[l];
				l++;
			}
			ret += i - l + 1;
		}
		return ret;
	}
	
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k == 0) {
			return 0;
		}
		int cur = 1, count = 0;
		int ret = 0;
		int len = nums.length;
		int idx = 0;
		for (int i = 0; i < len; i++) {
			while (idx < len && cur * nums[idx] < k) {
				count++;
				cur *= nums[idx];
				idx++;
			}
			if (count > 0) {
				ret += count;
				count--;
				cur /= nums[i];
			}
			if (i == idx) {
				idx++;
			}
		}
		return ret;
	}
	
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0, max = Integer.MIN_VALUE;
		for (int num : nums) {
			sum += num;
			if (num > max) {
				max = num;
			}
		}
		if (sum % k != 0 || sum / k < max) {
			return false;
		}
		int target = sum / k;
		int len = nums.length;
		return canPartitionKSubsetsHelper(nums, new boolean[len], 0, 0, len, target, k);
	}
	
	private boolean canPartitionKSubsetsHelper(int[] nums, boolean[] visit, int cur, int begin,
		int len,
		int target,
		int k) {
		if (cur == target) {
			if (k < 2) {
				return true;
			} else {
				return canPartitionKSubsetsHelper(nums, visit, 0, 0, len, target, k - 1);
			}
		}
		for (int i = begin; i < len; i++) {
			if (!visit[i] && cur + nums[i] <= target) {
				visit[i] = true;
				if (canPartitionKSubsetsHelper(nums, visit, cur + nums[i], i + 1, len, target, k)) {
					return true;
				}
				visit[i] = false;
			}
		}
		return false;
	}
	
	public double knightProbability(int N, int K, int r, int c) {
		if (K == 0) {
			return 1.0;
		}
		if (N < 3) {
			return 0;
		}
		double[][] ret = new double[N][N];
		double[][] next;
		int[][] d = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
		
		double t = 1.0;
		for (double[] doubles : ret) {
			Arrays.fill(doubles, t);
		}
		for (int i = 0; i < K; i++) {
			next = new double[N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					t = 0;
					for (int[] ints : d) {
						int y = j + ints[0];
						int x = k + ints[1];
						if (x < 0 || y < 0 || x >= N || y >= N) {
							continue;
						} else {
							t += ret[y][x];
						}
					}
					next[j][k] = t / 8;
				}
			}
			ret = next;
		}
		return ret[r][c];
	}
	
	
	public int findNumberOfLIS(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return len;
		}
		int[][] count = new int[len][2];
		int l, c, cur;
		count[0][0] = 1;
		count[0][1] = 1;
		for (int i = 1; i < len; i++) {
			l = 0;
			c = 1;
			cur = nums[i];
			for (int j = i - 1; j >= 0; j--) {
				
				if (cur > nums[j]) {
					
					if (count[j][0] > l) {
						l = count[j][0];
						c = count[j][1];
					} else if (count[j][0] == l) {
						c += count[j][1];
					}
				}
			}
			count[i][0] = l + 1;
			count[i][1] = c;
		}
		l = 0;
		c = 1;
		for (int[] ints : count) {
			if (ints[0] > l) {
				l = ints[0];
				c = ints[1];
			} else if (ints[0] == l) {
				c += ints[1];
			}
		}
		return c;
	}
	
	public boolean checkValidString1(String s) {
		char[] array = s.toCharArray();
		int count = 0;
		int xcount = 0;
		int lcount = 0;
		for (char c : array) {
			if (c == '*') {
				xcount++;
			} else if (c == '(') {
				count++;
			} else {
				count--;
			}
			if (count < 0) {
				if (xcount > 0) {
					xcount--;
					count++;
					lcount++;
				} else {
					return false;
				}
			}
		}
		if (count == 0) {
			return true;
		} else if (count > xcount) {
			return false;
		}
		int i = array.length - 1;
		while (count > 0) {
			if (array[i] == '*') {
				array[i] = ')';
				count--;
			}
			i--;
		}
		count = 0;
		for (char c : array) {
			if (c == '*' && lcount > 0) {
				count++;
				lcount--;
			} else if (c == '(') {
				count++;
			} else if (c == ')') {
				count--;
				if (count < 0) {
					return false;
				}
			}
		}
		return count == 0;
	}
	
	public boolean checkValidString(String s) {
		char[] array = s.toCharArray();
		int len = array.length;
		return checkValidStringHelper(array, 0, 0, len);
	}
	
	private boolean checkValidStringHelper(char[] array, int count, int i, int len) {
		if (count < 0) {
			return false;
		}
		while (i < len && array[i] != '*') {
			if (array[i] == '(') {
				count++;
			} else {
				count--;
				if (count < 0) {
					return false;
				}
			}
			i++;
		}
		if (i < len) {
			return checkValidStringHelper(array, count - 1, i + 1, len)
				|| checkValidStringHelper(array, count + 1, i + 1, len)
				|| checkValidStringHelper(array, count, i + 1, len);
		}
		return count == 0;
	}
	
	public int maximumSwap(int num) {
		if (num < 12) {
			return num;
		}
		char[] array = (num + "").toCharArray();
		int len = array.length;
		for (int i = 0; i < len; i++) {
			char c = array[i];
			int cur = i;
			for (int j = len - 1; j > i; j--) {
				if (array[j] > array[cur]) {
					cur = j;
				}
			}
			if (cur != i) {
				array[i] = array[cur];
				array[cur] = c;
				return Integer.valueOf(String.valueOf(array));
			}
		}
		return num;
	}
	
	public boolean isPossible(int[] nums) {
		int len = nums.length;
		if (len < 1) {
			return false;
		}
		int base = nums[0];
		int[] ints = new int[nums[len - 1] - base + 2];
		for (int num : nums) {
			ints[num - base]++;
		}
		int length = ints.length;
		for (int i = 1; i < length; i++) {
			if (ints[i] >= ints[i - 1]) {
				continue;
			} else {
				int x = ints[i - 1] - ints[i];
				if (i < 3 || ints[i - 3] < x) {
					return false;
				} else {
					for (int j = 1; j < 3; j++) {
						ints[i - j] -= x;
					}
				}
			}
		}
		return true;
	}
	
	public String predictPartyVictory2(String senate) {
		return predictPartyVictoryHelper(senate, 0, 0);
	}
	
	int widthOfBinaryTree = 0;
	
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		widthOfBinaryTreeHelper(root, 0, 0, map);
		return widthOfBinaryTree + 1;
	}
	
	private void widthOfBinaryTreeHelper(TreeNode root, int level, int count,
		HashMap<Integer, Integer> map) {
		if (!map.containsKey(level)) {
			map.put(level, count);
		} else {
			widthOfBinaryTree = Math.max(widthOfBinaryTree, count - map.get(level));
		}
		if (root.left != null) {
			widthOfBinaryTreeHelper(root.left, level + 1, count * 2, map);
		}
		if (root.right != null) {
			widthOfBinaryTreeHelper(root.right, level + 1, count * 2 + 1, map);
		}
	}
	
	public List<Integer> findClosestElements1(int[] arr, int k, int x) {
		int len = arr.length;
		int l = 0, r = len - k, m;
		while (l < r) {
			m = (l + r) / 2;
			int lv = x - arr[m];
			int rv = arr[m + k] - x;
			if (lv <= rv) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		int lv = x - arr[l];
		int rv = l + k < len ? arr[l + k] - x : Integer.MAX_VALUE;
		if (lv > rv) {
			l++;
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = l; i < l + k; i++) {
			list.add(arr[i]);
		}
		return list;
	}
	
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int l = 0, r = arr.length - 1, m;
		while (l < r) {
			m = (l + r) / 2;
			if (arr[m] > x) {
				r = m - 1;
			} else if (arr[m] < x) {
				l = m + 1;
			} else {
				l = m;
				break;
			}
		}
		if (arr[l] == x) {
			k--;
			r = l + 1;
			l--;
		} else if (arr[l] > x) {
			r = l;
			l--;
		} else {
			r = l + 1;
		}
		while (k > 0) {
			int rv = r == arr.length ? Integer.MAX_VALUE : arr[r] - x;
			int lv = l < 0 ? Integer.MAX_VALUE : x - arr[l];
			if (rv < lv) {
				r++;
				k--;
			} else {
				l--;
				k--;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = l + 1; i < r; i++) {
			list.add(arr[i]);
		}
		return list;
	}
	
	
	private String predictPartyVictoryHelper(String senate, int R, int D) {
		StringBuffer buffer = new StringBuffer();
		int rc = 0, rd = 0;
		for (char c : senate.toCharArray()) {
			if (c == 'R') {
				if (D > 0) {
					D--;
				} else {
					buffer.append(c);
					R++;
					rc++;
				}
			} else {
				if (R > 0) {
					R--;
				} else {
					D++;
					buffer.append(c);
					rd++;
				}
			}
		}
		if (rc == buffer.length()) {
			return "Radiant";
		} else if (rd == buffer.length()) {
			return "Dire";
		} else {
			return predictPartyVictoryHelper(buffer.toString(), R, D);
		}
	}
	
	class LinkNode {
		
		char c;
		LinkNode next;
		
		public LinkNode(char c) {
			this.c = c;
		}
	}
	
	public String predictPartyVictory1(String senate) {
		char[] array = senate.toCharArray();
		LinkNode head = new LinkNode(array[0]);
		LinkNode cur = head;
		int len = array.length;
		for (int i = 1; i < len; i++) {
			cur.next = new LinkNode(array[i]);
			cur = cur.next;
		}
		cur.next = head;
		cur = head;
		LinkNode next;
		while (true) {
			LinkNode before = cur;
			next = cur.next;
			while (cur != next && cur.c == next.c) {
				before = next;
				next = next.next;
			}
			if (cur == next) {
				return cur.c == 'R' ? "Radiant" : "Dire";
			}
			before.next = before.next.next;
			cur = cur.next;
		}
	}
	
	public String predictPartyVictory(String senate) {
		int cur = 0;
		int next = 1;
		char[] array = senate.toCharArray();
		int len = array.length;
		while (true) {
			char a = array[cur];
			next = cur + 1;
			if (next == len) {
				next = 0;
			}
			while (array[next] == a || array[next] == 'X') {
				next++;
				if (next == len) {
					next = 0;
				}
				if (next == cur) {
					return array[cur] == 'R' ? "Radiant" : "Dire";
				}
			}
			array[next] = 'X';
			cur++;
			if (cur == len) {
				cur = 0;
			}
			while (array[cur] == 'X') {
				cur++;
				if (cur == len) {
					cur = 0;
				}
			}
		}
	}
	
	public String solveEquation(String equation) {
		char[] array = equation.toCharArray();
		int xsum = 0;
		int sum = 0;
		int base = 1;
		int len = array.length;
		for (int i = 0; i < len; ) {
			if (array[i] == '=') {
				base = -1;
				i++;
			}
			int cur = 1;
			if (array[i] == '-') {
				cur *= -1;
				i++;
			} else if (array[i] == '+') {
				i++;
			}
			int num = 0;
			while (i < len && array[i] >= '0' && array[i] <= '9') {
				num = num * 10 + array[i] - '0';
				i++;
			}
			if (i < len) {
				if (array[i] == 'x') {
					if (num == 0) {
						if (i == 0 || array[i - 1] == '-' || array[i - 1] == '+'
							|| array[i - 1] == '=') {
							num = 1;
						}
					}
					xsum += cur * base * num;
					i++;
				} else {
					sum += cur * (-base) * num;
				}
			} else {
				sum += cur * (-base) * num;
			}
			
		}
		if (sum == 0 && xsum == 0) {
			return "Infinite solutions";
		} else if (xsum == 0) {
			return "No solution";
		} else {
			return "x=" + sum / xsum;
		}
	}
	
	public int findPaths(int m, int n, int N, int i, int j) {
		int[][] base = new int[m][n];
		int[][] next;
		int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		int mod = 1000000007;
		for (; N > 0; N--) {
			next = new int[m][n];
			for (int y = 0; y < m; y++) {
				for (int x = 0; x < n; x++) {
					for (int[] d : ds) {
						int ny = y + d[0];
						int nx = x + d[1];
						if (ny < 0 || nx < 0 || ny > m || nx > n) {
							next[y][x] += 1;
						} else {
							next[y][x] = (next[y][x] + base[ny][nx]) % mod;
						}
					}
				}
			}
			base = next;
		}
		return base[i][j];
	}
	
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		
		int l12 = validSquareHelper(p1, p2);
		int l13 = validSquareHelper(p1, p3);
		int l14 = validSquareHelper(p1, p4);
		if (l12 == 0 || l13 == 0 || l14 == 0) {
			return false;
		}
		if (l12 == l13 && l12 + l13 == l14) {
			int l24 = validSquareHelper(p2, p4);
			if (l12 == l24) {
				int l34 = validSquareHelper(p3, p4);
				if (l34 == l24) {
					int l23 = validSquareHelper(p2, p3);
					return l23 == l14;
					
				}
			}
		} else if (l12 == l14 && l12 + l14 == l13) {
			int l23 = validSquareHelper(p2, p3);
			if (l12 == l23) {
				int l34 = validSquareHelper(p4, p3);
				if (l23 == l34) {
					int l24 = validSquareHelper(p2, p4);
					return l24 == l13;
				}
			}
		} else if (l13 == l14 && l13 + l14 == l12) {
			int l23 = validSquareHelper(p2, p3);
			if (l23 == l13) {
				int l24 = validSquareHelper(p2, p4);
				if (l23 == l24) {
					int l34 = validSquareHelper(p3, p4);
					return l34 == l12;
				}
			}
		}
		return false;
	}
	
	private int validSquareHelper(int[] p1, int[] p2) {
		return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
	}
	
	public boolean checkInclusion(String s1, String s2) {
		int len2 = s2.length();
		int len1 = s1.length();
		if (len1 > len2) {
			return false;
		}
		int[] s1c = new int[26];
		int[] s2c = new int[26];
		for (char c : s1.toCharArray()) {
			s1c[c - 'a']++;
		}
		int i = 0;
		char[] array = s2.toCharArray();
		for (; i < len1; i++) {
			s2c[array[i] - 'a']++;
		}
		
		for (; i < len2; i++) {
			int j = 0;
			for (; j < 26; j++) {
				if (s1c[j] != s2c[j]) {
					break;
				}
			}
			if (j == 26) {
				return true;
			} else {
				s2c[array[i] - 'a']++;
				s2c[array[i - len1] - 'a']--;
			}
		}
		int j = 0;
		for (; j < 26; j++) {
			if (s1c[j] != s2c[j]) {
				break;
			}
		}
		return j == 26;
	}
	
	public int nextGreaterElement(int n) {
		char[] array = String.valueOf(n).toCharArray();
		int l = array.length - 2;
		int r = array.length - 1;
		while (l >= 0) {
			if (array[l] >= array[l + 1]) {
				l--;
			} else {
				break;
			}
		}
		if (l < 0) {
			return -1;
		}
		while (r > l) {
			if (array[r] <= array[l]) {
				r--;
			} else {
				break;
			}
		}
		char t = array[l];
		array[l] = array[r];
		array[r] = t;
		Arrays.sort(array, l + 1, array.length);
		try {
			return Integer.valueOf(String.valueOf(array));
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int[][] updateMatrix(int[][] matrix) {
		int h = matrix.length;
		int l = matrix[0].length;
		int[][] ret = new int[h][l];
		int base = 100000;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < l; j++) {
				if (matrix[i][j] == 0) {
					list.add(i * base + j);
				} else {
					ret[i][j] = -1;
				}
			}
		}
		ArrayList<Integer> next;
		int cur = 1;
		while (list.size() > 0) {
			next = new ArrayList<>();
			for (Integer i : list) {
				updateMatrixHelper(i, base, matrix, ret, next, cur);
			}
			list = next;
			cur++;
		}
		return ret;
	}
	
	private void updateMatrixHelper(Integer x, int base, int[][] matrix, int[][] ret,
		ArrayList<Integer> next, int cur) {
		int i = x / base;
		int j = x % base;
		if (i > 0) {
			if (ret[i - 1][j] == -1 && matrix[i - 1][j] == 1) {
				ret[i - 1][j] = cur;
				next.add((i - 1) * base + j);
			}
		}
		if (i < ret.length - 1) {
			if (ret[i + 1][j] == -1 && matrix[i + 1][j] == 1) {
				ret[i + 1][j] = cur;
				next.add((i + 1) * base + j);
			}
		}
		if (j > 0) {
			if (ret[i][j - 1] == -1 && matrix[i][j - 1] == 1) {
				ret[i][j - 1] = cur;
				next.add(i * base + j - 1);
			}
		}
		if (j < matrix[0].length - 1) {
			if (ret[i][j + 1] == -1 && matrix[i][j + 1] == 1) {
				ret[i][j + 1] = cur;
				next.add(i * base + j + 1);
			}
		}
	}
	
	public boolean checkSubarraySum1(int[] nums, int k) {
		int len = nums.length;
		if (k == 0) {
			for (int i = 0; i < len - 1; i++) {
				if (nums[i] == 0 && nums[i + 1] == 0) {
					return true;
				}
			}
			return false;
		}
		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			sum %= k;
			Integer x = map.get(sum);
			if (x != null) {
				if (i - x > 1) {
					return true;
				}
			} else {
				map.put(sum, i);
			}
		}
		return false;
	}
	
	public boolean checkSubarraySum(int[] nums, int k) {
		int len = nums.length, t;
		if (k == 0) {
			for (int i = 0; i < len - 1; i++) {
				if (nums[i] == 0 && nums[i + 1] == 0) {
					return true;
				}
			}
			return false;
		}
		for (int i = 1; i < len; i++) {
			t = nums[i] + nums[i - 1];
			if (t % k == 0) {
				return true;
			}
			nums[i] = t;
		}
		for (int i = 0; i < len; i++) {
			for (int j = i + 2; j < len; j++) {
				if ((nums[j] - nums[i]) % k == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean makesquare(int[] nums) {
		int sum = 0, max = 0;
		for (int num : nums) {
			sum += num;
			max = Math.max(max, num);
		}
		if ((sum & 3) > 0) {
			return false;
		}
		int len = sum >> 2;
		if (max > len) {
			return false;
		}
		int[] ints = new int[nums.length];
		ints[0] = 2;
		return makesquareHelper(nums, ints, nums[0], 0, 2, 1, 2 * len);
		
	}
	
	
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] ints = new int[m + 1][n + 1];
		int o, z;
		for (int i = 0; i < strs.length; i++) {
			o = 0;
			z = 0;
			char[] array = strs[i].toCharArray();
			for (char c : array) {
				if (c == '0') {
					z++;
				} else {
					o++;
				}
			}
			for (int j = m; j >= z; j--) {
				for (int k = n; k >= 0; k--) {
					ints[j][k] = Math.max(ints[j][k], ints[j - z][k - o] + 1);
				}
			}
		}
		return ints[m][n];
	}
	
	
	private boolean makesquareHelper(int[] nums, int[] ints, int sum, int s, int t, int begin,
		int target) {
		if (sum == target) {
			if (t == 2) {
				ints[0] = t + 1;
				if (makesquareHelper(nums, ints, nums[0], t, t + 1, 1, target / 2)) {
					int k = 0;
					while (ints[k] != s) {
						k++;
					}
					ints[k] = s + 1;
					if (makesquareHelper(nums, ints, nums[k], s, s + 1, k + 1, target / 2)) {
						return true;
					}
					ints[k] = s;
					for (int i = 0; i < ints.length; i++) {
						if (ints[i] == t + 1) {
							ints[i] = t;
						}
					}
					return false;
				}
				ints[0] = t;
			} else {
				return true;
			}
		} else if (sum > target) {
			return false;
		}
		for (int i = begin; i < nums.length; i++) {
			if (ints[i] != s) {
				continue;
			}
			ints[i] = t;
			
			if (makesquareHelper(nums, ints, sum + nums[i], s, t, i + 1, target)) {
				return true;
			}
			
			ints[i] = s;
		}
		return false;
	}
	
	class Node {
		
		public int val;
		public List<Node> neighbors;
		
		public Node() {
		}
		
		public Node(int _val, List<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
	
	Map<Node, Node> map = new HashMap();
	
	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}
		if (map.containsKey(node)) {
			return map.get(node);
		}
		Node ret = new Node();
		map.put(node, ret);
		ret.val = node.val;
		ret.neighbors = cloneGraphHelper(node.neighbors);
		return ret;
	}
	
	private List<Node> cloneGraphHelper(List<Node> neighbors) {
		ArrayList<Node> ret = new ArrayList<>();
		for (Node neighbor : neighbors) {
			ret.add(cloneGraph(neighbor));
		}
		return ret;
	}
}
