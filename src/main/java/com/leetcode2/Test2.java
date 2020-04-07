package com.leetcode2;

import com.alibaba.fastjson.JSON;
import com.leetcode2.Test1.ListNode;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test2
 * @Author: WuMing
 * @CreateDate: 2020/3/27 10:06
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test2 {
	
	public static void main(String[] args) throws Exception {
		Test2 test = new Test2();
		
		test.stoneGameIII(new int[]{-1,-2,-3});
	}
	
	//1406. 石子游戏 III
	public String stoneGameIII(int[] stoneValue) {
		int len = stoneValue.length;
		int[] mem = new int[len];
		Arrays.fill(mem,Integer.MIN_VALUE);
		stoneGameIIIHelper(stoneValue, mem, 0);
		return mem[0] == 0 ? "Tie" : (mem[0] > 0 ? "Alice" : "Bob");
	}
	
	private int stoneGameIIIHelper(int[] stoneValue, int[] mem, int i) {
		int ret = Integer.MIN_VALUE;
		int len = stoneValue.length;
		if(i>= len){
			return 0;
		}else if(mem[i]>ret){
			return mem[i];
		}
		ret = Math.max(ret, stoneValue[i] - stoneGameIIIHelper(stoneValue, mem, i + 1));
		if(i+1<len){
			
			ret = Math.max(ret,
				stoneValue[i] + stoneValue[i + 1] - stoneGameIIIHelper(stoneValue, mem, i + 2));
		}
		if(i+2<len){
			ret = Math.max(ret,
				stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - stoneGameIIIHelper(stoneValue,
					mem, i + 3));
		}
		mem[i] = ret;
		return ret;
		
	}
	
	//1404. 将二进制表示减到 1 的步骤数
	public int numSteps(String s) {
		int len = s.length();
		int[] num = new int[len];
		for (int i = 0; i < len; i++) {
			num[i] = s.charAt(i) - '0';
		}
		int i = len - 1, ret = 0;
		while (i >= 0) {
			while (i > 0 && num[i] == 0) {
				ret++;
				i--;
			}
			if (i > 0 && num[i] == 1) {
				ret++;
			} else if (i == 0 && num[i] == 0) {
				ret++;
				return ret;
			} else if (i == 0 && num[i] == 1) {
				return ret;
			}
			for (int j = i; j >= 0; j--) {
				if (num[j] > 0) {
					num[j] = 0;
				} else {
					num[j] = 1;
					break;
				}
			}
		}
		return ret;
	}
	
	//1400. 构造 K 个回文字符串
	public boolean canConstruct(String s, int k) {
		int[] count = new int[26];
		int len = s.length();
		if (len < k) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			count[s.charAt(i) - 'a']++;
		}
		int x = 0;
		for (int i : count) {
			x += (i & 1);
		}
		if (x > k) {
			return false;
		}
		return true;
	}
	
	//1399. 统计最大组的数目
	public int countLargestGroup(int n) {
		int[] count = new int[10];
		int x, c;
		for (int i = 1; i <= n; i++) {
			x = 0;
			c = i;
			while (c > 0) {
				x += c % 10;
				c /= 10;
			}
			count[x]++;
		}
		int ret = 0, max = 0;
		for (int i : count) {
			if (i > max) {
				ret = 1;
				max = i;
			} else if (i == max) {
				ret++;
			}
		}
		return ret;
	}
	
	//面试题 01.07. 旋转矩阵
	public void rotate(int[][] matrix) {
		int len = matrix.length;
		for (int i = 0; i < len / 2; i++) {
			for (int j = 0; j < len / 2; j++) {
				rotateHelp(matrix, i, j, len - 1, 0);
			}
		}
		if ((len & 1) == 1) {
			int i = len / 2;
			for (int j = 0; j < i; j++) {
				rotateHelp(matrix, i, j, len, 0);
			}
		}
	}
	
	private int rotateHelp(int[][] matrix, int i, int j, int len, int count) {
		int ret = matrix[len - j][i];
		if (count == 4) {
			return ret;
		}
		rotateHelp(matrix, len - j, i, len, count + 1);
		matrix[i][j] = ret;
		return ret;
	}
	
	//面试题03. 数组中重复的数字
	public int findRepeatNumber(int[] nums) {
		int len = nums.length, c, t;
		for (int i = 0; i < len; i++) {
			c = nums[i];
			while (c != i) {
				t = nums[c];
				if (t == c) {
					return t;
				}
				nums[c] = c;
				c = t;
			}
			nums[i] = c;
		}
		return -1;
	}
	
	//8
	public int myAtoi(String str) {
		int len = str.length();
		long ret = 0;
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == ' ') {
				continue;
			} else if (c == '+') {
				ret = myAtoiHelper(str, i + 1);
				break;
			} else if (c == '-') {
				ret = -1 * myAtoiHelper(str, i + 1);
				break;
			} else if (c >= '0' && c <= '9') {
				ret = myAtoiHelper(str, i);
				break;
			} else {
				return 0;
			}
		}
		if (ret > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (ret < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) ret;
		}
	}
	
	private long myAtoiHelper(String str, int s) {
		int len = str.length();
		long ret = 0, count = 0;
		for (int i = s; i < len && count < 11; i++) {
			if (ret > 0) {
				count++;
			}
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
			} else {
				break;
			}
		}
		return ret;
	}
	
	//面试题 17.19. 消失的两个数字
	public int[] missingTwo(int[] nums) {
		int total = (1 + nums.length + 2) * (nums.length + 2) / 2;
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int x = total - sum;
		int half = x / 2;
		int ls = 0;
		for (int num : nums) {
			if (num <= half) {
				ls += num;
			}
		}
		int a = (1 + half) * half / 2 - ls;
		int b = x - a;
		return new int[]{a, b};
	}
	
	//面试题59 - I. 滑动窗口的最大值
	public int[] maxSlidingWindow(int[] nums, int k) {
		LinkedList<Integer> list = new LinkedList<>();
		int i = 1;
		list.add(nums[0]);
		while (i < k) {
			while (!list.isEmpty() && list.getLast() < nums[i]) {
				list.removeLast();
			}
			list.add(nums[i]);
			i++;
		}
		int len = nums.length;
		int[] ret = new int[len - k + 1];
		for (; i < len; i++) {
			ret[i - k] = list.getFirst();
			if (nums[i - k] == list.getFirst()) {
				list.removeFirst();
			}
			while (!list.isEmpty() && list.getLast() < nums[i]) {
				list.removeLast();
			}
			list.add(nums[i]);
		}
		ret[len - k] = list.getFirst();
		return ret;
	}
	
	//面试题50. 第一个只出现一次的字符
	public char firstUniqChar(String s) {
		int[][] count = new int[256][2];
		int len = s.length();
		int[] cur;
		for (int i = 0; i < len; i++) {
			cur = count[s.charAt(i)];
			cur[0] = i;
			cur[1]++;
		}
		int c = -1;
		for (int i = 0; i < 256; i++) {
			cur = count[i];
			if (cur[1] == 1) {
				if (c < 0 || count[c][0] > cur[0]) {
					c = i;
				}
			}
		}
		return c >= 0 ? (char) c : ' ';
	}
	
	//面试题61. 扑克牌中的顺子
	public boolean isStraight(int[] nums) {
		int[] count = new int[14];
		for (int num : nums) {
			count[num]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (count[i] == 1) {
				for (int j = i + 1; j < i + 5; j++) {
					if (count[i] == 0) {
						if (count[0] == 0) {
							return false;
						} else {
							count[0]--;
						}
					}
				}
				break;
			} else if (count[i] > 1) {
				return false;
			}
		}
		return true;
	}
	
	//面试题66. 构建乘积数组
	public int[] constructArr(int[] a) {
		int t = 1;
		int len = a.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = t;
			t *= a[i];
		}
		t = 1;
		for (int i = len - 1; i >= 0; i--) {
			ret[i] *= t;
			t *= a[i];
		}
		return ret;
	}
	
	//面试题39. 数组中出现次数超过一半的数字
	public int majorityElement(int[] nums) {
		int val = 0, count = 0;
		for (int num : nums) {
			if (count == 0) {
				val = num;
				count++;
			} else if (num == val) {
				count++;
			} else {
				count--;
			}
		}
		return val;
	}
	
	//面试题27. 二叉树的镜像
	public TreeNode mirrorTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode ret = new TreeNode(root.val);
		ret.left = mirrorTree(root.right);
		ret.right = mirrorTree(root.left);
		return ret;
	}
	
	//面试题15. 二进制中1的个数
	public int hammingWeight(int n) {
		int ret = 0;
		while (n > 0) {
			ret += (n & 1);
			n >>= 1;
		}
		return ret;
	}
	
	//面试题10- II. 青蛙跳台阶问题
	public int numWays(int n) {
		if (n == 0) {
			return 1;
		} else if (n < 3) {
			return n;
		}
		long a = 1, b = 2, c = 3, t = 3;
		int mod = 1000000007;
		while (t <= n) {
			c = a + b;
			c %= mod;
			a = b;
			b = c;
			t++;
		}
		return (int) c;
	}
	
	//面试题06. 从尾到头打印链表
	public int[] reversePrint(ListNode head) {
		int count = 0;
		ListNode t = head;
		while (t != null) {
			count++;
			t = t.next;
		}
		int[] ret = new int[count];
		while (count > 0) {
			count--;
			ret[count] = head.val;
			head = head.next;
		}
		return ret;
	}
	
	
	public List<String> getValidT9Words(String num, String[] words) {
		int[] map = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
		ArrayList<String> ret = new ArrayList<>();
		int len = num.length();
		int i = 0;
		int[] nums = new int[len];
		for (; i < len; i++) {
			nums[i] = num.charAt(i) - '0';
		}
		
		for (String word : words) {
			if (word.length() == len) {
				for (i = 0; i < len; i++) {
					if (map[word.charAt(i) - 'a'] != nums[i]) {
						break;
					}
				}
				if (i == len) {
					ret.add(word);
				}
			}
		}
		return ret;
	}
	
	public int maxSubArray(int[] nums) {
		int sum = 0, ret = Integer.MIN_VALUE;
		for (int num : nums) {
			sum += num;
			ret = Math.max(ret, sum);
			if (sum < 0) {
				sum = 0;
			}
		}
		return ret;
	}
	
	public void gameOfLife(int[][] board) {
		int[] sta = {0, 1, 1, 0};
		int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
			{1, 1}};
		int high = board.length;
		int len = board[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				int count = gameOfLifeHelper(board, ds, i, j, sta);
				if (board[i][j] == 0) {
					if (count == 3) {
						board[i][j] = 3;
					}
				} else {
					if (count < 2 || count > 3) {
						board[i][j] = 2;
					}
				}
			}
		}
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				board[i][j] &= 1;
			}
		}
	}
	
	private int gameOfLifeHelper(int[][] board, int[][] ds, int i, int j, int[] sta) {
		int ret = 0, ni, nj;
		int high = board.length;
		int len = board[0].length;
		for (int[] d : ds) {
			ni = i + d[0];
			nj = j + d[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len) {
				continue;
			}
			ret += sta[board[ni][nj]];
		}
		return ret;
	}
	
	//面试题 16.19. 水域大小
	int pondSize;
	
	public int[] pondSizes(int[][] land) {
		int high = land.length;
		int len = land[0].length;
		int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
			{1, 1}};
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (land[i][j] == 0) {
					pondSize = 1;
					land[i][j] = 1;
					pondSizesHelper(land, i, j, ds);
					list.add(pondSize);
				}
			}
		}
		int size = list.size();
		int[] ret = new int[size];
		for (int i = 0; i < size; i++) {
			ret[i] = list.get(i);
		}
		Arrays.sort(ret);
		return ret;
	}
	
	private void pondSizesHelper(int[][] land, int i, int j, int[][] ds) {
		int ni, nj;
		int high = land.length;
		int len = land[0].length;
		for (int[] d : ds) {
			ni = i + d[0];
			nj = j + d[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len || land[ni][nj] > 0) {
				continue;
			}
			pondSize++;
			land[ni][nj] = 1;
			pondSizesHelper(land, ni, nj, ds);
		}
	}
	
	//面试题 16.10. 生存人数
	public int maxAliveYear(int[] birth, int[] death) {
		int[] count = new int[102];
		int len = birth.length;
		for (int i = 0; i < len; i++) {
			count[birth[i] - 1900]++;
			count[death[i] - 1899]--;
		}
		int idx = 0, max = count[0], sum = max;
		int l = count.length;
		for (int i = 1; i < l; i++) {
			sum += count[i];
			if (sum > max) {
				max = sum;
				idx = i;
			}
		}
		return idx + 1900;
	}
	
	//面试题 16.06. 最小差
	public int smallestDifference(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int la = a.length;
		int lb = b.length;
		int ai = 0, bi = 0;
		long min = Integer.MAX_VALUE;
		while (ai < la && bi < lb) {
			while (bi < lb && ai < la && a[ai] < b[bi]) {
				ai++;
			}
			if (ai > 0 && bi < lb) {
				min = Math.min(min, (long) b[bi] - a[ai - 1]);
			}
			if (ai < la && bi < lb) {
				min = Math.min(min, -b[bi] + a[ai]);
			}
			while (ai < la && bi < lb && b[bi] < a[ai]) {
				bi++;
			}
			if (bi > 0 && ai < la) {
				min = Math.min(min, a[ai] - b[bi - 1]);
			}
			if (bi < lb && ai < la) {
				min = Math.min(min, -a[ai] + b[bi]);
			}
			if (ai < la && bi < lb && a[ai] == b[bi]) {
				return 0;
			}
		}
		return (int) min;
	}
	
	//面试题 10.11. 峰与谷
	public void wiggleSort(int[] nums) {
		int len = nums.length;
		int[] copy = Arrays.copyOf(nums, len);
		Arrays.sort(copy);
		int j = 0;
		for (int i = 0; i < len; i += 2) {
			nums[i] = copy[j++];
		}
		for (int i = 1; i < len; i += 2) {
			nums[i] = copy[j++];
		}
	}
	
	//面试题 08.04. 幂集
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		ret.add(new ArrayList<>());
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= nums.length; i++) {
			subsetsHelper(ret, list, i, 0, nums);
		}
		return ret;
	}
	
	//面试题 17.08. 马戏团人塔
	public int bestSeqAtIndex(int[] height, int[] weight) {
		int len = height.length;
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			ints[i][0] = height[i];
			ints[i][1] = weight[i];
		}
		Arrays.sort(ints, (a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			} else {
				return a[0] - b[0];
			}
		});
		int[] ret = new int[len];
		Arrays.fill(ret, -1);
		int result = 0;
		for (int i = 0; i < len; i++) {
			if (ret[i] < 0) {
				bestSeqAtIndexHelper(ret, ints, i);
			}
			result = Math.max(result, ret[i]);
		}
		return result + 1;
	}
	
	private int bestSeqAtIndexHelper(int[] ret, int[][] ints, int cur) {
		if (ret[cur] >= 0) {
			return ret[cur];
		}
		int x = 0;
		for (int i = cur + 1; i < ints.length; i++) {
			if (ints[cur][1] < ints[i][1]) {
				x = Math.max(x, 1 + bestSeqAtIndexHelper(ret, ints, i));
			}
		}
		ret[cur] = x;
		return x;
	}
	
	private void subsetsHelper(List<List<Integer>> ret, LinkedList<Integer> list, int last, int s,
		int[] num) {
		int len = num.length;
		if (last == 0) {
			ArrayList<Integer> x = new ArrayList<>();
			x.addAll(list);
			ret.add(x);
			return;
		} else if (last > len - s) {
			return;
		}
		
		for (int j = s; j < len; j++) {
			list.add(num[j]);
			subsetsHelper(ret, list, last - 1, j + 1, num);
			list.removeLast();
		}
	}
	
	//面试题 04.06. 后继者
	TreeNode inorderSuccessor = null;
	boolean flag = false;
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		inorderSuccessorHelper(root, p);
		return inorderSuccessor;
	}
	
	private void inorderSuccessorHelper(TreeNode root, TreeNode p) {
		if (root == null) {
			return;
		}
		inorderSuccessorHelper(root.left, p);
		if (p == root) {
			flag = true;
		} else if (flag) {
			if (inorderSuccessor == null) {
				inorderSuccessor = root;
			}
			return;
		}
		inorderSuccessorHelper(root.right, p);
	}
	
	//面试题 04.02. 最小高度树
	public TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
	}
	
	private TreeNode sortedArrayToBSTHelper(int[] nums, int l, int r) {
		if (l > r) {
			return null;
		} else if (l == r) {
			return new TreeNode(nums[l]);
		}
		int m = (l + r) / 2;
		TreeNode ret = new TreeNode(nums[m]);
		ret.left = sortedArrayToBSTHelper(nums, l, m - 1);
		ret.right = sortedArrayToBSTHelper(nums, m + 1, r);
		return ret;
	}
	
	
	//面试题 05.07. 配对交换
	public int exchangeBits(int num) {
		int x = 1, a, b;
		while (num > x) {
			a = num & x;
			x <<= 1;
			b = num & x;
			x <<= 1;
			if (a == 0 && b != 0) {
				num -= (b >> 1);
			} else if (a != 0 && b == 0) {
				num += a;
			}
		}
		return num;
	}
	
	//面试题 04.01. 节点间通路
	public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
		boolean[] visited = new boolean[n];
		visited[start] = true;
		HashSet<Integer>[] sets = new HashSet[n];
		for (int i = 0; i < n; i++) {
			sets[i] = new HashSet();
		}
		for (int[] x : graph) {
			sets[x[0]].add(x[1]);
		}
		HashSet<Integer> cur = new HashSet(), next;
		cur.add(0);
		while (!cur.isEmpty()) {
			next = new HashSet<>();
			for (Integer c : cur) {
				for (Integer x : sets[c]) {
					if (!visited[x]) {
						visited[x] = true;
						next.add(x);
					}
				}
			}
			if (visited[target]) {
				return true;
			}
			cur = next;
		}
		return false;
	}
	
	int pathSumCount = 0;
	
	//面试题 04.12. 求和路径
	public int pathSum(TreeNode root, int sum) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		pathSumHelper(root, list, sum);
		return pathSumCount;
	}
	
	private void pathSumHelper(TreeNode root, LinkedList<Integer> list, int sum) {
		if (root == null) {
			return;
		}
		int cur = list.getLast() + root.val;
		for (Integer x : list) {
			if (cur - x == sum) {
				pathSumCount++;
			}
		}
		list.add(cur);
		pathSumHelper(root.left, list, sum);
		pathSumHelper(root.right, list, sum);
		list.removeLast();
	}
	
	public int pathSum1(TreeNode root, int sum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		pathSumHelper(root, map, sum, 0);
		return pathSumCount;
	}
	
	private void pathSumHelper(TreeNode root, HashMap<Integer, Integer> map, int sum, int before) {
		if (root == null) {
			return;
		}
		int cur = before + root.val;
		pathSumCount += map.getOrDefault(cur - sum, 0);
		map.put(cur, map.getOrDefault(cur, 0) + 1);
		pathSumHelper(root.left, map, sum, cur);
		pathSumHelper(root.right, map, sum, cur);
		map.put(cur, map.get(cur) - 1);
	}
	
	public ListNode[] listOfDepth(TreeNode tree) {
		ArrayList<ListNode> heads = new ArrayList<>();
		ArrayList<ListNode> tails = new ArrayList<>();
		listOfDepthHelper(tree, heads, tails, 0);
		int size = heads.size();
		ListNode[] ret = new ListNode[size];
		for (int i = 0; i < size; i++) {
			ret[i] = heads.get(i);
		}
		return ret;
	}
	
	public boolean canPermutePalindrome(String s) {
		int len = s.length();
		int[] count = new int[26];
		for (int i = 0; i < len; i++) {
			count[s.charAt(i) - 'a']++;
		}
		int x = 0;
		for (int i : count) {
			x += (i & 1);
		}
		return x < 2;
	}
	
	public boolean CheckPermutation(String s1, String s2) {
		int len = s1.length();
		if (len != s2.length()) {
			return false;
		}
		int[] count = new int[26];
		for (int i = 0; i < len; i++) {
			count[s1.charAt(i) - 'a']++;
		}
		for (int i = 0; i < len; i++) {
			char c = s2.charAt(i);
			if (count[c - 'a'] > 0) {
				count[c - 'a']--;
			} else {
				return false;
			}
		}
		return true;
	}
	
	private void listOfDepthHelper(TreeNode root, ArrayList<ListNode> heads,
		ArrayList<ListNode> tails, int level) {
		if (root == null) {
			return;
		}
		if (heads.size() == level) {
			ListNode node = new ListNode(root.val);
			heads.add(node);
			tails.add(node);
		} else {
			ListNode node = new ListNode(root.val);
			ListNode cur = tails.get(level);
			cur.next = node;
			tails.set(level, node);
		}
		listOfDepthHelper(root.left, heads, tails, level + 1);
		listOfDepthHelper(root.right, heads, tails, level + 1);
	}
	
	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	TreeNode lowestCommonAncestor = null;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		int i = lowestCommonAncestorHelper(root, p, q);
		return lowestCommonAncestor;
	}
	
	private int lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || lowestCommonAncestor != null) {
			return 0;
		}
		int ret = 0;
		if (root.val == p.val) {
			ret |= 1;
		} else if (root.val == q.val) {
			ret |= 2;
		}
		ret |= lowestCommonAncestorHelper(root.left, p, q);
		ret |= lowestCommonAncestorHelper(root.right, p, q);
		if (lowestCommonAncestor == null && ret == 3) {
			lowestCommonAncestor = root;
		}
		return ret;
	}
	
	//面试题 08.01. 三步问题
	public int waysToStep(int n) {
		if (n == 3) {
			return 4;
		} else if (n == 2) {
			return 2;
		} else if (n == 1) {
			return 1;
		}
		int a = 1, b = 2, c = 4, t = 3, x, mod = 1000000007;
		while (t < n) {
			x = a + b + c;
			x %= mod;
			a = b;
			b = c;
			c = x;
			t++;
		}
		return c;
	}
	
	static class Level {
		
		int id;
		String name;
		int parentId;
		List<Level> children;
		
		public Level(int id, String name, int parentId) {
			this.id = id;
			this.name = name;
			this.parentId = parentId;
			children = new ArrayList<>();
		}
	}
	
	
	//1390
	public int sumFourDivisors(int[] nums) {
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		TreeSet<Integer> set = new TreeSet<>();
		set.add(2);
		double x = Math.pow(max, (double) 1 / 3);
		boolean flag;
		for (int i = 3; i < max / 2; i++) {
			flag = true;
			for (Integer c : set) {
				if (i % c == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				set.add(i);
			}
		}
		int ret = 0;
		for (int num : nums) {
			for (Integer prim : set) {
				if (prim * prim >= num) {
					break;
				} else if (num % prim == 0) {
					if (prim < x && prim * prim * prim == num) {
						ret += (1 + prim + prim * prim + num);
						break;
					} else if (set.contains(num / prim)) {
						ret += (1 + prim + num / prim + num);
						break;
					}
				}
			}
		}
		return ret;
	}
	
	//1394
	public int findLucky(int[] arr) {
		int len = arr.length;
		int[] count = new int[len + 1];
		int ret = -1;
		for (int i : arr) {
			if (i <= len) {
				count[i]++;
			}
		}
		for (int i = 1; i < len + 1; i++) {
			if (i == count[i]) {
				ret = Math.max(ret, i);
			}
		}
		return ret;
	}
	
	//1395
	public int numTeams(int[] rating) {
		int len = rating.length;
		int[] bigger = new int[len];
		int[] smaller = new int[len];
		for (int i = 0; i < len; i++) {
			int cur = rating[i], l = 0, r = 0;
			for (int j = i + 1; j < len; j++) {
				if (rating[j] > cur) {
					smaller[j]++;
				} else {
					bigger[j]++;
				}
			}
		}
		int ret = 0;
		for (int i = 2; i < len; i++) {
			int cur = rating[i];
			for (int j = 1; j < i; j++) {
				if (cur > rating[j]) {
					ret += smaller[j];
				} else {
					ret += bigger[j];
				}
			}
		}
		return ret;
	}
	
	//面试题62. 圆圈中最后剩下的数字--约瑟夫环
	public int lastRemaining(int n, int m) {
		if (n == 1) {
			return 0;
		}
		return (lastRemaining(n - 1, m) + m) % n;
	}
	
	public int lastRemaining1(int n, int m) {
		int ret = 0;
		for (int i = 2; i <= n; i++) {
			ret = (ret + m) % i;
		}
		return ret;
	}
	
	//交换两个integer的值
	private static void swap(Integer a, Integer b) throws Exception {
		Field value = Integer.class.getDeclaredField("value");
		value.setAccessible(true);
		Integer t = new Integer(a.intValue());
		value.set(a, b.intValue());
		value.set(b, t);
	}
	
	public boolean hasGroupsSizeX(int[] deck) {
		int[] count = new int[10000];
		int l = count[0], r = l;
		for (int i : deck) {
			count[i]++;
			if (i < l) {
				l = i;
			} else if (i > r) {
				r = i;
			}
		}
		int g = count[r];
		for (int i = l; i < r; i++) {
			if (count[i] > 0) {
				g = gcd(g, count[i]);
			}
		}
		return g > 1;
	}
	
	private int gcd(int a, int b) {
		return a == 0 ? b : gcd(b % a, a);
	}
	
	//1375
	public int numTimesAllBlue(int[] light) {
		int max = 0, ret = 0;
		int len = light.length;
		for (int i = 0; i < len; i++) {
			max = Math.max(max, light[i]);
			if (max == i + 1) {
				ret++;
			}
		}
		return ret;
	}
}
