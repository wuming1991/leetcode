package com.leetcode;

import com.wuming.pattern.Observer.AllyControlCenter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test4
 * @Author: WuMing
 * @CreateDate: 2019/5/7 19:46
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test4 {
	
	public static void main(String[] args) throws ParseException {
		//removeStones1(new int[][]{{0, 3}, {0, 2}, {4, 3}, {2, 4}, {0, 1}, {1, 1}});
		//longestOnes1(new int[]{0,0,1,1,0,0}, 0);
		//subsets(new int[]{1,2,3});
		//arrayNesting(new int[]{5,4,0,3,1,6,2});
		//shipWithinDays1(new int[]{3,2,2,4,1,4},3);
		//	findPoisonedDuration(new int[]{1, 3}, 2);
		//largestOverlap(new int[][]{{1,1,0},{0,1,0},{0,1,0}},new int[][]{{0,0,0},{0,1,1},{0,0,1}});
		/*TreeNode treeNode = new TreeNode(1);
		treeNode.left = new TreeNode(2);
		treeNode.left.left = new TreeNode(4);
		treeNode.right = new TreeNode(3);
		printTree(treeNode);
		replaceWords1(new String[]{"a"}, "a bbb baba ababa");*/
		//maxChunksToSorted(new int[]{1, 0, 2, 3, 4});
		findRedundantConnection(new int[][]{
			{21, 22}, {4, 7}, {12, 13}, {13, 25},
			{12, 15}, {17, 23}, {15, 16}, {8, 21},
			{23, 24}, {3, 9}, {19, 21}, {13, 21},
			{4, 10}, {5, 6}, {1, 20}, {10, 16},
			{1, 4}, {10, 14}, {5, 20}, {1, 2},
			{3, 24}, {2, 11}, {11, 24}, {24, 25}, {17, 18}});
	}
	public int numRabbits1(int[] answers) {
		int count=0;
		int[] ints = new int[1000];
		for (int answer : answers) {
			if(answer==0){
				count++;
			}else if(ints[answer]==0){
				count+=answer+1;
				ints[answer]=answer;
			}else{
				ints[answer]--;
			}
		}
		return count;
	}
	public int numRabbits(int[] answers) {
		int[] ints = new int[1000];
		for (int answer : answers) {
			ints[answer]++;
		}
		int count = ints[0];
		for (int i = 1; i < ints.length; i++) {
			if(ints[i]%(i+1)==0){
				count+=(i+1)*(ints[i]/(i+1));
			}else{
				count+=(i+1)*(1+ints[i]/(i+1));
			}
		}
		return count;
	}
	
	public static int[] findRedundantConnection(int[][] edges) {
		int[] ints = new int[edges.length + 1];
		int[] ret = new int[2];
		for (int[] edge : edges) {
			if (ints[edge[1]] == 0) {
				ints[edge[1]] = edge[0];
			} else {
				int x = findBefore(edge[0], ints);
				int y = findBefore(ints[edge[1]], ints);
				if (x == y) {
					ret[0] = edge[0];
					ret[1] = edge[1];
					return ret;
				} else {
					if (y < x) {
						ints[x] = y;
					} else {
						ints[y] = x;
					}
				}
			}
		}
		return ret;
	}
	
	private static int findBefore(int i, int[] ints) {
		if (ints[i] == 0) {
			return i;
		} else {
			return findBefore(ints[i], ints);
		}
	}
	
	public static int maxChunksToSorted(int[] arr) {
		
		int max = arr[0];
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
			if (i == max) {
				count++;
				if (i < arr.length - 1) {
					max = arr[i + 1];
				}
			}
			
			
		}
		return count;
	}
	
	static class TrieNode {
		
		TrieNode[] children;
		String word;
		
		public TrieNode() {
			this.children = new TrieNode[26];
		}
	}
	
	public static String replaceWords1(String[] dict, String sentence) {
		TrieNode tree = new TrieNode();
		for (String s : dict) {
			TrieNode p = tree;
			for (char c : s.toCharArray()) {
				if (p.children[c - 'a'] == null) {
					p.children[c - 'a'] = new TrieNode();
				}
				p = p.children[c - 'a'];
			}
			p.word = s;
		}
		StringBuffer buffer = new StringBuffer();
		boolean flag = true;
		for (String word : sentence.split(" ")) {
			if (flag) {
				flag = false;
			} else {
				buffer.append(" ");
			}
			TrieNode p = tree;
			for (char c : word.toCharArray()) {
				if (p.word != null || p.children[c - 'a'] == null) {
					break;
				} else {
					
					p = p.children[c - 'a'];
				}
			}
			buffer.append(p.word == null ? word : p.word);
		}
		return buffer.toString();
	}
	
	public String replaceWords(List<String> dict, String sentence) {
		dict.sort((a, b) -> (a.length() - b.length()));
		StringBuffer buffer = new StringBuffer();
		String[] words = sentence.split(" ");
		for (String word : words) {
			int i = 0;
			while (i < dict.size() && (!word.startsWith(dict.get(i)))) {
				i++;
			}
			if (i < dict.size()) {
				buffer.append(dict.get(i));
			} else {
				buffer.append(word);
			}
			buffer.append(" ");
		}
		return buffer.toString().trim();
	}
	
	public static List<List<String>> printTree(TreeNode root) {
		int hight = getHight(root);
		Integer[][] tree = new Integer[hight][(1 << hight) - 1];
		fillTree(tree, root, 0, tree[0].length, 0);
		ArrayList<List<String>> lists = new ArrayList<>();
		for (int i = 0; i < tree.length; i++) {
			ArrayList<String> list = new ArrayList<>();
			for (int j = 0; j < tree[i].length; j++) {
				if (tree[i][j] == null) {
					list.add("");
				} else {
					list.add(String.valueOf(tree[i][j]));
				}
			}
			lists.add(list);
		}
		return lists;
	}
	
	private static void fillTree(Integer[][] tree, TreeNode root, int l, int r, int level) {
		if (root == null) {
			return;
		}
		tree[level][(l + r) / 2] = root.val;
		fillTree(tree, root.left, l, (l + r) / 2 - 1, level + 1);
		fillTree(tree, root.right, (l + r) / 2 + 1, r, level + 1);
		
	}
	
	private static int getHight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getHight(root.left), getHight(root.right));
	}
	
	public static boolean isValid(String S) {
		char[] chars = new char[S.length()];
		int index = 0;
		for (char c : S.toCharArray()) {
			chars[index++] = c;
			if (index >= 3 && c == 'c') {
				if (chars[index - 3] == 'a' && chars[index - 2] == 'b' && chars[index - 1] == 'c') {
					index -= 3;
				}
			}
		}
		if (index == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int mirrorReflection(int p, int q) {
		int l = p;
		int k = 0;
		while (l % q != 0) {
			k++;
			l += p;
		}
		if (((l / q) & 1) == 0) {
			return 2;
		} else {
			if (k % 2 == 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	static int largestOverlap;
	
	public static int largestOverlap(int[][] A, int[][] B) {
		int row = A.length;
		int col = A[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				largestOverlapHelper(A, B, i, j);
				largestOverlapHelper(B, A, i, j);
			}
		}
		return largestOverlap;
	}
	
	private static void largestOverlapHelper(int[][] A, int[][] B, int i, int j) {
		int y = B.length - 1;
		int count = 0;
		for (; i >= 0; i--, y--) {
			int x = B[0].length - 1;
			for (int k = j; k >= 0; k--, x--) {
				if (A[i][k] == 1 && B[y][x] == 1) {
					count++;
				}
			}
		}
		if (count > largestOverlap) {
			largestOverlap = count;
		}
	}
	
	
	public static int findPoisonedDuration(int[] timeSeries, int duration) {
		if (timeSeries.length < 1) {
			return 0;
		}
		int start = timeSeries[0];
		int end = timeSeries[0] + duration;
		int total = 0;
		for (int i = 1; i < timeSeries.length; i++) {
			if (timeSeries[i] > end) {
				total += (end - start);
				start = timeSeries[i];
			}
			end = timeSeries[i] + duration;
		}
		total += (end - start);
		return total;
	}
	
	public static int minMoves2(int[] nums) {
		Arrays.sort(nums);
		int l = 0, r = nums.length - 1;
		int ret = 0;
		while (l < r) {
			ret += (nums[r--] - nums[l++]);
		}
		return ret;
	}
	
	public static int shipWithinDays1(int[] weights, int D) {
		int l = weights[0];
		int r = 0;
		for (int weight : weights) {
			r += weight;
			if (weight > l) {
				l = weight;
			}
		}
		while (l < r) {
			int m = l + (r - l) / 2;
			if (shipWithinDaysHelper(weights, D, m)) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	private static boolean shipWithinDaysHelper(int[] weights, int D, int C) {
		int sum = 0;
		int d = 1;
		for (int weight : weights) {
			if (sum + weight <= C) {
				sum += weight;
			} else if (d < D) {
				d++;
				sum = weight;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static int shipWithinDays(int[] weights, int D) {
		int max = weights[0];
		int sum = 0;
		for (int weight : weights) {
			sum += weight;
			if (weight > max) {
				max = weight;
			}
		}
		int avg = sum % D == 0 ? sum / D : sum / D + 1;
		int ret = Math.max(max, avg);
		while (true) {
			int d = D - 1;
			int c = ret;
			int i = 0;
			for (; i < weights.length; ) {
				if (c - weights[i] >= 0) {
					c -= weights[i];
					i++;
				} else if (d > 0) {
					d--;
					c = ret;
				} else {
					break;
				}
			}
			if (i == weights.length) {
				return ret;
			} else {
				ret++;
			}
		}
	}
	
	public static int arrayNesting(int[] nums) {
		boolean[] flag = new boolean[nums.length];
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (!flag[i]) {
				int head = i;
				int next = i;
				int count = 0;
				while (nums[next] != head) {
					flag[next] = true;
					next = nums[next];
					count++;
				}
				flag[next] = true;
				max = Math.max(count + 1, max);
			}
		}
		return max;
	}
	
	public static char[][] updateBoard(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		updateBoardHelper(board, click[0], click[1]);
		return board;
	}
	
	public static List<List<Integer>> subsets(int[] nums) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		ArrayList<Integer> integers = new ArrayList<>();
		lists.add(integers);
		for (int i = 0; i < nums.length; i++) {
			subsetsHelper(lists, nums, 0, i + 1, integers);
		}
		return lists;
	}
	
	private static void subsetsHelper(ArrayList<List<Integer>> lists, int[] nums, int begin, int l,
		List<Integer> list) {
		if (l == 0) {
			lists.add(list);
			return;
		}
		for (int i = begin; i < nums.length; i++) {
			if (nums.length - i >= l) {
				ArrayList<Integer> curr = new ArrayList<>();
				curr.addAll(list);
				curr.add(nums[i]);
				subsetsHelper(lists, nums, i + 1, l - 1, curr);
			}
		}
		
	}
	
	private static void updateBoardHelper(char[][] board, int x, int y) {
		int count = 0;
		if (x > 0) {
			if (board[x - 1][y] == 'M') {
				count++;
			}
			if (y > 0 && board[x - 1][y - 1] == 'M') {
				count++;
			}
			if (y < board[x].length - 1 && board[x - 1][y + 1] == 'M') {
				count++;
			}
		}
		if (y > 0 && board[x][y - 1] == 'M') {
			count++;
		}
		if (y < board[x].length - 1 && board[x][y + 1] == 'M') {
			count++;
		}
		if (x < board.length - 1) {
			if (board[x + 1][y] == 'M') {
				count++;
			}
			if (y > 0 && board[x + 1][y - 1] == 'M') {
				count++;
			}
			if (y < board[x].length - 1 && board[x + 1][y + 1] == 'M') {
				count++;
			}
		}
		if (count == 0) {
			board[x][y] = 'B';
			if (x < board.length - 1) {
				if (board[x + 1][y] == 'E') {
					updateBoardHelper(board, x + 1, y);
				}
				if (y > 0 && board[x + 1][y - 1] == 'E') {
					updateBoardHelper(board, x + 1, y - 1);
				}
				if (y < board[x].length - 1 && board[x + 1][y + 1] == 'E') {
					updateBoardHelper(board, x + 1, y + 1);
				}
			}
			if (x > 0) {
				if (board[x - 1][y] == 'E') {
					updateBoardHelper(board, x - 1, y);
				}
				if (y > 0 && board[x - 1][y - 1] == 'E') {
					updateBoardHelper(board, x - 1, y - 1);
				}
				if (y < board[x].length - 1 && board[x - 1][y + 1] == 'E') {
					updateBoardHelper(board, x - 1, y + 1);
				}
			}
			if (y < board[x].length - 1 && board[x][y + 1] == 'E') {
				updateBoardHelper(board, x, y + 1);
			}
			if (y > 0 && board[x][y - 1] == 'E') {
				updateBoardHelper(board, x, y - 1);
			}
		} else {
			board[x][y] = (char) (count + '0');
		}
	}
	
	static int longestOnes;
	
	public static int longestOnes(int[] A, int K) {
		int l = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 1) {
				l++;
			} else if (A[i] == 0 && K > 0) {
				longestOnesHelper(A, l + 1, K - 1, i);
				if (l + 1 > longestOnes) {
					longestOnes = l + 1;
				}
				l = 0;
			} else {
				if (l > longestOnes) {
					longestOnes = l;
				}
				l = 0;
			}
			
		}
		return longestOnes;
	}
	
	public static int longestOnes1(int[] A, int K) {
		int index = 0;
		int before = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				index = i;
				break;
			} else {
				before++;
			}
		}
		int x = 0;
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 1 || K > 0) {
				x = x + 1;
				if (x > max) {
					max = x;
				}
				if (A[i] == 0) {
					K--;
				}
			} else {
				x = x - before;
				index++;
				before = 0;
				while (index < A.length && A[index] == 1) {
					index++;
					before++;
				}
				
			}
		}
		return max;
	}
	
	private static void longestOnesHelper(int[] a, int l, int K, int index) {
		for (int i = index; i < a.length; i++) {
			if (a[i] == 1 || a[i] == 0 && K > 0) {
				l++;
			} else {
				if (l > longestOnes) {
					longestOnes = l;
				}
				l = 0;
			}
		}
	}
	
	public static int[] beautifulArray(int N) {
		int[] a = new int[N];
		int m = N - 1;
		int k = 1;
		while (m != 1) {
			m >>= 1;
			k <<= 1;
		}
		a[0] = 1;
		int i = 1, t = 1, j;
		while (i < N) {
			for (j = 0; j < t; j++) {
				if (a[j] + k <= N) {
					a[i] = a[j] + k;
					i++;
				}
			}
			t = i;
			k /= 2;
		}
		return a;
	}
	
	int findCircleNum;
	
	public int findCircleNum1(int[][] M) {
		boolean[] booleans = new boolean[M.length];
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (!booleans[i]) {
				count++;
				findCircleNum1Helper(booleans, i, M);
			}
		}
		return count;
	}
	
	private void findCircleNum1Helper(boolean[] booleans, int p, int[][] M) {
		booleans[p] = true;
		for (int i = 0; i < M[p].length; i++) {
			if (!booleans[i] && M[p][i] == 1) {
				findCircleNum1Helper(booleans, i, M);
			}
		}
	}
	
	public int findCircleNum(int[][] M) {
		int[] friend = new int[M.length + 1];
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j] == 1) {
					int x = findFriend(friend, i + 1);
					int y = findFriend(friend, j + 1);
					if (x != y) {
						friend[x] = y;
						findCircleNum--;
					}
				}
			}
		}
		return findCircleNum;
	}
	
	private int findFriend(int[] friend, int i) {
		if (friend[i] == 0) {
			friend[i] = i;
			findCircleNum++;
			return friend[i];
		} else {
			int x = friend[i];
			if (friend[x] != x) {
				return findFriend(friend, x);
			}
			return x;
		}
	}
	
	
	public static String removeOuterParentheses(String S) {
		char[] array = S.toCharArray();
		StringBuffer buffer = new StringBuffer();
		int count = 0;
		int begin = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '(') {
				count++;
			} else if (array[i] == ')') {
				count--;
			}
			if (count == 0) {
				buffer.append(S.substring(begin + 1, i));
				begin = i + 1;
			}
		}
		return buffer.toString();
	}
	
	public static int clumsy(int N) {
		if (N < 3) {
			return N;
		}
		int sum = N * (N - 1) / (N - 2) + (N - 3);
		int i = N - 4;
		for (; i >= 3; ) {
			sum -= i * (i - 1) / (i - 2);
			sum += i - 3;
			i -= 4;
		}
		if (i > 0) {
			sum -= i;
		}
		return sum;
	}
	
	static int[] uf = new int[11];
	static int islands = 0;
	
	public static int removeStones1(int[][] stones) {
		for (int[] s : stones) {
			union(s[0], s[1]);
		}
		return stones.length - islands;
	}
	
	public static void union(int row, int col) {
		int rowParent = find(row + 1);
		int colParent = find(col + 6);
		if (rowParent != colParent) {
			uf[rowParent] = colParent;
			islands--;
		}
	}
	
	public static int find(int x) {
		if (uf[x] == 0) {
			uf[x] = x;
			islands++;
		} else {
			int parent = uf[x];
			if (uf[parent] != parent) {
				uf[x] = find(parent);
			}
		}
		return uf[x];
	}
	
	static int removeStones;
	
	public static int removeStones(int[][] stones) {
		boolean[] booleans = new boolean[stones.length];
		HashSet<Integer> x = new HashSet<>();
		HashSet<Integer> y = new HashSet<>();
		for (int i = 0; i < stones.length; i++) {
			if (!booleans[i]) {
				booleans[i] = true;
				x.add(stones[i][0]);
				y.add(stones[i][1]);
				removeStonesHelper(stones, booleans, x, y);
				x.clear();
				y.clear();
			}
		}
		return removeStones;
	}
	
	private static void removeStonesHelper(int[][] stones, boolean[] booleans, HashSet<Integer> x,
		HashSet<Integer> y) {
		
		boolean f = true;
		while (f) {
			f = false;
			for (int i = 0; i < stones.length; i++) {
				if (!booleans[i]) {
					if (x.contains(stones[i][0])) {
						removeStones++;
						booleans[i] = true;
						y.add(stones[i][1]);
						f = true;
					} else if (y.contains(stones[i][1])) {
						removeStones++;
						booleans[i] = true;
						x.add(stones[i][0]);
						f = true;
					}
				}
			}
		}
	}
	
	public static int minimumDeleteSum1(String s1, String s2) {
		char[] s = s1.toCharArray(), t = s2.toCharArray();
		int m = s.length, n = t.length;
		
		int[][] table = new int[2][n + 1];
		int[] dp = table[0], dpn = table[1];//dpnew dp交替参与计算
		int sum = 0;
		for (int j = 0; j < n; j++) {
			table[0][j + 1] = table[0][j] + t[j];
		}
		for (int i = 0; i < m; i++) {
			sum += s[i];
			dpn[0] = sum;
			for (int j = 0; j < n; j++) {
				if (s[i] == t[j]) {
					dpn[j + 1] = Math.min(dp[j], Math.min(dp[j + 1] + s[i], dpn[j] + t[j]));
				} else {
					dpn[j + 1] = Math.min(dp[j + 1] + s[i], dpn[j] + t[j]);
				}
			}
			int[] tmp = dp;
			dp = dpn;
			dpn = tmp;
		}
		return dp[n];
	}
	
	public static int minimumDeleteSum(String s1, String s2) {
		int r = s1.length();
		int c = s2.length();
		int[][] dp = new int[r + 1][c + 1];
		for (int i = 1; i < r + 1; i++) {
			dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
		}
		for (int i = 1; i < c + 1; i++) {
			dp[0][i] = s2.charAt(i - 1) + dp[0][i - 1];
		}
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math
						.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
				}
			}
		}
		return dp[r][c];
	}
	
	
	int maxFreq;
	
	public int[] findFrequentTreeSum(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		HashMap<Integer, Integer> freq = new HashMap<>();
		findFrequentTreeSumHelper(root, freq);
		int[] ints = new int[freq.size()];
		int index = 0;
		for (Integer sum : freq.keySet()) {
			if (freq.get(sum) == maxFreq) {
				ints[index++] = sum;
			}
		}
		return Arrays.copyOf(ints, index);
	}
	
	private int findFrequentTreeSumHelper(TreeNode root, HashMap<Integer, Integer> freq) {
		int sum = root.val;
		if (root.left != null) {
			sum += findFrequentTreeSumHelper(root.left, freq);
		}
		if (root.right != null) {
			sum += findFrequentTreeSumHelper(root.right, freq);
		}
		Integer f = freq.getOrDefault(sum, 0) + 1;
		if (maxFreq < f) {
			maxFreq = f;
		}
		freq.put(sum, f);
		return sum;
	}
	
	public static List<String> generateParenthesis(int n) {
		ArrayList<String> list = new ArrayList<>();
		if (n < 1) {
			list.add("");
			return list;
		}
		generateParenthesisHelper(list, n - 1, n - 1, "");
		return list;
	}
	
	static int countArrangement;
	
	public static int countArrangement(int N) {
		if (N > 3) {
			int[] ints = new int[N + 1];
			for (int i = 1; i < ints.length; i++) {
				ints[i] = i;
			}
			countArrangementHelper1(ints, N);
			return countArrangement;
		}
		//回溯
		boolean[] booleans = new boolean[N + 1];
		countArrangementHelper(booleans, 1);
		return countArrangement;
	}
	
	private static void countArrangementHelper(boolean[] arr, int index) {
		if (index == arr.length) {
			countArrangement++;
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			if (arr[i]) {
				continue;
			}
			if (index % i == 0 || i % index == 0) {
				arr[i] = true;
				countArrangementHelper(arr, index + 1);
				arr[i] = false;
			}
		}
	}
	
	static void countArrangementHelper1(int[] arr, int begin) {
		if (begin == 0) {
			countArrangement++;
			return;
		}
		for (int i = begin; i > 0; i--) {
			swap(arr, begin, i);
			if (arr[begin] % begin == 0 || begin % arr[begin] == 0) {
				countArrangementHelper1(arr, begin - 1);
			}
			swap(arr, begin, i);
		}
		
	}
	
	private static void swap(int[] arr, int begin, int i) {
		int temp = arr[begin];
		arr[begin] = arr[i];
		arr[i] = temp;
	}
	
	private static void generateParenthesisHelper(ArrayList<String> list, int l, int r, String s) {
		if (l == 0 && r == 0) {
			list.add("(" + s + ")");
			return;
		}
		int lc = l, rc = r;
		if (lc > 0) {
			lc--;
			generateParenthesisHelper(list, lc, rc, s + "(");
		}
		lc = l;
		rc = r;
		if (rc > 0 && rc - lc > -1) {
			rc--;
			generateParenthesisHelper(list, lc, rc, s + ")");
		}
		
		
	}
	
	public int numComponents(ListNode head, int[] G) {
		int[] ints = new int[10001];
		for (int i : G) {
			ints[i]++;
		}
		boolean flag = false;
		int count = 0;
		while (head != null) {
			if (ints[head.val] > 0) {
				flag = true;
			} else if (flag) {
				count++;
				flag = false;
			}
			head = head.next;
		}
		return count;
	}
	
	
	public static List<Integer> topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap();
		for (int n : nums) {
			Integer num = map.getOrDefault(n, 0);
			map.put(n, num + 1);
			
		}
		ArrayList<Integer>[] db = new ArrayList[nums.length + 1];
		for (Integer i : map.keySet()) {
			Integer count = map.get(i);
			if (db[count] == null) {
				db[count] = new ArrayList<>();
			}
			db[count].add(i);
		}
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = db.length - 1; i > 0; i--) {
			if (db[i] != null) {
				for (Integer integer : db[i]) {
					list.add(integer);
					if (list.size() == k) {
						return list;
					}
				}
			}
		}
		return list;
	}
	
	public static int numEnclaves(int[][] A) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][j] == 1) {
					int count1 = numEnclavesHelper(A, i, j);
					if (count1 > 0) {
						count += count1;
					}
				}
			}
		}
		return count;
	}
	
	public static int numEnclaves1(int[][] A) {
		int row = A.length;
		int col = A[0].length;
		for (int i = 0; i < row; i++) {
			if (A[i][0] == 1) {
				numEnclaves1Helper(A, i, 0);
			}
			if (A[i][col - 1] == 1) {
				numEnclaves1Helper(A, i, col - 1);
			}
		}
		for (int i = 0; i < col; i++) {
			if (A[0][i] == 1) {
				numEnclaves1Helper(A, 0, i);
			}
			if (A[row - 1][i] == 1) {
				numEnclaves1Helper(A, row - 1, i);
			}
		}
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < A[i].length; j++) {
				count += A[i][j];
			}
		}
		return count;
	}
	
	private static void numEnclaves1Helper(int[][] a, int i, int j) {
		a[i][j] = 0;
		if (i > 0 && a[i - 1][j] == 1) {
			numEnclaves1Helper(a, i - 1, j);
		}
		if (i < a.length - 1 && a[i + 1][j] == 1) {
			numEnclaves1Helper(a, i + 1, j);
		}
		if (j > 0 && a[i][j - 1] == 1) {
			numEnclaves1Helper(a, i, j - 1);
		}
		if (j < a[0].length - 1 && a[i][j + 1] == 1) {
			numEnclaves1Helper(a, i, j + 1);
		}
	}
	
	private static int numEnclavesHelper(int[][] a, int i, int j) {
		int count = 1;
		a[i][j] = 0;
		if (i == 0 || j == 0 || i == a.length - 1 || j == a[i].length - 1) {
			count = -1;
		}
		int k;
		if (i > 0 && a[i - 1][j] == 1) {
			k = numEnclavesHelper(a, i - 1, j);
			if (k > 0 && count > 0) {
				count += k;
			} else {
				count = -1;
			}
		}
		
		if (i < a.length - 1 && a[i + 1][j] == 1) {
			k = numEnclavesHelper(a, i + 1, j);
			if (k > 0 && count > 0) {
				count += k;
			} else {
				count = -1;
			}
		}
		if (j > 0 && a[i][j - 1] == 1) {
			k = numEnclavesHelper(a, i, j - 1);
			if (k > 0 && count > 0) {
				count += k;
			} else {
				count = -1;
			}
		}
		if (j < a[i].length - 1 && a[i][j + 1] == 1) {
			k = numEnclavesHelper(a, i, j + 1);
			if (k > 0 && count > 0) {
				count += k;
			} else {
				count = -1;
			}
		}
		return count;
	}
	
	public static int[] productExceptSelf(int[] nums) {
		if (nums.length < 2) {
			return nums;
		}
		int[] l = new int[nums.length];
		int[] r = new int[nums.length];
		l[0] = nums[0];
		r[nums.length - 1] = nums[nums.length - 1];
		for (int i = 1; i < nums.length; i++) {
			l[i] = l[i - 1] * nums[i];
			r[nums.length - 1 - i] = r[nums.length - i] * nums[nums.length - 1 - i];
		}
		nums[0] = r[1];
		nums[nums.length - 1] = l[nums.length - 2];
		for (int i = 1; i < nums.length - 1; i++) {
			nums[i] = l[i - 1] * r[i + 1];
		}
		return nums;
	}
	
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> permutelist = new ArrayList<>();
		if (nums.length == 1) {
			List<Integer> integers = new ArrayList<>();
			integers.add(nums[0]);
			permutelist.add(integers);
			return permutelist;
		}
		for (int i = 0; i < nums.length; i++) {
			ArrayList<Integer> contain = new ArrayList<>();
			contain.add(nums[i]);
			List<List<Integer>> c = permuteHelper(nums, contain);
			permutelist.addAll(c);
		}
		return permutelist;
	}
	
	private static List<List<Integer>> permuteHelper(int[] nums, ArrayList<Integer> contain) {
		List<List<Integer>> permutelist = new ArrayList<>();
		for (int j = 0; j < nums.length; j++) {
			if (!contain.contains(nums[j])) {
				ArrayList<Integer> contains = new ArrayList<>();
				contains.addAll(contain);
				contains.add(nums[j]);
				if (contains.size() == nums.length) {
					permutelist.add(contains);
				} else {
					List<List<Integer>> c = permuteHelper(nums, contains);
					permutelist.addAll(c);
				}
			}
		}
		return permutelist;
	}
	
	
	public List<List<String>> findDuplicate(String[] paths) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String path : paths) {
			String[] split = path.split(" ");
			for (int i = 1; i < split.length; i++) {
				String[] file = split[i].split("\\(");
				if (map.get(file[1]) == null) {
					map.put(file[1], new ArrayList<>());
				}
				List<String> list = map.get(file[1]);
				list.add(split[0] + file[0]);
			}
		}
		ArrayList<List<String>> lists = new ArrayList<>();
		for (String s : map.keySet()) {
			List<String> list = map.get(s);
			if (list.size() > 1) {
				lists.add(list);
				
			}
		}
		return lists;
	}
	
	public boolean escapeGhosts(int[][] ghosts, int[] target) {
		int d = Math.abs(target[0]) + Math.abs(target[1]);
		for (int[] ghost : ghosts) {
			if (d >= Math.abs(ghost[0]) + Math.abs(ghost[1])) {
				return false;
			}
		}
		return true;
	}
	
	public static int calculate(String s) {
		char[] array = s.toCharArray();
		int ret = 0;
		boolean op = true;
		for (int i = 0; i < array.length; i++) {
			if (i < array.length && array[i] == '(') {
				int count = 1;
				int begin = i;
				while (count != 0) {
					i++;
					if (array[i] == ')') {
						count--;
					} else if (array[i] == '(') {
						count++;
					}
				}
				if (op) {
					ret += calculate(s.substring(begin + 1, i));
				} else {
					ret -= calculate(s.substring(begin + 1, i));
				}
				i--;
			} else if (array[i] == '+') {
				if (!op) {
					op = true;
				}
			} else if (array[i] == '-') {
				if (op) {
					op = false;
				}
			} else if (array[i] == ' ') {
			
			} else {
				int k = array[i] - '0';
				i++;
				while (i < array.length && "1234567890".indexOf(array[i]) > 0) {
					k = k * 10 + array[i] - '0';
					i++;
				}
				if (op) {
					ret += k;
				} else {
					ret -= k;
				}
				i--;
				
			}
		}
		return ret;
	}
}
