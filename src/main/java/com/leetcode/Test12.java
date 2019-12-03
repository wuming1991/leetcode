package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test12
 * @Author: WuMing
 * @CreateDate: 2019/11/5 18:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test12 {
	
	public static void main(String[] args) {
		Test12 test = new Test12();
		/*test.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}});
		test.shortestAlternatingPaths(5, new int[][]{{3,2},{4,1},{1,4},{2,4}},
			new int[][]{{2,3},{0,4},{4,3},{4,4},{4,0},{1,0}});*/
		/*TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		root.left.left = new TreeNode(4);
		root.left.left.right = new TreeNode(9);
		root.left.right = new TreeNode(5);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(11);
		root.left.left.left = new TreeNode(8);
		test.btreeGameWinningMove(root, 11, 3);*/
		List<String> list = Arrays.asList("un", "iq", "ue");
		char c = 2660;
		System.out.println(c);
		System.out.println(test.isGoodArrayHelper(6, 14));
		
		
	}
	
	
	
	public String multiply(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		if (len1 < len2) {
			return multiply(num2, num1);
		}
		char[] a = num1.toCharArray();
		char[] b = num2.toCharArray();
		int[] ret = new int[len1 + len2];
		int t;
		for (int i = len2 - 1; i >= 0; i--) {
			t = 0;
			if (b[i] == '0') {
				continue;
			}
			for (int j = len1 - 1; j >= 0; j--) {
				t = t + (a[j] - '0') * (b[i] - '0') + ret[i + j + 1];
				ret[i + j + 1] = t % 10;
				t /= 10;
			}
			ret[i] = t;
		}
		int i = 0;
		while (ret[i] == 0) {
			i++;
		}
		StringBuffer buffer = new StringBuffer();
		while (i < len1 + len2) {
			buffer.append(ret[i]);
			i++;
		}
		return buffer.toString();
	}
	
	public int swimInWater1(int[][] grid) {
		int len = grid.length;
		int l = Math.max(grid[len - 1][len - 1], grid[0][0]), r = len * len, m;
		int[][] ds = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
		while (l < r) {
			m = (l + r) >> 1;
			if (swimInWater1Helper(grid, new boolean[len][len], 0, 0, m, ds)) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	private boolean swimInWater1Helper(int[][] grid, boolean[][] visited, int i, int j, int m,
		int[][] ds) {
		int len = grid.length;
		if (i < 0 || j < 0 || i >= len || j >= len || grid[i][j] > m || visited[i][j]) {
			return false;
		}
		visited[i][j] = true;
		if (i == len - 1 && j == len - 1) {
			return true;
		}
		for (int[] d : ds) {
			if (swimInWater1Helper(grid, visited, i + d[0], j + d[1], m, ds)) {
				return true;
			}
		}
		return false;
	}
	
	public int swimInWater(int[][] grid) {
		int len = grid.length;
		int l = Math.max(grid[len - 1][len - 1], grid[0][0]), r = len * len, m;
		int[][] ds = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
		while (l < r) {
			m = (l + r) >> 1;
			if (swimInWaterHelper(grid, m, ds)) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	private boolean swimInWaterHelper(int[][] grid, int m, int[][] ds) {
		int len = grid.length;
		int[] mem = new int[len * len * 2];
		int idx = 2, bef = 0;
		int x, y;
		boolean[][] visited = new boolean[len][len];
		visited[0][0] = true;
		for (; bef < idx; bef += 2) {
			for (int[] d : ds) {
				x = mem[bef] + d[0];
				y = mem[bef + 1] + d[1];
				if (x < 0 || y < 0 || x >= len || y >= len || grid[x][y] > m || visited[x][y]) {
					continue;
				}
				if (x == len - 1 && y == len - 1) {
					return true;
				}
				visited[x][y] = true;
				mem[idx++] = x;
				mem[idx++] = y;
			}
		}
		return false;
	}
	
	
	public boolean isGoodArray1(int[] nums) {
		int x = nums[0], y;
		for (int num : nums) {
			while (num > 0) {
				y = x % num;
				x = num;
				num = y;
			}
			if (x == 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isGoodArray(int[] nums) {
		int cur = nums[0];
		for (int num : nums) {
			cur = isGoodArrayHelper(num, cur);
			if (cur == 1) {
				return true;
			}
		}
		return false;
	}
	
	private int isGoodArrayHelper(int cur, int num) {
		int t;
		while (num != 0) {
			t = cur % num;
			cur = num;
			num = t;
		}
		return cur;
	}
	
	
	public List<Integer> postorderTraversal1(TreeNode root) {
		LinkedList<Integer> list = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode cur;
		while (!stack.isEmpty()) {
			cur = stack.pop();
			list.addFirst(cur.val);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		return list;
	}
	
	public List<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<>();
		postorderTraversalHelper(root, ret);
		return ret;
	}
	
	private void postorderTraversalHelper(TreeNode root, ArrayList<Integer> ret) {
		if (root == null) {
			return;
		}
		postorderTraversalHelper(root.left, ret);
		postorderTraversalHelper(root.right, ret);
		ret.add(root.val);
	}
	
	public int countVowelPermutation(int n) {
		long[][] count = new long[n][5];
		for (int i = 0; i < 5; i++) {
			count[0][i] = 1;
		}
		int mod = 1000000007;
		for (int i = 1; i < n; i++) {
			count[i][0] = (count[i - 1][1] + count[i - 1][2] + count[i - 1][4]) % mod;
			count[i][1] = (count[i - 1][0] + count[i - 1][2]) % mod;
			count[i][2] = (count[i - 1][1] + count[i - 1][3]) % mod;
			count[i][3] = count[i - 1][2];
			count[i][4] = (count[i - 1][2] + count[i - 1][3]) % mod;
		}
		int ret = 0;
		for (int i = 0; i < 5; i++) {
			ret = (ret + (int) count[n - 1][i]) % mod;
		}
		return ret;
	}
	
	int rotatedDigits = 0;
	
	public int rotatedDigits(int N) {
		int[] ds = {0, 1, 2, 5, 6, 8, 9};
		int len = 1;
		int cur = N;
		while (cur > 9) {
			len++;
			cur /= 10;
		}
		boolean[] booleans = new boolean[10];
		booleans[2] = true;
		booleans[5] = true;
		booleans[6] = true;
		booleans[9] = true;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < 7; j++) {
				rotatedDigitsHelper(ds, N, ds[j], i, booleans);
			}
		}
		return rotatedDigits;
	}
	
	private boolean rotatedDigitsHelper(int[] ds, int N, int cur, int len, boolean[] check) {
		if (cur > N) {
			return false;
		}
		if (len == 0) {
			while (cur > 0) {
				if (check[cur % 10]) {
					rotatedDigits++;
					break;
				}
				cur /= 10;
			}
			return true;
		}
		boolean flag;
		for (int d : ds) {
			flag = rotatedDigitsHelper(ds, N, cur * 10 + d, len - 1, check);
			if (!flag) {
				return false;
			}
		}
		return true;
	}
	
	public int numSubmatrixSumTarget1(int[][] matrix, int target) {
		int hi = matrix.length;
		int len = matrix[0].length;
		int ret = 0;
		int[][] sum = new int[hi][len + 1];
		for (int j = 0; j < len; j++) {
			for (int i = 0; i < hi; i++) {
				sum[i][j + 1] = sum[i][j] + matrix[i][j];
			}
			ret += numSubmatrixSumTarget1Helper(sum, j + 1, target);
		}
		return ret;
	}
	
	private int numSubmatrixSumTarget1Helper(int[][] sum, int x, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int hi = sum.length, cur, ret = 0;
		for (int i = 0; i < x; i++) {
			map.put(0, 1);
			cur = 0;
			for (int j = 0; j < hi; j++) {
				cur += sum[j][x] - sum[j][i];
				ret += map.getOrDefault(cur - target, 0);
				map.put(cur, map.getOrDefault(cur, 0) + 1);
			}
			map.clear();
		}
		return ret;
	}
	
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int hi = matrix.length;
		int len = matrix[0].length;
		//	int total;
		int ret = 0;
		int[][] sum = new int[hi + 1][len + 1];
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
				ret += numSubmatrixSumTargetHelper(sum, i + 1, j + 1, target);
			}
		}
		return ret;
	}
	
	private int numSubmatrixSumTargetHelper(int[][] sum, int x, int y, int target) {
		int base = sum[x][y], ret = 0;
		for (int i = 1; i <= x; i++) {
			for (int j = 1; j <= y; j++) {
				if (base - sum[x][j - 1] - sum[i - 1][y] + sum[i - 1][j - 1] == target) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	
	int index = 0;
	
	public TreeNode recoverFromPreorder(String S) {
		TreeNode ret = new TreeNode(0);
		recoverFromPreorderHelper(ret, 0, S);
		return ret.left;
	}
	
	private int recoverFromPreorderHelper(TreeNode ret, int level, String s) {
		int len = s.length();
		if (index == len) {
			return -1;
		}
		int l = 0;
		while (s.charAt(index) == '-') {
			l++;
			index++;
		}
		if (l == level) {
			int val = 0;
			while (index < len && s.charAt(index) != '-') {
				val *= 10;
				val += s.charAt(index) - '0';
				index++;
			}
			
			ret.left = new TreeNode(val);
			l = recoverFromPreorderHelper(ret.left, level + 1, s);
			if (l == level) {
				val = 0;
				while (index < len && s.charAt(index) != '-') {
					val *= 10;
					val += s.charAt(index) - '0';
					index++;
				}
				ret.right = new TreeNode(val);
				l = recoverFromPreorderHelper(ret.right, level + 1, s);
			}
		}
		return l;
	}
	
	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		int[] count = new int[26];
		for (char c : letters) {
			count[c - 'a']++;
		}
		return maxScoreWordsHelper(count, 0, 0, words, score);
	}
	
	private int maxScoreWordsHelper(int[] count, int sum, int idx, String[] words, int[] score) {
		if (idx == words.length) {
			return sum;
		}
		char[] array = words[idx].toCharArray();
		int[] clone = count.clone();
		int tsum = 0;
		for (char c : array) {
			clone[c - 'a']--;
			if (clone[c - 'a'] < 0) {
				return maxScoreWordsHelper(count, sum, idx + 1, words, score);
			}
			tsum += score[c - 'a'];
		}
		int x = maxScoreWordsHelper(count, sum, idx + 1, words, score);
		int y = maxScoreWordsHelper(clone, sum + tsum, idx + 1, words, score);
		return Math.max(x, y);
	}
	
	int uniquePathsIII = 0;
	
	public int uniquePathsIII(int[][] grid) {
		int hi = grid.length;
		int len = grid[0].length;
		//boolean[][] visited = new boolean[hi][len];
		int si = 0, sj = 0, ei = 0, ej = 0, total = 0;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] != 0) {
					if (grid[i][j] == 1) {
						si = i;
						sj = j;
						grid[i][j] = 3;
					}
				} else {
					total++;
				}
			}
		}
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		uniquePathsIIIHelper(ds, grid, si, sj, 0, total);
		return uniquePathsIII;
	}
	
	private void uniquePathsIIIHelper(int[][] ds, int[][] grid, int si, int sj, int c,
		int total) {
		int hi = grid.length;
		int len = grid[0].length;
		int x, y;
		for (int[] d : ds) {
			x = si + d[0];
			y = sj + d[1];
			if (x < 0 || y < 0 || x >= hi || y >= len || grid[x][y] == -1 || grid[x][y] == 3) {
				continue;
			}
			if (grid[x][y] == 0) {
				grid[x][y] = 3;
				uniquePathsIIIHelper(ds, grid, x, y, c + 1, total);
				grid[x][y] = 0;
			} else if (c == total) {
				uniquePathsIII++;
			}
		}
	}
	
	public int search(int[] nums, int target) {
		int l = 0, r = nums.length - 1, m;
		if (r < 0) {
			return r;
		}
		if (nums[r] < nums[l]) {
			while (l < r) {
				m = (l + r) >> 1;
				if (nums[m] >= nums[l]) {
					if (nums[m] < nums[m + 1]) {
						l = m + 1;
					} else {
						l = m;
						break;
					}
				} else {
					r = m - 1;
				}
			}
		} else {
			l = r;
		}
		if (target > nums[l]) {
			return -1;
		} else if (target == nums[l]) {
			return l;
		} else if (target < nums[0]) {
			int i = Arrays.binarySearch(nums, l + 1, nums.length, target);
			return i < 0 ? -1 : i;
		} else {
			int i = Arrays.binarySearch(nums, 0, l, target);
			return i < 0 ? -1 : i;
		}
		
	}
	
	public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}
		int max = 1;
		char[] array = s.toCharArray();
		int[] ret = {0, 1};
		for (int i = 1; i < len - max / 2; i++) {
			int[] cur = longestPalindromeHelper(array, i);
			if (cur[1] - cur[0] > ret[1] - ret[0]) {
				ret = cur;
				max = ret[1] - ret[0];
			}
		}
		return s.substring(ret[0], ret[1]);
	}
	
	public String convert1(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		StringBuffer buffer = new StringBuffer();
		int len = s.length();
		int length = numRows * 2 - 2;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < len; j += length) {
				buffer.append(s.charAt(j + i));
				if (i > 0 && i < numRows - 1 && j + length - i < len) {
					buffer.append(s.charAt(j + length - i));
				}
			}
		}
		return buffer.toString();
	}
	
	public String convert(String s, int numRows) {
		int len = s.length();
		StringBuffer[] buffers = new StringBuffer[numRows];
		for (int i = 0; i < numRows; i++) {
			buffers[i] = new StringBuffer();
		}
		int i = 0;
		while (i < len) {
			for (int j = 0; j < numRows && i < len; j++, i++) {
				buffers[j].append(s.charAt(i));
				
			}
			for (int j = numRows - 2; j > 0 && i < len; j--, i++) {
				buffers[j].append(s.charAt(i));
			}
		}
		StringBuffer ret = buffers[0];
		for (int j = 1; j < numRows; j++) {
			ret.append(buffers[j]);
		}
		return ret.toString();
	}
	
	private int[] longestPalindromeHelper(char[] array, int i) {
		int l = i, r = i;
		int len = array.length;
		while (l >= 0 && r < len && array[l] == array[r]) {
			l--;
			r++;
		}
		int[] x = {l + 1, r};
		l = i;
		r = i + 1;
		while (l >= 0 && r < len && array[l] == array[r]) {
			l--;
			r++;
		}
		int[] y = {l + 1, r};
		return x[1] - x[0] > y[1] - y[0] ? x : y;
	}
	
	public int lengthOfLongestSubstring1(String s) {
		HashSet<Character> set = new HashSet<>();
		int ret = 0, l = 0;
		int len = s.length();
		char c, t;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (set.add(c)) {
				ret = Math.max(ret, i - l + 1);
			} else {
				t = s.charAt(l);
				l++;
				while (t != c) {
					set.remove(t);
					t = s.charAt(l);
					l++;
				}
			}
		}
		//ret= Math.max(ret,len-l+1);
		return ret;
	}
	
	public int lengthOfLongestSubstring(String s) {
		int ret = 0, l = 0, mask = 0, t, c;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			t = 1 << (s.charAt(i) - 'a');
			if ((mask & t) == 0) {
				mask += t;
				ret = Math.max(ret, i - l + 1);
			} else {
				while ((mask & t) > 0) {
					c = 1 << (s.charAt(l) - 'a');
					mask -= c;
					l++;
				}
				mask += t;
			}
		}
		return ret;
	}
	
	public int closedIsland(int[][] grid) {
		int ret = 0;
		int hi = grid.length;
		int len = grid[0].length;
		for (int i = 1; i < hi - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				if (grid[i][j] == 0 && closedIslandHelper(i, j, grid)) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	private boolean closedIslandHelper(int i, int j, int[][] grid) {
		grid[i][j] = 1;
		boolean u, d, l, r;
		if (i == 0) {
			u = false;
		} else if (grid[i - 1][j] == 0) {
			u = closedIslandHelper(i - 1, j, grid);
		} else {
			u = true;
		}
		if (i == grid.length - 1) {
			d = false;
		} else if (grid[i + 1][j] == 0) {
			d = closedIslandHelper(i + 1, j, grid);
		} else {
			d = true;
		}
		if (j == 0) {
			l = false;
		} else if (grid[i][j - 1] == 0) {
			l = closedIslandHelper(i, j - 1, grid);
		} else {
			l = true;
		}
		if (j == grid[0].length - 1) {
			r = false;
		} else if (grid[i][j + 1] == 0) {
			r = closedIslandHelper(i, j + 1, grid);
		} else {
			r = true;
		}
		return u && d && l && r;
	}
	
	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		int len = colsum.length;
		Integer[] a = new Integer[len];
		Integer[] b = new Integer[len];
		ArrayList<List<Integer>> ret = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (colsum[i] == 2) {
				if (upper > 0 && lower > 0) {
					a[i] = 1;
					b[i] = 1;
					upper--;
					lower--;
				} else {
					return ret;
				}
			} else if (colsum[i] == 0) {
				a[i] = 0;
				b[i] = 0;
			} else {
				count++;
			}
		}
		if (count != upper + lower) {
			return ret;
		}
		for (int i = 0; i < len; i++) {
			if (colsum[i] == 1) {
				if (upper > 0) {
					a[i] = 1;
					b[i] = 0;
					upper--;
				} else {
					a[i] = 0;
					b[i] = 1;
				}
			}
		}
		ret.add(Arrays.asList(a));
		ret.add(Arrays.asList(b));
		return ret;
		
	}
	
	public int oddCells(int n, int m, int[][] indices) {
		int[] r = new int[n];
		int[] c = new int[m];
		for (int[] index : indices) {
			r[index[0]]++;
			c[index[1]]++;
		}
		int a = 0, b = 0;
		for (int i : r) {
			if ((i & 1) == 1) {
				a++;
			} else {
				b++;
			}
		}
		int ret = 0;
		for (int i : c) {
			if ((i & 1) == 1) {
				ret += b;
			} else {
				ret += a;
			}
		}
		return ret;
	}
	
	public String minRemoveToMakeValid2(String s) {
		int count = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == ')') {
				count++;
			}
		}
		StringBuffer buffer = new StringBuffer();
		char c, left = 0;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c == '(') {
				if (count > 0) {
					buffer.append(c);
					count--;
					left++;
				}
			} else if (c == ')') {
				if (left > 0) {
					left--;
					buffer.append(c);
				} else {
					count--;
				}
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	
	public String minRemoveToMakeValid1(String s) {
		int len = s.length();
		char[] array = s.toCharArray();
		StringBuffer buffer = new StringBuffer();
		int ritht = 0, count = 0;
		for (int i = 0; i < len; i++) {
			if (array[i] == '(') {
				ritht = minRemoveToMakeValid1Helper(array, ritht + 1);
				if (ritht < len) {
					count++;
					buffer.append(array[i]);
				}
			} else if (array[i] == ')') {
				if (count > 0) {
					count--;
					buffer.append(array[i]);
				} else {
					ritht = i + 1;
				}
			} else {
				buffer.append(array[i]);
			}
		}
		
		return buffer.toString();
	}
	
	private int minRemoveToMakeValid1Helper(char[] array, int i) {
		int len = array.length;
		for (; i < len; i++) {
			if (array[i] == ')') {
				return i;
			}
		}
		return len;
	}
	
	
	public String minRemoveToMakeValid(String s) {
		char[] array = s.toCharArray();
		return minRemoveToMakeValidHelper(array, 0);
	}
	
	public String minRemoveToMakeValidHelper(char[] array, int start) {
		
		StringBuffer ret = new StringBuffer();
		int l = start, count = 0;
		int len = array.length;
		char c;
		for (int i = start; i < len; i++) {
			c = array[i];
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count > 1) {
					count--;
				} else if (count == 1) {
					count--;
					ret.append(array, l, i - l + 1);
					l = i + 1;
				} else {
					ret.append(array, l, i - l);
					l = i + 1;
				}
			}
		}
		for (int i = l; i < len; i++) {
			c = array[i];
			if (c == '(') {
				return ret.toString() + minRemoveToMakeValidHelper(array, i + 1);
			} else {
				ret.append(c);
			}
		}
		return ret.toString();
	}
	
	
	public int numberOfSubarrays(int[] nums, int k) {
		int len = nums.length;
		int[] count = new int[len + 1];
		count[0] = 1;
		int c = 0;
		for (int num : nums) {
			c += (num & 1);
			count[c]++;
		}
		int ret = 0;
		for (int i = k; i <= len; i++) {
			if (count[i] == 0) {
				break;
			}
			ret += count[i] * count[i - k];
		}
		return ret;
	}
	
	public int minimumSwap(String s1, String s2) {
		int len = s2.length(), count = 0, xc = 0;
		char c1, c2;
		for (int i = 0; i < len; i++) {
			c1 = s1.charAt(i);
			c2 = s2.charAt(i);
			if (c1 != c2) {
				if (c1 == 'x') {
					xc++;
				}
				count++;
			}
		}
		return (count & 1) == 1 ? -1 : (xc & 1) == 0 ? count >> 1 : 1 + (count >> 1);
	}
	
	public int maxLength(List<String> arr) {
		ArrayList<Integer> list = new ArrayList<>();
		int ret = 0, cur, len, i;
		for (String s : arr) {
			ret = 0;
			len = s.length();
			for (i = 0; i < len; i++) {
				cur = 1 << (s.charAt(i) - 'a');
				if ((ret & cur) == 0) {
					ret += cur;
				} else {
					break;
				}
			}
			if (i == len && !list.contains(ret)) {
				list.add(ret);
			}
		}
		return maxLengthHelper(list, 0, list.size() - 1);
	}
	
	private int maxLengthHelper(ArrayList<Integer> list, int cur, int len) {
		int ret = 0;
		if (len < 0) {
			while (cur > 0) {
				ret += cur & 1;
				cur >>= 1;
			}
			return ret;
		}
		if ((cur & list.get(len)) == 0) {
			ret = Math.max(ret, maxLengthHelper(list, cur + list.get(len), len - 1));
		}
		ret = Math.max(ret, maxLengthHelper(list, cur, len - 1));
		return ret;
	}
	
	public List<Integer> circularPermutation1(int n, int start) {
		ArrayList<Integer> list = new ArrayList<>();
		int total = 1 << n;
		for (int i = 0; i < total; i++) {
			list.add(start ^ i ^ i >> 1);
		}
		return list;
	}
	
	public List<Integer> circularPermutation(int n, int start) {
		int i = 0;
		ArrayList<Integer> tail = new ArrayList<>();
		ArrayList<Integer> head = new ArrayList<>();
		int cur;
		for (; i < 1 << n; i++) {
			cur = i ^ (i >> 1);
			if (cur != start) {
				tail.add(cur);
			} else {
				head.add(cur);
				break;
			}
		}
		for (; i < 1 << n; i++) {
			cur = i ^ (i >> 1);
			head.add(cur);
		}
		head.addAll(tail);
		return head;
	}
	
	public boolean checkStraightLine(int[][] coordinates) {
		int len = coordinates.length;
		if (len < 3) {
			return true;
		}
		int x1 = coordinates[0][0];
		int y1 = coordinates[0][1];
		int x2 = coordinates[1][0];
		int y2 = coordinates[1][1], x3, y3;
		for (int i = 2; i < len; i++) {
			x3 = coordinates[i][0];
			y3 = coordinates[i][1];
			if (x1 * y2 + y1 * x3 + x2 * y3 - x1 * y3 - y1 * x2 - y2 * x3 != 0) {
				return false;
			}
		}
		return true;
	}
	
	class TireNode {
		
		String name;
		boolean isEnd;
		HashMap<String, TireNode> sub = new HashMap<>();
		
		public TireNode(String name, boolean isEnd) {
			this.name = name;
			this.isEnd = isEnd;
		}
	}
	
	public int balancedString(String s) {
		int len = s.length();
		char c;
		int q = -len / 4, w = q, e = q, r = q;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			switch (c) {
				case 'Q':
					q++;
					break;
				case 'W':
					w++;
					break;
				case 'E':
					e++;
					break;
				default:
					r++;
			}
		}
		int cq = 0, cw = 0, ce = 0, cr = 0, left = 0, right = 0, ret = Integer.MAX_VALUE;
		while (right < len) {
			while (cq < q || cw < w || ce < e || cr < r) {
				if (right == len) {
					break;
				}
				c = s.charAt(right);
				switch (c) {
					case 'Q':
						cq++;
						break;
					case 'W':
						cw++;
						break;
					case 'E':
						ce++;
						break;
					default:
						cr++;
				}
				right++;
			}
			while (cq >= q && cw >= w && ce >= e && cr >= r) {
				if (left == len) {
					break;
				}
				ret = Math.min(ret, right - left);
				c = s.charAt(left);
				switch (c) {
					case 'Q':
						cq--;
						break;
					case 'W':
						cw--;
						break;
					case 'E':
						ce--;
						break;
					default:
						cr--;
				}
				left++;
			}
			
		}
		return ret;
	}
	
	public List<String> removeSubfolders(String[] folder) {
		TireNode root = new TireNode("", false), cur;
		for (String s : folder) {
			String[] split = s.split("/");
			cur = root;
			for (int i = 1; i < split.length; i++) {
				if (cur.isEnd) {
					break;
				}
				if (cur.sub.containsKey(split[i])) {
					cur = cur.sub.get(split[i]);
				} else {
					TireNode node = new TireNode(split[i], false);
					cur.sub.put(split[i], node);
					cur = node;
				}
			}
			if (!cur.isEnd) {
				cur.name = s;
				cur.isEnd = true;
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		removeSubfoldersHelper(ret, root);
		return ret;
	}
	
	private void removeSubfoldersHelper(ArrayList<String> ret, TireNode root) {
		if (root.isEnd) {
			ret.add(root.name);
			return;
		}
		for (TireNode cur : root.sub.values()) {
			removeSubfoldersHelper(ret, cur);
		}
	}
	
	public double nthPersonGetsNthSeat(int n) {
		if (n < 3) {
			return 1 / (double) n;
		}
		double ret = 0.5;
		for (int i = 3; i <= n; i++) {
			ret = 1.0 / i + ret * (i - 2) / i;
		}
		return ret;
	}
	
	public int dieSimulator(int n, int[] rollMax) {
		int mod = 1000000007;
		long[][] count = new long[n][6];
		for (int i = 0; i < 6; i++) {
			count[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					count[i][j] += count[i - 1][k];
				}
				if (i - rollMax[j] > 0) {
					for (int k = 0; k < 6; k++) {
						if (k != j) {
							count[i][j] -= count[i - rollMax[j] - 1][k] - mod;
						}
					}
				} else if (i - rollMax[j] == 0) {
					count[i][j]--;//
				}
				count[i][j] %= mod;
			}
		}
		int ret = 0;
		for (int i = 0; i < 6; i++) {
			ret += count[n - 1][i];
			ret %= mod;
		}
		return ret;
	}
	
	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
		HashSet<Integer> set = new HashSet<>();
		for (int[] queen : queens) {
			set.add(queen[0] * 10 + queen[1]);
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		int x, y;
		for (int[] d : ds) {
			x = king[0];
			y = king[1];
			while (true) {
				x += d[0];
				y += d[1];
				if (x < 0 || y < 0 || x >= 8 || y >= 8) {
					break;
				} else if (set.contains(x * 10 + y)) {
					ret.add(Arrays.asList(x, y));
					break;
				}
			}
		}
		return ret;
	}
	
	public int balancedStringSplit(String s) {
		int count = 0, ret = 0;
		char c;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c == 'L') {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public int getMaximumGold(int[][] grid) {
		int hi = grid.length;
		int len = grid[0].length;
		int ret = 0;
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] > 0) {
					ret = Math.max(getMaximumGoldHelper(grid, i, j, ds), ret);
					
				}
			}
		}
		return ret;
	}
	
	private int getMaximumGoldHelper(int[][] grid, int i, int j, int[][] ds) {
		int len = grid[0].length;
		int hi = grid.length;
		if (i < 0 || j < 0 || i >= hi || j >= len || grid[i][j] == 0) {
			return 0;
		}
		int cur = grid[i][j], next = 0;
		grid[i][j] = 0;
		for (int[] d : ds) {
			next = Math.max(next, getMaximumGoldHelper(grid, i + d[0], j + d[1], ds));
		}
		grid[i][j] = cur;
		return cur + next;
	}
	
	public int longestSubsequence(int[] arr, int difference) {
		int[] count = new int[40001];
		int ret = 0;
		for (int i : arr) {
			count[i + difference + 20000] = count[i + 20000] + 1;
			ret = Math.max(ret, count[i + difference + 20000]);
		}
		return ret;
	}
	
	public int minCostToMoveChips(int[] chips) {
		int a = 0, b = chips.length;
		for (int chip : chips) {
			if ((chip & 1) == 0) {
				a++;
			}
		}
		return Math.min(a, b - a);
	}
	
	public String removeDuplicates1(String s, int k) {
		int len = s.length();
		int[] count = new int[len];
		char[] array = s.toCharArray();
		int idx = 0;
		for (int i = 0; i < len; i++) {
			array[idx] = array[i];
			if (idx > 0 && array[i] == array[idx - 1]) {
				count[idx] = count[idx - 1] + 1;
			} else {
				count[idx] = 1;
			}
			if (count[idx] == k) {
				idx -= k;
			}
			idx++;
		}
		return new String(array, 0, idx);
	}
	
	public String removeDuplicates(String s, int k) {
		int len = s.length();
		int[][] count = new int[len][2];
		int idx = 0;
		char c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (idx == 0 || c != count[idx - 1][0]) {
				count[idx][0] = c;
				count[idx][1] = 1;
				idx++;
			} else {
				count[idx - 1][1]++;
				if (count[idx - 1][1] == k) {
					count[idx - 1][1] = 0;
					idx--;
				}
			}
		}
		StringBuffer buffer = new StringBuffer();
		int x;
		for (int i = 0; i < idx; i++) {
			c = (char) count[i][0];
			x = count[i][1];
			while (x > 0) {
				buffer.append(c);
				x--;
			}
		}
		return buffer.toString();
	}
	
	public int equalSubstring(String s, String t, int maxCost) {
		int len = s.length();
		int[] cost = new int[len + 1];
		int c;
		for (int i = 0; i < len; i++) {
			c = Math.abs(s.charAt(i) - t.charAt(i));
			cost[i + 1] = cost[i] + c;
		}
		int l = 0, ret = 0;
		for (int i = 1; i <= len; i++) {
			if (cost[i] - cost[l] <= maxCost) {
				ret = Math.max(i - l, ret);
			} else {
				while (cost[i] - cost[l] > maxCost) {
					l++;
				}
			}
		}
		return ret;
	}
	
	public boolean uniqueOccurrences(int[] arr) {
		int[] ints = new int[2001];
		for (int i : arr) {
			ints[i + 1000]++;
		}
		boolean[] booleans = new boolean[1001];
		for (int x : ints) {
			if (x > 0) {
				if (booleans[x]) {
					return false;
				}
				booleans[x] = true;
			}
		}
		return true;
	}
	
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int len = s.length();
		int[] ints = new int[len];
		for (int i = 0; i < len; i++) {
			ints[i] = i;
		}
		
		int x, y;
		for (List<Integer> pair : pairs) {
			x = pair.get(0);
			y = pair.get(1);
			x = findBefore(ints, x);
			y = findBefore(ints, y);
			if (x < y) {
				ints[y] = x;
			} else {
				ints[x] = y;
			}
		}
		
		int t, c;
		HashMap<Integer, int[]> map = new HashMap<>();
		int[] count;
		for (int i = 0; i < len; i++) {
			t = findBefore(ints, ints[i]);
			ints[i] = t;
			c = s.charAt(i) - 'a' + 1;
			if (map.containsKey(t)) {
				count = map.get(t);
			} else {
				count = new int[27];
				count[0] = 26;
				map.put(t, count);
			}
			if (c < count[0]) {
				count[0] = c;
			}
			count[c]++;
		}
		char[] ret = new char[len];
		for (int i = 0; i < len; i++) {
			t = ints[i];
			ret[i] = getChar(map.get(t));
		}
		return new String(ret);
	}
	
	private char getChar(int[] ints) {
		int i = ints[0];
		while (ints[i] == 0) {
			i++;
		}
		ints[i]--;
		ints[0] = i;
		return (char) (i - 1 + 'a');
	}
	
	private int findBefore(int[] ints, int x) {
		while (ints[x] != x) {
			x = ints[x];
		}
		return x;
	}
	
	public int nthUglyNumber(int n, int a, int b, int c) {
		long ab = (long) a * b / gcb(a, b);
		long bc = (long) c * b / gcb(c, b);
		long ac = (long) a * c / gcb(a, c);
		long abc = (long) a * bc / gcb(a, (int) bc);
		int l = Math.min(a, Math.min(b, c)), r = Integer.MAX_VALUE, m;
		long count;
		while (l < r) {
			m = l + (r - l) / 2;
			count = m / a + m / b + m / c - m / ab - m / ac - m / bc + m / abc;
			if (count >= n) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	private long gcb(int a, int b) {
		int x;
		while (b > 0) {
			x = a % b;
			a = b;
			b = x;
		}
		return a;
	}
	
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		Arrays.sort(arr);
		int len = arr.length;
		int[] ints = new int[len * 2];
		int idx = 0;
		int x = arr[1] - arr[0], cur;
		ints[0] = arr[0];
		ints[1] = arr[1];
		idx = 2;
		for (int i = 2; i < len; i++) {
			cur = arr[i] - arr[i - 1];
			if (cur > x) {
				continue;
				
			}
			if (cur < x) {
				idx = 0;
				x = cur;
			}
			ints[idx++] = arr[i - 1];
			ints[idx++] = arr[i];
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> list;
		for (int i = 0; i < idx; i += 2) {
			list = new ArrayList<>();
			ret.add(list);
			list.add(ints[i]);
			list.add(ints[i + 1]);
		}
		return ret;
	}
	
	int idx = 0;
	
	public int kConcatenationMaxSum(int[] arr, int k) {
		int mod = 1000000007;
		int len = arr.length;
		int sum = arr[0];
		int[] left = new int[len];
		left[0] = arr[0];
		int lret = arr[0];
		for (int i = 1; i < len; i++) {
			sum += arr[i];
			left[i] = arr[i] + (left[i - 1] > 0 ? left[i - 1] : 0);
			lret = Math.max(lret, left[i]);
		}
		lret = lret > 0 ? lret : 0;
		if (k == 1) {
			return lret % mod;
		}
		int[] right = new int[len];
		right[len - 1] = arr[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			right[i] = arr[i] + (right[i + 1] > 0 ? right[i + 1] : 0);
		}
		int lm = left[len - 1] > 0 ? left[len - 1] : 0;
		int lr = right[0] > 0 ? right[0] : 0;
		return Math.max(lret, Math.max(lm + lr, lm + lr + (int) (((long) (k - 2) * sum) % mod)))
			% mod;
	}
	
	public String reverseParentheses(String s) {
		char[] array = s.toCharArray();
		int count = 0;
		int len = array.length;
		for (char c : array) {
			if (c == '(') {
				count++;
			}
		}
		char[] ret = new char[len - count * 2];
		reverseParenthesesHelper1(0, len - 1, array, ret);
		return new String(ret);
	}
	
	private void reverseParenthesesHelper1(int l, int r, char[] array, char[] ret) {
		int count = 0, t;
		for (int i = l; i <= r; ) {
			if (array[i] == '(') {
				count++;
				t = i + 1;
				while (count != 0) {
					if (array[t] == '(') {
						count++;
					} else if (array[t] == ')') {
						count--;
					}
					t++;
				}
				reverseParenthesesHelper2(i + 1, t - 2, array, ret);
				i = t;
			} else {
				ret[idx] = array[i];
				idx++;
				i++;
			}
		}
	}
	
	private void reverseParenthesesHelper2(int l, int r, char[] array, char[] ret) {
		int count = 0, t;
		for (int i = r; i >= l; ) {
			if (array[i] == ')') {
				count--;
				t = i - 1;
				while (t >= 0 && count != 0) {
					if (array[t] == '(') {
						count++;
					} else if (array[t] == ')') {
						count--;
					}
					t--;
				}
				reverseParenthesesHelper1(t + 2, i - 1, array, ret);
				i = t;
			} else {
				ret[idx] = array[i];
				idx++;
				i--;
			}
		}
	}
	
	public int maxNumberOfBalloons(String text) {
		int[] count = new int[26];
		int len = text.length();
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			count[c - 'a']++;
		}
		int[][] ds = {{1, 1}, {'l' - 'a', 2}, {'o' - 'a', 2}, {'n' - 'a', 1}};
		int ret = count[0];
		for (int[] d : ds) {
			ret = Math.min(ret, count[d[0]] / d[1]);
		}
		return ret;
	}
	
	public int maximumSum1(int[] arr) {
		int len = arr.length;
		int[] left = new int[len];
		left[0] = arr[0];
		int[] right = new int[len];
		right[len - 1] = arr[len - 1];
		int ret = Integer.MIN_VALUE;
		for (int i = 1; i < len; i++) {
			left[i] = Math.max(arr[i], left[i - 1] + arr[i]);
			ret = Math.max(ret, left[i]);
		}
		for (int i = len - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1] + arr[i], arr[i]);
		}
		
		for (int i = 1; i < len - 1; i++) {
			ret = Math.max(ret, left[i - 1] + right[i + 1]);
		}
		return ret;
	}
	
	public int maximumSum(int[] arr) {
		int len = arr.length;
		if (len == 1) {
			return arr[0];
		}
		int[] sum = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			sum[i] += sum[i - 1] + arr[i - 1];
		}
		
		LinkedList<Integer> list = new LinkedList<>();
		list.add(len);
		for (int i = len - 1; i > 0; i--) {
			if (sum[i] > sum[list.getFirst()]) {
				list.addFirst(i);
			}
		}
		int ret = Integer.MIN_VALUE;
		int leftmin = 0;
		
		for (int i = 1; i < len; i++) {
			if (i == list.getFirst()) {
				list.removeFirst();
			}
			if (arr[i - 1] >= 0) {
				ret = Math.max(ret, sum[list.getFirst()] - sum[leftmin]);
			} else {
				ret = Math.max(ret, sum[list.getFirst()] - sum[leftmin] - arr[i - 1]);
			}
			if (sum[i] < sum[leftmin]) {
				leftmin = i;
			}
		}
		return ret;
	}
	
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int sum = 0;
		int lsum = 0;
		for (int i : distance) {
			sum += i;
		}
		for (int i = start; i < destination; i++) {
			lsum += distance[i];
		}
		return Math.min(lsum, sum - lsum);
		
	}
	
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		int len = s.length();
		int[][] count = new int[len + 1][26];
		char[] array = s.toCharArray();
		count[0][array[0] - 'a']++;
		for (int i = 1; i < len; i++) {
			count[i] = Arrays.copyOf(count[i - 1], 26);
			count[i][array[i] - 'a']++;
		}
		ArrayList<Boolean> ret = new ArrayList<>();
		for (int[] query : queries) {
			ret.add(canMakePaliQueriesHelper(count, query[0], query[1], query[2]));
		}
		return ret;
	}
	
	private Boolean canMakePaliQueriesHelper(int[][] array, int l, int r, int c) {
		int count = 0;
		for (int i = 0; i < 26; i++) {
			count += ((array[r][i] - (l > 0 ? array[l - 1][i] : 0)) & 1);
		}
		
		return count - 2 * c < 2;
	}
	
	public int numPrimeArrangements(int n) {
		boolean[] flag = new boolean[n + 1];
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (flag[i]) {
				continue;
			} else {
				count++;
				for (int j = 2; j * i <= n; j++) {
					flag[i * j] = true;
				}
			}
		}
		int ret = 1, mod = 1000000007;
		for (int i = n - count; i > 1; i--) {
			ret *= i;
			ret %= mod;
		}
		for (int i = count; i > 1; i--) {
			ret *= i;
			ret %= mod;
		}
		return ret;
	}
	
	public ListNode removeZeroSumSublists3(ListNode head) {
		ListNode cur = head;
		int sum = 0;
		while (cur != null) {
			sum += cur.val;
			if (sum == 0) {
				head = cur.next;
			}
			cur = cur.next;
		}
		if (head != null) {
			head.next = removeZeroSumSublists3(head.next);
		}
		return head;
	}
	
	public ListNode removeZeroSumSublists2(ListNode head) {
		HashMap<Integer, ListNode> map = new HashMap<>();
		ListNode cur = head;
		int sum = 0;
		while (cur != null) {
			map.put(sum, cur);
			sum += cur.val;
			cur = cur.next;
		}
		map.put(sum, null);
		ListNode ret = map.get(0);
		cur = ret;
		sum = 0;
		while (cur != null) {
			sum += cur.val;
			ListNode listNode = map.get(sum);
			cur.next = listNode;
			cur = cur.next;
		}
		return ret;
	}
	
	public ListNode removeZeroSumSublists1(ListNode head) {
		HashMap<Integer, ListNode> map = new HashMap<>();
		int sum = 0;
		ListNode cur = head, ret = head;
		while (cur != null) {
			sum += cur.val;
			if (sum == 0) {
				ret = cur.next;
				map.clear();
			} else if (map.containsKey(sum)) {
				ListNode temp = map.get(sum).next;
				map.get(sum).next = cur.next;
				cur.next = null;
				int x = sum + temp.val;
				while (x != sum) {
					map.remove(x);
					temp = temp.next;
					x += temp.val;
				}
				cur = map.get(sum);
			} else {
				map.put(sum, cur);
			}
			cur = cur.next;
		}
		return ret;
	}
	
	public ListNode removeZeroSumSublists(ListNode head) {
		HashMap<Integer, ListNode> map = new HashMap<>();
		int sum = 0;
		ListNode cur = head, ret = head;
		while (cur != null) {
			sum += cur.val;
			if (sum == 0) {
				ret = cur.next;
			} else if (map.containsKey(sum)) {
				map.get(sum).next = cur.next;
				cur = ret;
				sum = ret.val;
				map.clear();
			} else {
				map.put(sum, cur);
			}
			cur = cur.next;
		}
		return ret;
	}
	
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] count = new int[11];
		for (String word : words) {
			count[numSmallerByFrequencyHelper(word)]++;
		}
		for (int i = 1; i < 11; i++) {
			count[i] += count[i - 1];
		}
		int len = queries.length;
		int[] ret = new int[len];
		int l;
		for (int i = 0; i < len; i++) {
			l = numSmallerByFrequencyHelper(queries[i]);
			ret[i] = count[10] - count[l];
		}
		return ret;
	}
	
	private int numSmallerByFrequencyHelper(String word) {
		char c = 'z';
		int ret = 0;
		for (char x : word.toCharArray()) {
			if (x == c) {
				ret++;
			} else if (x < c) {
				c = x;
				ret = 1;
			}
		}
		return ret;
	}
	
	public int maxDistance2(int[][] grid) {
		int max = 10000;
		int hi = grid.length;
		int len = grid[0].length;
		int a, b;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 0) {
					a = i == 0 ? max : grid[i - 1][j];
					b = j == 0 ? max : grid[i][j - 1];
					grid[i][j] = Math.min(a, b) + 1;
				}
			}
		}
		int ret = -1;
		for (int i = hi - 1; i >= 0; i--) {
			for (int j = len - 1; j >= 0; j--) {
				if (grid[i][j] > 1) {
					a = i == hi - 1 ? max : grid[i + 1][j];
					b = i == len - 1 ? max : grid[i][j + 1];
					grid[i][j] = Math.min(grid[i][j], Math.min(a, b) + 1);
					ret = Math.max(ret, grid[i][j]);
				}
			}
		}
		return ret == max ? -1 : ret - 1;
	}
	
	public int maxDistance1(int[][] grid) {
		int hi = grid.length;
		if (hi == 0) {
			return -1;
		}
		int len = grid[0].length;
		int[][] visit = new int[hi * len][2];
		int idx = 0;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					visit[idx][0] = i;
					visit[idx][1] = j;
					idx++;
				}
			}
		}
		if (idx == 0 || idx == hi * len) {
			return -1;
		}
		int l = 0, r, x, y, nx, ny, ret = 0;
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while (idx < hi * len) {
			ret++;
			r = idx;
			for (int i = l; i < r; i++) {
				x = visit[i][0];
				y = visit[i][1];
				for (int[] d : ds) {
					nx = x + d[0];
					ny = y + d[1];
					if (nx < 0 || ny < 0 || nx >= hi || ny >= len || grid[nx][ny] == 1) {
						continue;
					} else {
						visit[idx][0] = nx;
						visit[idx][1] = ny;
						idx++;
						grid[nx][ny] = 1;
					}
				}
			}
			l = r;
		}
		return ret;
	}
	
	public int maxDistance(int[][] grid) {
		ArrayList<Integer> list = new ArrayList<>(), next;
		int count = 0;
		int hi = grid.length;
		if (hi == 0) {
			return -1;
		}
		int len = grid[0].length;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					list.add(i);
					list.add(j);
					count++;
				}
			}
		}
		if (count == 0 || count == hi * len) {
			return -1;
		}
		int ret = -1, x, y, nx, ny;
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		while (!list.isEmpty()) {
			ret++;
			next = new ArrayList<>();
			for (int i = 0; i < list.size(); i += 2) {
				x = list.get(i);
				y = list.get(i + 1);
				for (int[] d : ds) {
					nx = d[0] + x;
					ny = d[1] + y;
					if (nx < 0 || ny < 0 || nx >= hi || ny >= len || grid[nx][ny] == 1) {
						continue;
					} else {
						next.add(nx);
						next.add(ny);
						grid[nx][ny] = 1;
					}
				}
			}
			list = next;
		}
		return ret;
	}
	
	public int numRollsToTarget1(int d, int f, int target) {
		int[][] ints = new int[d + 1][target + 1];
		for (int i = 0; i <= d; i++) {
			Arrays.fill(ints[i], -1);
		}
		int mod = 1000000000 + 7;
		return numRollsToTargetHelper(d, f, target, ints, mod);
	}
	
	int max = 0;
	
	public int maxLevelSum(TreeNode root) {
		int[] sum = new int[10001];
		maxLevelSumHelper(root, 0, sum);
		int ret = 0;
		for (int i = 0; i < max; i++) {
			if (sum[i] > sum[ret]) {
				ret = i;
			}
		}
		return ret + 1;
	}
	
	private void maxLevelSumHelper(TreeNode root, int i, int[] sum) {
		if (root == null) {
			max = Math.max(i, max);
			return;
		}
		sum[i] += root.val;
		maxLevelSumHelper(root.left, i + 1, sum);
		maxLevelSumHelper(root.right, i + 1, sum);
	}
	
	public int countCharacters(String[] words, String chars) {
		int[] ints = new int[26], cur;
		for (int i = 0; i < chars.length(); i++) {
			ints[chars.charAt(i) - 'a']++;
		}
		int ret = 0, c;
		for (String word : words) {
			cur = new int[26];
			int i = 0, len = word.length();
			for (; i < len; i++) {
				c = word.charAt(i) - 'a';
				if (cur[c] < ints[c]) {
					cur[c]++;
				} else {
					break;
				}
			}
			if (i == len) {
				ret += len;
			}
		}
		return ret;
	}
	
	public int maxRepOpt12(String text) {
		char[] array = text.toCharArray();
		int ret = 0;
		for (int i = 'a'; i <= 'z'; i++) {
			int pre = 0, cur = 0, count = 0, res = 0;
			for (char c : array) {
				if (c == i) {
					count++;
					pre++;
					cur++;
				} else {
					cur = pre + 1;
					pre = 0;
				}
				res = Math.max(res, cur);
			}
			ret = Math.max(ret, Math.min(count, res));
		}
		return ret;
	}
	
	public int maxRepOpt11(String text) {
		int len = text.length();
		int[][] ints = new int[len + 1][2];
		int idx = 0;
		ints[idx][0] = text.charAt(0);
		int[] count = new int['z' + 1];
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			count[c]++;
			if (c == ints[idx][0]) {
				ints[idx][1]++;
			} else {
				idx++;
				ints[idx][0] = c;
				ints[idx][1]++;
			}
		}
		int ret = 0, cur;
		for (int i = 1; i <= idx + 1; i++) {
			if (ints[i][1] == 1 && ints[i - 1][0] == ints[i + 1][0]
				&& ints[i - 1][1] + ints[i + 1][1] > ret - 1) {
				cur = ints[i - 1][1] + ints[i + 1][1];
				if (count[ints[i - 1][0]] > cur) {
					cur++;
				}
				ret = Math.max(cur, ret);
			} else if (ints[i - 1][1] > ret - 1) {
				cur = ints[i - 1][1];
				if (count[ints[i - 1][0]] > cur) {
					cur++;
				}
				ret = Math.max(cur, ret);
			}
		}
		return ret;
	}
	
	public int maxRepOpt1(String text) {
		int len = text.length();
		int[] count = new int[26];
		int c, ret = 1;
		char[] array = text.toCharArray();
		c = array[0] - 'a';
		int first = 0, second = -1;
		boolean flag = false;
		int i = 1;
		for (int j = 0; j < len; j++) {
			count[array[j] - 'a']++;
		}
		for (; i < len; i++) {
			if (array[i] != array[first]) {
				if (flag) {
					if (ret < i - first) {
						ret = i - first;
						c = array[first] - 'a';
					}
					first = second;
					i = second;
					flag = false;
				} else {
					flag = true;
					second = i;
				}
			}
		}
		if (ret < i - first + 1) {
			ret = i - first + 1;
			c = array[first] - 'a';
		}
		if (ret > count[c]) {
			ret = count[c];
		}
		return ret;
	}
	
	private int numRollsToTargetHelper(int d, int f, int target, int[][] ints, int mod) {
		if (d == 0 && target == 0) {
			return 1;
		}
		if (d == 0 || target == 0) {
			return 0;
		}
		if (d == 1 && f >= target) {
			return 1;
		}
		if (ints[d][target] >= 0) {
			return ints[d][target];
		}
		int ret = 0;
		for (int i = 1; i <= f; i++) {
			if (target >= i) {
				ret = (ret + numRollsToTargetHelper(d - 1, f, target - i, ints, mod)) % mod;
			} else {
				break;
			}
		}
		ints[d][target] = ret;
		return ret;
	}
	
	public int numRollsToTarget(int d, int f, int target) {
		int mod = 1000000007;
		int[][] ints = new int[d + 1][target + 1];
		for (int i = 1; i <= f; i++) {
			ints[1][i] = 1;
		}
		for (int i = 2; i <= d; i++) {
			for (int j = 1; j <= f; j++) {
				for (int k = 0; k <= target - j; k++) {
					ints[i][j + k] += ints[i - 1][k];
					ints[i][j + k] %= mod;
				}
				
			}
		}
		return ints[d][target];
	}
	
	public int dayOfYear(String date) {
		int year = 0, month = 0, day = 0;
		int[] days = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
		int i = 0;
		while (date.charAt(i) != '-') {
			year *= 10;
			year += date.charAt(i) - '0';
			i++;
		}
		i++;
		while (date.charAt(i) != '-') {
			month *= 10;
			month += date.charAt(i) - '0';
			i++;
		}
		i++;
		while (i < 10 && date.charAt(i) != '-') {
			day *= 10;
			day += date.charAt(i) - '0';
			i++;
		}
		int ret = day + days[month - 1];
		if (month > 2 && year % 4 == 0) {
			if (year % 100 == 0 && year % 400 != 0) {
			
			} else {
				ret++;
			}
		}
		return ret;
		
	}
	
	TreeNode temp;
	
	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		
		if (root.val == x) {
			int count = btreeGameWinningMoveHelper(root.left, x);
			return count * 2 != n - 1;
		} else {
			int a = btreeGameWinningMoveHelper(root, x);
			if (a * 2 > n) {
				return true;
			}
			int b = btreeGameWinningMoveHelper(temp.left, x);
			return b * 2 > n || (n - a - b - 1) * 2 > n;
		}
	}
	
	private int btreeGameWinningMoveHelper(TreeNode root, int x) {
		if (root == null) {
			return 0;
		}
		if (root.val == x) {
			temp = root;
			return 0;
		}
		int ret = 0;
		if (root.left != null) {
			ret += btreeGameWinningMoveHelper(root.left, x);
		}
		if (root.right != null) {
			ret += btreeGameWinningMoveHelper(root.right, x);
		}
		return ret + 1;
	}
	
	public int movesToMakeZigzag(int[] nums) {
		int count1 = 0, count2 = count1;
		int len = nums.length;
		for (int i = 0; i < len; i += 2) {
			int l = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
			int r = i < len - 1 ? nums[i + 1] : Integer.MAX_VALUE;
			int min = Math.min(l, r);
			if (min <= nums[i]) {
				count1 += nums[i] - min + 1;
			}
		}
		for (int i = 1; i < len; i += 2) {
			int l = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
			int r = i < len - 1 ? nums[i + 1] : Integer.MAX_VALUE;
			int min = Math.min(l, r);
			if (min <= nums[i]) {
				count2 += nums[i] - min + 1;
			}
		}
		return Math.min(count1, count2);
	}
	
	public int stoneGameII2(int[] piles) {
		int len = piles.length;
		for (int i = len - 1; i > 0; i--) {
			piles[i - 1] += piles[i];
		}
		int[][] mem = new int[len][len + 1];
		return stoneGameIIHelper(0, 1, mem, piles, len);
	}
	
	public int longestCommonSubsequence(String text1, String text2) {
		char[] a = text1.toCharArray();
		char[] b = text2.toCharArray();
		int al = a.length;
		int bl = b.length;
		int[][] ints = new int[al + 1][bl + 1];
		for (int i = 1; i < al + 1; i++) {
			for (int j = 1; j < bl + 1; j++) {
				if (a[i - 1] == b[j - 1]) {
					ints[i][j] = ints[i - 1][j - 1] + 1;
				} else {
					ints[i][j] = Math.max(ints[i - 1][j], ints[i][j - 1]);
				}
			}
		}
		return ints[al][bl];
	}
	
	private int stoneGameIIHelper(int i, int m, int[][] mem, int[] sum, int len) {
		if (i >= len) {
			return 0;
		}
		//剩余的全部获取
		if (i + 2 * m >= len) {
			return sum[i];
		}
		//已经给计算过的记录
		if (mem[i][m] > 0) {
			return mem[i][m];
		}
		int min = sum[i];
		//当前剩余的全部减去所有取1到2m中对方能取得最小值
		for (int j = 1; j <= m; j++) {
			min = Math.min(min, stoneGameIIHelper(i + j, m, mem, sum, len));
		}
		for (int j = m + 1; j <= 2 * m; j++) {
			min = Math.min(min, stoneGameIIHelper(i + j, j, mem, sum, len));
		}
		mem[i][m] = sum[i] - min;
		return sum[i] - min;
	}
	
	public int stoneGameII1(int[] piles) {
		int len = piles.length;
		for (int i = len - 1; i > 0; i--) {
			piles[i - 1] += piles[i];
		}
		int[][] ret = new int[len][len + 1];
		for (int i = len - 1; i >= 0; i--) {
			for (int j = 1; j <= len; j++) {
				if (2 * j + i >= len) {
					ret[i][j] = piles[i];
				} else {
					for (int k = 1; k <= 2 * j; k++) {
						ret[i][j] = Math.max(ret[i][j], piles[i] - ret[i + k][Math.max(k, j)]);
					}
				}
			}
		}
		return ret[0][1];
	}
	
	public int stoneGameII(int[] piles) {
		int len = piles.length;
		for (int i = len - 1; i > 0; i--) {
			piles[i - 1] += piles[i];
		}
		int[][] ret = new int[len][len];
		int m;
		for (int i = len - 1; i >= 0; i--) {
			for (int j = 0; j < len; j++) {
				m = j + 1;
				if (2 * m >= len - i) {
					ret[i][j] = piles[i];
				} else {
					for (int k = 1; k <= 2 * m; k++) {
						int x = piles[i] - ret[i + k][Math.max(k, m) - 1];
						ret[i][j] = Math.max(ret[i][j], x);
					}
				}
			}
		}
		return ret[0][0];
	}
	
	public int largest1BorderedSquare1(int[][] grid) {
		int hi = grid.length;
		int len = grid[0].length;
		int[][] left = new int[hi][len];
		int[][] up = new int[hi][len];
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] > 0) {
					up[i][j] = i > 0 ? up[i - 1][j] + 1 : 1;
					left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
				}
			}
		}
		int ret = 0, cur;
		for (int i = hi - 1; i >= ret; i--) {
			for (int j = len - 1; j >= ret; j--) {
				cur = Math.min(up[i][j], left[i][j]);
				while (cur > ret) {
					if (up[i][j - cur + 1] >= cur && left[i - cur + 1][j] >= cur) {
						ret = cur;
					}
					cur--;
				}
			}
		}
		return ret * ret;
	}
	
	public int largest1BorderedSquare(int[][] grid) {
		int hi = grid.length;
		int len = grid[0].length;
		int ret = 0;
		for (int i = 0; i < hi - ret; i++) {
			for (int j = 0; j < len - ret; j++) {
				if (grid[i][j] == 0) {
					continue;
				}
				for (int k = ret; i + k < hi && j + k < len; k++) {
					if (grid[i + k][j + k] == 1 && largest1BorderedSquareHelper(i, j, k,
						grid)) {
						ret = k + 1;
					}
				}
			}
		}
		return ret * ret;
	}
	
	private boolean largest1BorderedSquareHelper(int i, int j, int k, int[][] grid) {
		for (int l = 0; l <= k; l++) {
			if (grid[i][j + l] == 0 || grid[i + k][j + l] == 0 || grid[i + l][j] == 0
				|| grid[i + l][j + k] == 0) {
				return false;
			}
		}
		return true;
	}
	
	public String alphabetBoardPath(String target) {
		
		int idx = 0;
		int[][] ints = new int[26][2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				ints[idx++] = new int[]{i, j};
			}
		}
		ints[25] = new int[]{5, 0};
		int cur = 0, next, x, y;
		StringBuffer buffer = new StringBuffer();
		for (char c : target.toCharArray()) {
			next = c - 'a';
			x = ints[next][1] - ints[cur][1];
			y = ints[next][0] - ints[cur][0];
			while (y < 0) {
				buffer.append('U');
				y++;
			}
			while (x < 0) {
				buffer.append('L');
				x++;
			}
			while (x > 0) {
				buffer.append('R');
				x--;
			}
			while (y > 0) {
				buffer.append('D');
				y--;
			}
			
			buffer.append('!');
			cur = next;
		}
		return buffer.toString();
	}
	
	public int maxAbsValExpr(int[] arr1, int[] arr2) {
		//{1,1}右上-左下,{-1,1}左上-右下,{1,-1}右下-左上,{-1,-1}左下-右上
		int[][] ds = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
		int max, min;
		int len = arr1.length, t;
		int ret = 0;
		for (int[] d : ds) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < len; i++) {
				t = arr1[i] * d[0] + arr2[i] * d[1] + i;
				if (t > max) {
					max = t;
				}
				if (t < min) {
					min = t;
				}
			}
			ret = Math.max(ret, max - min);
		}
		return ret;
	}
	
	public int tribonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n < 3) {
			return 1;
		}
		int a = 0, b = 1, c = 1, d = 0;
		for (int i = 3; i <= n; i++) {
			d = a + b + c;
			a = b;
			b = c;
			c = d;
		}
		return d;
	}
	
	public int mctFromLeafValues1(int[] arr) {
		int len = arr.length;
		int[] ints = new int[len + 1];
		ints[0] = 16;
		int idx = 0;
		int ret = 0;
		int l, r;
		for (int i = 0; i < len; i++) {
			while (ints[idx] < arr[i]) {
				l = ints[idx] * ints[idx - 1];
				r = ints[idx] * arr[i];
				ret += Math.min(l, r);
				idx--;
			}
			idx++;
			ints[idx] = arr[i];
		}
		for (int i = idx; i > 1; i--) {
			ret += ints[i] * ints[i - 1];
		}
		return ret;
	}
	
	int mctFromLeafValues = 0;
	
	public int mctFromLeafValues(int[] arr) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i : arr) {
			list.add(i);
		}
		Arrays.sort(arr);
		int length = arr.length, len = length;
		int cur, x, l, r, ret = 0, c;
		for (int i = 0; i < length - 1; i++) {
			while (i < length - 1 && arr[i] == arr[i + 1]) {
				i++;
			}
			cur = arr[i];
			x = 0;
			while (x < len && len > 1) {
				c = list.get(x);
				if (cur == c) {
					l = x > 0 ? list.get(x - 1) * c : Integer.MAX_VALUE;
					r = x + 1 < len ? list.get(x + 1) * c : Integer.MAX_VALUE;
					ret += Math.min(l, r);
					list.remove(x);
					len--;
				} else {
					x++;
				}
			}
		}
		return ret;
	}
	
	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
		int[] ret = new int[n];
		Arrays.fill(ret, Integer.MAX_VALUE);
		ret[0] = 0;
		int[][] red = new int[n][n];
		int[][] blue = new int[n][n];
		int[][] cur;
		int len;
		for (int[] edge : red_edges) {
			len = red[edge[0]][0] + 1;
			red[edge[0]][0] = len;
			red[edge[0]][len] = edge[1];
		}
		for (int[] edge : blue_edges) {
			len = blue[edge[0]][0] + 1;
			blue[edge[0]][0] = len;
			blue[edge[0]][len] = edge[1];
		}
		ArrayList<Integer> list = new ArrayList<>(), next;
		list.add(0);
		int count = 0;
		cur = red;
		boolean[] redFlag = new boolean[n];
		boolean[] blueFlag = new boolean[n];
		boolean[] curFlag = redFlag;
		while (!list.isEmpty()) {
			next = new ArrayList<>();
			count++;
			for (Integer x : list) {
				for (int i = cur[x][0]; i > 0; i--) {
					int y = cur[x][i];
					if (!curFlag[y]) {
						next.add(y);
						curFlag[y] = true;
					}
					if (ret[y] > count) {
						ret[y] = count;
						
					}
				}
			}
			if ((count & 1) == 1) {
				cur = blue;
				curFlag = blueFlag;
			} else {
				cur = red;
				curFlag = redFlag;
			}
			list = next;
		}
		list.add(0);
		count = 0;
		cur = blue;
		redFlag = new boolean[n];
		blueFlag = new boolean[n];
		curFlag = blueFlag;
		while (!list.isEmpty()) {
			next = new ArrayList<>();
			count++;
			for (Integer x : list) {
				for (int i = cur[x][0]; i > 0; i--) {
					int y = cur[x][i];
					if (!curFlag[y]) {
						next.add(y);
						curFlag[y] = true;
					}
					if (ret[y] > count) {
						ret[y] = count;
					}
				}
			}
			if ((count & 1) == 1) {
				cur = red;
				curFlag = redFlag;
			} else {
				cur = blue;
				curFlag = blueFlag;
			}
			list = next;
		}
		for (int i = 0; i < n; i++) {
			if (ret[i] == Integer.MAX_VALUE) {
				ret[i] = -1;
			}
		}
		return ret;
	}
	
	public int numEquivDominoPairs(int[][] dominoes) {
		int[] count = new int[100];
		for (int[] dominoe : dominoes) {
			count[dominoe[0] * 10 + dominoe[1]]++;
		}
		int ret = 0, sum;
		for (int i = 1; i < 10; i++) {
			sum = count[i * 10 + i];
			ret += sum * (sum - 1) / 2;
			for (int j = i + 1; j < 10; j++) {
				sum = count[i * 10 + j] + count[j * 10 + i];
				ret += sum * (sum - 1) / 2;
			}
		}
		return ret;
	}
	
	public int longestWPI(int[] hours) {
		int len = hours.length;
		int[] exist = new int[len * 2 + 1];
		Arrays.fill(exist, -1);
		int count = 0, ret = 0;
		for (int i = 0; i < len; i++) {
			count += hours[i] > 8 ? 1 : -1;
			if (count > 0) {
				ret = i + 1;
			} else {
				if (exist[count + len + 1] < 0) {
					exist[count + len + 1] = i;
				}
				if (exist[count + len] >= 0) {
					ret = Math.max(ret, i - exist[count + len]);
				}
			}
		}
		return ret;
	}
}
