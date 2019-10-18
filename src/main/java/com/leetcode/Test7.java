package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test7
 * @Author: WuMing
 * @CreateDate: 2019/6/25 23:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test7 {
	
	public static void main(String[] args) {
		Test7 test = new Test7();
/*		//test.findRightInterval(new int[][]{{4, 5}, {2, 3}, {1, 2}});
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(2);
		l2.next = new ListNode(3);
		l1.next = new ListNode(9);
		test.addTwoNumbers1(l1, l2);
		test.sortColors1(new int[]{2, 0, 2, 1, 1, 0 ,2,1,2});*//*
		
		//test.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
		*//*test.partition("aab");
		test.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});*//*
		//test.totalFruit2(new int[]{0, 1, 2, 2});
		*//*test.subarraySum2(new int[]{1, 1, 1, 2, 4, -2}, 2);*//*
		//test.findMaxLength2(new int[]{0, 1, 0, 0, 1});
		//test.threeSum2(new int[]{0, 0, 0});
		//test.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
		//test.searchRange(new int[]{6, 6, 7}, 6);
		//test.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3);
		//test.spiralOrder(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}});
		//test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
		//test.search(new int[]{1, 2, 1}, 2);
		test.numDecodings("1020026");*/
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(4);
		test.recoverTree(root);
	}
	
	
	public void recoverTree(TreeNode root) {
		LinkedList<TreeNode> x = new LinkedList<>();
		recoverTreeHelper(null, null, root, x);
		int val = x.getFirst().val;
		x.getFirst().val=x.getLast().val;
		x.getLast().val=val;
	}
	
	void recoverTreeHelper(TreeNode l, TreeNode r, TreeNode root, LinkedList<TreeNode> x) {
		if (l != null && l.val > root.val) {
			if (x.size() == 0) {
				x.add(root);
				x.add(l);
			} else {
				if (root.val < x.getFirst().val) {
					x.set(0, root);
				}
				if (l.val > x.getLast().val) {
					x.set(1, l);
				}
			}
			
		}
		if (r != null && r.val < root.val) {
			if (x.size() == 0) {
				x.add(r);
				x.add(root);
			} else {
				if (r.val < x.getFirst().val) {
					x.set(0, r);
				}
				if (root.val > x.getLast().val) {
					x.set(1, root);
				}
			}
		}
		if (root.left != null) {
			recoverTreeHelper(l, root, root.left, x);
		}
		if (root.right != null) {
			recoverTreeHelper(root, r, root.right, x);
		}
	}
	
	
	public List<TreeNode> generateTrees(int n) {
		ArrayList<TreeNode> ret = new ArrayList<>();
		Set<TreeNode> set = generateTreesHelper(1, n);
		ret.addAll(set);
		return ret;
	}
	
	private Set<TreeNode> generateTreesHelper(int l, int r) {
		HashSet<TreeNode> set = new HashSet<>();
		if (l > r) {
			return set;
		} else if (l == r) {
			set.add(new TreeNode(l));
			return set;
		}
		for (int i = l; i <= r; i++) {
			Set<TreeNode> leftSet = generateTreesHelper(l, i - 1);
			Set<TreeNode> rightSet = generateTreesHelper(i + 1, r);
			if (leftSet.size() == 0) {
				leftSet.add(null);
			}
			if (rightSet.size() == 0) {
				rightSet.add(null);
			}
			for (TreeNode left : leftSet) {
				for (TreeNode right : rightSet) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					set.add(root);
				}
			}
		}
		return set;
	}
	
	
	public int numDecodings(String s) {
		
		char[] array = s.toCharArray();
		int[] ints = new int[array.length + 1];
		ints[array.length] = 1;
		
		ints[array.length - 1] = s.endsWith("0") ? 0 : 1;
		int num = array[array.length - 1] - '0', c;
		for (int i = array.length - 2; i >= 0; i--) {
			c = array[i] - '0';
			if (num == 0) {
				if (c == 0 || c > 2) {
					return 0;
				}
			}
			if (c == 0) {
				ints[i] = 0;
			} else if (c * 10 + num < 27) {
				ints[i] = ints[i + 1] + ints[i + 2];
			} else {
				ints[i] = ints[i + 1];
			}
			num = c;
		}
		return ints[0];
		
	}
	
	
	public ListNode deleteDuplicates(ListNode head) {
		
		if (head == null || head.next == null) {
			return head;
		}
		ListNode ret = new ListNode(0);
		ret.next = head;
		ListNode before = ret;
		ListNode cur = head;
		ListNode next = head.next;
		while (next != null) {
			if (cur.val == next.val) {
				while (next != null && cur.val == next.val) {
					next = next.next;
				}
				cur = next;
				if (next != null) {
					next = next.next;
				}
			} else {
				before.next = cur;
				before = before.next;
				cur = cur.next;
				next = cur.next;
			}
		}
		before.next = cur;
		return ret.next;
	}
	
	public boolean search1(int[] nums, int target) {
		int l = 0, r = nums.length - 1, m;
		while (l <= r) {
			m = l + (r - l) / 2;
			if (nums[m] == target) {
				return true;
			}
			if (nums[m] == nums[l] && nums[m] == nums[r]) {
				l++;
				r--;
			} else if (nums[l] <= nums[m]) {
				if (nums[l] <= target && target < nums[m]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			} else {
				if (nums[m] < target && target <= nums[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
		}
		return false;
	}
	
	public boolean search(int[] nums, int target) {
		if (nums.length == 0) {
			return false;
		}
		
		int l = 0, r = nums.length - 1, m;
		while (l < r && nums[r] == nums[l]) {
			r--;
		}
		if (nums[l] < nums[r]) {
			while (l < r) {
				m = l + (r - l) / 2;
				if (nums[m] > target) {
					r = m;
				} else if (nums[m] == target) {
					return true;
				} else {
					l = m + 1;
				}
			}
			if (l == r) {
				return nums[l] == target;
			}
			return false;
		}
		while (l < r) {
			m = l + (r - l) / 2;
			if (nums[m] > nums[l]) {
				l = m;
			} else if (nums[m] < nums[r]) {
				r = m;
			} else if (nums[m] == nums[l] && m != l) {
				l++;
			} else {
				r--;
			}
		}
		int p = l;
		if (target >= nums[0]) {
			l = 0;
			r = p;
		} else {
			l = p + 1;
			r = nums.length - 1;
		}
		
		while (l < r) {
			m = l + (r - l) / 2;
			if (nums[m] > target) {
				r = m;
			} else if (nums[m] == target) {
				return true;
			} else {
				l = m + 1;
			}
		}
		if (l == r) {
			return nums[l] == target;
		}
		return false;
	}
	
	public int removeDuplicates1(int[] nums) {
		int ret = 0;
		for (int num : nums) {
			if (ret < 2 || nums[ret - 2] < num) {
				nums[ret++] = num;
			}
		}
		return ret;
	}
	
	public int removeDuplicates(int[] nums) {
		if (nums.length < 3) {
			return nums.length;
		}
		int ret = 1, last = nums[0];
		boolean flag = true;
		for (int i = 1; i < nums.length; i++) {
			if (last == nums[i]) {
				if (flag) {
					last = nums[i];
					nums[ret] = nums[i];
					ret++;
					flag = false;
				}
			} else {
				nums[ret] = nums[i];
				last = nums[i];
				ret++;
				flag = true;
			}
		}
		return ret;
	}
	
	public void setZeroes(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		boolean[] row = new boolean[n];
		boolean[] column = new boolean[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (row[i] || column[j]) {
					matrix[i][j] = 0;
				}
			}
		}
	}
	
	public ListNode rotateRight(ListNode head, int k) {
		if (k == 0 || head == null) {
			return head;
		}
		int len = 0;
		ListNode cur = head;
		ListNode tail = head;
		while (cur != null) {
			len++;
			tail = cur;
			cur = cur.next;
		}
		k = k % len;
		if (k == 0) {
			return head;
		}
		cur = head;
		while (k > 0) {
			cur = cur.next;
			k--;
		}
		ListNode ret = head;
		ListNode before = head;
		while (cur != null) {
			before = ret;
			ret = ret.next;
			cur = cur.next;
		}
		before.next = null;
		tail.next = head;
		return ret;
	}
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		
		int n = obstacleGrid.length;
		if (n == 0) {
			return 0;
		}
		int m = obstacleGrid[0].length;
		if (m == 0) {
			return 1;
		}
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
		int[][] ints = new int[n][m];
		ints[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (obstacleGrid[i][j] == 1) {
					ints[i][j] = 0;
				} else {
					if (i > 0) {
						ints[i][j] += ints[i - 1][j];
					}
					if (j > 0) {
						ints[i][j] += ints[i][j - 1];
					}
					
				}
			}
		}
		return ints[n - 1][m - 1];
	}
	
	public ListNode partition(ListNode head, int x) {
		ListNode left = new ListNode(0);
		ListNode right = new ListNode(0);
		ListNode l = left;
		ListNode r = right;
		ListNode t;
		while (head != null) {
			t = head;
			head = head.next;
			t.next = null;
			if (t.val < x) {
				l.next = t;
				l = l.next;
			} else {
				r.next = t;
				r = r.next;
			}
		}
		l.next = right.next;
		return left.next;
	}
	
	public List<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> ret = new ArrayList<>();
		if (matrix.length == 0 || matrix[0].length == 0) {
			return ret;
		}
		int total = matrix.length * matrix[0].length;
		ret.add(matrix[0][0]);
		int ci = 0, cj = 0;
		int l = -1, r = matrix[0].length, u = -1, d = matrix.length;
		int[][] n = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int cur = 0;
		while (ret.size() < total) {
			if (ci + n[cur][0] < d && ci + n[cur][0] > u && cj + n[cur][1] < r
				&& cj + n[cur][1] > l) {
				ci += n[cur][0];
				cj += n[cur][1];
				ret.add(matrix[ci][cj]);
			} else {
				switch (cur) {
					case 0:
						u++;
						break;
					case 1:
						r--;
						break;
					case 2:
						d--;
						break;
					default:
						l++;
						break;
				}
				cur++;
				cur %= 4;
			}
		}
		return ret;
	}
	
	public boolean canJump(int[] nums) {
		
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0) {
				int before = i - 1;
				while (before >= 0 && nums[before] < i - before) {
					before--;
				}
				if (before < 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int l = 0, r = matrix.length - 1, m;
		while (l < r) {
			m = (r - l) / 2 + l;
			if (matrix[m][0] == target) {
				return true;
			} else if (matrix[m][0] < target) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		int cur = matrix[l][0] > target ? l - 1 : l;
		if (cur < 0) {
			return false;
		}
		l = 0;
		r = matrix[cur].length - 1;
		while (l < r) {
			m = (r - l) / 2 + l;
			if (matrix[cur][m] == target) {
				return true;
			} else if (matrix[cur][m] < target) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		if (matrix[cur][l] == target) {
			return true;
		}
		return false;
	}
	
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left != null) {
			if (!isValidBSTHeler(root.left, null, root.val)) {
				return false;
			}
		}
		if (root.right != null) {
			if (!isValidBSTHeler(root.right, root.val, null)) {
				return false;
			}
		}
		return true;
		
		
	}
	
	private boolean isValidBSTHeler(TreeNode root, Integer min, Integer max) {
		int val = root.val;
		if (min != null && val <= min) {
			return false;
		}
		if (max != null && val >= max) {
			return false;
		}
		if (root.left != null) {
			if (!isValidBSTHeler(root.left, min, val)) {
				return false;
			}
		}
		if (root.right != null) {
			if (!isValidBSTHeler(root.right, val, max)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean exist(char[][] board, String word) {
		if (word.length() < 1) {
			return true;
		}
		char[] array = word.toCharArray();
		int n = board.length;
		int m = board[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == array[0]) {
					if (existHelper(board, i, j, array, 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean existHelper(char[][] board, int i, int j, char[] word, int cur
	) {
		if (cur == word.length) {
			return true;
		}
		char before = board[i][j];
		board[i][j] = '0';
		int n = board.length;
		int m = board[0].length;
		char c = word[cur];
		if (i > 0 && board[i - 1][j] == c) {
			if (existHelper(board, i - 1, j, word, cur + 1)) {
				return true;
			}
		}
		if (i < n - 1 && board[i + 1][j] == c) {
			if (existHelper(board, i + 1, j, word, cur + 1)) {
				return true;
			}
		}
		if (j < m - 1 && board[i][j + 1] == c) {
			if (existHelper(board, i, j + 1, word, cur + 1)) {
				return true;
			}
		}
		if (j > 0 && board[i][j - 1] == c) {
			if (existHelper(board, i, j - 1, word, cur + 1)) {
				return true;
			}
		}
		board[i][j] = before;
		return false;
	}
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			return head;
		}
		ListNode before = null;
		ListNode cur = head;
		int i = 1;
		while (i < m) {
			before = cur;
			cur = cur.next;
			i++;
		}
		ListNode middle = null;
		ListNode next;
		ListNode last = cur;
		while (i <= n) {
			next = cur.next;
			cur.next = middle;
			middle = cur;
			cur = next;
			i++;
		}
		last.next = cur;
		if (before != null) {
			before.next = middle;
			return head;
		} else {
			return middle;
		}
		
		
	}
	
	public int[] searchRange(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		int m = l + (r - l) / 2;
		while (l < r) {
			m = l + (r - l) / 2;
			if (nums[m] == target) {
				break;
			} else if (nums[m] < target) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		int[] ints = {-1, -1};
		if (l == r) {
			if (nums[l] == target) {
				return new int[]{l, l};
			} else {
				return ints;
			}
		} else {
			int k = m, x = (l + k) / 2;
			if (nums[l] == target) {
				ints[0] = l;
			} else {
				while (true) {
					x = (l + k) / 2;
					if (nums[x] < target && nums[x + 1] == target) {
						ints[0] = x + 1;
						break;
					} else if (nums[x] < target) {
						l = x + 1;
					} else {
						k = x;
					}
				}
			}
			
			k = m;
			if (nums[r] == target) {
				ints[1] = r;
			} else {
				while (true) {
					x = (r + k) / 2;
					if (nums[x + 1] > target && nums[x] == target) {
						ints[1] = x;
						break;
					} else if (nums[x + 1] > target) {
						r = x;
					} else {
						k = x + 1;
					}
				}
			}
			
			return ints;
		}
	}
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode f = head;
		while (n > 0) {
			f = f.next;
			n--;
		}
		if (f == null) {
			return head.next;
		}
		ListNode s = head;
		while (f.next != null) {
			f = f.next;
			s = s.next;
		}
		s.next = s.next.next;
		return head;
	}
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; ) {
			for (int j = i + 1; j < nums.length; ) {
				fourSumHelper(ret, i, j, target, nums);
				j++;
				while (j < nums.length && nums[j - 1] == nums[j]) {
					j++;
				}
			}
			i++;
			while (i < nums.length && nums[i - 1] == nums[i]) {
				i++;
			}
		}
		return ret;
	}
	
	private void fourSumHelper(ArrayList<List<Integer>> ret, int first, int second, int target,
		int[] nums) {
		int t = target - nums[first] - nums[second];
		int l = second + 1, r = nums.length - 1;
		while (l < r) {
			if (nums[l] + nums[r] == t) {
				ret.add(Arrays.asList(nums[first], nums[second], nums[l], nums[r]));
				l++;
				r--;
				while (l < r && nums[l - 1] == nums[l]) {
					l++;
				}
				while (l < r && nums[r] == nums[r + 1]) {
					r--;
				}
			} else if (nums[l] + nums[r] > t) {
				r--;
			} else {
				l++;
			}
		}
	}
	
	
	public int findMaxLength3(int[] nums) {
		int[] ints = new int[nums.length * 2 + 1];
		int c = nums.length;
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			c += nums[i] == 0 ? -1 : 1;
			if (c == nums.length) {
				ret = i + 1;
			} else {
				if (ints[c] == 0) {
					ints[c] = i;
				} else if (i - ints[c] > ret) {
					ret = i - ints[c];
				}
			}
		}
		return ret;
	}
	
	public List<List<Integer>> threeSum2(int[] nums) {
		Arrays.sort(nums);
		ArrayList<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
			int ni = nums[i];
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int nl = nums[l];
				int nr = nums[r];
				int sum = ni + nl + nr;
				if (sum == 0) {
					ret.add(Arrays.asList(ni, nl, nr));
					while (l < r && nums[l + 1] == nl) {
						l++;
					}
					l++;
					while (l < r && nums[r - 1] == nr) {
						r--;
					}
					r--;
				} else if (sum > 0) {
					while (l < r && nums[r - 1] == nr) {
						r--;
					}
					r--;
				} else {
					while (l < r && nums[l + 1] == nl) {
						l++;
					}
					l++;
				}
			}
			while (i < nums.length - 1 && nums[i + 1] == nums[i]) {
				i++;
			}
		}
		return ret;
	}
	
	public int myAtoi(String str) {
		Integer flag = null;
		long ret = 0;
		String trim = str.trim();
		for (char c : trim.toCharArray()) {
			if (c == '+' && flag == null) {
				flag = 1;
			} else if (c == '-' && flag == null) {
				flag = -1;
			} else if (c >= '0' && c <= '9') {
				if (flag == null) {
					flag = 1;
				}
				ret *= 10;
				ret += c - '0';
				if (ret * flag > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				} else if (ret * flag < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			} else {
				break;
			}
		}
		if (flag == null) {
			flag = 1;
		}
		ret *= flag;
		return (int) ret;
		
	}
	
	public List<List<Integer>> threeSum1(int[] nums) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		int min = nums[0], max = min;
		for (int num : nums) {
			if (num > max) {
				max = num;
			} else if (num < min) {
				min = num;
			}
		}
		if (min > 0 || max < 0) {
			return ret;
		}
		int[] a = new int[max + 1];
		int[] b = new int[-min + 1];
		for (int num : nums) {
			if (num < 0) {
				b[-num]++;
			} else {
				a[num]++;
			}
		}
		
		for (int i = 1; i < a.length; i++) {
			if (a[i] > 0) {
				threeSum1Helper(i, ret, b);
			}
		}
		for (int i = 1; i < b.length; i++) {
			if (b[i] > 0) {
				threeSum1Helper(-i, ret, a);
			}
		}
		if (a.length > 0 && a[0] >= 3) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(0);
			list.add(0);
			list.add(0);
			ret.add(list);
		}
		if (a.length > 0 && a[0] > 0) {
			int i = 1;
			while (i < a.length && i < b.length) {
				if (a[i] > 0 && b[i] > 0) {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(0);
					list.add(-i);
					list.add(i);
					ret.add(list);
				}
				i++;
			}
		}
		return ret;
	}
	
	private void threeSum1Helper(int i, ArrayList<List<Integer>> ret, int[] b) {
		int i1 = Math.abs(i);
		int r = b.length - 1 >= i1 - 1 ? i1 - 1 : b.length - 1;
		int l = i1 - r;
		while (l < r) {
			if (b[l] > 0 && b[r] > 0) {
				ArrayList<Integer> list = new ArrayList<>();
				if (i > 0) {
					list.add(i);
					list.add(-l);
					list.add(-r);
				} else {
					list.add(i);
					list.add(l);
					list.add(r);
				}
				ret.add(list);
			}
			l++;
			r--;
		}
		if (l == r && b[l] >= 2) {
			ArrayList<Integer> list = new ArrayList<>();
			if (i > 0) {
				list.add(i);
				list.add(-l);
				list.add(-r);
			} else {
				list.add(i);
				list.add(l);
				list.add(r);
			}
			ret.add(list);
		}
	}
	
	public List<List<Integer>> threeSum(int[] nums) {
		int min = nums[0], max = min;
		for (int num : nums) {
			if (num > max) {
				max = num;
			} else if (num < min) {
				min = num;
			}
		}
		int[] ints = new int[max - min + 1];
		for (int num : nums) {
			ints[num - min]++;
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > 0) {
				ints[i]--;
				threeSumHelper(ints, i, ret, i, min);
			}
		}
		return ret;
	}
	
	private void threeSumHelper(int[] ints, int i, ArrayList<List<Integer>> ret, int first,
		int min) {
		for (; i < ints.length; i++) {
			int thrid = -3 * min - first - i;
			if (thrid < i) {
				return;
			}
			if (ints[i] > 0) {
				if (thrid >= ints.length) {
					continue;
				}
				ints[i]--;
				if (ints[thrid] > 0) {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(first + min);
					list.add(i + min);
					list.add(-2 * min - first - i);
					ret.add(list);
				}
				ints[i]++;
				
				
			}
		}
	}
	
	
	public int findMaxLength2(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int c = 0;
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				c--;
			} else {
				c++;
			}
			if (c == 0) {
				ret = i + 1;
			} else {
				if (!map.containsKey(c)) {
					map.put(c, i);
				} else {
					if (i - map.get(c) > ret) {
						ret = i - map.get(c);
					}
				}
			}
		}
		return ret;
	}
	
	public int findMaxLength1(int[] nums) {
		int[] ints = new int[nums.length];
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			int t = nums[i] == 0 ? -1 : 1;
			for (int j = 0; j <= i; j++) {
				ints[j] += t;
				if (ints[j] == 0 && i - j + 1 > ret) {
					ret = i - j + 1;
				}
			}
		}
		return ret;
	}
	
	public int findMaxLength(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int[] c0 = new int[nums.length];
		int[] c1 = new int[nums.length];
		if (nums[0] == 0) {
			c0[0] = 1;
		} else {
			c1[0] = 1;
		}
		for (int i = 1; i < nums.length; i++) {
			c0[i] = c0[i - 1];
			c1[i] = c1[i - 1];
			if (nums[i] == 0) {
				c0[i]++;
			} else {
				c1[i]++;
			}
		}
		int ret = 0;
		for (int i = nums.length - 1; i > ret; i--) {
			if (c0[i] == c1[i] && ret < i + 1) {
				ret = i + 1;
				break;
			}
			int j = 0;
			while (i - j > ret) {
				if (c0[i] - c0[j] == c1[i] - c1[j]) {
					ret = i - j;
					break;
				}
				j++;
			}
		}
		return ret;
	}
	
	public List<List<Integer>> findSubsequences(int[] nums) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		boolean[] use = new boolean[201];
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (use[num + 100]) {
				continue;
			}
			use[num + 100] = true;
			list.clear();
			list.add(num);
			findSubsequencesHelper(nums, i + 1, list, ret);
		}
		return ret;
	}
	
	public double[] sampleStats(int[] count) {
		double[] ret = new double[5];
		int cnt = count[0];
		long sum = 0;
		int mIdx = 0;
		int maxIdx = 0;
		int halfCnt = count[0];
		if (count[0] > 0) {
			ret[0] = 0;
		} else {
			ret[0] = -1;
		}
		for (int i = 1; i < count.length; i++) {
			if (count[i] != 0) {
				if (ret[0] == -1) {
					ret[0] = (double) i;
				}
				if (count[i] > count[maxIdx]) {
					maxIdx = i;
				}
				ret[1] = (double) i;
				cnt += count[i];
				sum += count[i] * i;
				while ((halfCnt + count[mIdx]) * 2 < cnt) {
					halfCnt += count[mIdx];
					mIdx++;
				}
			}
			
		}
		ret[4] = (double) maxIdx;
		ret[2] = ((double) sum) / cnt;
		if ((cnt & 1) == 1) {
			ret[3] = (double) mIdx;
		} else {
			if (halfCnt + count[mIdx] > cnt / 2) {
				ret[3] = (double) mIdx;
			} else {
				int i = mIdx + 1;
				while (i < count.length && count[i] == 0) {
					i++;
				}
				ret[3] = ((double) i + mIdx) / 2;
			}
		}
		return ret;
	}
	
	private void findSubsequencesHelper(int[] nums, int i, LinkedList<Integer> list,
		ArrayList<List<Integer>> ret) {
		Integer last = list.getLast();
		boolean[] use = new boolean[201];
		for (; i < nums.length; i++) {
			int num = nums[i];
			if (use[num + 100]) {
				continue;
			} else if (num >= last) {
				use[num + 100] = true;
				list.add(num);
				ret.add(new ArrayList<>(list));
				findSubsequencesHelper(nums, i + 1, list, ret);
				list.removeLast();
			}
		}
	}
	
	public int subarraySum1(int[] nums, int k) {
		int[] ints = new int[40000001];
		int last = 20000000;
		ints[last] = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			last += nums[i];
			ints[last]++;
		}
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			ints[last]--;
			ret += ints[last - k];
			last -= nums[i];
		}
		return ret;
	}
	
	public int subarraySum2(int[] nums, int k) {
		int ret = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] == k) {
					ret++;
				}
				nums[j] += nums[i];
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == k) {
				ret++;
			}
		}
		return ret;
	}
	
	public int subarraySum3(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i < nums.length; i++) {
			nums[i] += nums[i - 1];
		}
		map.put(0, 1);
		int ret = 0;
		for (int num : nums) {
			ret += map.getOrDefault(num - k, 0);
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		return ret;
	}
	
	public int subarraySum(int[] nums, int k) {
		int last = 0;
		
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		for (int i = nums.length - 1; i >= 0; i--) {
			last += nums[i];
			map.put(last, map.getOrDefault(last, 0) + 1);
		}
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			map.put(last, map.get(last) - 1);
			ret += map.getOrDefault(last - k, 0);
			last -= nums[i];
		}
		
		return ret;
	}
	
	public int totalFruit2(int[] tree) {
		int a = tree[0], b = tree[0];
		int ret = 0;
		int count = 1;
		int nextCount = 1;
		int i = 1;
		for (; i < tree.length; i++) {
			if (tree[i] != a) {
				b = tree[i];
				break;
			}
			count++;
		}
		for (; i < tree.length; i++) {
			if (tree[i] == a) {
				if (tree[i - 1] != tree[i]) {
					nextCount = 1;
				} else {
					nextCount++;
				}
				count++;
			} else if (tree[i] == b) {
				if (tree[i - 1] != tree[i]) {
					nextCount = 1;
				} else {
					nextCount++;
				}
				count++;
			} else {
				if (ret < count) {
					ret = count;
				}
				a = tree[i - 1];
				b = tree[i];
				count = nextCount + 1;
				nextCount = 1;
			}
		}
		return ret > count ? ret : count;
	}
	
	public int totalFruit1(int[] tree) {
		int a = tree[0], b = tree[0];
		int next = 0;
		int ret = 0;
		int count = 1;
		int i = 1;
		for (; i < tree.length; i++) {
			if (tree[i] != a) {
				b = tree[i];
				next = i;
				break;
			}
			count++;
		}
		for (; i < tree.length; i++) {
			if (tree[i] == a) {
				if (tree[i - 1] != tree[i]) {
					next = i;
				}
				count++;
			} else if (tree[i] == b) {
				if (tree[i - 1] != tree[i]) {
					next = i;
				}
				count++;
			} else {
				if (ret < count) {
					ret = count;
				}
				a = tree[next];
				b = tree[i];
				count = 1;
				i = next;
			}
		}
		return ret > count ? ret : count;
	}
	
	public int totalFruit(int[] tree) {
		int next = 0;
		HashSet<Integer> set = new HashSet<>();
		set.add(tree[0]);
		int ret = 0;
		int count = 1;
		for (int i = 1; i < tree.length; i++) {
			if (set.contains(tree[i])) {
				count++;
			} else if (set.size() < 2) {
				set.add(tree[i]);
				next = i;
				count++;
			} else {
				if (ret < count) {
					ret = count;
				}
				i = next;
				count = 1;
				set.clear();
				set.add(tree[i]);
			}
		}
		return ret > count ? ret : count;
	}
	
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] ints = new int[n];
		ints[0] = 1;
		Arrays.sort(primes);
		int[] idx = new int[primes.length];
		for (int i = 1; i < n; i++) {
			
			while (primes[0] * ints[idx[0]] <= ints[i - 1]) {
				idx[0]++;
			}
			int k = 0;
			for (int j = 1; j < primes.length; j++) {
				while (primes[j] * ints[idx[j]] <= ints[i - 1]) {
					idx[j]++;
				}
				if (primes[j] * ints[idx[j]] < primes[k] * ints[idx[k]]) {
					k = j;
				}
			}
			ints[i] = primes[k] * ints[idx[k]];
			idx[k]++;
		}
		return ints[n - 1];
	}
	
	public int nthSuperUglyNumber2(int n, int[] primes) {
		int[] ints = new int[n];
		ints[0] = 1;
		int[] idx = new int[primes.length];
		for (int i = 1; i < n; i++) {
			int x = Integer.MAX_VALUE;
			int k = -1;
			for (int j = 0; j < primes.length; j++) {
				if (primes[j] * ints[idx[j]] <= ints[i - 1]) {
					idx[j]++;
				}
				if (primes[j] * ints[idx[j]] < x) {
					k = j;
					x = primes[j] * ints[idx[j]];
				}
			}
			ints[i] = x;
			idx[k]++;
		}
		return ints[n - 1];
	}
	
	public int nthSuperUglyNumber1(int n, int[] primes) {
		int count = n - 1, ret = 2;
		while (count > 0) {
			int cur = ret;
			for (int prime : primes) {
				while (cur % prime == 0) {
					cur /= prime;
				}
			}
			if (cur == 1) {
				count--;
			} else {
				n++;
			}
			ret++;
		}
		return n;
	}
	
	public List<List<String>> partition(String s) {
		ArrayList<List<String>> ret = new ArrayList<>();
		if (s.length() == 0) {
			ArrayList<String> list = new ArrayList<String>();
			ret.add(list);
			return ret;
		}
		char[] array = s.toCharArray();
		partitionHelper(ret, new LinkedList<String>(), array, 0);
		return ret;
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
		for (int i = 1; i < preorder.length; i++) {
			buildTreeHelper(root, preorder[i], inorder);
		}
		return root;
	}
	
	private void buildTreeHelper(TreeNode root, int cur, int[] inorder) {
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == cur) {
				if (root.left == null) {
					root.left = new TreeNode(cur);
				} else {
					buildTreeHelper(root.left, cur, inorder);
				}
				return;
			} else if (inorder[i] == root.val) {
				if (root.right == null) {
					root.right = new TreeNode(cur);
				} else {
					buildTreeHelper(root.right, cur, inorder);
				}
				return;
			}
		}
	}
	
	int preIdx = 0, inIdx = 0;
	
	public TreeNode buildTree1(int[] preorder, int[] inorder) {
		return buildTree1Helper(preorder, inorder, Integer.MAX_VALUE);
	}
	
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildTree2Helper(preorder, inorder, 0, preorder.length, map);
	}
	
	private TreeNode buildTree2Helper(int[] preorder, int[] inorder, int l, int r,
		Map<Integer, Integer> map) {
		if (l < r) {
			int val = preorder[preIdx++];
			TreeNode root = new TreeNode(val);
			root.left = buildTree2Helper(preorder, inorder, l, map.get(val), map);
			root.right = buildTree2Helper(preorder, inorder, map.get(val) + 1, r, map);
			return root;
		} else {
			return null;
		}
	}
	
	private TreeNode buildTree1Helper(int[] preorder, int[] inorder, int bound) {
		if (inIdx == inorder.length || inorder[inIdx] == bound) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preIdx]);
		preIdx++;
		TreeNode left = buildTree1Helper(preorder, inorder, root.val);
		inIdx++;
		TreeNode right = buildTree1Helper(preorder, inorder, bound);
		root.left = left;
		root.right = right;
		return root;
	}
	
	int numSquares = Integer.MAX_VALUE;
	
	public int numSquares(int n) {
		if (n < 3) {
			return n;
		}
		int[] ints = new int[n + 1];
		ints[1] = 1;
		ints[2] = 2;
		int k = 2;
		for (int i = 3; i <= n; i++) {
			if (k * k == i) {
				k++;
				ints[i] = 1;
			} else {
				int min = Integer.MAX_VALUE;
				for (int j = 1; j * j < i; j++) {
					if (ints[i - j * j] < min) {
						min = ints[i - j * j];
						if (min == 1) {
							break;
						}
					}
				}
				ints[i] = min + 1;
			}
		}
		return ints[n];
	}
	
	private void partitionHelper(ArrayList<List<String>> ret, LinkedList<String> list,
		char[] array, int begin) {
		for (int i = begin; i < array.length; i++) {
			int l = begin, r = i;
			while (l < r) {
				if (array[l] == array[r]) {
					l++;
					r--;
				} else {
					break;
				}
			}
			if (l < r) {
				continue;
			} else {
				if (i < array.length - 1) {
					String s = String.valueOf(array, begin, i - begin + 1);
					list.add(s);
					partitionHelper(ret, list, array, i + 1);
					list.removeLast();
				} else {
					ArrayList<String> ls = new ArrayList<>(list);
					ls.add(String.valueOf(array, begin, i - begin + 1));
					ret.add(ls);
				}
			}
		}
	}
	
	public List<List<Integer>> permuteUnique(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		for (int num : nums) {
			if (num > max) {
				max = num;
			} else if (num < min) {
				min = num;
			}
		}
		int[] ints = new int[max - min + 1];
		for (int num : nums) {
			ints[num - min]++;
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		permuteUniqueHelper(ints, min, new LinkedList<Integer>(), ret, 0, nums.length);
		return ret;
	}
	
	private void permuteUniqueHelper(int[] ints, int min, LinkedList<Integer> list,
		ArrayList<List<Integer>> ret, int c, int length) {
		if (c == length) {
			ArrayList<Integer> l = new ArrayList<>(list);
			ret.add(l);
			return;
		}
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > 0) {
				ints[i]--;
				list.add(i + min);
				permuteUniqueHelper(ints, min, list, ret, c + 1, length);
				ints[i]++;
				list.removeLast();
			}
		}
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		if (root == null) {
			return ret;
		}
		LinkedList<Integer> list = new LinkedList<>();
		pathSumHelper(root, list, 0, sum, ret);
		return ret;
		
	}
	
	private void pathSumHelper(TreeNode root, LinkedList<Integer> list, int i, int sum,
		List<List<Integer>> ret) {
		int val = root.val;
		if (root.left == null && root.right == null) {
			if (i + val == sum) {
				ArrayList<Integer> l = new ArrayList<>();
				l.addAll(list);
				l.add(val);
				ret.add(l);
			}
		} else {
			list.add(val);
			if (root.left != null) {
				pathSumHelper(root.left, list, i + val, sum, ret);
			}
			if (root.right != null) {
				pathSumHelper(root.right, list, i + val, sum, ret);
			}
			list.removeLast();
		}
	}
	
	public int findPeakElement(int[] nums) {
		int l = 0;
		int r = nums.length - 1;
		int m;
		while (l < r) {
			m = l + (r - l) / 2;
			if (nums[m] < nums[m + 1]) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		return l;
	}
	
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return new TreeNode(head.val);
		}
		ListNode s = head;
		ListNode f = head;
		ListNode before = head;
		while (f != null && f.next != null) {
			before = s;
			s = s.next;
			f = f.next.next;
		}
		TreeNode root = new TreeNode(s.val);
		root.right = sortedListToBST(s.next);
		before.next = null;
		root.left = sortedListToBST(head);
		return root;
	}
	
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > '0') {
					count++;
					numIslandsHelper(i, j, grid);
				}
			}
		}
		return count;
	}
	
	private void numIslandsHelper(int i, int j, char[][] grid) {
		grid[i][j] = '0';
		if (i > 0 && grid[i - 1][j] > '0') {
			numIslandsHelper(i - 1, j, grid);
		}
		if (i < grid.length - 1 && grid[i + 1][j] > '0') {
			numIslandsHelper(i + 1, j, grid);
		}
		if (j > 0 && grid[i][j - 1] > '0') {
			numIslandsHelper(i, j - 1, grid);
		}
		if (j < grid[i].length - 1 && grid[i][j + 1] > '0') {
			numIslandsHelper(i, j + 1, grid);
		}
	}
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<List<Integer>> list = new ArrayList<>();
		if (root != null) {
			zigzagLevelOrderHelper(list, root, 0);
		}
		return list;
	}
	
	public List<String> letterCombinations1(String digits) {
		char[][] chars = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
			{'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}};
		ArrayList<String> list = new ArrayList<>();
		if (digits.length() < 1) {
			return list;
		}
		char[] array = digits.toCharArray();
		char[] s = new char[array.length];
		letterCombinationsHelper(s, 0, array, chars, list);
		return list;
	}
	
	private void letterCombinationsHelper(char[] s, int i, char[] array, char[][] chars,
		ArrayList<String> list) {
		if (i == array.length) {
			list.add(new String(s));
			return;
		}
		for (char c : chars[array[i] - '0']) {
			s[i] = c;
			letterCombinationsHelper(s, i + 1, array, chars, list);
		}
	}
	
	public List<String> letterCombinations(String digits) {
		char[][] chars = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
			{'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}};
		char[] array = digits.toCharArray();
		ArrayList<String> list = new ArrayList<>();
		if (digits.length() < 1) {
			return list;
		}
		for (char c : chars[array[0] - '0']) {
			list.add(c + "");
		}
		for (int i = 1; i < array.length; i++) {
			ArrayList<String> newList = new ArrayList<>();
			for (char c : chars[array[i] - '0']) {
				for (int j = 0; j < list.size(); j++) {
					newList.add(list.get(j) + c);
				}
			}
			list = newList;
		}
		return list;
	}
	
	private void zigzagLevelOrderHelper(ArrayList<List<Integer>> list, TreeNode root, int level) {
		List<Integer> cur = null;
		if (list.size() == level) {
			cur = new ArrayList<>();
			list.add(cur);
		} else {
			cur = list.get(level);
		}
		if ((level & 1) == 1) {
			cur.add(0, root.val);
		} else {
			cur.add(root.val);
		}
		if (root.left != null) {
			zigzagLevelOrderHelper(list, root.left, level + 1);
		}
		if (root.right != null) {
			zigzagLevelOrderHelper(list, root.right, level + 1);
		}
	}
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		ArrayList<List<Integer>> list = new ArrayList<>();
		Arrays.sort(candidates);
		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] < target) {
				ArrayList<Integer> cur = new ArrayList<>();
				cur.add(candidates[i]);
				combinationSum2Helper(candidates, i + 1, candidates[i], target, cur, list);
			} else if (candidates[i] == target) {
				ArrayList<Integer> l = new ArrayList<>();
				l.add(target);
				list.add(l);
				break;
			}
			while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {
				i++;
			}
		}
		return list;
	}
	
	private void combinationSum2Helper(int[] candidates, int i, int sum, int target,
		ArrayList<Integer> cur,
		ArrayList<List<Integer>> list) {
		for (; i < candidates.length; i++) {
			int s = sum + candidates[i];
			if (s < target) {
				cur.add(candidates[i]);
				combinationSum2Helper(candidates, i + 1, s, target, cur, list);
				cur.remove(candidates[i]);
			} else if (s == target) {
				ArrayList<Integer> c = new ArrayList<>();
				c.addAll(cur);
				c.add(candidates[i]);
				list.add(c);
				return;
			} else {
				return;
			}
			while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {
				i++;
			}
		}
	}
	
	public void sortColors1(int[] nums) {
		int l = 0;
		int r = nums.length - 1;
		for (int i = 0; i <= r; ) {
			if (nums[i] == 0) {
				int t = nums[l];
				nums[l++] = 0;
				nums[i++] = t;
			} else if (nums[i] == 2) {
				int t = nums[r];
				nums[r--] = 2;
				nums[i] = t;
			} else {
				i++;
			}
			
		}
	}
	
	public void sortColors(int[] nums) {
		int[] ints = new int[3];
		for (int num : nums) {
			ints[num]++;
		}
		int l = 0;
		for (int i = 0; i < ints.length; i++) {
			while (ints[i] > 0) {
				nums[l] = i;
				ints[i]--;
				l++;
			}
		}
	}
	
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		ListNode ret = l1;
		ListNode before = l1;
		int k = 0;
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + k;
			l1.val = sum % 10;
			k = sum > 9 ? 1 : 0;
			before = l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		if (l2 != null) {
			before.next = l2;
		}
		l1 = before.next;
		while (k != 0 && l1 != null) {
			int sum = l1.val + k;
			l1.val = sum % 10;
			k = sum > 9 ? 1 : 0;
			before = l1;
			l1 = l1.next;
		}
		if (k == 1) {
			before.next = new ListNode(1);
		}
		return ret;
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int k = 0;
		ListNode ret = null;
		ListNode cur = ret;
		
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + k;
			cur = new ListNode(sum % 10);
			k = sum > 9 ? 1 : 0;
			l1 = l1.next;
			l2 = l2.next;
			cur = cur.next;
		}
		if (k == 0) {
			cur = l1 == null ? l2 : l1;
		} else {
			ListNode l = l1 == null ? l2 : l1;
			while (k != 0 && l != null) {
				int sum = k + l.val;
				k = sum > 9 ? 1 : 0;
				cur = new ListNode(sum % 10);
				cur = cur.next;
				l = l.next;
			}
			if (k == 0) {
				cur = l;
			} else {
				cur = new ListNode(1);
			}
		}
		
		return ret;
	}
	
	public boolean isBipartite(int[][] graph) {
		boolean[] a = new boolean[graph.length];
		boolean[] b = new boolean[graph.length];
		boolean[] used = new boolean[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (!used[i]) {
				if (!isBipartiteHelper(graph, i, a, b, used)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isBipartiteHelper(int[][] graph, int i, boolean[] a, boolean[] b,
		boolean[] used) {
		used[i] = true;
		a[i] = true;
		for (int j : graph[i]) {
			if (a[j]) {
				return false;
			}
			if (!used[j]) {
				if (!isBipartiteHelper(graph, j, b, a, used)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int minIncrementForUnique(int[] A) {
		int[] ints = new int[80001];
		for (int i : A) {
			ints[i]++;
		}
		int ret = 0;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > 1) {
				ret += ints[i] - 1;
				ints[i + 1] += ints[i] - 1;
			}
		}
		return ret;
	}
	
	public String reorganizeString(String S) {
		int length = S.length();
		if (length < 2) {
			return S;
		}
		int[] count = new int[26];
		int max = 0;
		char[] array = S.toCharArray();
		for (char c : array) {
			count[c - 'a']++;
			if (count[c - 'a'] > count[max]) {
				max = c - 'a';
			}
		}
		
		if (count[max] > (length + 1) / 2) {
			return "";
		}
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i - 1]) {
				reorganizeStringHelper(i, array);
			}
		}
		return new String(array);
	}
	
	private void reorganizeStringHelper(int i, char[] array) {
		for (int j = i + 1; j < array.length; j++) {
			if (array[i] != array[j]) {
				char temp = array[j];
				array[j] = array[i];
				array[i] = temp;
				return;
			}
		}
		for (int j = 0; j < i; j++) {
			if (array[i] != array[j] && (j == 0 || array[i] != array[j - 1]) && array[i] != array[j
				+ 1]) {
				char temp = array[j];
				array[j] = array[i];
				array[i] = temp;
				return;
			}
		}
		char c = array[i];
		for (int j = i - 1; j >= 0; j--) {
			if (array[i] != array[j] && (j == 0 || array[i] != array[j - 1])) {
				array[j + 1] = array[j];
				array[j] = c;
				break;
			} else {
				array[j + 1] = array[j];
			}
		}
		
	}
	
	public int[] findRightInterval(int[][] intervals) {
		int min = intervals[0][0];
		int max = intervals[0][0];
		for (int[] interval : intervals) {
			int cur = interval[0];
			if (cur > max) {
				max = cur;
			} else if (cur < min) {
				min = cur;
			}
		}
		int[] ints = new int[max - min + 1];
		Arrays.fill(ints, -1);
		for (int i = 0; i < intervals.length; i++) {
			ints[intervals[i][0] - min] = i;
		}
		int[] ret = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			int end = intervals[i][1] - min;
			while (end < ints.length && ints[end] == -1) {
				end++;
			}
			if (end >= ints.length) {
				ret[i] = -1;
			} else {
				ret[i] = ints[end];
			}
		}
		return ret;
	}
	
	class Node {
		
		public int val;
		public Node prev;
		public Node next;
		public Node child;
		
		public Node() {
		}
		
		public Node(int _val, Node _prev, Node _next, Node _child) {
			val = _val;
			prev = _prev;
			next = _next;
			child = _child;
		}
		
	}
	
	public Node flatten(Node head) {
		Node curr = head;
		while (curr != null) {
			Node next = curr.next;
			if (curr.child != null) {
				Node last = flattenNodeHelper(curr);
				last.next = next;
				if (next != null) {
					next.prev = last;
				}
			}
			curr = next;
		}
		return head;
	}
	
	private Node flattenNodeHelper(Node curr) {
		Node child = curr.child;
		child.prev = curr;
		curr.next = child;
		curr.child = null;
		Node ret = curr;
		while (curr != null) {
			ret = curr;
			Node next = curr.next;
			if (curr.child != null) {
				ret = flattenNodeHelper(curr);
				ret.next = next;
				if (next != null) {
					next.prev = ret;
				}
			}
			curr = next;
		}
		return ret;
	}
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		ArrayList<List<Integer>> ret = new ArrayList<>();
		ret.add(new ArrayList<>());
		for (int i = 0; i < nums.length; ) {
			int num = nums[i];
			ArrayList<List<Integer>> newList = new ArrayList<>();
			ArrayList<Integer> list = new ArrayList<>();
			while (i < nums.length && nums[i] == num) {
				i++;
				list.add(num);
				for (List<Integer> integers : ret) {
					ArrayList<Integer> cur = new ArrayList<>();
					cur.addAll(integers);
					cur.addAll(list);
					newList.add(cur);
				}
			}
			ret.addAll(newList);
		}
		return ret;
	}
	
	int sumNumbers;
	
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		sumNumbersHelper(root, 0);
		return sumNumbers;
	}
	
	private void sumNumbersHelper(TreeNode root, int i) {
		int before = i * 10 + root.val;
		if (root.left == null && root.right == null) {
			this.sumNumbers += before;
		}
		if (root.left != null) {
			sumNumbersHelper(root.left, before);
		}
		if (root.right != null) {
			sumNumbersHelper(root.right, before);
		}
	}
	
	public void flatten(TreeNode root) {
		TreeNode curr = root;
		if (curr == null) {
			return;
		}
		if (curr.left == null && curr.right == null) {
			return;
		}
		flattenHelper(curr);
	}
	
	private TreeNode flattenHelper(TreeNode root) {
		TreeNode left = root.left;
		TreeNode right = root.right;
		if (left == null && right == null) {
			return root;
		}
		if (left != null) {
			root.left = null;
			root.right = left;
			root = flattenHelper(left);
		}
		if (right != null) {
			root.right = right;
			root = flattenHelper(right);
		}
		return root;
	}
	
	public void flatten1(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode cur = root;
		while (cur != null && cur.left == null) {
			cur = cur.right;
		}
		if (cur == null) {
			return;
		}
		Stack<TreeNode> treeNodes = new Stack<>();
		while (cur.left != null || !treeNodes.empty()) {
			if (cur.left == null) {
				if (cur.right == null) {
					cur.right = treeNodes.pop();
				}
				cur = cur.right;
			} else {
				if (cur.right != null) {
					treeNodes.push(cur.right);
				}
				cur.right = cur.left;
				cur.left = null;
				cur = cur.right;
			}
			while (cur.left == null && cur.right != null) {
				cur = cur.right;
			}
		}
	}
	
	public int maxProfit(int[] prices) {
		return maxProfitHelper(prices, 0);
	}
	
	private int maxProfitHelper(int[] prices, int i) {
		for (; i < prices.length; i++) {
			int k = i;
			while (k < prices.length - 1 && prices[k] < prices[k + 1]) {
				k++;
			}
			if (i < k) {
				return Math.max(prices[k] - prices[i] + maxProfitHelper(prices, k + 1),
					prices[k - 1] - prices[i] + maxProfitHelper(prices, k));
			}
		}
		return 0;
	}
	
	public int maxProfit1(int[] prices) {
		int[][] ints = new int[prices.length][2];
		ints[0][1] = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			ints[i][0] = Math.max(ints[i][0], ints[i][1] + prices[i]);
			if (i < 1) {
				ints[i][1] = Math.max(ints[i][1], ints[i][0] - prices[i]);
			} else {
				ints[i][1] = Math.max(ints[i][1], ints[i - 2][0] - prices[i]);
			}
		}
		return ints[prices.length - 1][0];
	}
	
	
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1, m;
		if (nums[l] < nums[r]) {
			return nums[l];
		}
		int num = nums[0];
		while (l < r) {
			m = l + (r - l) / 2;
			if (nums[m] >= num) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		return nums[l];
	}
	
	public int change(int amount, int[] coins) {
		int[] ints = new int[amount + 1];
		ints[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				ints[j] += ints[j - coins[i]];
			}
		}
		return ints[amount];
	}
	
	public int orderOfLargestPlusSign(int N, int[][] mines) {
		int[][] grid = new int[N][N];
		Arrays.fill(grid, N);
		for (int[] mine : mines) {
			grid[mine[0]][mine[1]] = -N;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0, k = N - 1, l = -N, r = -N, u = -N, d = -N; j < N; j++, k--) {
				grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == -N ? -N : l + 1));
				grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == -N ? -N : r + 1));
				grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == -N ? -N : u + 1));
				grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == -N ? -N : d + 1));
			}
		}
		int ret = -N;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret = Math.max(grid[i][j], ret);
			}
		}
		return ret + N;
	}
	
	public boolean isValidSudoku(char[][] board) {
		boolean[][] check = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			boolean[][] booleans = new boolean[2][9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int c = board[i][j] - '0';
					if (booleans[0][c]) {
						return false;
					} else {
						booleans[0][c] = true;
					}
					int k = i / 3 * 3 + j / 3;
					if (check[k][c]) {
						return false;
					} else {
						check[k][c] = true;
					}
				}
				if (board[j][i] != '.') {
					int c = board[j][i] - '0';
					if (booleans[1][c]) {
						return false;
					} else {
						booleans[1][c] = true;
					}
					/*int k=j/3*3+i/3;
					if(check[k][c]){
						return false;
					}else{
						check[k][c]=true;
					}*/
				}
				
			}
		}
		return true;
	}
	
	public int numRescueBoats(int[] people, int limit) {
		int[] ints = new int[limit + 1];
		for (int person : people) {
			ints[person]++;
		}
		int ret = 0;
		for (int i = limit; i > 0; i--) {
			while (ints[i] > 0) {
				ints[i]--;
				ret++;
				int k = Math.min(i, limit - i);
				while (k > 0 && ints[k] == 0) {
					k--;
				}
				if (k > 0) {
					ints[k]--;
				}
			}
		}
		return ret;
	}
	
	public List<Integer> eventualSafeNodes(int[][] graph) {
		boolean[] booleans = new boolean[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (booleans[i]) {
				continue;
			}
			HashSet<Integer> set = new HashSet<>();
			set.add(i);
			eventualSafeNodesHelper(graph, set, i, booleans);
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			if (!booleans[i]) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	private void eventualSafeNodesHelper(int[][] graph, HashSet<Integer> set, int i,
		boolean[] booleans) {
		
		for (int curr : graph[i]) {
			if (booleans[i] || set.contains(curr)) {
				for (Integer j : set) {
					booleans[j] = true;
				}
				return;
			}
			set.add(curr);
			eventualSafeNodesHelper(graph, set, curr, booleans);
			set.remove(curr);
		}
	}
	
	public List<Integer> eventualSafeNodes1(int[][] graph) {
		int[] color = new int[graph.length];
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			if (eventualSafeNodes1Helper(i, graph, color)) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	private boolean eventualSafeNodes1Helper(int curr, int[][] graph, int[] color) {
		if (color[curr] > 0) {
			return color[curr] == 2;
		}
		color[curr] = 1;
		for (int next : graph[curr]) {
			if (color[next] == 2) {
				continue;
			}
			if (color[next] == 1 || !eventualSafeNodes1Helper(next, graph, color)) {
				return false;
			}
		}
		color[curr] = 2;
		return true;
		
	}
	
	int triangleNumber;
	
	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);
		int[] ints = new int[2];
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] == 0) {
				continue;
			}
			ints[0] = nums[i];
			triangleNumberHelper(nums, i + 1, ints);
		}
		return triangleNumber;
	}
	
	private void triangleNumberHelper(int[] nums, int i, int[] ints) {
		for (; i < nums.length - 1; i++) {
			ints[1] = nums[i];
			int k = i + 1;
			while (k < nums.length) {
				if (nums[k] < ints[0] + ints[1]) {
					k++;
					triangleNumber++;
				} else {
					break;
				}
			}
		}
		
	}
	
	public int triangleNumber1(int[] nums) {
		Arrays.sort(nums);
		int ret = 0;
		for (int i = 2; i < nums.length; i++) {
			int l = 0, r = i - 1;
			while (l < r) {
				if (nums[l] + nums[r] > nums[i]) {
					ret += (r - l);
					r--;
				} else {
					l++;
				}
			}
		}
		return ret;
	}
	
	public String pushDominoes1(String dominoes) {
		char[] array = dominoes.toCharArray();
		for (int i = 0; i < array.length; ) {
			if (array[i] == '.') {
				int k = i;
				while (i < array.length && array[i] == '.') {
					i++;
				}
				if (i == array.length || array[i] == 'R') {
					while (k < i) {
						array[k] = '.';
						k++;
					}
				} else {
					while (k < i) {
						array[k] = 'L';
						k++;
					}
				}
			} else if (array[i] == 'R') {
				while (i < array.length && array[i] == 'R') {
					i++;
				}
				if (i < array.length && array[i] == '.') {
					int l = i;
					while (i < array.length && array[i] == '.') {
						i++;
					}
					if (array[i] == 'L') {
						int r = i - 1;
						while (l < r) {
							array[l] = 'R';
							array[r] = 'L';
							l++;
							r--;
						}
					} else {
						while (l < i) {
							array[l] = 'R';
							l++;
						}
					}
				}
			} else {
				i++;
			}
		}
		return new String(array);
	}
	
	public String pushDominoes(String dominoes) {
		StringBuffer buffer = new StringBuffer();
		char[] array = dominoes.toCharArray();
		for (int i = 0; i < array.length; ) {
			if (array[i] == 'L') {
				buffer.append('L');
				i++;
			} else if (array[i] == '.') {
				int k = 0;
				while (i < array.length && array[i] == '.') {
					k++;
					i++;
				}
				if (i == array.length || array[i] == 'R') {
					while (k > 0) {
						buffer.append('.');
						k--;
					}
				} else {
					while (k > 0) {
						buffer.append('L');
						k--;
					}
				}
			} else {
				while (i < array.length && array[i] == 'R') {
					buffer.append('R');
					i++;
				}
				int k = 0;
				while (i < array.length && array[i] == '.') {
					k++;
					i++;
				}
				if (i < array.length && array[i] == 'L') {
					int l = 0;
					while (l < k / 2) {
						buffer.append('R');
						l++;
					}
					if ((k & 1) == 1) {
						buffer.append('.');
					}
					l = 0;
					while (l < k / 2) {
						buffer.append('L');
						l++;
					}
				} else {
					while (k > 0) {
						buffer.append('R');
						k--;
					}
				}
			}
		}
		return buffer.toString();
	}
	
	double minAreaFreeRect = Double.MAX_VALUE;
	
	public double minAreaFreeRect(int[][] points) {
		HashSet<Integer> p = new HashSet<>();
		for (int[] point : points) {
			p.add(point[0] * 50000 + point[1]);
		}
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				minAreaFreeRectHelper(i, j, points, p);
			}
		}
		if (minAreaFreeRect == Double.MAX_VALUE) {
			return 0;
		} else {
			return minAreaFreeRect;
		}
	}
	
	private void minAreaFreeRectHelper(int i, int j, int[][] points, HashSet<Integer> p) {
		int ix = points[i][0];
		int jx = points[j][0];
		int x = ix + jx;
		int iy = points[i][1];
		int jy = points[j][1];
		int y = iy + jy;
		int d = (ix - jx) * (ix - jx) + (iy - jy) * (iy - jy);
		for (int k = i + 1; k < points.length; k++) {
			if (k != j) {
				int kx = points[k][0];
				int ky = points[k][1];
				int v = (x - kx) * 50000 + y - ky;
				if (p.contains(v)) {
					int l1 = (ix - kx) * (ix - kx) + (iy - ky) * (iy - ky);
					int l2 = (jx - kx) * (jx - kx) + (jy - ky) * (jy - ky);
					if (l1 + l2 == d) {
						minAreaFreeRect = Math.min(minAreaFreeRect, Math.sqrt(l1 * l2));
					}
				}
			}
		}
	}
	
	
	int minDistance = Integer.MAX_VALUE;
	
	public int minDistance(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0) {
			return word1.length() + word2.length();
		}
		int[][] ints = new int[word1.length() + 1][word2.length() + 1];
		char[] a = word1.toCharArray();
		char[] b = word2.toCharArray();
		minDistanceHelper(a, b, ints, 0, 0);
		return minDistance;
	}
	
	private void minDistanceHelper(char[] a, char[] b, int[][] ints, int i, int j) {
		while (i < a.length && j < b.length && a[i] == b[j]) {
			ints[i + 1][j + 1] = ints[i][j];
			i++;
			j++;
		}
		if (i < a.length && j < b.length) {
			ints[i + 1][j] = ints[i][j] + 1;
			minDistanceHelper(a, b, ints, i + 1, j);
			ints[i][j + 1] = ints[i][j] + 1;
			minDistanceHelper(a, b, ints, i, j + 1);
		} else if (i == a.length) {
			minDistance = Math.min(minDistance, ints[i][j] + b.length - j);
		} else {
			minDistance = Math.min(minDistance, ints[i][j] + a.length - i);
		}
	}
	
	
	public int minDistance1(String word1, String word2) {
		int min = Integer.MAX_VALUE;
		int[][] ints = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < word2.length(); i++) {
			ints[0][i + 1] = i + 1;
		}
		for (int i = 0; i < word1.length(); i++) {
			ints[i + 1][0] = i + 1;
			for (int j = 0; j < word2.length(); j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					ints[i + 1][j + 1] = ints[i][j];
				} else {
					ints[i + 1][j + 1] = Math.min(ints[i + 1][j], ints[i][j + 1]) + 1;
				}
			}
			min = Math.min(min, ints[i + 1][word2.length()] + word1.length() - i - 1);
		}
		return min;
	}
	
	public int minDistance2(String word1, String word2) {
		int l = word1.length();
		int r = word2.length();
		int[][] ints = new int[l + 1][r + 1];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					ints[i + 1][j + 1] = ints[i][j] + 1;
				} else {
					ints[i + 1][j + 1] = Math.max(ints[i][j + 1], ints[i + 1][j]);
				}
			}
		}
		return l + r - 2 * ints[l][r];
	}
	
	public int shortestBridge(int[][] A) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][j] == 1) {
					shortestBridgeHelper(a, i, j, A);
					break;
				}
			}
		}
		return shortestBridgeHelper1(a, A, 0);
	}
	
	private int shortestBridgeHelper1(ArrayList<Integer> list, int[][] a, int d) {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = 0; i < list.size(); i += 2) {
			int x = list.get(i), y = list.get(i + 1);
			if (x > 0) {
				if (a[x - 1][y] == 0) {
					a[x - 1][y] = 2;
					l.add(x - 1);
					l.add(y);
				} else if (a[x - 1][y] == 1) {
					return d;
				}
			}
			if (x < a.length - 1) {
				if (a[x + 1][y] == 0) {
					a[x + 1][y] = 2;
					l.add(x + 1);
					l.add(y);
				} else if (a[x + 1][y] == 1) {
					return d;
				}
			}
			if (y > 0) {
				if (a[x][y - 1] == 0) {
					a[x][y - 1] = 2;
					l.add(x);
					l.add(y - 1);
				} else if (a[x][y - 1] == 1) {
					return d;
				}
			}
			if (y < a[0].length - 1) {
				if (a[x][y + 1] == 0) {
					a[x][y + 1] = 2;
					l.add(x);
					l.add(y + 1);
				} else if (a[x][y + 1] == 1) {
					return d;
				}
			}
		}
		return shortestBridgeHelper1(l, a, d + 1);
	}
	
	private void shortestBridgeHelper(ArrayList<Integer> list, int i, int j, int[][] A) {
		A[i][j] = 2;
		int count = 0;
		if (i > 0 && A[i - 1][j] == 1) {
			shortestBridgeHelper(list, i - 1, j, A);
			count++;
		}
		if (j > 0 && A[i][j - 1] == 1) {
			shortestBridgeHelper(list, i, j - 1, A);
			count++;
		}
		if (i < A.length - 1 && A[i + 1][j] == 1) {
			shortestBridgeHelper(list, i + 1, j, A);
			count++;
		}
		if (j < A[0].length - 1 && A[i][j + 1] == 1) {
			shortestBridgeHelper(list, i, j + 1, A);
			count++;
		}
		if (count < 4) {
			list.add(i);
			list.add(j);
		}
	}
	
	public int expressiveWords(String S, String[] words) {
		int ret = 0;
		if (S.length() < 3) {
			for (String word : words) {
				if (S.equals(word)) {
					ret++;
				}
			}
			return ret;
		}
		for (String word : words) {
			if (S.length() >= word.length() && expressiveWordsHelper(S, word)) {
				ret++;
			}
		}
		return ret;
	}
	
	private boolean expressiveWordsHelper(String s, String w) {
		char[] a = s.toCharArray();
		char[] b = w.toCharArray();
		if (a[0] != b[0]) {
			return false;
		}
		int i = 1, j = 1;
		boolean flag = false;
		while (i < a.length) {
			if (j < b.length && a[i] == b[j]) {
				if (flag) {
					if (i > 2 && a[i - 1] == a[i - 2] && a[i - 1] == a[i - 3]) {
						flag = false;
					} else {
						return false;
					}
				}
				i++;
				j++;
			} else if (a[i] == b[j - 1]) {
				flag = true;
				i++;
			} else {
				return false;
			}
		}
		if (i < a.length || j < b.length) {
			return false;
		}
		if (!flag) {
			return true;
		}
		if (flag && a[i - 1] == a[i - 2] && a[i - 1] == a[i - 3]) {
			return true;
		}
		return false;
	}
}
