package com.leetcode;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test6
 * @Author: WuMing
 * @CreateDate: 2019/5/23 15:21
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test6 {
	
	public static void main(String[] args) {
		/*List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<List<String>> lists = new ArrayList<>();
		lists.add(list1);
		lists.add(list2);
		list1.add("a");
		list1.add("b");
		list2.add("b");
		list2.add("c");
		double[] doubles = {2, 3};
		List<String> list3 = new ArrayList<>();
		List<List<String>> lists1 = new ArrayList<>();
		lists1.add(list3);
		list3.add("a");
		list3.add("c");
		calcEquation(lists, doubles, lists1);*/
		//minimumLengthEncoding(new String[]{"time", "me", "bell", "ime"});
		//fractionAddition("-1/2+1/2+1/3");
		//groupAnagrams(new String[]{"boo", "bob"});
		TreeNode treeNode = new TreeNode(4);
		treeNode.left = new TreeNode(0);
		treeNode.right = new TreeNode(1);
		treeNode.left.left = new TreeNode(1);
		//smallestFromLeaf(treeNode);
		//findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});
		//longestArithSeqLength(new int[]{13, 10, 9, 4, 7, 2, 10, 4});
		//numTrees(3);
		//findTargetSumWays(new int[]{1, 1, 1, 1, 1, 3, 4, 5}, 3);
		//findDiagonalOrder(new int[][]{{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 8, 9, 0}});
		//largestSumOfAverages(new int[]{9, 1, 2, 3, 9}, 3);
		//maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9});
		//findLength(new int[]{1, 2, 5, 3, 2, 1}, new int[]{5, 3, 2, 1, 4, 7});
		//magicalString(11);
		//originalDigits("nnie");
		//maxArea1(new int[]{1, 2, 3, 4});
		//decodeString("3[a]2[bc]");
		Test6 test = new Test6();
		//test.lastRemaining1(6);
		test.combinationSum4(new int[]{1,2,3,6,9}, 12);
	}
	
	
	public int combinationSum4(int[] nums, int target) {
		if(nums.length<1){
			return 0;
		}
		Arrays.sort(nums);
		
		if (nums[0] > target) {
			return 0;
		} else if (nums[0] == target) {
			return 1;
		} else {
			int[] ints = new int[target + 1];
			for (int i = nums[0]; i <= target; i++) {
				for (int j = 0; j < nums.length; j++) {
					if (i - nums[j] > 0) {
						ints[i] += ints[i - nums[j]];
					} else {
						if (i == nums[j]) {
							ints[i]++;
						}
						break;
					}
				}
			}
			return ints[target];
		}
		
	}
	
	
	public int bulbSwitch(int n) {
		int count = 0;
		for (int i = 1; i * i <= n; i++) {
			count++;
		}
		return count;
	}
	
	public int lastRemaining(int n) {
		if (n < 3) {
			return n;
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 2; i < n; i += 2) {
			list.add(i);
		}
		return lastRemainingRHelper(list);
	}
	
	public int lastRemaining1(int n) {
		return 2 * (n / 2 + 1 - lastRemaining1(n / 2));
	}
	
	private int lastRemainingRHelper(LinkedList<Integer> list) {
		int size = list.size();
		if (size == 1) {
			return list.get(0);
		}
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = size - 2; i >= 0; i -= 2) {
			list1.addFirst(list.get(i));
		}
		return lastRemainingLHelper(list1);
	}
	
	private int lastRemainingLHelper(LinkedList<Integer> list) {
		int size = list.size();
		if (size == 1) {
			return list.get(0);
		}
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = 1; i < size; i += 2) {
			list1.add(list.get(i));
		}
		return lastRemainingRHelper(list1);
	}
	
	public static String decodeString(String s) {
		int l = 0, r = s.length() - 1;
		char[] array = s.toCharArray();
		return decodeStringHelper(array, l, r);
		
	}
	
	private static String decodeStringHelper(char[] array, int l, int r) {
		StringBuffer ret = new StringBuffer();
		while (l <= r && ((array[l] >= 'a' && array[l] <= 'z') || (array[l] >= 'A'
			&& array[l] <= 'Z'))) {
			ret.append(array[l]);
			l++;
		}
		int i = 0;
		while (l <= r && array[l] >= '0' && array[l] <= '9') {
			i *= 10;
			i += array[l] - '0';
			l++;
		}
		int nl = ++l, k = 1;
		while (l <= r && k > 0) {
			if (array[l] == '[') {
				k++;
			} else if (array[l] == ']') {
				k--;
			}
			l++;
		}
		if (nl <= l - 2) {
			String s = decodeStringHelper(array, nl, l - 2);
			while (i > 0) {
				ret.append(s);
				i--;
			}
		}
		if (l <= r) {
			ret.append(decodeStringHelper(array, l, r));
		}
		return ret.toString();
	}
	
	static int maxArea;
	
	public static int maxArea1(int[] height) {
		int l = 0, r = height.length - 1, max = 0;
		while (l < r) {
			int hl = height[l], hr = height[r];
			int h = Math.min(hl, hr);
			max = Math.max(max, h * (r - l));
			if (hl < hr) {
				while (l < r && height[l] <= h) {
					l++;
				}
			} else {
				while (l < r && height[r] <= h) {
					r--;
				}
			}
		}
		return max;
	}
	
	public static int maxArea(int[] height) {
		for (int i = 0; i < height.length; i++) {
			maxAreaHelper(height, i);
		}
		return maxArea;
	}
	
	private static void maxAreaHelper(int[] height, int i) {
		int max = 0, r = height.length - 1;
		while (i < r && height[i] > height[r]) {
			if (height[i] * (r - i) < maxArea) {
				break;
			}
			max = Math.max(max, (r - i) * height[r]);
			r--;
		}
		if (r > i) {
			max = Math.max(max, (r - i) * height[i]);
		}
		if (max > maxArea) {
			maxArea = max;
		}
	}
	
	public int longestStrChain(String[] words) {
		HashMap<String, Integer>[] maps = new HashMap[17];
		for (String word : words) {
			int length = word.length();
			if (maps[length] == null) {
				maps[length] = new HashMap<>();
			}
			maps[length].put(word, 1);
		}
		int max = 1;
		for (int i = 16; i >= 1; i--) {
			if (maps[i] != null && maps[i - 1] != null) {
				Set<String> strings = maps[i - 1].keySet();
				for (String after : maps[i].keySet()) {
					for (String before : strings) {
						if (longestStrChainHelper(before, after)) {
							int value = 1 + maps[i].get(after);
							if (value > max) {
								max = value;
							}
							maps[i - 1].put(before, Math.max(maps[i - 1].get(before), value));
						}
					}
				}
			}
		}
		return max;
	}
	
	private boolean longestStrChainHelper(String before, String after) {
		char[] a = before.toCharArray();
		char[] b = after.toCharArray();
		boolean flag = true;
		for (int i = 0, j = 0; i < a.length; ) {
			if (a[i] == b[j]) {
				i++;
				j++;
			} else if (flag) {
				flag = false;
				j++;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static int[] prevPermOpt1(int[] A) {
		int k = A.length;
		int i = A.length - 2;
		for (; i >= 0; i--) {
			int j = A.length - 1;
			while (j > i) {
				if (A[i] > A[j]) {
					if (k == A.length) {
						k = j;
					} else if (A[k] <= A[j]) {
						k = j;
					}
				}
				j--;
			}
			if (k != A.length) {
				break;
			}
		}
		if (k == A.length) {
			return A;
		} else {
			int temp = A[k];
			A[k] = A[i];
			A[i] = temp;
			return A;
		}
	}
	
	public static String originalDigits(String s) {
		int[] ints = new int[26];
		for (char c : s.toCharArray()) {
			ints[c - 'a']++;
		}
		int[] count = new int[10];
		String[] strings = {"zero", "two", "four", "six", "seven", "five", "one", "three", "eight",
			"nine"
		};
		int[] num = {0, 2, 4, 6, 7, 5, 1, 3, 8, 9};
		char[] chars = {'z', 'w', 'u', 'x', 's', 'v', 'o', 'r', 'g', 'e'};
		for (int i = 0; i < chars.length; i++) {
			if (ints[chars[i] - 'a'] > 0) {
				int cnt = ints[chars[i] - 'a'];
				count[num[i]] = cnt;
				for (char c : strings[i].toCharArray()) {
					ints[c - 'a'] -= cnt;
				}
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < count.length; i++) {
			while (count[i] > 0) {
				buffer.append(i);
				count[i]--;
			}
		}
		return buffer.toString();
	}
	
	public static int magicalString1(int n) {
		int[] ints = new int[n + 1];
		ints[0] = 1;
		int k = 0, x = 1;
		for (int i = 0; i < n; k++) {
			if (ints[k] == 1) {
				ints[i++] = x;
			} else {
				ints[i++] = x;
				ints[i++] = x;
			}
			x = 3 - x;
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (ints[i] == 1) {
				count++;
			}
		}
		return count;
	}
	
	public static int magicalString(int n) {
		int[] ints = new int[n];
		ints[0] = 1;
		ints[1] = 2;
		ints[2] = 2;
		int s = 2, f = 3;
		int t = 1;
		int count = 1;
		while (f < n) {
			if (ints[s] == 2) {
				ints[f++] = t;
				if (f < n) {
					ints[f++] = t;
					if (t == 1) {
						count++;
					}
				}
				if (t == 1) {
					count++;
					t = 2;
				} else {
					t = 1;
				}
			} else {
				ints[f++] = t;
				if (t == 1) {
					count++;
					t = 2;
				} else {
					t = 1;
				}
			}
			s++;
		}
		return count;
	}
	
	public static int findLength(int[] A, int[] B) {
		int max = 0;
		int[][] dp = new int[A.length + 1][B.length + 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if (A[i] == B[j]) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = 0;
				}
				if (dp[i + 1][j + 1] > max) {
					max = dp[i + 1][j + 1];
				}
			}
		}
		return max;
	}
	
	public static double largestSumOfAverages(int[] A, int K) {
		int[] sum = new int[A.length + 1];
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
		}
		double[][] dp = new double[A.length + 1][K + 1];
		for (int i = 1; i < sum.length; i++) {
			dp[i][1] = 1.0 * sum[i] / i;
			for (int k = 2; k <= K && k <= i; k++) {
				for (int j = 1; j < i; j++) {
					dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + 1.0 * (sum[i] - sum[j]) / (i - j));
				}
			}
		}
		return dp[A.length][K];
	}
	
	public int minEatingSpeed(int[] piles, int H) {
		int l = 1;
		int r = 1000000000;
		while (l < r) {
			int k = l + (r - l) / 2;
			if (minEatingSpeedHelper(k, piles, H)) {
				r = k;
			} else {
				l = k + 1;
			}
		}
		return l;
		
	}
	
	public List<String> wordSubsets(String[] A, String[] B) {
		int[] mask = new int[26];
		for (String s : B) {
			int[] temp = new int[26];
			for (char c : s.toCharArray()) {
				temp[c - 'a']++;
			}
			for (int i = 0; i < mask.length; i++) {
				if (mask[i] < temp[i]) {
					mask[i] = temp[i];
				}
			}
		}
		ArrayList<String> list = new ArrayList<>();
		for (String s : A) {
			int[] temp = new int[26];
			for (char c : s.toCharArray()) {
				temp[c - 'a']++;
			}
			int i = 0;
			for (; i < mask.length; i++) {
				if (temp[i] < mask[i]) {
					break;
				}
			}
			if (i == mask.length) {
				list.add(s);
			}
		}
		return list;
	}
	
	public static int maxTurbulenceSize(int[] A) {
		int max = 1, cur = 1;
		int i = 1;
		boolean state = false;
		for (; i < A.length; i++) {
			if (A[i - 1] == A[i]) {
				cur = 1;
			} else if (A[i - 1] > A[i]) {
				if (cur == 1) {
					state = true;
					cur++;
				} else {
					if (state) {
						cur = 2;
					} else {
						state = !state;
						cur++;
						
					}
				}
			} else {
				if (cur == 1) {
					state = false;
					cur++;
				} else {
					if (state) {
						state = !state;
						cur++;
					} else {
						cur = 2;
					}
				}
				
			}
			if (cur > max) {
				max = cur;
			}
		}
		return max;
	}
	
	private boolean minEatingSpeedHelper(int k, int[] piles, int h) {
		for (int pile : piles) {
			h -= ((pile - 1) / k + 1);
			if (h <= 0) {
				return false;
			}
		}
		return true;
	}
	
	public int openLock(String[] deadends, String target) {
		boolean[] dead = new boolean[10000];
		for (String deadend : deadends) {
			dead[Integer.parseInt(deadend)] = true;
		}
		int t = Integer.parseInt(target);
		if (t == 0) {
			return 0;
		}
		if (dead[0]) {
			return -1;
		}
		dead[0] = true;
		LinkedList<int[]> a = new LinkedList<>();
		a.add(new int[]{0, 0});
		while (!a.isEmpty()) {
			int[] ints = a.removeFirst();
			List<Integer> next = getNext(ints[0]);
			for (Integer i : next) {
				if (i == t) {
					return ints[1] + 1;
				}
				if (!dead[i]) {
					a.add(new int[]{i, ints[1] + 1});
					dead[i] = true;
				}
			}
		}
		return -1;
	}
	
	private List<Integer> getNext(int v) {
		ArrayList<Integer> integers = new ArrayList<>();
		int t = v;
		for (int i = 1; i < 10000; i *= 10) {
			int l = t % 10;
			if (l < 9) {
				integers.add(v + i);
			} else {
				integers.add(v - 9 * i);
			}
			if (l > 0) {
				integers.add(v - i);
			} else {
				integers.add(v + 9 * i);
			}
			t /= 10;
		}
		return integers;
	}
	
	public static int[] findDiagonalOrder(int[][] matrix) {
		if (matrix.length < 1) {
			return new int[0];
		}
		int[] ret = new int[matrix.length * matrix[0].length];
		findDiagonalOrderUp(ret, 0, 0, 0, matrix);
		return ret;
	}
	
	private static void findDiagonalOrderUp(int[] ret, int index, int i, int j, int[][] matrix) {
		if (i == matrix.length - 1 && j == matrix[0].length - 1) {
			ret[index] = matrix[i][j];
			return;
		}
		while (i >= 0 && j < matrix[0].length) {
			ret[index] = matrix[i][j];
			i--;
			j++;
			index++;
		}
		if (i < 0 && j == matrix[0].length) {
			findDiagonalOrderDown(ret, index, i + 2, j - 1, matrix);
		} else if (i < 0) {
			findDiagonalOrderDown(ret, index, i + 1, j, matrix);
		} else {
			findDiagonalOrderDown(ret, index, i + 2, j - 1, matrix);
		}
	}
	
	private static void findDiagonalOrderDown(int[] ret, int index, int i, int j, int[][] matrix) {
		if (i == matrix.length - 1 && j == matrix[0].length - 1) {
			ret[index] = matrix[i][j];
			return;
		}
		while (j >= 0 && i < matrix.length) {
			ret[index] = matrix[i][j];
			i++;
			j--;
			index++;
		}
		if (j < 0 && i == matrix.length) {
			findDiagonalOrderUp(ret, index, i - 1, j + 2, matrix);
		} else if (j < 0) {
			findDiagonalOrderUp(ret, index, i, j + 1, matrix);
		} else {
			findDiagonalOrderUp(ret, index, i - 1, j + 2, matrix);
		}
	}
	
	public static String findLongestWord(String s, List<String> d) {
		char[] array = s.toCharArray();
		String ret = "";
		for (int i = 0; i < d.size(); i++) {
			if (ret.length() <= d.get(i).length()) {
				char[] dc = d.get(i).toCharArray();
				int l = 0;
				for (int j = 0; j < array.length; j++) {
					if (array[j] == dc[l]) {
						l++;
					}
					if (l == dc.length) {
						if (ret.length() == d.get(i).length()) {
							if (ret.compareTo(d.get(i)) > 0) {
								ret = d.get(i);
							}
						} else {
							ret = d.get(i);
						}
						break;
					}
				}
			}
		}
		return ret;
	}
	
	public static int findTargetSumWays1(int[] nums, int S) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (Math.abs(S) > sum || ((S + sum) & 1) == 1) {
			return 0;
		}
		int target = (S + sum) >> 1;
		int[] ints = new int[target + 1];
		ints[0] = 1;
		for (int num : nums) {
			for (int i = target; i >= num; i--) {
				ints[i] += ints[i - num];
			}
		}
		return ints[target];
	}
	
	public static int findTargetSumWays(int[] nums, int S) {
		if (Math.abs(S) > 1000) {
			return 0;
		}
		int[] a = new int[2001];
		a[nums[0] + 1000]++;
		a[-nums[0] + 1000]++;
		for (int i = 1; i < nums.length; i++) {
			int[] b = new int[41];
			for (int j = 0; j < a.length; j++) {
				if (a[j] != 0) {
					b[j + nums[i]] += a[j];
					b[j - nums[i]] += a[j];
				}
			}
			a = b;
		}
		return a[20 + S];
	}
	
	public static int lenLongestFibSubseq(int[] A) {
		int[][] ints = new int[A.length][A.length];
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			int sum = A[i], l = 0, r = i - 1;
			while (l < r) {
				int temp = A[l] + A[r];
				if (temp == sum) {
					ints[r][i] = ints[l][r] + 1;
					if (ints[r][i] > max) {
						max = ints[r][i];
					}
					r--;
					l++;
				} else if (temp > sum) {
					r--;
				} else {
					l++;
				}
			}
		}
		return max == 0 ? 0 : max + 2;
	}
	
	public List<Integer> lexicalOrder(int n) {
		ArrayList<Integer> ret = new ArrayList<>();
		
		for (int i = 1; i < 10; i++) {
			if (i <= n) {
				ret.add(i);
				lexicalOrderHelper(i * 10, n, ret);
			}
		}
		return ret;
	}
	
	private void lexicalOrderHelper(int i, int n, ArrayList<Integer> ret) {
		for (int j = 0; j < 10; j++) {
			if (i + j <= n) {
				ret.add(i + j);
				lexicalOrderHelper((i + j) * 10, n, ret);
			} else {
				break;
			}
		}
	}
	
	public static String findReplaceString(String S, int[] indexes, String[] sources,
		String[] targets) {
		int[] flag = new int[S.length()];
		Arrays.fill(flag, -1);
		char[] array = S.toCharArray();
		for (int i = 0; i < indexes.length; i++) {
			int s = indexes[i], j = 0;
			char[] array1 = sources[i].toCharArray();
			while (s + j < array.length && j < array1.length && array[s + j] == array1[j]) {
				j++;
			}
			if (j == sources[i].length()) {
				flag[indexes[i]] = i;
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; ) {
			if (flag[i] < 0) {
				buffer.append(array[i]);
				i++;
			} else {
				buffer.append(targets[flag[i]]);
				i += sources[flag[i]].length();
			}
		}
		return buffer.toString();
	}
	
	public void gameOfLife(int[][] board) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				gameOfLifeHelper(list, i, j, board);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			int y = list.get(i);
			int x = list.get(i + 1);
			board[y][x] = 1 ^ board[y][x];
		}
	}
	
	private void gameOfLifeHelper(ArrayList<Integer> list, int i, int j, int[][] board) {
		int state = board[i][j];
		int count = 0;
		if (i > 0) {
			if (j > 0) {
				count += board[i - 1][j - 1];
			}
			count += board[i - 1][j];
			if (j < board[i].length - 1) {
				count += board[i - 1][j + 1];
			}
		}
		if (j > 0) {
			count += board[i][j - 1];
		}
		if (j < board[i].length - 1) {
			count += board[i][j + 1];
		}
		if (i < board.length - 1) {
			if (j > 0) {
				count += board[i + 1][j - 1];
			}
			count += board[i + 1][j];
			if (j < board[i].length - 1) {
				count += board[i + 1][j + 1];
			}
		}
		if (state == 0 && count == 3) {
			if (count == 3) {
				list.add(i);
				list.add(j);
			}
		} else if (state == 1) {
			if (count > 3 || count < 2) {
				list.add(i);
				list.add(j);
			}
		}
	}
	
	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode tail = new ListNode(0);
		ListNode ret = head.next;
		if (ret == null) {
			return head;
		}
		while (head != null) {
			ListNode a = head;
			ListNode b = a.next;
			if (b == null) {
				tail.next = a;
				break;
			}
			head = b.next;
			b.next = a;
			a.next = head;
			tail.next = b;
			tail = a;
		}
		return ret;
	}
	
	public int singleNumber1(int[] nums) {
		int ret = 0, count = 0;
		for (int i = 0; i < 32; i++) {
			
			count = 0;
			for (int num : nums) {
				count += (num >> i) & 1;
			}
			if (count % 3 != 0) {
				ret |= 1 << i;
			}
		}
		return ret;
	}
	
	public int singleNumber(int[] nums) {
		int a = 0, b = 0;
		for (int num : nums) {
			a = (a ^ num) & ~b;
			b = (b ^ num) & ~a;
		}
		return a;
	}
	
	public static List<Integer> grayCode(int n) {
		ArrayList<Integer> ret = new ArrayList<>();
		ret.add(0);
		for (int i = 1; i <= n; i++) {
			int k = ret.size() - 1;
			int temp = 1 << (i - 1);
			while (k >= 0) {
				ret.add(temp + ret.get(k));
				k--;
			}
		}
		return ret;
	}
	
	public int deleteAndEarn(int[] nums) {
		int[] ints = new int[10001];
		for (int num : nums) {
			ints[num] += num;
		}
		for (int i = 2; i < ints.length; i++) {
			ints[i] = Math.max(ints[i] + ints[i - 2], ints[i - 1]);
		}
		return ints[ints.length - 1];
	}
	
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int close = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length; i++) {
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (Math.abs(close - target) > Math.abs(sum - target)) {
					close = sum;
				}
				if (sum > target) {
					r--;
				} else if (sum < target) {
					l++;
				} else {
					return target;
				}
			}
		}
		return close;
	}
	
	public static int longestArithSeqLength(int[] A) {
		if (A.length < 3) {
			return A.length;
		}
		int[][] dp = new int[A.length][20001];
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				int x = A[i] - A[j] + 10000;
				dp[i][x] = dp[j][x] + 1;
				if (dp[i][x] > max) {
					max = dp[i][x];
				}
			}
		}
		return max + 1;
	}
	
	public static int numTrees(int n) {
		int[] ints = new int[n + 1];
		ints[0] = 1;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < i; j++) {
				ints[i] += ints[j] * ints[i - j - 1];
			}
		}
		return ints[n];
	}
	
	public static int findMinArrowShots(int[][] points) {
		if (points.length < 1) {
			return 0;
		}
		Arrays.sort(points, (a, b) -> (a[1] - b[1]));
		int count = 1;
		int r = points[0][1];
		for (int i = 1; i < points.length; i++) {
			if (r < points[i][0]) {
				count++;
				r = points[i][1];
			}
		}
		return count;
	}
	
	public int minSteps(int n) {
		if (n == 1) {
			return 0;
		}
		int i = 2;
		while (n % i != 0 && i < n) {
			i++;
		}
		return n / (n / i) + minSteps(n / i);
	}
	
	public int[][] generateMatrix(int n) {
		int[][] ret = new int[n][n];
		generateMatrixHelper(ret, 0, -1, 1, n);
		return ret;
	}
	
	private void generateMatrixHelper(int[][] ret, int i, int j, int cur, int n) {
		if (cur > n * n) {
			return;
		}
		while (j < n - 1 && ret[i][j + 1] == 0) {
			j++;
			ret[i][j] = cur++;
		}
		while (i < n - 1 && ret[i + 1][j] == 0) {
			i++;
			ret[i][j] = cur++;
		}
		while (j > 0 && ret[i][j - 1] == 0) {
			j--;
			ret[i][j] = cur++;
		}
		while (i > 0 && ret[i - 1][j] == 0) {
			i--;
			ret[i][j] = cur++;
		}
		generateMatrixHelper(ret, i, j, cur, n);
	}
	
	static String smallest = (char) ('z' + 1) + "";
	
	public static String smallestFromLeaf(TreeNode root) {
		LinkedList<Integer> list = new LinkedList<>();
		smallestHelper(root, list);
		return smallest;
	}
	
	private static void smallestHelper(TreeNode root, LinkedList<Integer> list) {
		list.add(root.val);
		if (root.left == null && root.right == null) {
			int l = list.size() - 1;
			int i = 0;
			for (; i < smallest.length() && l - i >= 0; i++) {
				if (smallest.charAt(i) > list.get(l - i) + 'a') {
					break;
				} else if (smallest.charAt(i) < list.get(l - i) + 'a') {
					return;
				}
			}
			if (i != smallest.length()) {
				StringBuilder s = new StringBuilder();
				for (; l >= 0; l--) {
					s.append((char) ('a' + list.get(l)));
				}
				smallest = s.toString();
			}
			return;
		}
		if (root.left != null) {
			smallestHelper(root.left, list);
			list.removeLast();
		}
		if (root.right != null) {
			smallestHelper(root.right, list);
			list.removeLast();
		}
	}
	
	int longest;
	
	public int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];
		for (int i = dp.length - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < dp.length; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][s.length() - 1];
	}
	
	
	public static List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			int[] ints = new int[26];
			for (char c : s.toCharArray()) {
				ints[c - 'a']++;
			}
			String l = "";
			for (int i = 0; i < ints.length; i++) {
				if (ints[i] != 0) {
					l += i + "_" + ints[i] + "_";
				}
			}
			if (!map.containsKey(l)) {
				map.put(l, new ArrayList<>());
			}
			map.get(l).add(s);
		}
		ArrayList<List<String>> ret = new ArrayList<>();
		for (String l : map.keySet()) {
			ret.add(map.get(l));
		}
		return ret;
	}
	
	public boolean isSubsequence(String s, String t) {
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
		int j = 0;
		for (int i = 0; i < ta.length && j < sa.length; i++) {
			if (ta[i] == sa[j]) {
				j++;
			}
		}
		return j == sa.length;
	}
	
	static boolean change = false;
	static int hight = 0;
	static boolean isCompleteTree = true;
	
	public static boolean isCompleteTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		TreeNode temp = root;
		while (temp != null) {
			temp = temp.left;
			hight++;
		}
		isCompleteTreeHelper(root, 0);
		return isCompleteTree;
	}
	
	private static void isCompleteTreeHelper(TreeNode root, int cur) {
		if (root == null) {
			if (hight - cur > 1 || cur > hight) {
				isCompleteTree = false;
				return;
			} else if (hight - cur == 1) {
				if (change) {
					isCompleteTree = false;
					return;
				} else {
					change = true;
					hight = cur;
					return;
				}
			} else {
				return;
			}
		}
		isCompleteTreeHelper(root.left, cur + 1);
		isCompleteTreeHelper(root.right, cur + 1);
	}
	
	public static boolean PredictTheWinner(int[] nums) {
		if ((nums.length & 1) == 0) {
			return true;
		}
		int[] ints = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length - i; j++) {
				ints[j] = Math.max(nums[j + i] - ints[j], nums[j] - ints[j + 1]);
			}
		}
		return ints[0] >= 0;
	}
	
	public int minPathSum(int[][] grid) {
		int x = grid[0].length;
		int y = grid.length;
		for (int i = 1; i < x; i++) {
			grid[0][i] = grid[0][i] + grid[0][i - 1];
			
		}
		for (int i = 1; i < y; i++) {
			grid[i][0] = grid[i][0] + grid[i - 1][0];
			for (int j = 1; j < x; j++) {
				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
			}
		}
		return grid[x - 1][y - 1];
	}
	
	public int countNumbersWithUniqueDigits(int n) {
		
		if (n == 1) {
			return 10;
		} else if (n == 0) {
			return 0;
		}
		int k = 9, ret = 9;
		for (int i = 0; i < n - 1; i++) {
			ret *= k--;
		}
		return ret + countNumbersWithUniqueDigits(n - 1);
	}
	
	public static String fractionAddition(String expression) {
		int a = 0, b = 1;
		int flag = 1;
		int i = 0;
		char[] array = expression.toCharArray();
		if (array[i] == '-') {
			flag = -1;
			i++;
		}
		for (; i < array.length; i++) {
			int x = 0;
			while (array[i] >= '0' && array[i] <= '9') {
				
				x *= 10;
				x += array[i++] - '0';
			}
			x = x * flag;
			i++;
			int y = 0;
			while (i < array.length && array[i] >= '0' && array[i] <= '9') {
				y *= 10;
				y += array[i++] - '0';
			}
			int gcd = gcd(b, y);
			a = (a * y + x * b) / gcd;
			b = b * y / gcd;
			if (i < array.length && array[i] == '-') {
				flag = -1;
			} else {
				flag = 1;
			}
		}
		int gcd = gcd(Math.abs(a), b);
		a = a / gcd;
		b = b / gcd;
		return a + "/" + b;
	}
	
	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	int gbs(int a, int b) {
		int gcd = gcd(a, b);
		return b * a / gcd;
	}
	
	static class TrieNode {
		
		char c;
		TrieNode[] children;
		
		public TrieNode(char c) {
			this.c = c;
		}
	}
	
	static int minimumLength;
	
	public static int minimumLengthEncoding(String[] words) {
		TrieNode root = new TrieNode('0');
		for (String word : words) {
			TrieNode r = root;
			char[] array = word.toCharArray();
			for (int i = array.length - 1; i >= 0; i--) {
				char c = array[i];
				if (r.children == null) {
					r.children = new TrieNode[26];
					r.children[c - 'a'] = new TrieNode(c);
				} else if (r.children[c - 'a'] == null) {
					r.children[c - 'a'] = new TrieNode(c);
				}
				r = r.children[c - 'a'];
			}
		}
		minimumLengthEncodingHelper(root, 0);
		return minimumLength;
	}
	
	private static void minimumLengthEncodingHelper(TrieNode root, int i) {
		if (root.children == null) {
			minimumLength += (i + 1);
			return;
		}
		for (TrieNode child : root.children) {
			if (child != null) {
				minimumLengthEncodingHelper(child, i + 1);
			}
		}
	}
	
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		ArrayList<Integer> ret = new ArrayList<>();
		addValueToList(target, ret, K);
		distanceKHelper(root, target, K, ret);
		return ret;
	}
	
	private int distanceKHelper(TreeNode root, TreeNode target, int k, List<Integer> ret) {
		if (root == null) {
			return -1;
		}
		if (root == target) {
			return k - 1;
		}
		int l = distanceKHelper(root.left, target, k, ret);
		if (l > 0) {
			addValueToList(root.right, ret, l - 1);
			return l - 1;
		} else if (l == 0) {
			ret.add(root.val);
			return -1;
		}
		int r = distanceKHelper(root.right, target, k, ret);
		if (r > 0) {
			addValueToList(root.left, ret, r - 1);
			return r - 1;
		} else if (r == 0) {
			ret.add(root.val);
			return -1;
		}
		return -1;
	}
	
	private void addValueToList(TreeNode root, List<Integer> ret, int l) {
		if (root == null) {
			return;
		}
		if (l == 0) {
			ret.add(root.val);
			return;
		}
		addValueToList(root.left, ret, l - 1);
		addValueToList(root.right, ret, l - 1);
	}
	
	
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode treeNode = new TreeNode(v);
			treeNode.left = root;
			return treeNode;
		}
		addOneRowhelper(root, v, d - 1);
		return root;
	}
	
	private void addOneRowhelper(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode l = new TreeNode(v);
			TreeNode r = new TreeNode(v);
			l.left = root.left;
			r.right = root.right;
			root.left = l;
			root.right = r;
			return;
		}
		if (root.left != null) {
			addOneRowhelper(root.left, v, d - 1);
		}
		if (root.right != null) {
			addOneRowhelper(root.right, v, d - 1);
		}
	}
	
	public int videoStitching(int[][] clips, int T) {
		int[] ints = new int[T];
		for (int[] clip : clips) {
			if (clip[0] < T && ints[clip[0]] < clip[1]) {
				ints[clip[0]] = clip[1];
			}
		}
		return videoStitchingHelper(ints, 0, 0, T, 0);
	}
	
	private int videoStitchingHelper(int[] ints, int begin, int end, int t, int count) {
		if (end >= t) {
			return count;
		}
		int maxIndex = begin;
		for (int i = begin; i <= end; i++) {
			if (ints[maxIndex] < ints[i]) {
				maxIndex = i;
			}
		}
		if (ints[maxIndex] <= end) {
			return -1;
		} else {
			return videoStitchingHelper(ints, maxIndex, ints[maxIndex], t, count + 1);
		}
	}
	
	
	public static double[] calcEquation(List<List<String>> equations, double[] values,
		List<List<String>> queries) {
		HashMap<String, Map<String, Double>> map = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			List<String> list = equations.get(i);
			Map<String, Double> before = map.get(list.get(0));
			if (before == null) {
				before = new HashMap<>();
				map.put(list.get(0), before);
			}
			before.put(list.get(1), values[i]);
			Map<String, Double> after = map.get(list.get(1));
			if (after == null) {
				after = new HashMap<>();
				map.put(list.get(1), after);
			}
			after.put(list.get(0), 1 / values[i]);
		}
		double[] ret = new double[queries.size()];
		for (int i = 0; i < ret.length; i++) {
			List<String> list = queries.get(i);
			ret[i] = calcEquationHelper(list.get(0), list.get(1), map, 1);
		}
		return ret;
	}
	
	private static double calcEquationHelper(String before, String after,
		HashMap<String, Map<String, Double>> map, double v) {
		
		Map<String, Double> am = map.get(before);
		if (am == null) {
			return -1;
		}
		if (before.equals(after)) {
			return 1;
		}
		if (am.containsKey(after)) {
			return v * am.get(after);
		} else {
			map.remove(before);
			double v1 = -1;
			for (String s : am.keySet()) {
				Double k = am.get(s) * v;
				v1 = calcEquationHelper(s, after, map, k);
				if (v1 != -1) {
					break;
				}
			}
			map.put(before, am);
			return v1;
		}
	}
	
	public int uniquePaths(int m, int n) {
		int[][] ints = new int[m][n];
		for (int i = 0; i < n; i++) {
			ints[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			ints[i][0] = 1;
			for (int j = 1; j < n; j++) {
				ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
			}
		}
		return ints[m - 1][n - 1];
	}
	
	public List<List<Integer>> combine(int n, int k) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		combineHelper(1, n, k, new LinkedList<>(), lists);
		return lists;
	}
	
	private void combineHelper(int begin, int n, int l, LinkedList<Integer> list,
		ArrayList<List<Integer>> lists) {
		if (l == 0) {
			lists.add(new LinkedList<>(list));
			return;
		}
		for (int i = begin; i <= n - l + 1; i++) {
			list.add(i);
			combineHelper(i + 1, n, l - 1, list, lists);
			list.removeLast();
		}
	}
}
