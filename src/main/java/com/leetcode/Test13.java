package com.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test13
 * @Author: WuMing
 * @CreateDate: 2019/11/15 16:22
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test13 {
	
	public static void main(String[] args) throws ParseException {
		Test13 test = new Test13();
		
		ListNode a = new ListNode(1);
		a.next = new ListNode(2);
		a.next.next = new ListNode(3);
		a.next.next.next = new ListNode(4);
		a.next.next.next.next = new ListNode(5);
		System.out
			.println((new BigDecimal((double) 5 / 7).setScale(2, RoundingMode.UP) + "%"));
		//test.mergeKLists1(list);
		test.countSquares(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}});
	}
	
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		int x= tomatoSlices -2* cheeseSlices;
		if ((x & 1) == 0&&cheeseSlices>=(x>>1)&&x>=0) {
			return Arrays.asList(x >> 1, cheeseSlices - (x >> 1));
		} else {
			return new ArrayList<>();
		}
	}
	
	public int countSquares(int[][] matrix) {
		int hi = matrix.length;
		int len = matrix[0].length;
		for (int i = 0; i < hi; i++) {
			for (int j = len - 2; j >= 0; j--) {
				if (matrix[i][j] > 0) {
					matrix[i][j] += matrix[i][j + 1];
				}
			}
		}
		int ret = 0;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (matrix[i][j] > 0) {
					ret += countSquaresHelper(matrix, i, j);
				}
			}
		}
		return ret;
	}
	
	private int countSquaresHelper(int[][] matrix, int x, int y) {
		int ret = 1;
		int ml = matrix[x][y];
		int hi = matrix.length;
		for (int i = 1; i + x < hi; i++) {
			ml = Math.min(ml, matrix[i + x][y]);
			if (ml >= i + 1) {
				ret++;
			} else {
				break;
			}
		}
		return ret;
	}
	
	//有问题???
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] ret = new int[k];
		int d = len1 + len2 - k;
		maxNumberHelper(ret, nums1, nums2, 0, 0, 0, d);
		return ret;
	}
	
	private void maxNumberHelper(int[] ret, int[] a, int[] b, int idx, int ai, int bi,
		int d) {
		int al = a.length;
		int bl = b.length;
		int rl = ret.length;
		int l = idx;
		if (ai == al) {
			while (bi < bl) {
				if (d > 0 && ret[idx - 1] < b[bi] && idx > l) {
					idx--;
					d--;
				}
				if (idx < rl && ret[idx] <= b[bi]) {
					ret[idx++] = b[bi++];
				} else {
					break;
				}
			}
			return;
		} else if (bi == bl) {
			while (ai < al) {
				if (d > 0 && ret[idx - 1] < a[ai] && idx > l) {
					idx--;
					d--;
				}
				if (idx < rl && ret[idx] <= a[ai]) {
					ret[idx++] = a[ai++];
				} else {
					break;
				}
			}
			return;
		} else if (rl == idx) {
			return;
		}
		int mai = maxNumberHelperHelper(a, ai, d);
		int mbi = maxNumberHelperHelper(b, bi, d);
		if (a[mai] > b[mbi]) {
			if (a[mai] < ret[idx]) {
				return;
			}
			ret[idx] = a[mai];
			Arrays.fill(ret, idx + 1, rl, 0);
			maxNumberHelper(ret, a, b, idx + 1, mai + 1, bi, d - (mai - ai));
		} else if (a[mai] < b[mbi]) {
			if (ret[idx] > b[mbi]) {
				return;
			}
			ret[idx] = b[mbi];
			Arrays.fill(ret, idx + 1, rl, 0);
			maxNumberHelper(ret, a, b, idx + 1, ai, mbi + 1, d - (mbi - bi));
		} else {
			if (ret[idx] > a[mai]) {
				return;
			}
			ret[idx] = a[mai];
			/*int nmai=maxNumberHelperHelper(a, mai, d - (mai - ai));
			int nmbi=maxNumberHelperHelper(a, mai, d - (mbi - bi));*/
			maxNumberHelper(ret, a, b, idx + 1, mai + 1, bi, d - (mai - ai));
			maxNumberHelper(ret, a, b, idx + 1, ai, mbi + 1, d - (mbi - bi));
		}
	}
	
	private int maxNumberHelperHelper(int[] a, int ai, int d) {
		int len = a.length;
		int ret = ai;
		for (int i = ai + 1; i <= ai + d && i < len; i++) {
			if (a[ret] < a[i]) {
				ret = i;
			}
		}
		return ret;
	}
	
	public int maximalRectangle(char[][] matrix) {
		int hi = matrix.length;
		if (hi < 1) {
			return 0;
		}
		int len = matrix[0].length;
		if (len < 1) {
			return 0;
		}
		for (int i = 0; i < hi; i++) {
			matrix[i][0] -= '0';
			for (int j = 1; j < len; j++) {
				matrix[i][j] -= '0';
				if (matrix[i][j] == 1) {
					matrix[i][j] += matrix[i][j - 1];
				}
			}
		}
		int ret = 0;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (matrix[i][j] > 0) {
					ret = Math.max(ret, maximalRectangleHelper(matrix, i, j));
				}
			}
		}
		return ret;
	}
	
	private int maximalRectangleHelper(char[][] matrix, int i, int j) {
		int ret = matrix[i][j];
		int l = ret;
		int hi = matrix.length;
		for (int k = i + 1; k < hi; k++) {
			l = Math.min(l, matrix[k][j]);
			if (l == 0) {
				break;
			}
			ret = Math.max(ret, l * (k - i + 1));
		}
		return ret;
	}
	
	public int largestRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int maxarea = 0;
		for (int i = 0; i < heights.length; ++i) {
			while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
				maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
			}
			stack.push(i);
		}
		while (stack.peek() != -1) {
			maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
		}
		return maxarea;
	}
	
	public int largestRectangleArea2(int[] heights) {
		int len = heights.length;
		int[] stack = new int[len + 1];
		stack[0] = -1;
		int idx = 0;
		int ret = 0;
		for (int i = 0; i < len; i++) {
			while (idx > 0 && heights[stack[idx]] >= heights[i]) {
				ret = Math.max(ret, heights[stack[idx]] * (i - stack[idx - 1] - 1));
				idx--;
			}
			stack[++idx] = i;
		}
		while (idx > 0) {
			ret = Math.max(ret, heights[stack[idx]] * (len - stack[idx - 1] - 1));
			idx--;
		}
		return ret;
	}
	
	public int largestRectangleArea1(int[] heights) {
		int len = heights.length;
		int ret = 0;
		int[] mem = new int[len];
		LinkedList<Integer> stack = new LinkedList<>();
		int l, r;
		for (int i = 0; i < len; i++) {
			mem[i] = heights[i];
			while (!stack.isEmpty() && heights[stack.getLast()] >= heights[i]) {
				stack.removeLast();
			}
			if (stack.isEmpty()) {
				mem[i] = heights[i] * (i + 1);
				stack.push(i);
			} else {
				l = -1;
				stack.add(i);
				for (Integer x : stack) {
					mem[i] = Math.max(mem[i], heights[x] * (i - l));
					l = x;
				}
			}
			ret = Math.max(ret, mem[i]);
		}
		return ret;
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k == 1) {
			return head;
		}
		int i = 0;
		ListNode cur = head;
		for (; i < k && cur != null; i++) {
			cur = cur.next;
		}
		if (i < k) {
			return head;
		}
		ListNode next = reverseKGroup(cur, k);
		cur = head.next;
		for (int j = 0; j < k; j++) {
			head.next = next;
			next = head;
			if (cur == null) {
				break;
			}
			head = cur;
			cur = cur.next;
		}
		return next;
	}
	
	public int countServers(int[][] grid) {
		int hi = grid.length;
		int len = grid[0].length;
		int ret = 0, count, l = 0, lc;
		for (int i = 0; i < hi; i++) {
			count = 0;
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					count++;
					l = j;
				}
			}
			if (count != 1) {
				ret += count;
			} else {
				lc = 0;
				for (int j = 0; j < hi; j++) {
					if (grid[j][l] == 1) {
						lc++;
					}
					if (lc > 1) {
						break;
					}
				}
				if (lc > 1) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	public int minTimeToVisitAllPoints(int[][] points) {
		int len = points.length;
		int ret = 0;
		for (int i = 1; i < len; i++) {
			int x = points[i][0] - points[i - 1][0];
			int y = points[i][1] - points[i - 1][1];
			ret += Math.max(Math.abs(x), Math.abs(y));
		}
		return ret;
	}
	
	public int maxSumSubmatrix(int[][] matrix, int k) {
		int hi = matrix.length;
		int len = matrix[0].length;
		int[][] ints = new int[hi][len + 1];
		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				ints[i][j + 1] = ints[i][j] + matrix[i][j];
				
			}
		}
		TreeSet<Integer> set = new TreeSet<>();
		int sum;
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j + i <= len; j++) {
				sum = 0;
				set.add(0);
				for (int l = 0; l < hi; l++) {
					sum += ints[l][j + i] - ints[l][j];
					for (Integer x : set) {
						if (sum - x < ret) {
							break;
						} else if (sum - x == k) {
							return k;
						} else if (sum - x < k) {
							ret = sum - x;
						}
					}
					set.add(sum);
				}
				set.clear();
			}
		}
		return ret;
	}
	
	public int countTriplets(int[] A) {
		
		int[] mem = new int[2 << 16];
		for (int i : A) {
			for (int j : A) {
				mem[i & j]++;
			}
		}
		int len = A.length;
		int ret = mem[0] * len;
		int length = mem.length;
		for (int i = 1; i < length; i++) {
			if (mem[i] > 0) {
				for (int x : A) {
					if ((i & x) == 0) {
						ret += mem[i];
					}
				}
			}
		}
		return ret;
	}
	
	public int[] threeEqualParts(int[] A) {
		int count = 0;
		for (int i : A) {
			if (i == 1) {
				count++;
			}
		}
		if (count % 3 != 0) {
			return new int[]{-1, -1};
		} else if (count == 0) {
			return new int[]{0, 1};
		}
		int x = count / 3;
		int len = A.length, idx = len - 1;
		int c0 = 0;
		while (A[idx] == 0) {
			c0++;
			idx--;
		}
		int r = idx;
		while (x > 0) {
			if (A[idx] == 1) {
				x--;
			}
			idx--;
		}
		int[] ret = new int[2];
		if (threeEqualPartsHelper(idx, r, A, ret, 1, c0)) {
			return ret;
		} else {
			return new int[]{-1, -1};
		}
	}
	
	private boolean threeEqualPartsHelper(int l, int r, int[] a, int[] ret, int part, int c0) {
		int idx = l, count0 = 0;
		while (a[idx] == 0) {
			count0++;
			idx--;
		}
		if (count0 < c0) {
			return false;
		}
		
		ret[part] = idx + c0 + part;
		int end = idx;
		for (int i = 0; r - i > l; i++) {
			if (a[r - i] != a[idx--]) {
				return false;
			}
		}
		if (part == 0) {
			return true;
		} else {
			return threeEqualPartsHelper(idx, end, a, ret, part - 1, c0);
		}
	}
	
	
	public String crackSafe(int n, int k) {
		StringBuffer ret = new StringBuffer();
		HashSet<String> visited = new HashSet<>();
		for (int i = 0; i < n - 1; i++) {
			ret.append('0');
		}
		String s = ret.toString();
		ret.setLength(0);
		crackSafeHelper(ret, visited, k, s);
		ret.append(s);
		return ret.toString();
	}
	
	private void crackSafeHelper(StringBuffer ret, HashSet<String> visited, int k, String s) {
		String next;
		for (int i = 0; i < k; i++) {
			next = s + i;
			if (!visited.contains(next)) {
				visited.add(next);
				crackSafeHelper(ret, visited, k, next.substring(1));
				ret.append(i);
			}
		}
	}
	
	//理解有误
	public int findMinMoves(int[] machines) {
		int sum = 0;
		for (int i : machines) {
			sum += i;
		}
		int len = machines.length;
		if (sum % len != 0) {
			return -1;
		}
		int x = sum / len;
		int ret = 0;
		for (int i = 0; i < len - 1; i++) {
			ret += Math.abs(machines[i] - x);
			machines[i + 1] += machines[i] - x;
		}
		return ret;
	}
	
	public int minMutation(String start, String end, String[] bank) {
		int len = bank.length;
		boolean[] visited = new boolean[len];
		LinkedList<String> list = new LinkedList<>();
		int l = 1;
		list.add(start);
		String cur;
		int ret = 0;
		int le = start.length();
		while (l > 0) {
			ret++;
			while (l > 0) {
				cur = list.removeFirst();
				for (int i = 0; i < len; i++) {
					if (!visited[i]) {
						if (minMutationHelper(cur, bank[i], le)) {
							if (bank[i].equals(end)) {
								return ret;
							}
							visited[i] = true;
							list.add(bank[i]);
						}
					}
				}
				l--;
			}
			l = list.size();
		}
		return -1;
	}
	
	private boolean minMutationHelper(String a, String b, int le) {
		int count = 0;
		for (int i = 0; i < le; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count++;
				if (count > 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int countRangeSum1(int[] nums, int lower, int upper) {
		int len = nums.length;
		long[] per = new long[len + 1];
		for (int i = 0; i < len; i++) {
			per[i + 1] = per[i] + nums[i];
		}
		return mergeSort(per, 0, len, lower, upper);
	}
	
	private int mergeSort(long[] nums, int l, int r, int lower, int upper) {
		if (l >= r) {
			return 0;
		}
		int m = (l + r) >> 1;
		int ret = mergeSort(nums, l, m, lower, upper) + mergeSort(nums, m + 1, r, lower, upper);
		long[] mem = new long[r - l + 1];
		int lu = m + 1, ll = m + 1, ri = m + 1, j = 0;
		for (int i = l; i <= m; i++, j++) {
			while (lu <= r && nums[lu] - nums[i] <= upper) {
				lu++;
			}
			while (ll <= r && nums[ll] - nums[i] < lower) {
				ll++;
			}
			while (ri <= r && nums[ri] < nums[i]) {
				mem[j++] = nums[ri++];
			}
			mem[j] = nums[i];
			ret += lu - ll;
		}
		for (int i = l, x = 0; x < j; i++, x++) {
			nums[i] = mem[x];
		}
		return ret;
	}
	
	public int countRangeSum(int[] nums, int lower, int upper) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int sum = 0;
		map.put(0, 1);
		int ret = 0, x;
		for (int num : nums) {
			sum += num;
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				x = entry.getKey();
				if (sum - x < lower) {
					break;
				} else if (sum - x <= upper) {
					ret += entry.getValue();
				}
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return ret;
	}
	
	public String getHint(String secret, String guess) {
		int[] a = new int[10];
		int[] b = new int[10];
		int len = secret.length();
		char x, y;
		int l = 0, r = 0;
		for (int i = 0; i < len; i++) {
			x = secret.charAt(i);
			y = guess.charAt(i);
			if (x == y) {
				l++;
			}
			a[x - 'a']++;
			b[y - 'a']++;
		}
		for (int i = 0; i < 10; i++) {
			r += Math.min(a[i], b[i]);
		}
		return l + "A" + (r - l) + "B";
	}
	
	public int[] maxSlidingWindow2(int[] nums, int k) {
		int len = nums.length;
		if (len < 2 || k == 1) {
			return nums;
		}
		int[] ret = new int[len - k + 1];
		int idx = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			while (!list.isEmpty() && nums[list.getLast()] < nums[i]) {
				list.removeLast();
			}
			list.add(i);
		}
		ret[idx++] = nums[list.getFirst()];
		for (int i = k; i < len; i++) {
			if (list.getFirst() < i - k + 1) {
				list.removeFirst();
			}
			while (!list.isEmpty() && nums[list.getLast()] < nums[i]) {
				list.removeLast();
			}
			list.add(i);
			ret[idx++] = nums[list.getFirst()];
		}
		return ret;
	}
	
	public int[] maxSlidingWindow1(int[] nums, int k) {
		int len = nums.length;
		if (len < 2) {
			return nums;
		}
		int[] ret = new int[len - k + 1];
		int idx = 0;
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < k; i++) {
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.pollLast();
			}
			deque.offer(i);
		}
		ret[idx++] = nums[deque.peek()];
		for (int i = k; i < len; i++) {
			while (!deque.isEmpty() && deque.peek() < i - k + 1) {
				deque.poll();
			}
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.pollLast();
			}
			deque.offer(i);
			ret[idx++] = nums[deque.peek()];
		}
		return ret;
	}
	
	public int[] maxSlidingWindow(int[] nums, int k) {
		int len = nums.length;
		if (len < 2) {
			return nums;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
		for (int i = 0; i < k; i++) {
			queue.add(nums[i]);
		}
		
		int[] ret = new int[len - k + 1];
		ret[0] = queue.peek();
		
		for (int i = 1; i < len - k + 1; i++) {
			queue.remove(nums[i - 1]);
			queue.add(nums[i + k - 1]);
			ret[i] = queue.peek();
		}
		return ret;
	}
	
	public ListNode mergeKLists1(ListNode[] lists) {
		
		int len = lists.length;
		if (len < 1) {
			return null;
		} else if (len < 2) {
			return lists[0];
		}
		return mergeKListsHelper(lists, 0, len - 1);
	}
	
	private ListNode mergeKListsHelper(ListNode[] lists, int l, int r) {
		if (l > r) {
			return null;
		} else if (l == r) {
			return lists[l];
		}
		int m = (l + r) >> 1;
		ListNode left = mergeKListsHelper(lists, l, m);
		ListNode right = mergeKListsHelper(lists, m + 1, r);
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (left != null && right != null) {
			l = left.val;
			r = right.val;
			if (l < r) {
				cur.next = left;
				left = left.next;
			} else {
				cur.next = right;
				right = right.next;
			}
			cur = cur.next;
		}
		if (left == null) {
			cur.next = right;
		} else {
			cur.next = left;
		}
		return head.next;
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> (a.val - b.val));
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		ListNode head = new ListNode(0);
		ListNode cur = head, next;
		while (!queue.isEmpty()) {
			next = queue.poll();
			cur.next = next;
			cur = cur.next;
			if (next.next != null) {
				queue.add(next.next);
			}
		}
		return head.next;
	}
	
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int hi = grid.length;
		int len = grid[0].length;
		int i = 0, count = 0;
		int j = 0;
		k = hi * len - k % (hi * len);
		for (; i < hi && count < k; ) {
			for (; j < len && count < k; j++) {
				count++;
			}
			if (j == len) {
				i++;
				j = 0;
			}
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		count = 0;
		for (; i < hi; i++) {
			for (; j < len; j++) {
				list.add(grid[i][j]);
				count++;
				if (count == len) {
					ret.add(list);
					list = new ArrayList<>();
					count = 0;
				}
			}
			j = 0;
		}
		i = 0;
		j = 0;
		int c = 0;
		for (; i < hi && c < k; i++) {
			for (; j < len && c < k; j++) {
				list.add(grid[i][j]);
				count++;
				c++;
				if (count == len) {
					ret.add(list);
					list = new ArrayList<>();
					count = 0;
				}
			}
			j = 0;
		}
		return ret;
	}
	
	public int maxSumDivThree1(int[] nums) {
		int a1 = 100000;
		int b1 = 100000;
		int a2 = 100000;
		int b2 = 100000;
		int sum = 0, mod;
		for (int num : nums) {
			sum += num;
			mod = num % 3;
			if (mod == 1) {
				if (num < a1) {
					b1 = a1;
					a1 = num;
				} else if (num < b1) {
					b1 = num;
				}
			} else if (mod == 2) {
				if (num < a2) {
					b2 = a2;
					a2 = num;
				} else if (num < b2) {
					b2 = num;
				}
			}
		}
		mod = sum % 3;
		if (mod == 0) {
			return sum;
		} else if (mod == 1) {
			return sum - Math.min(a1, a2 + b2);
		} else {
			return sum - Math.min(a2, a1 + b1);
		}
	}
	
	public int maxSumDivThree(int[] nums) {
		Arrays.sort(nums);
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int x = sum % 3;
		if (x == 0) {
			return sum;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int i = x; i < sum; i += 3) {
			if (set.contains(i) || maxSumDivThreeHelper(nums, i, 0, 0, set)) {
				return sum - i;
			}
		}
		return 0;
	}
	
	private boolean maxSumDivThreeHelper(int[] nums, int target, int idx, int cur,
		Set<Integer> set) {
		for (int i = idx; i < nums.length; i++) {
			set.add(cur + nums[i]);
			if (cur + nums[i] < target) {
				if (maxSumDivThreeHelper(nums, target, i + 1, cur + nums[i], set)) {
					return true;
				}
			} else if (cur + nums[i] == target) {
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}
	
	public int[] sumOfDistancesInTree1(int N, int[][] edges) {
		Set<Integer>[] lists = new Set[N];
		for (int i = 0; i < N; i++) {
			lists[i] = new HashSet();
		}
		int x, y;
		for (int[] edge : edges) {
			x = edge[0];
			y = edge[1];
			lists[x].add(y);
			lists[y].add(x);
		}
		int[] count = new int[N];
		Arrays.fill(count, 1);
		int[] ret = new int[N];
		//以0为中心,得到每一个节点向外发散的路径和以及节点数量
		sumOfDistancesInTree1Helper(lists, 0, -1, count, ret);
		//计算任意节点的路径和(因为0路径和已经求的,所以从0开始发散,每个节点的路径和都是父节点和加上节点数减去2倍的当前节点下的节点数)
		sumOfDistancesInTree1Helper1(lists, 0, -1, count, ret, N);
		return ret;
	}
	
	private void sumOfDistancesInTree1Helper1(Set<Integer>[] lists, int cur, int parent
		, int[] count, int[] ret, int n) {
		for (Integer t : lists[cur]) {
			if (t != parent) {
				ret[t] = ret[cur] + n - 2 * count[t];
				sumOfDistancesInTree1Helper1(lists, t, cur, count, ret, n);
			}
		}
	}
	
	private void sumOfDistancesInTree1Helper(Set<Integer>[] lists, int cur, int parent, int[] count,
		int[] ret) {
		for (Integer t : lists[cur]) {
			if (t != parent) {
				sumOfDistancesInTree1Helper(lists, t, cur, count, ret);
				count[cur] += count[t];
				ret[cur] += ret[t] + count[t];
			}
		}
	}
	
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		int[] parent = new int[N];
		Arrays.fill(parent, -1);
		int p, s;
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		ArrayList<Integer> list;
		for (int[] edge : edges) {
			p = edge[0];
			s = edge[1];
			parent[s] = p;
			if (map.containsKey(p)) {
				map.get(p).add(s);
			} else {
				list = new ArrayList<>();
				list.add(s);
				map.put(p, list);
			}
		}
		int[] count = new int[N];
		int[] low = new int[N];
		int root = 0;
		for (int i = 0; i < N; i++) {
			if (parent[i] == -1) {
				root = i;
				break;
			}
		}
		sumOfDistancesInTreeCountHelper(count, root, map, low);
		int[] ret = new int[N];
		ret[root] = low[root];
		for (int i = 0; i < N; i++) {
			if (ret[i] == 0) {
				sumOfDistancesInTreeHelper(i, ret, count, parent);
			}
		}
		return ret;
	}
	
	private int sumOfDistancesInTreeHelper(int i, int[] ret, int[] count, int[] parent) {
		if (ret[i] > 0) {
			return ret[i];
		}
		ret[i] = sumOfDistancesInTreeHelper(parent[i], ret, count, parent) + parent.length - (2
			* count[i]);
		return ret[i];
	}
	
	private int sumOfDistancesInTreeCountHelper(int[] count, int root,
		HashMap<Integer, List<Integer>> map, int[] low) {
		count[root] = 1;
		if (map.containsKey(root)) {
			for (Integer s : map.get(root)) {
				count[root] += sumOfDistancesInTreeCountHelper(count, s, map, low);
				low[root] += low[s] + count[s];
			}
		}
		return count[root];
	}
	
	public int shortestPathLength1(int[][] graph) {
		int len = graph.length;
		int finish = (1 << len) - 1;
		int[][] mem = new int[len][finish + 1];
		for (int i = 0; i < len; i++) {
			Arrays.fill(mem[i], Integer.MAX_VALUE);
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			mem[i][1 << i] = 0;
			list.add(i);
			list.add(1 << i);
		}
		int cur, state, step, nextstate;
		while (!list.isEmpty()) {
			cur = list.removeFirst();
			state = list.removeFirst();
			if (state == finish) {
				return mem[cur][state];
			}
			step = mem[cur][state] + 1;
			for (int next : graph[cur]) {
				nextstate = state | 1 << next;
				if (step < mem[next][nextstate]) {
					mem[next][nextstate] = step;
					list.add(next);
					list.add(nextstate);
				}
			}
		}
		return -1;
	}
	
	public int shortestPathLength2(int[][] graph) {
		int len = graph.length;
		int ret = 0;
		for (int i = 0; i < len; i++) {
			ret = Math.max(ret, shortestPathLength2Helper(i, graph, 0, 0));
			if (ret == len - 1) {
				break;
			}
		}
		return 2 * (len - 1 - ret) + ret;
	}
	
	private int shortestPathLength2Helper(int i, int[][] graph, int status, int count) {
		status += (1 << i);
		int ret = count;
		int len = graph.length;
		for (int x : graph[i]) {
			if ((status & (1 << x)) == 0) {
				ret = Math.max(ret, shortestPathLength2Helper(x, graph, status, count + 1));
				if (ret == len - 1) {
					return len - 1;
				}
			}
		}
		status -= (1 << i);
		return ret;
	}
	
	int shortestPathLength = Integer.MAX_VALUE;
	
	public int shortestPathLength(int[][] graph) {
		int len = graph.length;
		int[][] min = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j : graph[i]) {
				min[i][j] = 1;
			}
		}
		boolean[] visited = new boolean[len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (min[i][j] == 0 && i != j) {
					shortestPathLengthHelper(i, j, min, visited);
				}
			}
		}
		
		for (int i = 0; i < len; i++) {
			shortestPathLengthHelperHelper(i, visited, min, 1, len, 0);
		}
		return shortestPathLength;
	}
	
	private void shortestPathLengthHelperHelper(int i, boolean[] visited,
		int[][] min, int count, int total, int len) {
		if (len >= shortestPathLength) {
			return;
		}
		if (count == total) {
			shortestPathLength = len;
			return;
		}
		visited[i] = true;
		for (int j = 0; j < total; j++) {
			if (!visited[j]) {
				shortestPathLengthHelperHelper(j, visited, min, count + 1, total, len + min[i][j]);
			}
		}
		visited[i] = false;
	}
	
	private int shortestPathLengthHelper(int i, int j, int[][] min, boolean[] visited) {
		if (min[i][j] > 0) {
			return min[i][j];
		}
		if (i == j) {
			return 0;
		}
		int ret = Integer.MAX_VALUE;
		int len = min[i].length;
		visited[i] = true;
		for (int k = 0; k < len; k++) {
			if (min[i][k] > 0 && !visited[k]) {
				int x = shortestPathLengthHelper(k, j, min, visited);
				ret = Math.min(ret, min[i][k] + x);
			}
		}
		visited[i] = false;
		min[i][j] = ret;
		min[j][i] = ret;
		return ret;
	}
	
	
	public int largestIsland(int[][] grid) {
		int len = grid.length;
		
		int color = 2;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					map.put(color, largestIslandColor(grid, i, j, color));
					color++;
				}
			}
		}
		int ret = 0, x, y, cur;
		int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 0) {
					cur = 1;
					for (int[] d : ds) {
						x = i + d[0];
						y = j + d[1];
						if (x < 0 || y < 0 || x >= len || y >= len || grid[x][y] == 0) {
							continue;
						}
						set.add(grid[x][y]);
					}
					for (Integer v : set) {
						cur += map.get(v);
					}
					ret = Math.max(ret, cur);
					set.clear();
				}
			}
		}
		if (ret == 0) {
			for (Integer v : map.values()) {
				ret = Math.max(ret, v);
			}
		}
		return ret;
	}
	
	private Integer largestIslandColor(int[][] grid, int i, int j, int color) {
		int ret = 0;
		int len = grid.length;
		if (i < 0 || j < 0 || i >= len || j >= len || grid[i][j] == 0 || grid[i][j] == color) {
			return ret;
		}
		grid[i][j] = color;
		ret++;
		ret += largestIslandColor(grid, i + 1, j, color);
		ret += largestIslandColor(grid, i - 1, j, color);
		ret += largestIslandColor(grid, i, j + 1, color);
		ret += largestIslandColor(grid, i, j - 1, color);
		return ret;
		
	}
	
	public int findKthNumber(int m, int n, int k) {
		if (m > n) {
			return findKthNumber(n, m, k);
		}
		int l = 1, r = n * m, x, count;
		while (l <= r) {
			x = (l + r) >> 1;
			count = 0;
			for (int i = m; i > 0; i--) {
				count += x / i > n ? n : x / i;
			}
			if (count < k) {
				l = x + 1;
			} else {
				r = x - 1;
			}
		}
		return l;
	}
	
	public int eraseOverlapIntervals1(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		int len = intervals.length;
		if (len < 2) {
			return 0;
		}
		int removed = 0;
		int end = intervals[0][1];
		for (int i = 1; i < len; i++) {
			if (intervals[i][0] >= end) {
				end = intervals[i][1];
			} else if (intervals[i][1] < end) {
				end = intervals[i][1];
				removed++;
			} else {
				removed++;
			}
		}
		return removed;
	}
	
	public int eraseOverlapIntervals(int[][] intervals) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int ret = 0, begin, end;
		for (int[] interval : intervals) {
			begin = interval[0];
			end = interval[1];
			if (map.containsKey(begin)) {
				ret++;
				map.put(begin, Math.min(map.get(begin), end));
			} else {
				map.put(begin, end);
			}
		}
		int size = map.size();
		int[][] ints = new int[size][2];
		int idx = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			ints[idx++] = new int[]{entry.getKey(), entry.getValue()};
		}
		int[] mem = new int[size];
		return ret + size - eraseOverlapIntervalsHelper(ints, 0, Integer.MIN_VALUE, mem);
	}
	
	private int eraseOverlapIntervalsHelper(int[][] ints, int i, int left, int[] mem) {
		int ret = 0;
		int len = ints.length;
		if (i >= len) {
			return 0;
		}
		if (mem[i] != 0) {
			return mem[i];
		}
		for (int j = i; j < len; j++) {
			if (ints[j][0] >= left) {
				ret = Math.max(ret, 1 + eraseOverlapIntervalsHelper(ints, j + 1, ints[j][1], mem));
			}
		}
		mem[i] = ret;
		return ret;
	}
	
	public int splitArray1(int[] nums, int m) {
		int len = nums.length;
		long l = nums[0];
		long r = 0, x, ret = r;
		for (int i = 0; i < len; i++) {
			r += nums[i];
			l = Math.max(nums[i], l);
		}
		if (m == 1) {
			return (int) r;
		}
		while (l < r) {
			x = (l + r) >> 1;
			long count = 0, sum = 0;
			int i;
			for (i = 0; i < len; i++) {
				sum += nums[i];
				if (sum > x) {
					sum = nums[i];
					count++;
				}
			}
			if (count < m) {
				ret = x;
				r = x - 1;
			} else {
				l = x + 1;
			}
		}
		return (int) ret;
	}
	
	public int splitArray(int[] nums, int m) {
		int len = nums.length;
		int[] sum = new int[1 + len];
		for (int i = 0; i < len; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		int[][] min = new int[len + 1][m + 1];
		return splitArrayHelper(sum, 0, m, min);
	}
	
	private int splitArrayHelper(int[] sum, int i, int m, int[][] min) {
		if (min[i][m] != 0) {
			return min[i][m];
		}
		int len = sum.length;
		if (m == 1) {
			min[i][m] = sum[len - 1] - sum[i];
			return min[i][m];
		}
		int ret = Integer.MAX_VALUE;
		for (int j = i + 1; j < len - m + 1; j++) {
			ret = Math.min(ret, Math.max(sum[j] - sum[i], splitArrayHelper(sum, j, m - 1, min)));
		}
		min[i][m] = ret;
		return ret;
	}
	
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		int len = nums1.length;
		int len2 = nums2.length;
		ArrayList<List<Integer>> ret = new ArrayList<>();
		if (len < 1 || len2 < 1) {
			return ret;
		}
		int[] count = new int[len];
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		for (int i = 0; i < len; i++) {
			queue.add(new int[]{nums1[i] + nums2[0], i});
		}
		for (int i = 0; i < k && queue.size() > 0; i++) {
			int[] poll = queue.poll();
			int x = poll[1];
			ret.add(Arrays.asList(new Integer[]{nums1[x], nums2[count[x]]}));
			if (count[x] + 1 < len2) {
				count[x]++;
				queue.add(new int[]{nums1[x] + nums2[count[x]], x});
			}
		}
		return ret;
	}
	
	public boolean isAdditiveNumber(String num) {
		char[] array = num.toCharArray();
		long f = 0, s;
		int len = array.length;
		for (int i = 0; i < len / 2 + 1; i++) {
			if (i > 0 && f == 0) {
				return false;
			}
			f *= 10;
			f += array[i] - '0';
			s = 0;
			for (int j = i + 1; j < len; j++) {
				if (j > i + 1 && s == 0) {
					break;
				}
				s *= 10;
				s += array[j] - '0';
				if (isAdditiveNumberHelper(f, s, array, j + 1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isAdditiveNumberHelper(long f, long s, char[] array, int idx) {
		long cur = 0;
		int len = array.length;
		for (int i = idx; i < len; i++) {
			if (i > idx && cur == 0) {
				return false;
			}
			cur *= 10;
			cur += array[i] - '0';
			if (cur > s + f) {
				return false;
			} else if (cur == s + f) {
				if (i == len - 1) {
					return true;
				} else {
					return isAdditiveNumberHelper(s, f + s, array, i + 1);
				}
			}
		}
		return false;
	}
	
	class TireNode {
		
		int val;
		int count;
		TireNode left;
		TireNode right;
		
		public TireNode(int val, int count) {
			this.val = val;
			this.count = count;
		}
	}
	
	public List<Integer> countSmaller(int[] nums) {
		
		int len = nums.length, cur, count;
		LinkedList<Integer> ret = new LinkedList<>();
		if (len < 1) {
			return ret;
		}
		ret.add(0);
		TireNode root = new TireNode(nums[len - 1], 1);
		for (int i = len - 2; i >= 0; i--) {
			cur = nums[i];
			count = searchFrom(root, cur);
			ret.addFirst(count);
			addTire(root, cur);
		}
		return ret;
	}
	
	private void addTire(TireNode root, int cur) {
		if (root.val == cur) {
			root.count++;
		} else if (root.val < cur) {
			
			if (root.right == null) {
				root.right = new TireNode(cur, 1);
			} else {
				addTire(root.right, cur);
			}
		} else {
			root.count++;
			if (root.left == null) {
				root.left = new TireNode(cur, 1);
			} else {
				addTire(root.left, cur);
			}
		}
	}
	
	private int searchFrom(TireNode root, int cur) {
		if (root == null) {
			return 0;
		}
		if (cur > root.val) {
			return root.count + searchFrom(root.right, cur);
		} else {
			return searchFrom(root.left, cur);
		}
	}
	
	public void reorderList1(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode s = head;
		ListNode f = head;
		while (true) {
			if (f == null || f.next == null) {
				break;
			}
			s = s.next;
			f = f.next.next;
		}
		ListNode tail = s.next, h = null, c;
		s.next = null;
		while (tail != null) {
			c = tail;
			tail = tail.next;
			c.next = h;
			h = c;
		}
		ListNode cur = head;
		while (h != null) {
			c = h;
			h = h.next;
			c.next = cur.next;
			cur.next = c;
			cur = c.next;
		}
	}
	
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode s = head;
		ListNode f = head;
		while (true) {
			if (f == null || f.next == null) {
				break;
			}
			s = s.next;
			f = f.next.next;
		}
		
		Stack<ListNode> stack = new Stack<>();
		while (s.next != null) {
			stack.push(s.next);
			f = s;
			s = s.next;
			f.next = null;
		}
		ListNode cur = head;
		while (!stack.isEmpty()) {
			ListNode x = stack.pop();
			x.next = cur.next;
			cur.next = x;
			cur = x.next;
		}
	}
	
	public ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode s = head;
		ListNode f = s;
		while (true) {
			s = s.next;
			if (f == null && f.next == null) {
				return null;
			}
			f = f.next.next;
			if (s == f) {
				break;
			}
		}
		while (s != head) {
			head = head.next;
			s = s.next;
		}
		return head;
	}
	
	public int[][] merge(int[][] intervals) {
		int len = intervals.length;
		if (len < 2) {
			return intervals;
		}
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		int[][] ret = new int[len][2];
		int idx = 0;
		ret[0][0] = intervals[0][0];
		int tail = intervals[0][1];
		for (int i = 1; i < len; i++) {
			if (intervals[i][0] <= tail) {
				tail = Math.max(tail, intervals[i][1]);
			} else {
				ret[idx++][1] = tail;
				ret[idx][0] = intervals[i][0];
				tail = intervals[i][1];
			}
		}
		ret[idx++][1] = tail;
		return Arrays.copyOf(ret, idx);
	}
}
