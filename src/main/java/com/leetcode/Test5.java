package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import jdk.nashorn.internal.ir.ReturnNode;
import sun.nio.cs.ext.MacArabic;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test5
 * @Author: WuMing
 * @CreateDate: 2019/5/15 17:51
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test5 {
	
	public static void main(String[] args) {
		//findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
		//minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2});
		loudAndRich(new int[][]{{0, 1}, {1, 2} },
			new int[]{0,2,1});
	}
	
	static class Person {
		
		int quietIndex;
		int index;
		List<Person> poor;
		
		public Person(int quiet, int index) {
			this.quietIndex = quiet;
			this.index = index;
			this.poor = new ArrayList<>();
		}
	}
	
	public static int[] loudAndRich(int[][] richer, int[] quiet) {
		Person[] people = new Person[quiet.length];
		for (int i = 0; i < quiet.length; i++) {
			people[i] = new Person(i, i);
		}
		for (int i = 0; i < richer.length; i++) {
			people[richer[i][0]].poor.add(people[richer[i][1]]);
			if (quiet[people[richer[i][0]].quietIndex] < quiet[people[richer[i][1]].quietIndex]) {
				setQuietIndex(people[richer[i][1]], people[richer[i][0]].quietIndex, quiet);
			}
		}
		int[] ret = new int[quiet.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = people[i].quietIndex;
		}
		return ret;
	}
	
	private static void setQuietIndex(Person person, int index, int[] quiet) {
		person.quietIndex = index;
		for (Person p : person.poor) {
			if (quiet[p.quietIndex] > quiet[index]) {
				setQuietIndex(p, index, quiet);
			}
		}
	}
	
	
	public int leastBricks(List<List<Integer>> wall) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (List<Integer> list : wall) {
			int l = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				l += list.get(i);
				int count = map.getOrDefault(l, 0) + 1;
				if (count > max) {
					max = count;
				}
				map.put(l, count);
			}
		}
		return wall.size() - max;
	}
	
	public static int minDominoRotations(int[] A, int[] B) {
		for (int x = 1; x < 7; x++) {
			int a = 0;
			int b = 0;
			int j = 0;
			for (; j < A.length; j++) {
				if (A[j] != x && B[j] != x) {
					break;
				} else if (A[j] != x) {
					a++;
				} else if (B[j] != x) {
					b++;
				}
			}
			if (j == A.length) {
				return Math.min(a, b);
			}
		}
		return -1;
	}
	
	public int rob1(TreeNode root) {
		int[] ret = robHelper(root);
		return Math.max(ret[0], ret[1]);
	}
	
	private int[] robHelper(TreeNode root) {
		if (root == null) {
			return new int[2];
		}
		int[] left = robHelper(root.left);
		int[] right = robHelper(root.right);
		int[] ret = new int[2];
		ret[0] = left[1] + right[1] + root.val;
		ret[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return ret;
	}
	
	public int rob(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int cur = root.val;
		if (root.left != null) {
			cur += rob(root.left.left) + rob(root.left.right);
		}
		if (root.right != null) {
			cur += rob(root.right.left) + rob(root.right.right);
		}
		return Math.max(rob(root.left) + rob(root.right), cur);
	}
	
	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> stack = new PriorityQueue<>((a, b) -> (a - b));
		for (int num : nums) {
			stack.add(num);
			if (stack.size() > k) {
				stack.poll();
			}
		}
		return stack.poll();
	}
	
	public static int integerBreak(int n) {
		if (n == 3) {
			return 2;
		}
		if (n == 2) {
			return 1;
		}
		int ret = 1;
		if (n % 3 == 0) {
			while (n > 0) {
				ret *= 3;
				n -= 3;
			}
		} else if (n % 3 == 2) {
			ret = 2;
			n -= 2;
			while (n > 0) {
				ret *= 3;
				n -= 3;
			}
		} else if (n % 3 == 1) {
			ret = 4;
			n -= 4;
			while (n > 0) {
				ret *= 3;
				n -= 3;
			}
		}
		return ret;
	}
	
	int maxLevel;
	
	public List<Integer> rightSideView(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		if (root != null) {
			rightSideViewHelper(root, list, 1);
		}
		return list;
	}
	
	private void rightSideViewHelper(TreeNode root, ArrayList<Integer> list, int level) {
		if (maxLevel < level) {
			list.add(root.val);
		}
		if (root.right != null) {
			rightSideViewHelper(root.right, list, level + 1);
		}
		if (root.left != null) {
			rightSideViewHelper(root.left, list, level + 1);
		}
		
	}
	
	public static int findMinDifference(List<String> timePoints) {
		boolean[] time = new boolean[24 * 60];
		for (String s : timePoints) {
			int t = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
			if (time[t]) {
				return 0;
			} else {
				time[t] = true;
			}
		}
		int begin = 0;
		while (!time[begin]) {
			begin++;
		}
		int i = begin + 1, last = begin;
		int min = Integer.MAX_VALUE;
		while (i < time.length) {
			while (i < time.length && !time[i]) {
				i++;
			}
			if (i < time.length) {
				min = Math.min(i - last, min);
				last = i;
				i++;
			}
		}
		min = Math.min(min, 24 * 60 + begin - last);
		return min;
	}
	
	public int maxScoreSightseeingPair(int[] A) {
		int max = 0;
		int left = A[0] + 0;
		for (int i = 1; i < A.length; i++) {
			max = Math.max(max, left + A[i] - i);
			if (left < A[i] + i) {
				left = A[i] + i;
			}
		}
		return max;
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		int[] ints = new int[target / candidates[0]];
		ArrayList<List<Integer>> ret = new ArrayList<>();
		combinationSumHelper(candidates, ints, 0, 0, target, ret);
		return ret;
	}
	
	private void combinationSumHelper(int[] candidates, int[] ints, int index, int begin, int last,
		ArrayList<List<Integer>> ret) {
		for (int i = begin; i < candidates.length; i++) {
			if (candidates[i] < last) {
				ints[index] = candidates[i];
				combinationSumHelper(candidates, ints, index + 1, i, last - candidates[i], ret);
				ints[index] = 0;
			} else if (candidates[i] == last) {
				ArrayList<Integer> list = new ArrayList<>();
				ret.add(list);
				for (int j = 0; j < index; j++) {
					list.add(ints[j]);
				}
				list.add(candidates[i]);
				break;
			} else {
				break;
			}
		}
	}
	
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int k = n / 2;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				rotateHelper(matrix, i, j, n);
			}
		}
		if ((n & 1) == 1) {
			for (int i = 0; i < k; i++) {
				rotateHelper(matrix, k + 1, i, n);
			}
		}
	}
	
	private void rotateHelper(int[][] matrix, int i, int j, int n) {
		int temp = matrix[i][j];
		int y = n - 1 - j, x = i;
		matrix[i][j] = matrix[y][x];
		int y1 = n - i - 1, x1 = n - 1 - j;
		matrix[y][x] = matrix[y1][x1];
		int y2 = j, x2 = n - 1 - i;
		matrix[y1][x1] = matrix[y2][x2];
		matrix[y2][x2] = temp;
	}
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		if (root != null) {
			levelOrderHelper(root, ret, 0);
		}
		return ret;
	}
	
	private void levelOrderHelper(TreeNode root, ArrayList<List<Integer>> ret, int level) {
		List<Integer> list;
		if (ret.size() > level) {
			list = ret.get(level);
		} else {
			list = new ArrayList<>();
			ret.add(list);
		}
		list.add(root.val);
		if (root.left != null) {
			levelOrderHelper(root.left, ret, level + 1);
		}
		if (root.right != null) {
			levelOrderHelper(root.right, ret, level + 1);
		}
	}
	
	int shoppingOffers = Integer.MAX_VALUE;
	
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special,
		List<Integer> needs) {
		int[] p = new int[price.size()];
		for (int i = 0; i < price.size(); i++) {
			p[i] = price.get(i);
		}
		int[][] s = new int[special.size()][p.length + 1];
		for (int i = 0; i < special.size(); i++) {
			List<Integer> list = special.get(i);
			for (int j = 0; j < list.size(); j++) {
				s[i][j] = list.get(j);
			}
		}
		int[] n = new int[p.length];
		for (int i = 0; i < needs.size(); i++) {
			n[i] = needs.get(i);
		}
		shoppingOffersHelper(p, s, n, new int[n.length], 0);
		return shoppingOffers;
	}
	
	private void shoppingOffersHelper(int[] p, int[][] s, int[] n, int[] l, int total) {
		for (int i = 0; i < s.length; i++) {
			int j = 0;
			for (; j < l.length; j++) {
				if (s[i][j] > n[j] - l[j]) {
					break;
				}
			}
			if (j == l.length) {
				j = 0;
				int[] newl = l.clone();
				for (; j < l.length; j++) {
					newl[j] += s[i][j];
				}
				shoppingOffersHelper(p, s, n, newl, total + s[i][j]);
			}
		}
		for (int i = 0; i < l.length; i++) {
			total += (n[i] - l[i]) * p[i];
		}
		shoppingOffers = Math.min(shoppingOffers, total);
	}
	
	public static int maxProduct(String[] words) {
		int maxProduct = 0;
		int[] mask = new int[words.length];
		for (int i = 0; i < words.length - 1; i++) {
			for (char c : words[i].toCharArray()) {
				mask[i] |= 1 << (c - 'a');
			}
			for (int j = 0; j < i; j++) {
				if ((mask[i] & mask[j]) == 0) {
					maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
				}
			}
		}
		return maxProduct;
	}
	
	private void maxProductHelper(boolean[] ex, String[] words, int i) {
		for (; i < words.length; i++) {
		
		}
	}
	
	public static int[] exclusiveTime(int n, List<String> logs) {
		int[] time = new int[n];
		Stack<int[]> stack = new Stack<>();
		for (String log : logs) {
			String[] split = log.split(":");
			int no = Integer.parseInt(split[0]);
			int cur = Integer.parseInt(split[2]);
			if (split[1].equals("start")) {
				stack.push(new int[]{no, cur});
			} else {
				int[] pop = stack.pop();
				int spend = cur - pop[2] + 1;
				time[pop[0]] += spend;
				if (!stack.empty()) {
					time[stack.peek()[0]] -= spend;
				}
			}
		}
		return time;
	}
	
	
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> ((a[1] - b[1]) == 0 ? a[0] - b[0] : a[1] - b[1]));
		int ret = 1, end = pairs[0][1];
		for (int[] pair : pairs) {
			if (pair[0] > end) {
				ret++;
				end = pair[1];
			}
		}
		return ret;
	}
	
	
	public static int totalHammingDistance(int[] nums) {
		int mask = 1;
		int c;
		int ret = 0, n = nums.length;
		for (int i = 0; i < 32; i++) {
			c = 0;
			for (int num : nums) {
				if ((num & mask) != 0) {
					c++;
				}
			}
			ret += c * (n - c);
			mask <<= 1;
		}
		return ret;
	}
	
	public ListNode[] splitListToParts(ListNode root, int k) {
		ListNode temp = root;
		int length = 0;
		while (temp != null) {
			temp = temp.next;
			length++;
		}
		ListNode[] ret = new ListNode[k];
		int rest = length % k;
		int l = length / k;
		int index = 0;
		while (root != null) {
			ret[index++] = root;
			for (int i = 0; i < l; i++) {
				temp = root;
				root = root.next;
			}
			if (rest > 0) {
				temp = root;
				root = root.next;
				rest--;
			}
			temp.next = null;
		}
		return ret;
	}
	
	public static boolean isNStraightHand(int[] hand, int W) {
		if (hand.length % W != 0) {
			return false;
		}
		if (W == 1) {
			return true;
		}
		Arrays.sort(hand);
		int b;
		boolean[] used = new boolean[hand.length];
		for (int i = 0; i < hand.length; i++) {
			if (!used[i]) {
				used[i] = true;
				b = hand[i];
				int k = i + 1;
				for (int j = 1; j < W; j++) {
					while (k < hand.length && (used[k] || hand[k] < b + j)) {
						k++;
					}
					if (k == hand.length || hand[k] > b + j) {
						return false;
					} else {
						used[k] = true;
					}
				}
			}
		}
		return true;
	}
	
	public static int minFlipsMonoIncr(String S) {
		char[] array = S.toCharArray();
		int index = 0;
		int count = 0;
		while (index < array.length) {
			while (index < array.length && array[index] == '0') {
				index++;
			}
			int c0 = 0, c1 = 0;
			while (index < array.length && c0 <= c1) {
				if (array[index] == '1') {
					c1++;
				} else {
					c0++;
				}
				index++;
			}
			if (c0 > c1) {
				count += c1;
			} else {
				count += c0;
			}
		}
		return count;
	}
	
	public static int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Integer> integers = new PriorityQueue<>((a, b) -> (b - a));
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				integers.add(matrix[i][j]);
				if (integers.size() > k) {
					integers.poll();
				}
			}
		}
		return integers.peek();
	}
	
	public static int findDuplicate1(int[] nums) {
		int slow = nums[0];
		int fast = nums[slow];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}
	
	public static int findDuplicate(int[] nums) {
		int l = 1;
		int r = nums.length - 1;
		while (l < r) {
			int m = (r - l) / 2 + l;
			int lc = 0, rc = 0, mc = 0;
			for (int num : nums) {
				if (num == m) {
					mc++;
				} else if (num < m && num >= l) {
					lc++;
				} else if (num > m && num <= r) {
					rc++;
				}
			}
			if (r - m == m - l) {
				if (mc > 1) {
					return m;
				} else if (lc > rc) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			} else {
				if (lc + mc > rc) {
					r = m;
				} else {
					l = m + 1;
				}
			}
		}
		return l;
	}
	
	public static int[] constructArray(int n, int k) {
		int[] ret = new int[n];
		int l = 1;
		int r = k + 1;
		int index = 0;
		while (r - l > 1) {
			ret[index++] = l++;
			ret[index++] = r--;
		}
		if (r - l == 1) {
			ret[index++] = l;
			ret[index++] = r;
		} else {
			ret[index++] = l;
		}
		for (int i = index; i < n; i++) {
			ret[i] = i + 1;
		}
		return ret;
	}
	
	public static ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode a = head;
		ListNode b = head.next;
		ListNode c = b;
		while (b != null) {
			a.next = b.next;
			if (a.next != null) {
				a = a.next;
			}
			if (b != null) {
				b.next = a.next;
				b = b.next;
			}
		}
		a.next = c;
		return head;
	}
	
	public static List<Integer> diffWaysToCompute(String input) {
		char[] array = input.toCharArray();
		ArrayList<String> list = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		String op = "+-*";
		for (char c : array) {
			if (op.indexOf(c) < 0) {
				buffer.append(c);
			} else {
				list.add(buffer.toString());
				list.add("" + c);
				buffer.setLength(0);
			}
			
		}
		list.add(buffer.toString());
		return diffWaysToComputeHelper(list, 0, list.size() - 1);
		
	}
	
	private static List<Integer> diffWaysToComputeHelper(List<String> array, int l, int r) {
		ArrayList<Integer> ret = new ArrayList<>();
		if (l == r) {
			ret.add(Integer.valueOf(array.get(l)));
			return ret;
		}
		for (int i = l + 1; i < r; i += 2) {
			List<Integer> a = diffWaysToComputeHelper(array, l, i - 1);
			List<Integer> b = diffWaysToComputeHelper(array, i + 1, r);
			for (Integer x : a) {
				for (Integer y : b) {
					int k = 0;
					switch (array.get(i)) {
						case "+":
							k = x + y;
							break;
						case "-":
							k = x - y;
							break;
						case "*":
							k = x * y;
							break;
					}
					ret.add(k);
					
				}
			}
		}
		return ret;
	}
	
	public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		int l = 0, r = 0;
		ListNode temp = l1;
		while (temp != null) {
			temp = temp.next;
			l++;
		}
		temp = l2;
		while (temp != null) {
			temp = temp.next;
			r++;
		}
		ListNode listNode;
		if (l > r) {
			listNode = addTwoNumbers1Helper(l1, l2, l - r);
			
		} else {
			listNode = addTwoNumbers1Helper(l2, l1, r - l);
		}
		if (listNode.val > 9) {
			ListNode head = new ListNode(1);
			listNode.val -= 10;
			head.next = listNode;
			return head;
		} else {
			return listNode;
		}
	}
	
	
	public int maxProfit(int[] prices, int fee) {
		int profit = 0;
		int hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			profit = Math.max(profit, prices[i] + hold - fee);
			hold = Math.max(hold, profit - prices[i]);
		}
		return profit;
	}
	
	
	private static ListNode addTwoNumbers1Helper(ListNode l1, ListNode l2, int i) {
		if (i > 0) {
			ListNode next = addTwoNumbers1Helper(l1.next, l2, i - 1);
			int flag = 0;
			if (next != null) {
				flag = next.val / 10;
				next.val = next.val % 10;
			}
			ListNode cur = new ListNode(l1.val + flag);
			cur.next = next;
			return cur;
		}
		if (l1 == null) {
			return null;
		}
		ListNode next = addTwoNumbers1Helper(l1.next, l2.next, i);
		int flag = 0;
		if (next != null) {
			flag = next.val / 10;
			next.val = next.val % 10;
		}
		ListNode cur = new ListNode(l1.val + l2.val + flag);
		cur.next = next;
		return cur;
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<ListNode> a = new Stack<>();
		Stack<ListNode> b = new Stack<>();
		int l = 0, r = 0;
		while (l1 != null) {
			a.push(l1);
			l1 = l1.next;
			l++;
		}
		while (l2 != null) {
			b.push(l2);
			l2 = l2.next;
			r++;
		}
		if (l > r) {
			return addTwoNumbersHelper(a, b);
		} else {
			return addTwoNumbersHelper(b, a);
		}
	}
	
	private static ListNode addTwoNumbersHelper(Stack<ListNode> a, Stack<ListNode> b) {
		int flag = 0;
		ListNode head = null;
		while (!b.empty()) {
			ListNode an = a.pop();
			ListNode bn = b.pop();
			int val = 0;
			if ((an.val + bn.val + flag) > 9) {
				val = an.val + bn.val + flag - 10;
				flag = 1;
			} else {
				val = an.val + bn.val + flag;
				flag = 0;
			}
			an.val = val;
			an.next = null;
			an.next = head;
			head = an;
		}
		while (!a.empty()) {
			ListNode an = a.pop();
			int val = 0;
			if ((an.val + flag) > 9) {
				val = an.val + flag - 10;
				flag = 1;
			} else {
				val = an.val + flag;
				flag = 0;
			}
			an.val = val;
			an.next = null;
			an.next = head;
			head = an;
		}
		if (flag == 1) {
			ListNode listNode = new ListNode(1);
			listNode.next = head;
			return listNode;
		} else {
			return head;
		}
	}
	
	public int minAreaRect(int[][] points) {
		HashSet<Long> longs = new HashSet<>();
		for (int[] point : points) {
			longs.add((((long) point[0]) << 32) + point[1]);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			for (int j = i; j < points.length; j++) {
				int[] a = points[i];
				int[] b = points[j];
				if (a[0] != b[0] && a[1] != b[1] && longs.contains((((long) a[0]) << 32) + b[1])
					&& longs.contains((((long) b[0]) << 32) + a[1])) {
					min = Math.min(min, Math.abs(b[0] - a[0]) * Math.abs(b[1] - a[1]));
				}
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		HashMap<Integer, Integer> a = new HashMap<>();
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				int k = A[i] + B[j];
				a.put(k, a.getOrDefault(k, 0) + 1);
			}
		}
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				int k = 0 - C[i] - D[j];
				count += a.getOrDefault(k, 0);
			}
		}
		return count;
	}
	
	
	static int maxUncrossedLines;
	
	
	private static void maxUncrossedLinesHelper(int[] a, int[] b, int ai, int bi, int sum) {
		if (a.length - ai < sum + maxUncrossedLines || b.length - bi < sum + maxUncrossedLines) {
			return;
		}
		for (int i = ai; i < a.length; i++) {
			for (int j = bi; j < b.length; j++) {
				if (a[i] == b[j]) {
					maxUncrossedLinesHelper(a, b, i + 1, j + 1, sum + 1);
				}
			}
		}
		if (sum > maxUncrossedLines) {
			maxUncrossedLines = sum;
		}
	}
	
	public static boolean reorderedPowerOf2(int N) {
		int i = 1;
		int k = N;
		int[] a = new int[10];
		
		while (k / 10 > 0) {
			i *= 10;
			k /= 10;
		}
		while (N > 0) {
			a[N % 10]++;
			N /= 10;
		}
		k = 1;
		while (k < i) {
			k <<= 1;
		}
		for (; k < i * 10; k <<= 1) {
			int[] b = new int[10];
			int temp = k;
			while (temp > 0) {
				b[temp % 10]++;
				temp /= 10;
			}
			int j = 0;
			for (; j < 10; j++) {
				if (a[j] != b[j]) {
					break;
				}
			}
			if (j == 10) {
				return true;
			}
		}
		return false;
	}
	
	public String intToRoman(int num) {
		StringBuffer buffer = new StringBuffer();
		while (num >= 1000) {
			num -= 1000;
			buffer.append("M");
		}
		if (num >= 900) {
			num -= 900;
			buffer.append("CM");
		} else if (num >= 500) {
			num -= 500;
			buffer.append("D");
		} else if (num >= 400) {
			num -= 400;
			buffer.append("CD");
		}
		while (num >= 100) {
			num -= 100;
			buffer.append("C");
		}
		if (num >= 90) {
			num -= 90;
			buffer.append("XC");
		} else if (num >= 50) {
			num -= 50;
			buffer.append("L");
		} else if (num >= 40) {
			num -= 40;
			buffer.append("XL");
		}
		while (num >= 10) {
			num -= 10;
			buffer.append("X");
		}
		if (num >= 9) {
			num -= 9;
			buffer.append("IX");
		} else if (num >= 5) {
			num -= 5;
			buffer.append("V");
		} else if (num >= 4) {
			num -= 4;
			buffer.append("IV");
		}
		while (num > 0) {
			num--;
			buffer.append("I");
		}
		return buffer.toString();
	}
	
	public static int[] nextGreaterElements1(int[] nums) {
		int length = nums.length;
		int[] ints = new int[length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 2 * length - 1; i >= 0; i--) {
			int x = i % length;
			while (!stack.empty() && nums[stack.peek()] <= nums[x]) {
				stack.pop();
			}
			ints[x] = stack.empty() ? -1 : nums[stack.peek()];
			stack.push(x);
			
		}
		return ints;
	}
	
	public static int[] nextGreaterElements(int[] nums) {
		int length = nums.length;
		int[] ints = new int[length];
		for (int i = 0; i < ints.length; i++) {
			int k = (i + 1) % length;
			while (k != i) {
				if (nums[k] > nums[i]) {
					ints[i] = nums[k];
					break;
				}
				k++;
				k %= length;
			}
			if (k == i) {
				ints[i] = -1;
			}
		}
		return ints;
	}
	
	public static int findMaximumXOR(int[] nums) {
		TreeNode root = new TreeNode(-1);
		int ret = 0;
		for (int num : nums) {
			TreeNode search = root;
			TreeNode add = root;
			int curr = 0;
			for (int i = 31; i >= 0; i--) {
				int k = ((num >> i) & 1);
				curr <<= 1;
				if (k == 0) {
					if (add.left == null) {
						add.left = new TreeNode(num);
					}
					add = add.left;
					if (search.right != null) {
						curr++;
						search = search.right;
					} else {
						search = search.left;
					}
				} else {
					if (add.right == null) {
						add.right = new TreeNode(num);
					}
					add = add.right;
					if (search.left != null) {
						curr++;
						search = search.left;
					} else {
						search = search.right;
					}
				}
			}
			ret = Math.max(ret, curr);
		}
		return ret;
	}
	
	public static List<List<Integer>> combinationSum3(int k, int n) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		int[] ints = new int[k];
		combinationSum3Helper(ints, ret, n, 0, 1);
		return ret;
	}
	
	static int kthSmallest;
	
	public static int kthSmallest(TreeNode root, int k) {
		int l = kthSmallestHelper(root, k);
		return kthSmallest;
	}
	
	private static int kthSmallestHelper(TreeNode root, int k) {
		if (root == null) {
			return 0;
		}
		int l = kthSmallestHelper(root.left, k);
		if (l + 1 == k) {
			kthSmallest = root.val;
		}
		int r = kthSmallestHelper(root.right, k - l - 1);
		return l + r + 1;
	}
	
	private static void combinationSum3Helper(int[] ints, ArrayList<List<Integer>> ret, int n,
		int i,
		int begin) {
		if (i == ints.length) {
			if (n == 0) {
				ArrayList<Integer> list = new ArrayList<>();
				for (int anInt : ints) {
					list.add(anInt);
				}
				ret.add(list);
			}
			return;
		}
		for (int j = begin; j < 10; j++) {
			ints[i] = j;
			combinationSum3Helper(ints, ret, n - j, i + 1, j + 1);
		}
	}
	
	public static boolean pyramidTransition(String bottom, List<String> allowed) {
		char[][] pyramid = new char[bottom.length()][bottom.length()];
		HashMap<String, HashSet<Character>> map = new HashMap<>();
		for (String s : allowed) {
			String key = s.substring(0, 2);
			HashSet<Character> set = map.get(key);
			if (set == null) {
				set = new HashSet<>();
				map.put(key, set);
			}
			set.add(s.charAt(2));
		}
		int y = 0, x = 0;
		for (char c : bottom.toCharArray()) {
			pyramid[0][x++] = c;
		}
		return pyramidTransitionHelper(pyramid, map, y, x);
	}
	
	ArrayList<Integer> preorderTraversal = new ArrayList<>();
	
	public List<Integer> preorderTraversal1(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		if (root != null) {
			stack.push(root);
		}
		while (!stack.empty()) {
			TreeNode pop = stack.pop();
			if (pop.right != null) {
				stack.push(pop.right);
			}
			if (pop.left != null) {
				stack.push(pop.left);
			}
			list.add(pop.val);
		}
		return list;
	}
	
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root != null) {
			preorderTraversalHelper(root);
		}
		return preorderTraversal;
	}
	
	private void preorderTraversalHelper(TreeNode root) {
		if (root == null) {
			return;
		}
		preorderTraversal.add(root.val);
		preorderTraversalHelper(root.left);
		preorderTraversalHelper(root.right);
	}
	
	private static boolean pyramidTransitionHelper(char[][] pyramid,
		HashMap<String, HashSet<Character>> map,
		int y, int x) {
		if (y == pyramid.length - 1 && x == 1) {
			return true;
		} else if (x + y == pyramid.length) {
			y++;
			x = 0;
		}
		String perfix = "" + pyramid[y - 1][x] + pyramid[y - 1][x + 1];
		HashSet<Character> set = map.get(perfix);
		if (set != null) {
			for (Character c : set) {
				pyramid[y][x] = c;
				if (pyramidTransitionHelper(pyramid, map, y, x + 1)) {
					return true;
				}
			}
		}
		return false;
	}
}
