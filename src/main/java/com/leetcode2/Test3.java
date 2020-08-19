package com.leetcode2;

import static com.base.Constant.ds_8;

import com.leetcode2.Test1.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import sun.security.util.Length;

/**
 * @Author: WuMing
 * @CreateDate: 2020/4/28 9:03
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test3 {
	
	public class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	class Node {
		
		public int val;
		public Node left;
		public Node right;
		
		public Node() {
		}
		
		public Node(int _val) {
			val = _val;
		}
		
		public Node(int _val, Node _left, Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	}
	
	class TireNode {
		
		boolean flag = false;
		TireNode[] child = new TireNode[26];
	}
	//面试题 08.07. 无重复字符串的排列组合
	
	public static void main(String[] args) {
		
		Test3 test = new Test3();
		test.cuttingRope1(2);
	}
	
	//剑指 Offer 14- II. 剪绳子 II
	public int cuttingRope1(int n) {
		 	if(n<4){
		 		return n-1;
			}
		 	int t=n%3,mod=100000007,ret=1,x=n/3;
		 	if(t==1){
		 		ret*=4;x--;
			}else if(t==2){
		 		ret*=2;
			}
			 for (int i = 0; i < x; i++) {
				 ret*=3;
				 ret%=mod;
			 }
			 return ret;
		  
	}
	//112. 路径总和
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root.left == null && root.right == null) {
			return root.val == sum;
		}
		return (root.left != null && hasPathSum(root.left, sum - root.val)) ||
			(root.right != null && hasPathSum(root.right, sum - root.val));
	}
	
	//剑指 Offer 16. 数值的整数次方
	public double myPow(double x, int n) {
		return n >= 0 ? myPowHelper(x, n) : 1 / myPowHelper(x, -n);
	}
	
	private double myPowHelper(double x, int n) {
		return n == 0 ? 1
			: ((n & 1) == 1 ? x * myPowHelper(x * x, n >> 1) : myPowHelper(x * x, n >> 1));
	}
	
	//5455. 最多 K 次交换相邻数位后得到的最小整数
	public String minInteger(String num, int k) {
		int[][] mem = new int[10][2];
		for (char i = '0'; i <= '9'; i++) {
			mem[i - '0'][0] = num.indexOf(i);
			mem[i - '0'][1] = mem[i - '0'][0];
		}
		int len = num.length();
		boolean[] used = new boolean[len];
		StringBuffer buffer = new StringBuffer();
		int l = 0;
		int c, i, j;
		while (l < len) {
			while (l < len && used[l]) {
				l++;
			}
			if (l == len) {
				break;
			}
			c = num.charAt(l) - '0';
			for (i = 0; i < c; i++) {
				if (mem[i][0] > 0 && mem[i][1] <= k) {
					k -= mem[i][1];
					break;
				}
			}
			used[mem[i][0]] = true;
			buffer.append(num.charAt(mem[i][0]));
			for (j = 0; j <= 9; j++) {
				if (mem[j][0] >= mem[i][0]) {
					mem[j][1]--;
				}
			}
			j = mem[i][0];
			mem[i][0] = -1;
			for (; j < len; j++) {
				if (!used[j]) {
					mem[i][1]++;
					if (i == num.charAt(j) - '0') {
						mem[i][0] = j;
						break;
					}
				}
			}
		}
		return buffer.toString();
	}
	
	//5454. 统计全 1 子矩形
	public int numSubmat(int[][] mat) {
		int high = mat.length;
		int len = mat[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = len - 2; j >= 0; j--) {
				if (mat[i][j] > 0) {
					mat[i][j] += mat[i][j + 1];
				}
			}
		}
		int ret = 0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (mat[i][j] > 0) {
					ret += numSubmatHelper(mat, i, j);
				}
			}
		}
		return ret;
	}
	
	private int numSubmatHelper(int[][] mat, int x, int y) {
		int ret = 0;
		int max = Integer.MAX_VALUE;
		int high = mat.length;
		for (int i = x; i < high && max > 0; i++) {
			max = Math.min(max, mat[i][y]);
			ret += max;
		}
		return ret;
	}
	
	//63. 不同路径 II
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int high = obstacleGrid.length;
		int len = obstacleGrid[0].length;
		int[] mem = new int[len];
		for (int i = 0; i < len; i++) {
			if (obstacleGrid[0][i] == 0) {
				mem[i] = 1;
			} else {
				break;
			}
		}
		for (int i = 1; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (obstacleGrid[i][j] == 1) {
					mem[j] = 0;
				} else {
					mem[j] += (j > 0 ? mem[j - 1] : 0);
				}
			}
		}
		return mem[len - 1];
	}
	
	//5453. 所有蚂蚁掉下来前的最后一刻
	public int getLastMoment(int n, int[] left, int[] right) {
		int ret = 0;
		for (int i : left) {
			ret = Math.max(ret, i);
		}
		for (int i : right) {
			ret = Math.max(ret, n - i);
		}
		return ret;
	}
	
	//5452. 判断能否形成等差数列
	public boolean canMakeArithmeticProgression(int[] arr) {
		int len = arr.length;
		if (len <= 2) {
			return true;
		}
		Arrays.sort(arr);
		int x = arr[0] - arr[1];
		for (int i = 1; i < len; i++) {
			if (x != arr[i - 1] - arr[i]) {
				return false;
			}
		}
		return true;
	}
	
	//1386. 安排电影院座位
	public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
		Arrays.sort(reservedSeats, (a, b) -> (a[0] - b[0]));
		int ret = 2 * n;
		int len = reservedSeats.length;
		boolean[] flag = new boolean[11];
		int x;
		for (int i = 0; i < len; i++) {
			x = reservedSeats[i][0];
			flag[reservedSeats[i][1]] = true;
			while (reservedSeats[i + 1][0] == x) {
				i++;
				flag[reservedSeats[i][1]] = true;
			}
			ret -= 2;
			if (!(flag[2] || flag[3] || flag[4] || flag[5] || flag[6] || flag[7] || flag[8]
				|| flag[9])) {
				ret += 2;
			} else if (!(flag[2] || flag[3] || flag[4] || flag[5])) {
				ret += 1;
			} else if (!(flag[4] || flag[5] || flag[6] || flag[7])) {
				ret += 1;
			} else if (!(flag[6] || flag[7] || flag[8] || flag[9])) {
				ret += 1;
			}
			Arrays.fill(flag, false);
		}
		return ret;
	}
	
	//面试题 17.05.  字母与数字
	public String[] findLongestSubarray(String[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		int len = array.length;
		char c;
		int a = 0, b = 0, max = 0, start = 0;
		Integer before;
		for (int i = 0; i < len; i++) {
			c = array[i].charAt(0);
			if (c >= '0' && c <= '9') {
				a++;
			} else {
				b++;
			}
			before = map.get(a - b);
			if (before == null) {
				map.put(a - b, i + 1);
			} else if (i - before > max) {
				start = before;
				max = i - before;
			}
		}
		return Arrays.copyOfRange(array, start, start + max + 1);
	}
	
	//面试题 08.14. 布尔运算
	public int countEval(String s, int result) {
		int len = s.length();
		int length = (len + 1) / 2;
		int[][][] mem = new int[length][length][2];
		countEvalHelper(mem, s, 0, length - 1);
		return mem[0][length - 1][result];
	}
	
	private int[] countEvalHelper(int[][][] mem, String s, int l, int r) {
		if (mem[l][r][0] > 0 || mem[l][r][1] > 0) {
			return mem[l][r];
		} else if (l == r) {
			mem[l][r][s.charAt(2 * l) - '0']++;
			return mem[l][r];
		}
		int[] ret = new int[2], a, b;
		char op;
		for (int i = l; i < r; i++) {
			op = s.charAt(i * 2 + 1);
			a = countEvalHelper(mem, s, l, i);
			b = countEvalHelper(mem, s, i + 1, r);
			switch (op) {
				case '&':
					ret[0] += a[0] * b[1] + a[0] * b[0] + a[1] * b[0];
					ret[1] += a[1] * b[1];
					break;
				case '|':
					ret[1] += a[1] * b[1] + a[1] * b[0] + a[0] * b[1];
					ret[0] += a[0] * b[0];
					break;
				case '^':
					ret[1] += a[0] * b[1] + a[1] * b[0];
					ret[0] += a[1] * b[1] + a[0] * b[0];
					break;
			}
		}
		mem[l][r] = ret;
		return ret;
	}
	
	//存在问题需要修改
	public int[] smallestK(int[] arr, int k) {
		smallestKHelper(arr, 0, arr.length - 1, k - 1);
		
		int[] a = Arrays.copyOfRange(arr, 0, k);
		Arrays.sort(arr);
		int[] b = Arrays.copyOfRange(arr, 0, k);
		Arrays.sort(a);
		return a;
	}
	
	private void smallestKHelper(int[] arr, int l, int r, int k) {
		int x = arr[l], i = l, a = l, b = r;
		l++;
		while (l < r) {
			while (l < r && arr[r] > x) {
				r--;
			}
			if (l < r) {
				arr[i] = arr[r];
				i = r;
			}
			while (l < r && arr[l] <= x) {
				l++;
			}
			if (l < r) {
				arr[i] = arr[l];
				i = l;
			}
		}
		arr[i] = x;
		if (i == k) {
			return;
		} else if (i < k) {
			smallestKHelper(arr, i + 1, b, k);
		} else {
			smallestKHelper(arr, a, i - 1, k);
		}
	}
	
	
	public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		int size = A.size();
		hanotaHelper(size, A, B, C);
	}
	
	private void hanotaHelper(int size, List<Integer> a, List<Integer> b, List<Integer> c) {
		if (size == 1) {
			int len = a.size();
			c.add(a.remove(len - 1));
			return;
		} else {
			hanotaHelper(size - 1, a, c, b);
			c.add(a.remove(a.size() - 1));
			hanotaHelper(size - 1, b, a, c);
		}
	}
	
	//面试题 08.06. 汉诺塔问题
	public void hanota1(List<Integer> A, List<Integer> B, List<Integer> C) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		map.put(1, A);
		map.put(2, B);
		map.put(3, C);
		int x = A.size();
		hanota1Helper(x, 1, 2, 3, map);
	}
	
	private void hanota1Helper(int x, int a, int b, int c, HashMap<Integer, List<Integer>> map) {
		if (x == 1) {
			List<Integer> A = map.get(a);
			int size = A.size();
			Integer t = A.remove(size - 1);
			map.get(c).add(t);
		} else {
			hanota1Helper(x - 1, a, c, b, map);
			int size = map.get(a).size();
			Integer t = map.get(a).remove(size - 1);
			map.get(c).add(t);
			hanota1Helper(x - 1, b, a, c, map);
		}
	}
	
	public int convertInteger(int A, int B) {
		A ^= B;
		B = 0;
		while (A != 0) {
			A &= (A - 1);
			B++;
		}
		return B;
	}
	
	//
	public List<List<Integer>> BSTSequences(TreeNode root) {
		int count = countNode(root);
		TreeNode[] arr = new TreeNode[count];
		arr[0] = root;
		HashSet<Integer> set = new HashSet<>();
		set.add(root.val);
		List<List<Integer>> ret = new ArrayList<>();
		LinkedList<Integer> cur = new LinkedList<>();
		cur.add(root.val);
		BSTSequencesHelper(ret, cur, set, arr, 1, count);
		return ret;
	}
	
	private void BSTSequencesHelper(List<List<Integer>> ret, LinkedList<Integer> cur,
		Set<Integer> set,
		TreeNode[] arr, int idx, int count) {
		if (idx == count) {
			ret.add(new ArrayList<>(cur));
			return;
		}
		for (int i = 0; i < idx; i++) {
			TreeNode x = arr[i];
			if (x.left != null && !set.contains(x.left.val)) {
				arr[idx] = x.left;
				cur.add(x.left.val);
				set.add(x.left.val);
				BSTSequencesHelper(ret, cur, set, arr, idx + 1, count);
				cur.removeLast();
				set.remove(x.left.val);
			}
			if (x.right != null && !set.contains(x.right.val)) {
				arr[idx] = x.right;
				cur.add(x.right.val);
				set.add(x.right.val);
				BSTSequencesHelper(ret, cur, set, arr, idx + 1, count);
				cur.removeLast();
				set.remove(x.right.val);
			}
		}
	}
	
	private int countNode(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + countNode(root.left) + countNode(root.right);
	}
	
	
	//面试题55 - II. 平衡二叉树
	boolean isBalanced = true;
	
	public boolean isBalanced(TreeNode root) {
		isBalancedHelper(root);
		return isBalanced;
		
	}
	
	private int isBalancedHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (!isBalanced) {
			return 0;
		}
		int l = 1 + isBalancedHelper(root.left);
		int r = 1 + isBalancedHelper(root.right);
		if (Math.abs(l - r) < 2) {
			return Math.max(l, r);
		} else {
			isBalanced = false;
			return 0;
		}
	}
	
	//面试题25. 合并两个排序的链表
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		if (l1 != null) {
			cur.next = l1;
		} else {
			cur.next = l2;
		}
		return head.next;
	}
	
	public int sumNums(int n) {
		boolean x = n > 0 && (n += sumNums(n - 1)) > 0;
		return n;
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val > q.val) {
			return lowestCommonAncestor(root, q, p);
		}
		if (root.val >= p.val && root.val <= q.val) {
			return root;
		} else if (root.val > q.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else {
			return lowestCommonAncestor(root.right, p, q);
		}
	}
	
	//
	public int singleNumber(int[] nums) {
		int[] count = new int[32];
		int c = 0, i;
		for (int num : nums) {
			if (num < 0) {
				c++;
				num *= -1;
			}
			i = 0;
			while (num > 0) {
				count[i] += num & 1;
				i++;
				num >>= 1;
			}
		}
		int ret = 0, b = 1;
		for (int x : count) {
			ret += (x % 3) * b;
			b <<= 1;
		}
		return c % 3 == 0 ? ret : -ret;
	}
	
	//面试题60. n个骰子的点数
	public double[] twoSum(int n) {
		double[] ret = new double[n * 6 + 1];
		twoSumHelper(n, ret, 0, 1);
		return Arrays.copyOfRange(ret, n, ret.length);
	}
	
	private void twoSumHelper(int n, double[] ret, int s, double r) {
		if (n == 0) {
			ret[s] += r;
			return;
		}
		for (int i = 1; i < 7; i++) {
			twoSumHelper(n - 1, ret, s + i, r / 6);
		}
	}
	
	//面试题59 - I. 滑动窗口的最大值
	public int[] maxSlidingWindow(int[] nums, int k) {
		LinkedList<Integer> list = new LinkedList<>();
		int len = nums.length, i;
		int[] ret = new int[len - k + 1];
		for (i = 0; i < k; i++) {
			while (!list.isEmpty() && nums[i] > nums[list.getLast()]) {
				list.removeLast();
			}
			list.add(i);
		}
		for (; i < len; i++) {
			ret[i - k] = nums[list.getFirst()];
			if (list.getFirst() <= i - k) {
				list.removeFirst();
			}
			while (!list.isEmpty() && nums[i] > nums[list.getLast()]) {
				list.removeLast();
			}
			list.add(i);
		}
		ret[i - k] = nums[list.getFirst()];
		return ret;
	}
	
	//面试题58 - II. 左旋转字符串
	public String reverseLeftWords(String s, int n) {
		String a = s.substring(0, n);
		String b = s.substring(n);
		return b + a;
	}
	
	//面试题55 - I. 二叉树的深度
	int maxDepth;
	
	public int maxDepth1(TreeNode root) {
		maxDepthHelper(root, 0);
		return maxDepth;
	}
	
	private void maxDepthHelper(TreeNode root, int i) {
		if (root == null) {
			maxDepth = Math.max(maxDepth, i);
		}
		i++;
		maxDepthHelper(root.left, i);
		maxDepthHelper(root.right, i);
	}
	
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
	
	//面试题54. 二叉搜索树的第k大节点
	int kthLargest;
	
	public int kthLargest(Codec.TreeNode root, int k) {
		int[] x = {k};
		kthLargestHelper(root, x);
		return kthLargest;
	}
	
	private void kthLargestHelper(Codec.TreeNode root, int[] x) {
		if (root == null) {
			return;
		}
		kthLargestHelper(root.right, x);
		x[0]--;
		if (x[0] == 0) {
			kthLargest = root.val;
		}
		kthLargestHelper(root.left, x);
		
	}
	
	//面试题 16.19. 水域大小
	public int[] pondSizes(int[][] land) {
		int high = land.length;
		int len = land[0].length;
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (land[i][j] == 0) {
					ret.add(pondSizesHelper(land, i, j));
				}
			}
		}
		int size = ret.size();
		int[] x = new int[size];
		for (int i = 0; i < size; i++) {
			x[i] = ret.get(i);
		}
		Arrays.sort(x);
		return x;
	}
	
	private Integer pondSizesHelper(int[][] land, int i, int j) {
		int ret = 1;
		land[i][j] = 1;
		int ni, nj;
		int high = land.length;
		int len = land[0].length;
		for (int[] d : ds_8) {
			ni = i + d[0];
			nj = j + d[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len || land[ni][nj] != 0) {
				continue;
			}
			ret += pondSizesHelper(land, ni, nj);
		}
		return ret;
	}
	
	//面试题 10.01. 合并排序的数组
	public void merge(int[] A, int m, int[] B, int n) {
		int idx = m + n - 1;
		m--;
		n--;
		while (m >= 0 && n >= 0) {
			if (A[m] < B[n]) {
				A[idx] = B[n];
				n--;
			} else {
				A[idx] = A[m];
			}
			idx--;
		}
		while (n >= 0) {
			A[idx--] = B[n--];
		}
	}
	
	//面试题 16.16. 部分排序
	public int[] subSort(int[] array) {
		int len = array.length;
		if (len < 2) {
			return new int[]{-1, -1};
		}
		int i = 1;
		for (; i < len; i++) {
			if (array[i - 1] > array[i]) {
				break;
			}
		}
		if (i == len) {
			return new int[]{-1, -1};
		}
		int l = i - 1, min = array[i];
		for (; i < len; i++) {
			min = Math.min(array[i], min);
		}
		while (l >= 0 && array[l] > min) {
			l--;
		}
		i = len - 2;
		for (; i >= 0; i--) {
			if (array[i] > array[i + 1]) {
				break;
			}
		}
		int max = array[i], r = i + 1;
		for (; i >= 0; i--) {
			max = Math.max(max, array[i]);
		}
		while (r < len && max > array[r]) {
			r++;
		}
		return new int[]{l + 1, r - 1};
	}
	
	//面试题 02.03. 删除中间节点
	public void deleteNode(ListNode node) {
		ListNode cur = node;
		node = node.next;
		while (node.next != null) {
			cur.val = node.val;
			cur = node;
			node = node.next;
		}
		cur.val = node.val;
		cur.next = null;
	}
	
	//1452. 收藏清单
	public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
		int t = 0;
		for (List<String> favoriteCompany : favoriteCompanies) {
			favoriteCompany.add(t + "");
			t++;
		}
		favoriteCompanies.sort((a, b) -> (b.size() - a.size()));
		int size = favoriteCompanies.size();
		boolean[] flag = new boolean[size];
		for (int i = 0; i < size; i++) {
			if (flag[i]) {
				continue;
			}
			for (int j = i + 1; j < size; j++) {
				if (flag[j]) {
					continue;
				}
				if (peopleIndexesHelper(favoriteCompanies, i, j)) {
					flag[j] = true;
				}
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (!flag[i]) {
				List<String> list = favoriteCompanies.get(i);
				String s = list.get(list.size() - 1);
				ret.add(Integer.parseInt(s));
			}
		}
		ret.sort((a, b) -> (a - b));
		return ret;
	}
	
	private boolean peopleIndexesHelper(List<List<String>> favoriteCompanies, int i, int j) {
		List<String> list = favoriteCompanies.get(j);
		List<String> l = favoriteCompanies.get(i);
		int size = list.size();
		for (int k = 0; k < size; k++) {
			if (!l.contains(list.get(k))) {
				return false;
			}
		}
		return true;
	}
	
	
	//1450. 在既定时间做作业的学生人数
	public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
		int len = startTime.length, ret = 0;
		for (int i = 0; i < len; i++) {
			if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
				ret++;
			}
		}
		return ret;
	}
	
	//1449. 数位成本和为目标值的最大数字
	public String largestNumber(int[] cost, int target) {
		int[][][] mem = new int[9][target + 1][];
		for (int[][] ints : mem) {
			Arrays.fill(ints, new int[]{-2, 0});
		}
		int[] x = largestNumberHelper(cost, 8, target, mem);
		if (x[1] <= 0) {
			return "0";
		}
		StringBuffer buffer = new StringBuffer();
		largestNumberHelper2(buffer, mem, x, 8);
		return buffer.reverse().toString();
	}
	
	private void largestNumberHelper2(StringBuffer buffer, int[][][] mem, int[] x, int c) {
		int len = x[1];
		if (x[0] > 0) {
			x = mem[c - 1][x[0]];
			largestNumberHelper2(buffer, mem, x, c - 1);
		}
		char t = (char) ('1' + c);
		while (buffer.length() < len) {
			buffer.append(t);
		}
	}
	
	private int[] largestNumberHelper(int[] cost, int i, int target, int[][][] mem) {
		if (mem[i][target][0] > -2) {
			return mem[i][target];
		} else if (i == 0) {
			if (target % cost[i] == 0) {
				mem[i][target] = new int[]{-1, target / cost[i]};
			} else {
				mem[i][target] = new int[]{-1, Integer.MIN_VALUE};
			}
			return mem[i][target];
		}
		int j = 0, t = 0, after = -1, len = Integer.MIN_VALUE;
		int[] next;
		for (; j <= target; j += cost[i], t++) {
			next = largestNumberHelper(cost, i - 1, target - j, mem);
			if (t + next[1] >= len) {
				after = target - j;
				len = t + next[1];
			}
		}
		if (target % cost[i] == 0) {
			if (target % cost[i] >= len) {
				after = 0;
				len = target % cost[i];
			}
		}
		mem[i][target] = new int[]{after, len};
		return mem[i][target];
	}
	
	//1449. 数位成本和为目标值的最大数字---超時
	public String largestNumber1(int[] cost, int target) {
		String[][] mem = new String[9][target + 1];
		StringBuffer buffer = new StringBuffer();
		Arrays.fill(mem[0], "");
		int last = cost[0];
		while (last <= target) {
			buffer.append(1);
			mem[0][last] = buffer.toString();
			last += cost[0];
		}
		String s = largestNumberHelper1(cost, 8, target, mem);
		System.out.println(s);
		return s.length() > 0 ? s : "0";
	}
	
	private String largestNumberHelper1(int[] cost, int i, int target, String[][] mem) {
		if (mem[i][target] != null) {
			return mem[i][target];
		}
		String ret = "", cur;
		StringBuffer buffer = new StringBuffer();
		int j = 0;
		for (; j < target; j += cost[i]) {
			String s = largestNumberHelper1(cost, i - 1, target - j, mem);
			if (s.length() > 0) {
				cur = buffer.toString() + s;
				if (ret.length() <= cur.length()) {
					ret = cur;
				}
			}
			buffer.append(i + 1);
			
		}
		if (target % cost[i] == 0) {
			cur = buffer.toString();
			if (ret.length() <= cur.length()) {
				ret = cur;
			}
		}
		mem[i][target] = ret;
		return ret;
	}
	
	//1446. 连续字符
	public int maxPower(String s) {
		int ret = 0, count = 0;
		char cur = '1';
		for (char c : s.toCharArray()) {
			if (c == cur) {
				count++;
			} else {
				ret = Math.max(count, ret);
				cur = c;
				count = 1;
			}
		}
		return ret;
	}
	
	// 统计二叉树中好节点的数目
	public int goodNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		goodNodesHelper(root, Integer.MIN_VALUE);
		return goodNodes;
	}
	
	int goodNodes = 0;
	
	private void goodNodesHelper(TreeNode root, int val) {
		if (root == null) {
			return;
		}
		if (root.val >= val) {
			goodNodes++;
			val = root.val;
		}
		
		goodNodesHelper(root.left, val);
		goodNodesHelper(root.right, val);
	}
	
	//5397. 最简分数
	public List<String> simplifiedFractions(int n) {
		ArrayList<String> ret = new ArrayList<>();
		//ret.add("1/"+n);
		for (int j = n; j > 1; j--) {
			for (int i = 1; i < j; i++) {
				if (gcb(i, j) == 1) {
					ret.add(i + "/" + j);
				}
			}
		}
		return ret;
	}
	
	//面试题38. 字符串的排列
	//面试题 08.07. 无重复字符串的排列组合
	//面试题 08.08. 有重复字符串的排列组合
	public String[] permutation(String s) {
		int[] mem = new int[26];
		HashSet<String> set = new HashSet<>();
		char[] array = s.toCharArray();
		for (char c : array) {
			mem[c - 'a']++;
		}
		permutationHelper(mem, set, array, 0);
		String[] ret = set.toArray(new String[]{});
		return ret;
	}
	
	private void permutationHelper(int[] mem, HashSet<String> set,
		char[] array, int i) {
		if (i == array.length) {
			set.add(new String(array));
			return;
		}
		for (int j = 0; j < 26; j++) {
			if (mem[j] > 0) {
				array[i] = (char) (j + 'a');
				mem[j]--;
				permutationHelper(mem, set, array, i + 1);
				mem[j]++;
			}
		}
	}
	
	//面试题33. 二叉搜索树的后序遍历序列
	public boolean verifyPostorder(int[] postorder) {
		return verifyPostorderHelper(postorder, 0, postorder.length - 1, Integer.MIN_VALUE,
			Integer.MAX_VALUE);
	}
	
	private boolean verifyPostorderHelper(int[] postorder, int l, int r, int min, int max) {
		if (l >= r) {
			return postorder[l] >= min && postorder[r] <= max;
		}
		int mid = postorder[r];
		if (mid < min || mid > max) {
			return false;
		}
		int i = l;
		for (; i < r; i++) {
			if (postorder[i] < min || postorder[i] > max) {
				return false;
			} else if (postorder[i] > mid) {
				break;
			}
		}
		return verifyPostorderHelper(postorder, l, i - 1, min, mid - 1) && verifyPostorderHelper(
			postorder, i, r - 1, mid + 1, max);
	}
	
	//面试题36. 二叉搜索树与双向链表
	public Node treeToDoublyList(Node root) {
		Node[] x = treeToDoublyListHelper(root);
		x[0].left = x[1];
		x[1].right = x[0];
		return x[0];
	}
	
	private Node[] treeToDoublyListHelper(Node root) {
		Node[] l, r;
		if (root.left == null && root.right == null) {
			return new Node[]{root, root};
		} else if (root.left == null) {
			r = treeToDoublyListHelper(root.right);
			root.right = r[0];
			r[0].left = root;
			return new Node[]{root, r[1]};
		} else if (root.right == null) {
			l = treeToDoublyListHelper(root.left);
			l[1].right = root;
			root.left = l[1];
			return new Node[]{l[1], root};
		}
		l = treeToDoublyListHelper(root.left);
		r = treeToDoublyListHelper(root.right);
		l[1].right = root;
		root.left = l[1];
		root.right = r[0];
		r[0].left = root;
		return new Node[]{l[0], r[1]};
	}
	
	//面试题 10.02. 变位词组
	public List<List<String>> groupAnagrams(String[] strs) {
		int[] x = new int[26];
		HashMap<Integer, ArrayList<String>> map = new HashMap<>();
		for (String str : strs) {
			for (char c : str.toCharArray()) {
				x[c - 'a']++;
			}
			int hashCode = Arrays.hashCode(x);
			
			if (map.containsKey(hashCode)) {
				map.get(hashCode).add(str);
			} else {
				ArrayList list = new ArrayList<String>();
				list.add(str);
				map.put(hashCode, list);
			}
			Arrays.fill(x, 0);
		}
		Collection<ArrayList<String>> values = map.values();
		return new ArrayList<>(values);
	}
	
	//面试题 08.02. 迷路的机器人
	public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
		LinkedList<List<Integer>> lists = pathWithObstaclesHelper(obstacleGrid, 0, 0,
			obstacleGrid.length - 1, obstacleGrid[0].length - 1);
		return lists == null ? new ArrayList<>() : lists;
	}
	
	private LinkedList<List<Integer>> pathWithObstaclesHelper(int[][] obstacleGrid, int x, int y,
		int tx, int ty) {
		if (x > tx || y > ty) {
			return null;
		} else if (obstacleGrid[x][y] == 1) {
			return null;
		}
		if (x == tx && y == ty) {
			List<Integer> list = Arrays.asList(x, y);
			LinkedList<List<Integer>> ret = new LinkedList<>();
			ret.add(list);
			return ret;
		}
		LinkedList<List<Integer>> lx = pathWithObstaclesHelper(obstacleGrid, x + 1, y, tx, ty);
		if (lx != null) {
			List<Integer> list = Arrays.asList(x, y);
			lx.addFirst(list);
			return lx;
		}
		lx = pathWithObstaclesHelper(obstacleGrid, x, y + 1, tx, ty);
		if (lx != null) {
			List<Integer> list = Arrays.asList(x, y);
			lx.addFirst(list);
			return lx;
		} else {
			obstacleGrid[x][y] = 1;
			return null;
		}
	}
	
	//面试题 04.06. 后继者
	boolean find = false;
	TreeNode inorderSuccessor = null;
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		inorderSuccessorHelper(root, p);
		return inorderSuccessor;
	}
	
	private void inorderSuccessorHelper(TreeNode root, TreeNode p) {
		if (root == null) {
			return;
		}
		inorderSuccessorHelper(root.left, p);
		if (!find) {
			find = root == p;
		} else if (inorderSuccessor == null) {
			inorderSuccessor = root;
			return;
		} else {
			return;
		}
		inorderSuccessorHelper(root.right, p);
	}
	
	//面试题 05.02. 二进制数转字符串
	public String printBin(double num) {
		StringBuffer buffer = new StringBuffer("0.");
		while (num != 0) {
			num *= 2;
			if (num >= 1.0) {
				buffer.append(1);
				num -= 1;
			} else {
				buffer.append(0);
			}
			if (buffer.length() > 32) {
				return "ERROR";
			}
		}
		return buffer.toString();
	}
	
	//1371. 每个元音包含偶数次的最长子字符串
	public int findTheLongestSubstring(String s) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = s.length(), l = -1;
		map.put(0, -1);
		int x = 0, ret = 0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if ("aeiou".indexOf(c) < 0) {
				ret = Math.max(i - l, ret);
				continue;
			}
			x ^= c;
			if (map.containsKey(x)) {
				l = map.get(x);
				ret = Math.max(i - l, ret);
			} else {
				map.put(x, i);
				l = i;
			}
		}
		return ret;
	}
	
	//1362. 最接近的因数
	int biggest = 0;
	
	public int[] closestDivisors1(int num) {
		int a = 1, b = 1, c, d;
		num++;
		int x = (int) Math.sqrt(num);
		for (int i = x; i > 1; i--) {
			if (num % i == 0) {
				a = i;
				break;
			}
		}
		c = (num) / a;
		num++;
		x = (int) Math.sqrt(num);
		for (int i = x; i > 1; i--) {
			if (num % i == 0) {
				b = i;
				break;
			}
		}
		d = (num) / b;
		if (c - a > d - b) {
			return new int[]{b, d};
		} else {
			return new int[]{a, c};
		}
		
	}
	
	public int[] closestDivisors(int num) {
		LinkedList<int[]> list = new LinkedList<>();
		biggest = 1;
		findDivisors(num + 1, list);
		getBiggest(list, 1, 0, num + 1);
		int[] a = {biggest, (num + 1) / biggest};
		list.clear();
		findDivisors(num + 2, list);
		biggest = 1;
		getBiggest(list, 1, 0, num + 2);
		int[] b = {biggest, (num + 2) / biggest};
		if (a[1] - a[0] < b[1] - b[0]) {
			return a;
		} else {
			return b;
		}
	}
	
	private void getBiggest(LinkedList<int[]> list, int x, int i, int total) {
		if (x > total / x) {
			return;
		} else if (x > biggest) {
			biggest = x;
		}
		if (i == list.size()) {
			return;
		}
		getBiggest(list, x, i + 1, total);
		int[] cur = list.get(i);
		for (int j = 0; j < cur[1]; j++) {
			x *= cur[0];
			getBiggest(list, x, i + 1, total);
		}
	}
	
	private void findDivisors(int t, LinkedList<int[]> list) {
		for (int i = 2; i * i <= t; i++) {
			if (t % i == 0) {
				int c = 0;
				do {
					t /= i;
					c++;
				} while (t % i == 0);
				list.add(new int[]{i, c});
			}
		}
		if (t != 1) {
			list.add(new int[]{t, 1});
		}
	}
	
	//1224. 最大相等频率
	public int maxEqualFreq(int[] nums) {
		int[] c = new int[100001];
		int[] n = new int[100001];
		int len = nums.length;
		int ret = 0, maxc = 0;
		for (int i = 0; i < len; i++) {
			int num = nums[i];
			c[num]++;
			maxc = Math.max(maxc, c[num]);
			n[c[num]]++;
			n[c[num] - 1]--;
			if (n[1] == 1) {
				if (maxc * (-1 - n[0]) == i) {
					ret = i + 1;
				}
			} else if (n[maxc] == 1) {
				if ((maxc - 1) * (-n[0]) == i) {
					ret = i + 1;
				}
			}
		}
		if (n[1] == len) {
			ret = len;
		}
		return ret;
	}
	
	//1373. 二叉搜索子树的最大键值和
	int maxSumBST = 0;
	
	public int maxSumBST(TreeNode root) {
		int[] x = maxSumBSTHelper(root);
		return maxSumBST;
	}
	
	private int[] maxSumBSTHelper(TreeNode root) {
		if (root == null) {
			return null;
		}
		int[] l = maxSumBSTHelper(root.left);
		int[] r = maxSumBSTHelper(root.right);
		if (l != null && l[1] >= root.val) {
			return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
		} else if (r != null && r[0] <= root.val) {
			return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
		} else {
			int x = root.val;
			int y = root.val;
			int z = root.val;
			if (l != null) {
				x = l[0];
				z += l[2];
			}
			if (r != null) {
				y = r[1];
				z += r[2];
			}
			maxSumBST = Math.max(maxSumBST, z);
			return new int[]{x, y, z};
		}
	}
	
	private int[] maxSumBSTHelper1(TreeNode root) {
		if (root.left == null && root.right == null) {
			return new int[]{root.val, root.val, root.val};
		} else if (root.left == null) {
			int[] x = maxSumBSTHelper(root.right);
			if (x[0] <= root.val) {
				return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
			} else {
				x[0] = root.val;
				x[2] += root.val;
				maxSumBST = Math.max(maxSumBST, x[2]);
				return x;
			}
		} else if (root.right == null) {
			int[] x = maxSumBSTHelper(root.left);
			if (x[1] >= root.val) {
				return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
			} else {
				x[1] = root.val;
				x[2] += root.val;
				maxSumBST = Math.max(maxSumBST, x[2]);
				return x;
			}
		} else {
			int[] l = maxSumBSTHelper(root.left);
			int[] r = maxSumBSTHelper(root.right);
			if (l[1] >= root.val || r[0] <= root.val) {
				return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
			} else {
				maxSumBST = Math.max(maxSumBST, l[2] + r[2] + root.val);
				return new int[]{l[0], r[1], l[2] + r[2] + root.val};
			}
		}
		
	}
	
	//1334. 阈值距离内邻居最少的城市
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		int[][] mem = new int[n][n];
		List<Integer>[] lists = new List[n];
		for (int i = 0; i < n; i++) {
			lists[i] = new ArrayList();
		}
		for (int[] edge : edges) {
			mem[edge[0]][edge[1]] = edge[2];
			mem[edge[1]][edge[0]] = edge[2];
			lists[edge[0]].add(edge[1]);
			lists[edge[1]].add(edge[0]);
		}
		int rc = Integer.MAX_VALUE, ret = 0, cur;
		for (int i = 0; i < n; i++) {
			int[] x = new int[n];
			Arrays.fill(x, Integer.MAX_VALUE);
			x[i] = 0;
			findTheCityHelper(x, mem, lists, i, 0, distanceThreshold);
			cur = 0;
			for (int t : x) {
				if (t <= distanceThreshold) {
					cur++;
				}
			}
			if (cur <= rc) {
				ret = i;
				rc = cur;
			}
		}
		return ret;
	}
	
	private void findTheCityHelper(int[] min, int[][] mem, List<Integer>[] lists,
		int i, int cost, int distanceThreshold) {
		for (Integer next : lists[i]) {
			if (cost + mem[i][next] <= distanceThreshold && cost + mem[i][next] < min[next]) {
				min[next] = cost + mem[i][next];
				findTheCityHelper(min, mem, lists, next, min[next], distanceThreshold);
			}
		}
	}
	
	//1347. 制造字母异位词的最小步骤数
	public int minSteps(String s, String t) {
		int[] mem = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			mem[s.charAt(i) - 'a']++;
			mem[t.charAt(i) - 'a']--;
		}
		int ret = 0;
		for (int i : mem) {
			if (i > 0) {
				ret += i;
			}
		}
		return ret;
	}
	
	//1297. 子串的最大出现次数
	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
		HashMap<String, Integer> map = new HashMap<>();
		char[] arr = s.toCharArray();
		int[] flag = new int[26];
		int l = 0;
		int ret = 0;
		int i = 0, j = 0;
		int len = arr.length;
		for (; i < len; i++) {
			flag[arr[i] - 'a']++;
			if (flag[arr[i] - 'a'] == 1) {
				l++;
			}
			while (l > maxLetters) {
				flag[arr[j] - 'a']--;
				if (flag[arr[j] - 'a'] == 0) {
					l--;
				}
				j++;
			}
			while (true) {
				if (i - j + 1 > maxSize && flag[arr[j] - 'a'] > 1) {
					flag[arr[j] - 'a']--;
					j++;
				} else if (i - j + 1 >= minSize) {
					while (i - j + 1 > minSize && flag[arr[j] - 'a'] > 1) {
						flag[arr[j] - 'a']--;
						j++;
					}
					String key = new String(arr, j, i - j + 1);
					map.put(key, map.getOrDefault(key, 0) + 1);
					ret = Math.max(ret, map.get(key));
					flag[arr[j] - 'a']--;
					if (flag[arr[j] - 'a'] == 0) {
						l--;
					}
					j++;
				} else {
					break;
				}
			}
		}
		return ret;
	}
	
	//1441. 用栈操作构建数组
	public List<String> buildArray(int[] target, int n) {
		ArrayList<String> ret = new ArrayList<>();
		int idx = 0;
		for (int i = 1; idx < target.length && i <= n; i++) {
			ret.add("Push");
			if (i == target[idx]) {
				idx++;
			} else {
				ret.add("Pop");
			}
		}
		return ret;
	}
	
	//1442. 形成两个异或相等数组的三元组数目
	public int countTriplets1(int[] arr) {
		int len = arr.length;
		int ret = 0;
		int l, r;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < len - 1; i++) {
			l = 0;
			r = 0;
			map.clear();
			for (int j = i; j >= 0; j--) {
				l ^= arr[j];
				map.put(l, map.getOrDefault(l, 0) + 1);
			}
			for (int j = i + 1; j < len; j++) {
				r ^= arr[j];
				ret += map.getOrDefault(r, 0);
			}
		}
		return ret;
	}
	
	public int countTriplets(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			arr[i] ^= arr[i - 1];
		}
		HashMap<Integer, Integer> left = new HashMap<>(), right = new HashMap<>();
		int ret = 0, cur;
		for (int i = 0; i < len - 1; i++) {
			left.clear();
			right.clear();
			right.put(0, 1);
			int x = arr[i];
			left.put(x, 1);
			for (int j = 0; j < i; j++) {
				left.put(x ^ arr[j], left.getOrDefault(x ^ arr[j], 0) + 1);
			}
			for (int j = i + 1; j < len; j++) {
				cur = arr[j] ^ arr[i];
				for (Entry<Integer, Integer> entry : left.entrySet()) {
					if (right.containsKey(cur ^ entry.getKey())) {
						ret += entry.getValue();
					}
					right.put(cur, right.getOrDefault(cur, 0) + 1);
				}
			}
		}
		return ret;
	}
	
	//收集树上所有苹果的最少时间
	public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
		int[] parent = new int[n];
		boolean[] visited = new boolean[n];
		visited[0] = true;
		for (int[] edge : edges) {
			parent[edge[1]] = edge[0];
		}
		int ret = 0;
		for (int i = 0; i < n; i++) {
			if (hasApple.get(i)) {
				ret += minTimeHelper(i, visited, parent, 0);
			}
		}
		return ret;
	}
	
	private int minTimeHelper(int i, boolean[] visited, int[] parent, int count) {
		if (visited[i]) {
			return count;
		}
		visited[i] = true;
		return minTimeHelper(parent[i], visited, parent, count + 2);
	}
	
	//面试题 08.04. 幂集
	public List<List<Integer>> subsets(int[] nums) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		subsetsHelper(new ArrayList<Integer>(), ret, 0, nums);
		ret.add(new ArrayList<>());
		return ret;
	}
	
	private void subsetsHelper(ArrayList<Integer> list, ArrayList<List<Integer>> ret, int idx,
		int[] nums) {
		if (idx >= nums.length) {
			return;
		}
		ArrayList<Integer> copy = new ArrayList<>();
		copy.addAll(list);
		subsetsHelper(copy, ret, idx + 1, nums);
		list.add(nums[idx]);
		ret.add(new ArrayList<>(list));
		subsetsHelper(list, ret, idx + 1, nums);
	}
	
	
	//面试题 04.05. 合法二叉搜索树
	public boolean isValidBST(TreeNode root) {
		long min = Integer.MIN_VALUE - 1L;
		long max = Integer.MAX_VALUE + 1L;
		return isValidBSTHelper(root, min, max);
	}
	
	private boolean isValidBSTHelper(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		} else if (root.val > min && root.val < max) {
			return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right,
				root.val, max);
		} else {
			return false;
		}
	}
	
	//面试题 17.23. 最大黑方阵
	public int[] findSquare(int[][] matrix) {
		int high = matrix.length;
		int len = matrix[0].length;
		int[][] l = new int[high][len];
		int[][] u = new int[high][len];
		if (matrix[0][0] == 0) {
			l[0][0] = 1;
			u[0][0] = 1;
		}
		for (int i = 1; i < high; i++) {
			if (matrix[i][0] == 0) {
				l[i][0] = 1;
				u[i][0] = u[i - 1][0] + 1;
			}
		}
		for (int i = 1; i < len; i++) {
			if (matrix[0][i] == 0) {
				l[0][i] = l[0][i - 1] + 1;
				u[0][i] = 1;
			}
		}
		for (int i = 1; i < high; i++) {
			for (int j = 1; j < len; j++) {
				if (matrix[i][j] == 0) {
					l[i][j] = l[i][j - 1] + 1;
					u[i][j] = u[i - 1][j] + 1;
				}
			}
		}
		int[][] r = new int[high][len];
		int[][] d = new int[high][len];
		int[] ret = new int[3];
		int maxl;
		for (int i = high - 1; i >= 0; i--) {
			for (int j = len - 1; j >= 0; j--) {
				if (matrix[i][j] == 0) {
					maxl = Integer.MAX_VALUE;
					r[i][j] = (i + 1 < high ? r[i + 1][j] : 0) + 1;
					maxl = Math.min(maxl, r[i][j]);
					d[i][j] = (j + 1 < len ? d[i][j + 1] : 0) + 1;
					maxl = Math.min(maxl, d[i][j]);
					while (maxl >= ret[2]) {
						if (u[i + maxl - 1][j + maxl - 1] >= maxl
							&& l[i + maxl - 1][j + maxl - 1] >= maxl) {
							ret[0] = i;
							ret[1] = j;
							ret[2] = maxl;
						}
						maxl--;
					}
				}
			}
		}
		return ret[2] == 0 ? new int[0] : ret;
	}
	
	//面试题57 - II. 和为s的连续正数序列
	public int[][] findContinuousSequence(int target) {
		int x = 1, l = 2, r, b;
		ArrayList<int[]> list = new ArrayList<>();
		while (x < target) {
			r = target - x;
			if (r % l == 0) {
				int[] ints = new int[l];
				b = r / l;
				for (int i = 0; i < l; i++) {
					ints[i] = b + i;
				}
				list.add(ints);
			}
			x += l;
			l++;
		}
		int size = list.size();
		int[][] ret = new int[size][];
		for (int i = 0; i < size; i++) {
			ret[size - i - 1] = list.get(i);
		}
		return ret;
	}
	
	//面试题 05.03. 翻转数位
	public int reverseBits1(int num) {
		int l = 0, r = 0, ret = 0;
		while (num > 0) {
			if ((num & 1) == 1) {
				r++;
			} else {
				ret = Math.max(l + r + 1, ret);
				l = r;
				r = 0;
			}
			num >>= 1;
		}
		ret = Math.max(l + r + 1, ret);
		return ret;
	}
	
	public int reverseBits(int num) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		int c = 0;
		while (num > 0) {
			if ((num & 1) == 0) {
				list.add(c);
				c = 0;
			} else {
				c++;
			}
			num >>= 1;
		}
		list.add(c);
		int ret = 0;
		int a = 0, b;
		for (int i = 1; i < list.size(); i++) {
			b = list.get(i);
			ret = Math.max(a + b + 1, ret);
			a = b;
		}
		return ret > 32 ? 32 : ret;
	}
	
	//1436. 旅行终点站
	public String destCity1(List<List<String>> paths) {
		HashSet<String> a = new HashSet<>();
		HashSet<String> b = new HashSet<>();
		for (List<String> path : paths) {
			a.add(path.get(0));
			b.add(path.get(1));
		}
		for (String s : b) {
			if (!a.contains(s)) {
				return s;
			}
		}
		return "";
	}
	
	public String destCity(List<List<String>> paths) {
		HashMap<String, Integer> map = new HashMap<>();
		int size = paths.size();
		int[] mem = new int[size + 1];
		Arrays.fill(mem, -1);
		int x = 0, l, r;
		for (List<String> path : paths) {
			if (!map.containsKey(path.get(0))) {
				map.put(path.get(0), x++);
			}
			if (!map.containsKey(path.get(1))) {
				map.put(path.get(1), x++);
			}
			l = map.get(path.get(0));
			r = map.get(path.get(1));
			mem[l] = r;
		}
		int t = 0;
		for (int i = 0; i <= size; i++) {
			if (mem[i] < 0) {
				t = i;
				break;
			}
		}
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == t) {
				return entry.getKey();
			}
		}
		return "";
	}
	
	//1434. 每个人戴不同帽子的方案数--超时
	public int numberWays(List<List<Integer>> hats) {
		HashMap<Long, Long> mem = new HashMap<>();
		hats.sort(Comparator.comparingInt(List::size));
		int x = (int) numberWaysHelper(hats, 0L, 0, mem);
		return x;
	}
	
	private long numberWaysHelper(List<List<Integer>> hats, long mask, int i, Map<Long, Long> mem) {
		if (hats.size() == i) {
			return 1;
		} else if (mem.containsKey(mask)) {
			return mem.get(mask);
		}
		long ret = 0;
		int mod = 1000000007;
		for (Integer x : hats.get(i)) {
			if ((mask & (1 << x)) == 0) {
				ret += numberWaysHelper(hats, mask | (1 << x), i + 1, mem);
				ret %= mod;
			}
		}
		mem.put(mask, ret);
		return ret;
	}
	
	//1439. 有序矩阵中的第 k 个最小数组和
	public int kthSmallest(int[][] mat, int k) {
		int[] cur = mat[0], next;
		int high = mat.length;
		int len = mat[0].length;
		PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		for (int i = 1; i < high; i++) {
			int l = Math.min(k, cur.length * len);
			next = new int[l];
			queue.clear();
			for (int j = 0; j < cur.length; j++) {
				queue.add(new int[]{mat[i][0] + cur[j], j, 0});
			}
			for (int j = 0; j < l; j++) {
				int[] x = queue.remove();
				next[j] = x[0];
				x[2]++;
				if (x[2] < len) {
					x[0] = mat[i][x[2]] + cur[x[1]];
					queue.add(x);
				}
			}
			cur = next;
		}
		return cur[k - 1];
	}
	
	//1438. 绝对差不超过限制的最长连续子数组
	public int longestSubarray(int[] nums, int limit) {
		LinkedList<Integer> max = new LinkedList<>();
		LinkedList<Integer> min = new LinkedList<>();
		int l = -1, ret = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			while (!max.isEmpty() && nums[max.getLast()] < nums[i]) {
				max.removeLast();
			}
			max.add(i);
			while (!min.isEmpty() && nums[min.getLast()] > nums[i]) {
				min.removeLast();
			}
			min.add(i);
			while (nums[max.getFirst()] - nums[min.getFirst()] > limit) {
				if (max.getFirst() < min.getFirst()) {
					l = max.removeFirst();
				} else {
					l = min.removeFirst();
				}
			}
			ret = Math.max(ret, i - l);
		}
		return ret;
	}
	
	//1437. 是否所有 1 都至少相隔 k 个元素;
	public boolean kLengthApart(int[] nums, int k) {
		int b = -k - 1;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 1) {
				if (i - b - 1 < k) {
					return false;
				}
				b = i;
			}
		}
		return true;
	}
	
	//1433. 检查一个字符串是否可以打破另一个字符串
	public boolean checkIfCanBreak(String s1, String s2) {
		int[] a = new int[26];
		int[] b = new int[26];
		int len = s1.length();
		for (int i = 0; i < len; i++) {
			a[s1.charAt(i) - 'a']++;
			b[s2.charAt(i) - 'a']++;
		}
		int x = 0;
		for (int i = 25; i >= 0; i++) {
			x += a[i];
			x -= b[i];
			if (x < 0) {
				break;
			}
		}
		if (x == 0) {
			return true;
		}
		x = 0;
		for (int i = 25; i >= 0; i++) {
			x -= a[i];
			x += b[i];
			if (x < 0) {
				break;
			}
		}
		return x == 0;
	}
	
	//1432. 改变一个整数能得到的最大差值
	public int maxDiff(int num) {
		int x = 1, t = num;
		while (t > 9) {
			x *= 10;
			t /= 10;
		}
		int nx = x, r = -1;
		t = num;
		while (nx > 0 && t / nx == 9) {
			t %= nx;
			nx /= 10;
		}
		long a = 0, b = 0;
		a = num;
		if (t > 0) {
			r = t / nx;
			a = num;
			while (nx > 0) {
				if (t / nx == r) {
					a += (9 - r) * nx;
				}
				t %= nx;
				nx /= 10;
			}
		}
		t = num;
		nx = x;
		if (num / x != 1) {
			r = num / nx;
			b = num;
			while (nx > 0) {
				if (t / nx == r) {
					b -= (r - 1) * nx;
				}
				t %= nx;
				nx /= 10;
			}
		} else {
			t %= nx;
			nx /= 10;
			b = num;
			while (nx > 0 && t / nx < 2) {
				t %= nx;
				nx /= 10;
			}
			if (t > 0) {
				r = t / nx;
				while (nx > 0) {
					if (t / nx == r) {
						b -= r * nx;
					}
					t %= nx;
					nx /= 10;
				}
			}
		}
		return (int) (a - b);
	}
	
	//1431. 拥有最多糖果的孩子
	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		int max = candies[0];
		for (int candy : candies) {
			max = Math.max(candy, max);
		}
		ArrayList<Boolean> ret = new ArrayList<>();
		for (int candy : candies) {
			ret.add(candy + extraCandies >= max);
		}
		return ret;
	}
	
	//面试题 17.12. BiNode
	TreeNode biNode = new TreeNode(0), cur = biNode;
	
	public TreeNode convertBiNode1(TreeNode root) {
		if (root == null) {
			return null;
		}
		convertBiNode1(root.left);
		cur.right = root;
		cur = cur.right;
		cur.left = null;
		convertBiNode1(root.right);
		return biNode.right;
	}
	
	public TreeNode convertBiNode(TreeNode root) {
		if (root == null) {
			return root;
		}
		if (root.left == null) {
			return root;
		}
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		root.right = convertBiNode(right);
		if (root.left == null) {
			return root;
		}
		TreeNode ret = convertBiNode(left);
		TreeNode next = ret;
		while (next.right != null) {
			next = next.right;
		}
		next.right = root;
		return ret;
	}
	
	//面试题 16.01. 交换数字
	public int[] swapNumbers(int[] numbers) {
		numbers[0] = numbers[0] ^ numbers[1];
		numbers[1] = numbers[0] ^ numbers[1];
		numbers[0] = numbers[0] ^ numbers[1];
		return numbers;
	}
	
	//面试题 17.15. 最长单词
	public String longestWord(String[] words) {
		TireNode root = new TireNode();
		for (String word : words) {
			insertTree(word, root);
		}
		String ret = "";
		for (String word : words) {
			if (word.length() > ret.length() || (word.length() == ret.length()
				&& word.compareTo(ret) < 0)) {
				if (searchTree(word, root, 0, 0)) {
					ret = word;
				}
			}
		}
		return ret;
	}
	
	
	private boolean searchTree(String word, TireNode root, int idx, int c) {
		TireNode cur = root;
		for (int i = idx, x; i < word.length(); i++) {
			x = word.charAt(i) - 'a';
			if (cur.child[x] == null) {
				return false;
			}
			cur = cur.child[x];
			if (cur.flag && searchTree(word, root, i + 1, c + 1)) {
				return true;
			}
		}
		return c > 1;
	}
	
	private void insertTree(String word, TireNode root) {
		for (int i = 0; i < word.length(); i++) {
			int x = word.charAt(i) - 'a';
			if (root.child[x] == null) {
				root.child[x] = new TireNode();
			}
			root = root.child[x];
		}
		root.flag = true;
	}
	
	//面试题06. 从尾到头打印链表
	public int[] reversePrint(ListNode head) {
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			count++;
			cur = cur.next;
		}
		int[] ret = new int[count];
		for (int i = count - 1; i >= 0; i--) {
			ret[i] = head.val;
			head = head.next;
		}
		return ret;
	}
	
	//面试题32 - III. 从上到下打印二叉树 III
	public List<List<Integer>> levelOrder2(TreeNode root) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		if (root == null) {
			return ret;
		}
		ArrayList<TreeNode> list = new ArrayList<>(), next;
		list.add(root);
		LinkedList<Integer> x;
		boolean d = true;
		while (!list.isEmpty()) {
			next = new ArrayList<>();
			x = new LinkedList<>();
			for (TreeNode cur : list) {
				if (d) {
					x.add(cur.val);
				} else {
					x.addFirst(cur.val);
				}
				if (cur.left != null) {
					next.add(cur.left);
				}
				if (cur.right != null) {
					next.add(cur.right);
				}
			}
			ret.add(x);
			d ^= true;
			list = next;
		}
		return ret;
	}
	
	//面试题32 - I. 从上到下打印二叉树
	public int[] levelOrder(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		TreeNode cur;
		ArrayList<Integer> ret = new ArrayList<>();
		while (!list.isEmpty()) {
			cur = list.removeFirst();
			ret.add(cur.val);
			if (cur.left != null) {
				list.add(cur.left);
			}
			if (cur.right != null) {
				list.add(cur.right);
			}
		}
		int size = ret.size();
		int[] x = new int[size];
		int idx = 0;
		for (Integer t : ret) {
			x[idx++] = t;
		}
		return x;
	}
	
	//202. 快乐数
	public boolean isHappy(int n) {
		HashSet<Integer> set = new HashSet<>();
		int t;
		while (n != 1) {
			if (set.contains(n)) {
				return false;
			}
			set.add(n);
			t = 0;
			while (n > 0) {
				t += Math.pow(n % 10, 2);
				n /= 10;
			}
			n = t;
		}
		return true;
	}
	
	//面试题 17.07. 婴儿名字
	public String[] trulyMostPopular(String[] names, String[] synonyms) {
		int len = names.length;
		int[] count = new int[len + synonyms.length * 2];
		int[] follow = new int[len + synonyms.length * 2];
		int idx = 0;
		String[] mem = new String[len + synonyms.length * 2];
		HashMap<String, Integer> map = new HashMap<>();
		for (String name : names) {
			follow[idx] = idx;
			String s = name.substring(0, name.length() - 1);
			String[] split = s.split("\\(");
			map.put(split[0], idx);
			mem[idx] = split[0];
			count[idx] = Integer.parseInt(split[1]);
			idx++;
		}
		for (String synonym : synonyms) {
			String s = synonym.substring(1, synonym.length() - 1);
			String[] split = s.split(",");
			Integer a = map.get(split[0]);
			if (a == null) {
				mem[idx] = split[0];
				follow[idx] = idx;
				map.put(split[0], idx);
				a = idx++;
			}
			Integer b = map.get(split[1]);
			if (b == null) {
				mem[idx] = split[1];
				follow[idx] = idx;
				map.put(split[1], idx);
				b = idx++;
			}
			while (follow[a] != a) {
				a = follow[a];
			}
			while (b != follow[b]) {
				b = follow[b];
			}
			if (a < b) {
				follow[b] = a;
			} else {
				follow[a] = b;
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		for (int i = idx - 1; i >= 0; i--) {
			if (i != follow[i]) {
				count[follow[i]] += count[i];
				if (mem[i].compareTo(mem[follow[i]]) < 0) {
					mem[follow[i]] = mem[i];
				}
			} else {
				String e = mem[i] + "(" + count[i] + ")";
				System.out.println(e);
				ret.add(e);
			}
		}
		return ret.toArray(new String[]{});
	}
	
	//343. 整数拆分
	public int cuttingRope(int n) {
		if (n < 4) {
			return n - 1;
		}
		long[] mem = new long[n + 1];
		mem[1] = 1;
		cuttingRopeHelper(n, mem);
		return (int) mem[n];
	}
	
	private long cuttingRopeHelper(int n, long[] mem) {
		if (mem[n] > 0) {
			return mem[n];
		}
		long ret = n;
		for (int i = 1; i <= n / 2; i++) {
			ret = Math.max(ret, i * cuttingRopeHelper(n - i, mem));
		}
		mem[n] = ret;
		return ret;
	}
	
	//面试题13. 机器人的运动范围
	public int movingCount(int m, int n, int k) {
		int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		boolean[][] visited = new boolean[m][n];
		visited[0][0] = true;
		int ret = 1;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		int x, y, cur, nx, ny;
		while (!list.isEmpty()) {
			cur = list.removeFirst();
			x = cur / 100;
			y = cur % 100;
			for (int[] d : ds) {
				nx = x + d[0];
				ny = y + d[1];
				if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
					continue;
				} else if (nx / 10 + nx % 10 + ny / 10 + ny % 10 <= k) {
					list.add(nx * 100 + ny);
					ret++;
				}
				visited[nx][ny] = true;
			}
		}
		return ret;
	}
	
	//面试题 17.14. 最小K个数
	
	
	//LCP 14. 切分数组
	int ret = Integer.MAX_VALUE;
	
	public int splitArray(int[] nums) {
		splitArrayHelper(nums, nums.length - 1, 0);
		return ret;
	}
	
	private void splitArrayHelper(int[] nums, int r, int c) {
		if (c >= ret) {
			return;
		} else if (r < 0) {
			ret = c;
		}
		for (int i = 0; i <= r; i++) {
			if (gcb(nums[i], nums[r]) > 1) {
				splitArrayHelper(nums, i - 1, c + 1);
			}
		}
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
	
	//面试题 17.09. 第 k 个数
	public int getKthMagicNumber(int k) {
		long[] mem = new long[k + 1];
		mem[0] = 1;
		int[] count = new int[3];
		long[] x = new long[]{3, 5, 7};
		int[] base = {3, 5, 7};
		int t;
		for (int i = 1; i < k; i++) {
			t = 0;
			if (x[1] < x[t]) {
				t = 1;
			} else if (x[2] < x[t]) {
				t = 2;
			}
			mem[i] = x[t];
			for (int j = 0; j < 3; j++) {
				int c = count[j];
				while (x[j] <= mem[i]) {
					c++;
					if (c == k) {
						x[j] = Integer.MAX_VALUE;
					} else {
						x[j] = base[j] * mem[c];
					}
				}
				count[j] = c;
			}
		}
		return (int) mem[k - 1];
	}
	
	interface MountainArray {
		
		public int get(int index);
		
		public int length();
		
	}
	
	public int findInMountainArray(int target, MountainArray mountainArr) {
		int len = mountainArr.length();
		return findInMountainArrayHelper(target, 0, len - 1, mountainArr);
	}
	
	private int findInMountainArrayHelper(int target, int l, int r, MountainArray mountainArr) {
		if (l > r) {
			return -1;
		} else if (l == r) {
			return mountainArr.get(l) == target ? l : -1;
		}
		int m = (l + r) >> 1;
		int a = mountainArr.get(m);
		int b = mountainArr.get(m + 1);
		if (a == target) {
			if (a > b) {
				int x = findInMountainArrayHelper(target, l, m - 1,
					mountainArr);
				if (x >= 0) {
					return x;
				}
			}
			return m;
		} else if (b == target) {
			if (a > b) {
				int x = findInMountainArrayHelper(target, l, m - 1,
					mountainArr);
				if (x >= 0) {
					return x;
				}
			}
			return m + 1;
		}
		if (a < b) {
			if (a > target) {
				int x = findInMountainArrayHelper(target, l, m - 1,
					mountainArr);
				if (x < 0) {
					return findInMountainArrayHelper(target, m + 2, r, mountainArr);
				} else {
					return x;
				}
			} else {
				return findInMountainArrayHelper(target, m + 2, r, mountainArr);
			}
		} else {
			if (b < target) {
				return findInMountainArrayHelper(target, l, m - 1, mountainArr);
			} else {
				int x = findInMountainArrayHelper(target, l, m - 1, mountainArr);
				if (x < 0) {
					return findInMountainArrayHelper(target, m + 2, r, mountainArr);
				} else {
					return x;
				}
			}
		}
	}
	
	//面试题 16.11. 跳水板
	public int[] divingBoard(int shorter, int longer, int k) {
		if (k == 0) {
			return new int[0];
		}
		if (shorter == longer) {
			int[] ret = {shorter * k};
			return ret;
		}
		int[] ret = new int[k + 1];
		ret[0] = shorter * k;
		int d = longer - shorter;
		for (int i = 1; i <= k; i++) {
			ret[i] = d + ret[i - 1];
		}
		return ret;
	}
	
	//面试题 08.03. 魔术索引
	public int findMagicIndex(int[] nums) {
		return findMagicIndexHelper(nums, 0, nums.length - 1);
	}
	
	private int findMagicIndexHelper(int[] nums, int l, int r) {
		if (l > r) {
			return -1;
		}
		int m = (l + r) / 2, ret = findMagicIndexHelper(nums, l, m - 1);
		if (ret >= 0) {
			return ret;
		} else if (m == nums[m]) {
			return m;
		} else {
			return findMagicIndexHelper(nums, m + 1, r);
		}
	}
	
	//面试题 05.04. 下一个数
	public int[] findClosedNumbers(int num) {
		long i = 1, t = num;
		int[] ret = new int[2];
		while ((num & i) == 0) {
			i <<= 1;
		}
		t -= i;
		while ((num & i) != 0) {
			i <<= 1;
		}
		t += i;
		i >>= 1;
		long x = 1;
		while (x < i) {
			if ((t & x) != 0) {
				x <<= 1;
			}
			if ((t & i) == 0) {
				i >>= 1;
			}
			if ((t & x) == 0 && (t & i) != 0 && x < i) {
				t += x;
				t -= i;
			}
		}
		if (t > Integer.MAX_VALUE) {
			ret[0] = -1;
		} else {
			ret[0] = (int) t;
		}
		i = 1;
		t = num;
		while ((num & i) != 0 && i < num) {
			i <<= 1;
		}
		if (i >= num) {
			ret[1] = -1;
		} else {
			t += i;
			while ((i & num) == 0) {
				i <<= 1;
			}
			t -= i;
			i >>= 1;
			x = i;
			while (x > 0) {
				if ((x & t) > 0) {
					t -= x;
					t += i;
					i >>= 1;
				}
				x >>= 1;
			}
			ret[1] = (int) t;
		}
		return ret;
	}
	
	//面试题 01.01. 判定字符是否唯一
	public boolean isUnique(String astr) {
		if (astr.length() > 26) {
			return false;
		}
		int mask = 0, cur;
		for (int i = 0; i < astr.length(); i++) {
			cur = 1 << (astr.charAt(i) - 'a');
			if ((mask & cur) > 0) {
				return false;
			}
			mask |= cur;
		}
		return true;
		
	}
}
