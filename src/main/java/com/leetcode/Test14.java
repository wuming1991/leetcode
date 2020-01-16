package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test14
 * @Author: WuMing
 * @CreateDate: 2019/12/3 10:42
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test14 {
	
	public static void main(String[] args) {
		Test14 test = new Test14();
		test.palindromePartition("aabbc",3);
	}
	
	int palindromePartition = Integer.MAX_VALUE;
	
	public int palindromePartition(String s, int k) {
		int len = s.length();
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			Arrays.fill(mem[i], -1);
		}
		palindromePartitionHelper(mem, s, len, k, 0);
		return palindromePartition;
	}
	
	private void palindromePartitionHelper(int[][] mem, String s, int end, int k, int sum) {
		if (k == 1) {
			palindromePartition = Math
				.min(palindromePartition, sum + getChange(mem, s, 0, end - 1));
			return;
		} else if (k > end) {
			return;
		}
		int t;
		for (int i = end - 1; i >= 0; i--) {
			t = sum + getChange(mem, s, i, end - 1);
			if (t >= palindromePartition) {
				continue;
			} else {
				palindromePartitionHelper(mem, s, i, k - 1, t);
			}
		}
	}
	
	private int getChange(int[][] mem, String s, int i, int end) {
		if (mem[i][end] >= 0) {
			return mem[i][end];
		}
		int ret = 0, l = i, r = end;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				ret++;
				l++;
				r--;
			}
		}
		mem[i][end] = ret;
		return ret;
	}
	
	public int subtractProductAndSum(int n) {
		int x = 1, y = 0, t;
		while (n > 0) {
			t = n % 10;
			x *= t;
			y += t;
			n /= 10;
		}
		return x - y;
	}
	
	public List<List<Integer>> groupThePeople(int[] groupSizes) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		int len = groupSizes.length, size;
		ArrayList<List<Integer>> ret = new ArrayList<>();
		List<Integer> list;
		for (int i = 0; i < len; i++) {
			size = groupSizes[i];
			if (map.containsKey(size)) {
				list = map.get(size);
			} else {
				list = new ArrayList<>();
				map.put(size, list);
			}
			list.add(i);
			if (list.size() == size) {
				ret.add(list);
				map.put(size, new ArrayList<>());
			}
		}
		return ret;
		
	}
	
	int tallestBillboard = 0;
	
	public int tallestBillboard1(int[] rods) {
		int len = rods.length;
		if (len < 2) {
			return 0;
		}
		Arrays.sort(rods);
		int sum = 0;
		for (int rod : rods) {
			sum += rod;
		}
		tallestBillboard1Helper(rods, len - 1, rods[len - 1], 0, sum / 2, sum - rods[len - 1]);
		tallestBillboard1Helper(rods, len - 1, 0, 0, sum - rods[len - 1] / 2, sum - rods[len - 1]);
		return tallestBillboard;
	}
	
	private void tallestBillboard1Helper(int[] rods, int i, int l, int r, int half, int last) {
		if (l > half || r > half || i <= 0) {
			return;
		}
		int x = Math.abs(l - r);
		if (l + r + last <= tallestBillboard * 2 || x > last) {
			return;
		}
		if (x == 0) {
			tallestBillboard = Math.max(l, tallestBillboard);
		} else if (x == last) {
			tallestBillboard = Math.max(Math.max(l, r), tallestBillboard);
			return;
		}
		i--;
		last -= rods[i];
		tallestBillboard1Helper(rods, i, l + rods[i], r, half, last);
		tallestBillboard1Helper(rods, i, l, rods[i] + r, half, last);
		tallestBillboard1Helper(rods, i, l, r, half - (rods[i] / 2), last);
	}
	
	public int tallestBillboard(int[] rods) {
		int sum = 0;
		for (int rod : rods) {
			sum += rod;
		}
		List<Integer>[] lists = new List[sum / 2 + 1];
		tallestBillboardHelper(rods, 0, 0, 0, lists);
		for (int i = sum / 2; i >= 0; i--) {
			if (lists[i] != null) {
				List<Integer> list = lists[i];
				int size = list.size();
				for (int j = 0; j < size; j++) {
					for (int k = j + 1; k < size; k++) {
						if ((list.get(j) & list.get(k)) == 0) {
							return i;
						}
					}
				}
			}
		}
		return 0;
	}
	
	private void tallestBillboardHelper(int[] rods, int cur, int idx, int sum, List[] lists) {
		if (sum >= lists.length) {
			return;
		}
		if (lists[sum] == null) {
			lists[sum] = new ArrayList();
		}
		lists[sum].add(cur);
		int len = rods.length;
		for (int i = idx; i < len; i++) {
			tallestBillboardHelper(rods, cur | (1 << i), i + 1, sum + rods[i], lists);
		}
		
	}
	
	public int longestIncreasingPath(int[][] matrix) {
		int hi = matrix.length;
		if (hi < 1) {
			return 0;
		}
		int len = matrix[0].length;
		int[][] max = new int[hi][len];
		int ret = 0;
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (max[i][j] == 0) {
					ret = Math.max(ret, longestIncreasingPathHelper(matrix, max, i, j, ds));
				}
			}
		}
		return ret;
	}
	
	private int longestIncreasingPathHelper(int[][] matrix, int[][] max, int i, int j,
		int[][] ds) {
		if (max[i][j] > 0) {
			return max[i][j];
		}
		int hi = matrix.length;
		int len = matrix[0].length;
		int ret = 0, x, y;
		for (int[] d : ds) {
			x = i + d[0];
			y = j + d[1];
			if (x < 0 || y < 0 || x >= hi || y >= len || matrix[x][y] <= matrix[i][j]) {
				continue;
			}
			ret = Math.max(ret, longestIncreasingPathHelper(matrix, max, x, y, ds));
		}
		max[i][j] = ret + 1;
		return ret + 1;
	}
	
	
	public String shortestPalindrome(String s) {
		int len = s.length();
		int i = len / 2;
		if ((len & 1) == 1) {
			if (shortestPalindromeHelper(s, i - 1, i + 1)) {
				return s;
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (; i >= 1; i--) {
			if (shortestPalindromeHelper(s, i - 1, i)) {
				for (int j = len - 1; j > 2 * i - 1; j--) {
					buffer.append(s.charAt(j));
				}
				buffer.append(s);
				return buffer.toString();
			}
			if (shortestPalindromeHelper(s, i - 2, i)) {
				for (int j = len - 1; j > 2 * i - 2; j--) {
					buffer.append(s.charAt(j));
				}
				buffer.append(s);
				return buffer.toString();
			}
		}
		return null;
	}
	
	private boolean shortestPalindromeHelper(String s, int l, int r) {
		while (l >= 0) {
			if (s.charAt(l) == s.charAt(r)) {
				l--;
				r++;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public int firstMissingPositive(int[] nums) {
		int len = nums.length, t, x;
		if (len < 1) {
			return 1;
		}
		for (int i = 1; i < len; i++) {
			if (nums[i - 1] != i) {
				firstMissingPositiveHelper(nums, nums[i - 1]);
			}
		}
		for (int i = 0; i < len; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums[len - 1] + 1;
	}
	
	private void firstMissingPositiveHelper(int[] nums, int i) {
		int len = nums.length;
		if (i <= 0 || i > len) {
			return;
		} else if (nums[i - 1] == i) {
			return;
		}
		int n = nums[i - 1];
		nums[i - 1] = i;
		firstMissingPositiveHelper(nums, n);
	}
	
	public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
		int len = difficulty.length;
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			ints[i][0] = difficulty[i];
			ints[i][1] = profit[i];
		}
		Arrays.sort(ints, (a, b) -> (a[0] - b[0]));
		Arrays.sort(worker);
		int ret = 0, max, j = 0;
		for (int i : worker) {
			max = 0;
			for (; j < len; j++) {
				if (ints[j][0] <= i) {
					max = Math.max(max, ints[j][1]);
				} else {
					break;
				}
			}
			ret += max;
		}
		return ret;
	}
	
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		int[] map = new int[100001];
		int h = 0, p = 0;
		int end = 100001;
		int len = difficulty.length;
		while (end != 0) {
			for (int i = 0; i < len; i++) {
				if (difficulty[i] < end && profit[i] > p) {
					p = profit[i];
					h = difficulty[i];
				}
			}
			Arrays.fill(map, h, end, p);
			p = 0;
			end = h;
			h = 0;
		}
		int ret = 0;
		for (int i : worker) {
			ret += map[i];
		}
		return ret;
		
	}
	
	public boolean splitArraySameAverage(int[] A) {
		int len = A.length;
		int sum = 0;
		for (int i : A) {
			sum += i;
		}
		Arrays.sort(A);
		for (int i = 1; i <= len / 2; i++) {
			if ((sum * i) % len == 0 && splitArraySameAverageHelper(A, len - 1, sum * i, 0, i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean splitArraySameAverageHelper(int[] a, int idx, int target, int sum, int last) {
		int len = a.length;
		if (last == 0) {
			return target == len * sum;
		} else if (idx < 0 || target < len * sum) {
			return false;
		}
		if (splitArraySameAverageHelper(a, idx - 1, target, sum + a[idx], last - 1)) {
			return true;
		}
		while (idx > 1 && a[idx - 1] == a[idx]) {
			idx--;
		}
		return splitArraySameAverageHelper(a, idx - 1, target, sum, last);
	}
	
	public int longestDecomposition(String text) {
		int len = text.length();
		int l = 0, r = len - 1, ret = 0;
		for (int i = 0; i < r; i++) {
			if (longestDecompositionHelper(text, l, r, i)) {
				ret += 2;
				r -= (i - l + 1);
				l = i + 1;
			}
		}
		if (r >= l) {
			ret++;
		}
		return ret;
	}
	
	private boolean longestDecompositionHelper(String text, int l, int r, int end) {
		int x = r - (end - l);
		for (int i = l; i <= end; i++, x++) {
			if (text.charAt(i) != text.charAt(x)) {
				return false;
			}
		}
		return true;
	}
	
	public int game(int[] guess, int[] answer) {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			if (guess[i] == answer[i]) {
				ret++;
			}
		}
		return ret;
	}
	
	public int[] fraction(int[] cont) {
		int[] ret = fractionHelper(cont, 0);
		return new int[]{ret[1], ret[0]};
	}
	
	private int[] fractionHelper(int[] cont, int i) {
		if (i == cont.length - 1) {
			return new int[]{1, cont[i]};
		}
		int[] ints = fractionHelper(cont, i + 1);
		int x = ints[1];
		int y = cont[i] * ints[1] + ints[0];
		int c = gcb(x, y);
		return new int[]{x / c, y / c};
	}
	
	
	private int gcb(int x, int y) {
		int t;
		while (y != 0) {
			t = x % y;
			x = y;
			y = t;
		}
		return x;
	}
	
	class TireNode {
		
		TireNode[] next;
		boolean isword;
		String word;
		
		public TireNode(boolean isword) {
			this.isword = isword;
			next = new TireNode[26];
		}
	}
	
	public String tictactoe(int[][] moves) {
		int[][][] mem = {{{0, 3, 7}, {0, 4}, {0, 5, 6}}, {{1, 3}, {1, 4, 6, 7}, {1, 5}},
			{{2, 3, 6}, {2, 4}, {2, 5, 7}}};
		int cur = 1;
		int[] count = new int[8];
		for (int[] move : moves) {
			for (int i : mem[move[0]][move[1]]) {
				count[i] += cur;
				if (count[i] == 3) {
					return "A";
				} else if (count[i] == -3) {
					return "B";
				}
			}
			cur *= -1;
		}
		if (moves.length == 9) {
			return "Draw";
		} else {
			return "Pending";
		}
	}
	
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TireNode root = new TireNode(false);
		char x = searchWord.charAt(0);
		for (String product : products) {
			if (product.charAt(0) == x) {
				buildTree(root, product);
			}
		}
		TireNode cur = root;
		ArrayList<List<String>> ret = new ArrayList<>();
		for (char c : searchWord.toCharArray()) {
			if (cur != null) {
				cur = cur.next[c - 'a'];
			}
			ret.add(SearchTree(cur, new ArrayList<String>()));
		}
		return ret;
	}
	
	private List<String> SearchTree(TireNode cur, List<String> list) {
		if (cur == null) {
			return list;
		}
		if (cur.isword) {
			list.add(cur.word);
			if (list.size() == 3) {
				return list;
			}
		}
		for (TireNode next : cur.next) {
			if (next != null) {
				SearchTree(next, list);
				if (list.size() == 3) {
					return list;
				}
			}
		}
		return list;
	}
	
	private void buildTree(TireNode t, String product) {
		for (char c : product.toCharArray()) {
			if (t.next[c - 'a'] == null) {
				t.next[c - 'a'] = new TireNode(false);
			}
			t = t.next[c - 'a'];
		}
		t.isword = true;
		t.word = product;
	}
}
