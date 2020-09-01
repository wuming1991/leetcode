package com.leetcode2;

import static com.base.Constant.ds_4;

import com.base.ListNode;
import com.leetcode2.Codec.TreeNode;
import com.wuming.vm.dispatch.MethodHandleTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Author: WuMing
 * @CreateDate: 2020/7/23 11:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test6 {
	
	
	public static void main(String[] args) throws IOException {
		Test6 test = new Test6();
		test.PredictTheWinner(new int[]{1,5,2});
	}
	
	public boolean PredictTheWinner(int[] nums) {
		int len = nums.length;
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			mem[i][i]=nums[i];
		}
		for (int l = 1; l < len; l++) {
			for (int i = 0; i+l < len; i++) {
				mem[i][i+l]=Math.max(nums[i]-mem[i+1][i+l],nums[i+l]-mem[i][i+l-1]);
			}
		}
		return mem[0][len-1]>0;
	}
	//1567. 乘积为正数的最长子数组长度
	public int getMaxLen(int[] nums) {
		int count = 0, ret = 0, a = Integer.MAX_VALUE, b = Integer.MAX_VALUE, c = -1;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0) {
				count = 0;
				b = Integer.MAX_VALUE;
				a = Integer.MAX_VALUE;
				c = i;
			} else if (nums[i] > 0) {
				a = Math.min(i, a);
				if ((count & 1) == 1) {
					ret = Math.max(i - b, ret);
				} else {
					ret = Math.max(i - c, ret);
				}
			} else {
				count++;
				b = Math.min(i, b);
				if ((count & 1) == 1) {
					ret = Math.max(i - b, ret);
				} else {
					ret = Math.max(i - c, ret);
				}
			}
		}
		return ret;
	}
	
	//841. 钥匙和房间
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int len = rooms.size();
		boolean[] visited = new boolean[len];
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		while (!list.isEmpty() && len > 0) {
			Integer key = list.removeFirst();
			if (!visited[key]) {
				list.addAll(rooms.get(key));
				visited[key] = true;
				len--;
			}
		}
		return len == 0;
	}
	
	//68. 文本左右对齐
	public List<String> fullJustify(String[] words, int maxWidth) {
		ArrayList<String> ret = new ArrayList<>();
		int left = 0;
		int len = words.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			if (sum + words[i].length() <= maxWidth) {
				sum += words[i].length() + 1;
			} else {
				fullJustifyHelper(ret, words, left, i, maxWidth - sum + 1);
				left = i;
				sum = words[i].length() + 1;
			}
		}
		if (left < len) {
			StringBuffer buffer = new StringBuffer();
			for (int i = left; i < len; i++) {
				buffer.append(words[i] + " ");
			}
			while (sum < maxWidth) {
				buffer.append(' ');
				sum++;
			}
			while (sum > maxWidth) {
				sum--;
				buffer.deleteCharAt(sum);
			}
			ret.add(buffer.toString());
		}
		return ret;
	}
	
	private void fullJustifyHelper(ArrayList<String> ret, String[] words, int i, int r, int last) {
		int c = r - i - 1;
		StringBuffer buffer = new StringBuffer();
		if (c == 0) {
			buffer.append(words[i]);
			while (last > 0) {
				buffer.append(' ');
				last--;
			}
		} else {
			last += c;
			int x = last / c, y = last % c;
			for (; i < r - 1; i++) {
				buffer.append(words[i]);
				for (int j = 0; j < x; j++) {
					buffer.append(' ');
				}
				if (y > 0) {
					buffer.append(' ');
					y--;
				}
			}
			buffer.append(words[i]);
		}
		ret.add(buffer.toString());
	}
	
	//87. 扰乱字符串
	public boolean isScramble(String s1, String s2) {
		HashMap<String, Boolean> map = new HashMap<>();
		return isScrambleHelper(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1, map);
	}
	
	private boolean isScrambleHelper(String s1, int l1, int r1, String s2, int l2, int r2,
		Map<String, Boolean> map) {
		String key = l1 + "_" + r1 + "_" + l2 + "_" + r2;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		int i = l1, j = r2;
		int[] mem = new int[26];
		int count = 0;
		int x, k = 0;
		while (l1 + k <= r1) {
			if (s1.charAt(l1 + k) == s2.charAt(l2 + k)) {
				k++;
			} else {
				break;
			}
		}
		if (k + l1 > r1) {
			map.put(key, true);
			return true;
		}
		while (i < r1) {
			x = s1.charAt(i) - 'a';
			mem[x]++;
			if (mem[x] == 1) {
				count++;
			} else if (mem[x] == 0) {
				count--;
			}
			x = s2.charAt(j) - 'a';
			mem[x]--;
			if (mem[x] == -1) {
				count++;
			} else if (mem[x] == 0) {
				count--;
			}
			if (count == 0) {
				if (isScrambleHelper(s1, l1, i, s2, j, r2, map) && isScrambleHelper(s1, i + 1, r1,
					s2,
					l2, j - 1, map)) {
					map.put(key, true);
					return true;
				}
			}
			i++;
			j--;
		}
		i = l1;
		j = l2;
		count = 0;
		Arrays.fill(mem, 0);
		while (i < r1) {
			x = s1.charAt(i) - 'a';
			mem[x]++;
			if (mem[x] == 1) {
				count++;
			} else if (mem[x] == 0) {
				count--;
			}
			x = s2.charAt(j) - 'a';
			mem[x]--;
			if (mem[x] == -1) {
				count++;
			} else if (mem[x] == 0) {
				count--;
			}
			if (count == 0) {
				if (isScrambleHelper(s1, l1, i, s2, l2, j, map) && isScrambleHelper(s1, i + 1, r1,
					s2,
					j + 1, r2, map)) {
					map.put(key, true);
					return true;
				}
			}
			i++;
			j++;
		}
		map.put(key, false);
		return false;
	}
	
	
	//188. 买卖股票的最佳时机 IV
	public int maxProfit1(int k, int[] prices) {
		int len = prices.length, ret = 0, x;
		if (k / 2 > len) {
			for (int i = 1; i < len; i++) {
				x = prices[i] - prices[i - 1];
				if (x > 0) {
					ret += x;
				}
			}
			return ret;
		}
		int[] buy = new int[k];//持有股票的最大值
		int[] sell = new int[k];//不持有股票的最大值
		Arrays.fill(buy, Integer.MIN_VALUE);
		for (int p : prices) {
			buy[0] = Math.max(-p, buy[0]);
			sell[0] = Math.max(sell[0], buy[0] + p);
			for (int i = 1; i < k; i++) {
				buy[i] = Math.max(buy[i], sell[i - 1] - p);
				sell[i] = Math.max(sell[i], buy[i] + p);
			}
		}
		return sell[k - 1];
	}
	
	class TireeNode {
		
		boolean isEnd = false;
		String s;
		TireeNode[] children = new TireeNode[26];
	}
	
	public List<String> wordBreak(String s, List<String> wordDict) {
		TireeNode root = new TireeNode();
		int len, x;
		for (String w : wordDict) {
			TireeNode cur = root;
			len = w.length();
			for (int i = 0; i < len; i++) {
				x = w.charAt(i) - 'a';
				if (cur.children[x] == null) {
					cur.children[x] = new TireeNode();
				}
				cur = cur.children[x];
			}
			cur.isEnd = true;
			cur.s = w;
		}
		
		HashMap<Integer, List<String>> map = new HashMap<>();
		List<String> ret = wordBreakHelper(root, s, 0, map);
		return ret;
	}
	
	private List<String> wordBreakHelper(TireeNode root, String s, int i,
		HashMap<Integer, List<String>> map) {
		if (map.containsKey(i)) {
			return map.get(i);
		}
		int len = s.length();
		TireeNode cur = root;
		int x, b = i;
		List<String> ret = new ArrayList<>(), right;
		for (; i < len; i++) {
			x = s.charAt(i) - 'a';
			if (cur.children[x] == null) {
				map.put(b, ret);
				return ret;
			} else {
				cur = cur.children[x];
			}
			if (cur.isEnd) {
				right = wordBreakHelper(root, s, i + 1, map);
				for (String t : right) {
					ret.add(cur.s + " " + t);
				}
			}
		}
		if (cur.isEnd) {
			ret.add(s.substring(b));
		}
		map.put(b, ret);
		return ret;
	}
	
	
	//188. 买卖股票的最佳时机 IV--超时
	public int maxProfit(int k, int[] prices) {
		int len = prices.length;
		int[] mem = new int[len], next;
		int x;
		for (int i = 0; i < k; i++) {
			next = new int[len];
			for (int j = 1; j < len; j++) {
				next[j] = Math.max(next[j - 1], mem[j]);
				for (int l = j; l >= 0; l--) {
					x = prices[j] - prices[l];
					next[j] = Math.max(next[j], Math.max(x, 0) + (l > 0 ? mem[l - 1] : 0));
				}
			}
			mem = next;
		}
		return mem[len - 1];
	}
	
	//1552. 两球之间的磁力
	public int maxDistance1(int[] position, int m) {
		Arrays.sort(position);
		int len = position.length;
		int l = 1, r = position[len - 1];
		int mid, c, b;
		while (l <= r) {
			mid = (l + r) >> 1;
			c = 1;
			b = position[0];
			for (int p : position) {
				if (p - b >= mid) {
					c++;
					b = p;
				}
			}
			if (c >= m) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return l;
	}
	
	public int maxDistance(int[] position, int m) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i : position) {
			set.add(i);
		}
		int l = 1, r = set.last(), mid;
		int min = set.first();
		Integer t;
		while (l <= r) {
			mid = (r - l) / 2 + l;
			int c = 0;
			t = min;
			while (t != null && c < m) {
				t = set.higher(t + mid);
				c++;
			}
			if (c < m) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
	
	public List<List<Integer>> findSubsequences(int[] nums) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		findSubsequencesHelper(nums, 0, ret, list);
		return ret;
	}
	
	private void findSubsequencesHelper(int[] nums, int idx, ArrayList<List<Integer>> ret,
		LinkedList<Integer> list) {
		if (idx == nums.length) {
			if (list.size() > 1) {
				ret.add(new ArrayList<>(list));
			}
			return;
		}
		if (list.isEmpty() || list.getLast() <= nums[idx]) {
			list.add(nums[idx]);
			findSubsequencesHelper(nums, idx + 1, ret, list);
			list.removeLast();
		}
		if (list.isEmpty() || list.getLast() != nums[idx]) {
			findSubsequencesHelper(nums, idx + 1, ret, list);
		}
	}
	
	
	//1559. 二维网格图中探测环
	public boolean containsCycle(char[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		boolean[][] visited = new boolean[high][len];
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					for (int[] x : ds_4) {
						int ni = i + x[0], nj = j + x[1];
						if (ni < 0 || nj < 0 || ni >= high || nj >= len
							|| grid[ni][nj] != grid[i][j]) {
							continue;
						}
						if (containsCycleHelper(visited, grid, ni, nj, grid[i][j], i,
							j)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean containsCycleHelper(boolean[][] visited, char[][] grid,
		int i, int j, char c, int fi, int fj) {
		visited[i][j] = true;
		int ni, nj, high = grid.length, len = grid[0].length;
		for (int[] x : ds_4) {
			ni = i + x[0];
			nj = j + x[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len || grid[ni][nj] != c) {
				continue;
			}
			if (visited[ni][nj]) {
				if (ni != fi || nj != fj) {
					return true;
				}
			} else if (containsCycleHelper(visited, grid, ni, nj, c, i, j)) {
				return true;
			}
		}
		return false;
	}
	
	
	//1556. 千位分隔数
	public String thousandSeparator(int n) {
		if (n < 1000) {
			return n + "";
		}
		StringBuffer buffer = new StringBuffer();
		while (n > 0) {
			for (int i = 0; i < 3 && n > 0; i++) {
				buffer.insert(0, n % 10);
				n /= 10;
			}
			if (n > 0) {
				buffer.insert(0, '.');
			}
		}
		return buffer.toString();
	}
	
	//1560. 圆形赛道上经过次数最多的扇区
	public List<Integer> mostVisited(int n, int[] rounds) {
		ArrayList<Integer> ret = new ArrayList<>();
		int begin = rounds[0];
		int end = rounds[rounds.length - 1];
		if (begin <= end) {
			for (int i = begin; i <= end; i++) {
				ret.add(i);
			}
		} else {
			for (int i = 1; i <= end; i++) {
				ret.add(i);
			}
			for (int i = begin; i <= n; i++) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	//1562. 查找大小为 M 的最新分组
	public int findLatestStep(int[] arr, int m) {
		int len = arr.length;
		int[] meml = new int[len + 2];
		int[] memr = new int[len + 2];
		for (int i = 0; i <= len + 1; i++) {
			meml[i] = i;
			memr[i] = i;
		}
		int ret = 0, l, r;
		HashSet<Integer> set = new HashSet<>(), removed = new HashSet<>();
		for (int i = 0; i < len; i++) {
			l = arr[i] - 1;
			r = arr[i] + 1;
			while (meml[l] != l) {
				l = meml[l];
			}
			meml[arr[i]] = l;
			while (memr[r] != r) {
				r = memr[r];
			}
			memr[arr[i]] = r;
			for (Integer x : set) {
				if (x >= l && x < r) {
					removed.add(x);
				}
			}
			set.removeAll(removed);
			if (r - l - 1 == m) {
				set.add(l);
			}
			if (set.size() > 0) {
				ret = i;
			}
		}
		return ret + 1;
	}
	
	//5481. 得到目标数组的最少函数调用次数
	public int minOperations(int[] nums) {
		int ret = 0;
		int len = nums.length;
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < len; i++) {
				if ((nums[i] & 1) > 0) {
					ret++;
					nums[i]--;
				}
			}
			for (int i = 0; i < len; i++) {
				if (nums[i] > 0) {
					nums[i] >>= 1;
					flag = true;
				}
			}
			if (flag) {
				ret++;
			}
		}
		return ret;
	}
	
	public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
		int[] mem = new int[n];
		for (int i = 0; i < n; i++) {
			mem[i] = i;
		}
		int f, t;
		for (List<Integer> edge : edges) {
			f = edge.get(0);
			t = edge.get(1);
			while (mem[f] != f) {
				f = mem[f];
			}
			mem[t] = f;
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (mem[i] == i) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	public int maxCoins(int[] piles) {
		Arrays.sort(piles);
		int count = piles.length / 3;
		int ret = 0;
		for (int i = piles.length - 2; count > 0; i -= 2, count--) {
			ret += piles[i];
		}
		return ret;
	}
	
	//111. 二叉树的最小深度
	public int minDepth(TreeNode root) {
		return minDepthHelper(root, 0);
	}
	
	private int minDepthHelper(TreeNode root, int i) {
		if (root == null) {
			return i;
		}
		if (root.left == null && root.right == null) {
			return i + 1;
		} else if (root.left == null) {
			return minDepthHelper(root.right, i + 1);
		} else if (root.right == null) {
			return minDepthHelper(root.left, i + 1);
		}
		return Math.min(minDepthHelper(root.left, i + 1), minDepthHelper(root.right, i + 1));
	}
	
	//1539. 第 k 个缺失的正整数
	public int findKthPositive(int[] arr, int k) {
		int i = 1, idx = 0;
		while (true) {
			int len = arr.length;
			if (idx < len && arr[idx] == i) {
				idx++;
			} else {
				k--;
				if (k == 0) {
					return i;
				}
			}
		}
	}
	
	//647. 回文子串
	public int countSubstrings(String s) {
		int len = s.length(), ret = 0;
		for (int i = 0; i < len; i++) {
			ret += countSubstringsHelper(s, i, i) + countSubstringsHelper(s, i, i + 1);
		}
		return ret;
	}
	
	private int countSubstringsHelper(String s, int l, int r) {
		int len = s.length(), ret = 0;
		while (l >= 0 && r < len) {
			if (s.charAt(l) == s.charAt(r)) {
				ret++;
			} else {
				break;
			}
			l--;
			r++;
		}
		return ret;
	}
	
	//1210. 穿过迷宫的最少移动次数
	public int minimumMoves(int[][] grid) {
		HashSet<Integer> cur = new HashSet<>(), next;
		int x1, y1, x2, y2;
		int high = grid.length;
		int ret = 0;
		cur.add(1);
		int target = (high - 1) * 1000000 + (high - 2) * 10000 + (high - 1) * 100 + high - 1;
		while (!cur.isEmpty()) {
			next = new HashSet<>();
			ret++;
			for (Integer t : cur) {
				y2 = t % 100;
				t /= 100;
				x2 = t % 100;
				t /= 100;
				y1 = t % 100;
				t /= 100;
				x1 = t;
				if (x1 == x2) {
					if (y2 + 1 < high && grid[x2][y2 + 1] == 0) {
						next.add(x2 * 1000000 + y2 * 10000 + x2 * 100 + y2 + 1);
					}
					if (x1 + 1 < high && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0) {
						next.add((x1 + 1) * 1000000 + y1 * 10000 + (x2 + 1) * 100 + y2);
						next.add(x1 * 1000000 + y1 * 10000 + (x1 + 1) * 100 + y1);
					}
				} else {
					if (x2 + 1 < high && grid[x2 + 1][y2] == 0) {
						next.add(x2 * 1000000 + y2 * 10000 + (x2 + 1) * 100 + y2);
					}
					if (y1 + 1 < high && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
						next.add(x1 * 1000000 + (y1 + 1) * 10000 + x2 * 100 + y2 + 1);
						next.add(x1 * 1000000 + y1 * 10000 + x1 * 100 + y2 + 1);
					}
				}
				
			}
			if (next.contains(target)) {
				return ret;
			}
			cur = next;
		}
		return -1;
	}
	
	//1550. 存在连续三个奇数的数组
	public boolean threeConsecutiveOdds(int[] arr) {
		int counter = 0;
		for (int i : arr) {
			if ((i & 1) == 1) {
				counter++;
				if (counter == 3) {
					return true;
				}
			} else {
				counter = 0;
			}
		}
		return false;
	}
	
	//110. 平衡二叉树
	boolean isBalanced = true;
	
	public boolean isBalanced(TreeNode root) {
		int x = isBalancedHelper(root);
		return isBalanced;
	}
	
	private int isBalancedHelper(TreeNode root) {
		if (isBalanced) {
			if (root == null) {
				return 0;
			}
			int left = isBalancedHelper(root.left);
			int right = isBalancedHelper(root.right);
			if (Math.abs(left - right) < 2) {
				return Math.max(left, right) + 1;
			} else {
				isBalanced = false;
			}
		}
		return -1;
	}
	
	//1553. 吃掉 N 个橘子的最少天数
	public int minDays(int n) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		return minDaysHelper(map, n);
	}
	
	private int minDaysHelper(HashMap<Integer, Integer> map, int n) {
		Integer ret = map.get(n);
		if (ret != null) {
			return ret;
		}
		int m2 = n % 2;
		int m3 = n % 3;
		ret = 1 + Math
			.min(minDaysHelper(map, (n - m2) / 2) + m2, minDaysHelper(map, (n - m3) / 3) + m3);
		map.put(n, ret);
		return ret;
	}
	
	///5488. 使数组中所有元素相等的最小操作数
	public int minOperations(int n) {
		
		int t = n / 2, x;
		if ((n & 1) == 0) {
			x = 2 + ((n - 1) / 2) * 2;
		} else {
			x = 2 + n;
		}
		return x * t / 2;
	}
	
	public boolean isSolvable(String[] words, String result) {
		boolean[] first = new boolean[26];
		boolean[] used = new boolean[10];
		int max = 0;
		for (String word : words) {
			max = Math.max(max, word.length());
			first[word.charAt(0) - 'A'] = true;
		}
		if (max > result.length()) {
			return false;
		}
		int[] mem = new int[26];
		Arrays.fill(mem, -1);
		return isSolvableHelper(1, words, result, first, used, mem, 0, 0);
	}
	
	private boolean isSolvableHelper(int t, String[] words, String result, boolean[] first,
		boolean[] used, int[] mem, int sum, int idx) {
		if (t > result.length()) {
			return sum == 0;
		}
		while (idx < words.length) {
			int len = words[idx].length();
			if (len >= t) {
				int x = words[idx].charAt(len - t) - 'A';
				int c = mem[x];
				if (c >= 0) {
					sum += c;
				} else {
					for (int i = mem[x] + 1; i < 10; i++) {
						if ((i == 0 && first[x]) || used[i]) {
							continue;
						}
						mem[x] = i;
						used[i] = true;
						if (isSolvableHelper(t, words, result, first, used, mem, sum + i,
							idx + 1)) {
							return true;
						}
						used[i] = false;
					}
					mem[x] = -1;
					return false;
				}
			}
			idx++;
		}
		int num = sum % 10;
		int len = result.length();
		int x = result.charAt(len - t) - 'A';
		if (mem[x] == -1) {
			if (used[num]) {
				return false;
			}
			used[num] = true;
			mem[x] = num;
			if (isSolvableHelper(t + 1, words, result, first, used, mem, sum / 10, 0)) {
				return true;
			}
			used[num] = false;
			mem[x] = -1;
			return false;
		} else if (mem[x] != num) {
			return false;
		}
		return isSolvableHelper(t + 1, words, result, first, used, mem, sum / 10, 0);
	}
	
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode f = head.next.next, s = head.next;
		while (f.val != s.val) {
			if (f.next == null) {
				return null;
			}
			f = f.next.next;
			s = s.next;
		}
		f = head;
		while (f.val != s.val) {
			f = f.next;
			s = s.next;
		}
		return s;
	}
	
	//面试题 08.13. 堆箱子
	public int pileBox(int[][] box) {
		Arrays.sort(box, (a, b) -> (a[2] - b[2]));
		int len = box.length;
		int[] mem = new int[len];
		mem[0] = box[0][2];
		int h = box[0][2], i = 1;
		while (box[i][2] == h) {
			mem[i] = h;
			i++;
		}
		int l = i - 1, ret = 0;
		for (; i < len; i++) {
			if (box[i][2] != box[i - 1][2]) {
				l = i - 1;
			}
			for (int j = l; j >= 0; j--) {
				if (mem[i] < mem[j] && box[i][0] > box[j][0] && box[i][1] > box[j][1]) {
					mem[i] = mem[j];
				}
			}
			mem[i] += box[i][2];
			ret = Math.max(ret, mem[i]);
		}
		return ret;
	}
	
	//1542. 找出最长的超赞子字符串
	public int longestAwesome(String s) {
		int ret = 0;
		int[] mem = new int[1 << 11];
		Arrays.fill(mem, -2);
		mem[0] = -1;
		int cur = 0;
		int len = s.length();
		int x, b;
		for (int i = 0; i < len; i++) {
			x = 1 << (s.charAt(i) - '0');
			if ((cur & x) > 0) {
				cur -= x;
			} else {
				cur += x;
			}
			for (int j = 0; j < 10; j++) {
				if ((cur & (1 << j)) > 0) {
					b = cur - (1 << j);
				} else {
					b = cur + (1 << j);
				}
				if (mem[b] >= -1) {
					ret = Math.max(ret, i - mem[b]);
				}
			}
			if (mem[cur] >= -1) {
				ret = Math.max(ret, i - mem[cur]);
			} else {
				mem[cur] = i;
			}
		}
		return ret;
	}
	
	//1540. K 次操作转变字符串
	public boolean canConvertString(String s, String t, int k) {
		int[] mem = new int[26];
		Arrays.fill(mem, k / 26);
		for (int i = k % 26; i > 0; i--) {
			mem[i]++;
		}
		int len = s.length();
		char l, r;
		int x;
		for (int i = 0; i < len; i++) {
			l = s.charAt(i);
			r = t.charAt(i);
			if (l != r) {
				x = r - l;
				if (x < 0) {
					x += 26;
				}
				if (mem[x] == 0) {
					return false;
				}
				mem[x]--;
			}
		}
		return true;
	}
	
	//1544. 整理字符串
	public String makeGood(String s) {
		int len = s.length();
		char[] ret = new char[len];
		int idx = 0;
		char c;
		int x = 'a' - 'A';
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			while (idx > 0 && (c + x == ret[idx - 1] || c - x == ret[idx - 1])) {
				idx--;
			}
			ret[idx++] = c;
		}
		return new String(ret, 0, idx);
	}
	
	public char findKthBit(int n, int k) {
		int[] mem = new int[n + 1];
		mem[1] = 1;
		for (int i = 2; i <= n; i++) {
			mem[i] = mem[i - 1] * 2 + 1;
		}
		int ret = findKthBitHelper(mem, n, k);
		return (char) ('0' + ret);
	}
	
	private int findKthBitHelper(int[] mem, int n, int k) {
		if (n == 1 || k == 1) {
			return 0;
		}
		int t = (mem[n] + 1) / 2;
		if (k == t) {
			return 1;
		} else if (k < t) {
			return findKthBitHelper(mem, n - 1, k);
		} else {
			int x = k - t;
			return 1 ^ findKthBitHelper(mem, n - 1, mem[n - 1] - x + 1);
		}
	}
	
	//1546. 和为目标值的最大数目不重叠非空子数组数目
	public int maxNonOverlapping(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int len = nums.length;
		int[] mem = new int[len];
		int sum = 0;
		Integer idx;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			idx = map.get(sum - target);
			if (idx != null) {
				mem[i] = Math.max(i > 0 ? mem[i - 1] : 0, 1 + (idx >= 0 ? mem[idx] : 0));
			} else {
				mem[i] = i > 0 ? mem[i - 1] : 0;
			}
			map.put(sum, i);
		}
		return mem[len - 1];
	}
	
	//1547. 切棍子的最小成本
	public int minCost(int n, int[] cuts) {
		int len = cuts.length;
		Arrays.sort(cuts);
		int[][] mem = new int[len + 2][len + 2];
		for (int i = 2; i <= len + 1; i++) {
			for (int j = 0; j + i <= len + 1; j++) {
				mem[j][j + i] = Integer.MAX_VALUE;
				for (int k = 1; k < i; k++) {
					mem[j][j + i] = Math.min(mem[j][j + i],
						(j + i - 1 == len ? n : cuts[j + i - 1]) - (j > 0 ? cuts[j - 1] : 0)
							+ mem[j][j + k] + mem[j + k][j + i]);
				}
			}
		}
		return mem[0][len + 1];
	}
	
	//782. 变为棋盘
	public int movesToChessboard(int[][] board) {
		int len = board.length;
		int[] mem = new int[len];
		int mask = (1 << len) - 1;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				mem[i] <<= 1;
				mem[i] += board[i][j];
			}
		}
		int row1 = movesToChessboardHelper(1, Arrays.copyOf(board[0], len), 1);
		int row0 = movesToChessboardHelper(0, Arrays.copyOf(board[0], len), 1);
		int row = Math.min(row0, row1);
		if (row == Integer.MAX_VALUE) {
			return -1;
		}
		int column0 = movesToChessboardHelper(mem[0], Arrays.copyOf(mem, len), mask);
		int column1 = movesToChessboardHelper(mask - mem[0], Arrays.copyOf(mem, len), mask);
		int column = Math.min(column0, column1);
		if (column == Integer.MAX_VALUE) {
			return -1;
		}
		return row + column;
	}
	
	private int movesToChessboardHelper(int cur, int[] arr, int mask) {
		int ret = 0;
		int len = arr.length;
		for (int i = 0, j; i < len; i++) {
			if (arr[i] != cur) {
				ret++;
				for (j = i + 1; j < len; j += 2) {
					if (arr[j] == cur) {
						arr[j] = mask ^ cur;
						break;
					}
				}
				if (j >= len) {
					return Integer.MAX_VALUE;
				}
			}
			cur ^= mask;
		}
		return ret;
	}
	
	//696. 计数二进制子串
	public int countBinarySubstrings(String s) {
		int cur = 0, next = 0;
		int len = s.length();
		char c = s.charAt(0);
		int i = 0;
		for (; i < len; i++) {
			if (c == s.charAt(i)) {
				cur++;
			} else {
				break;
			}
		}
		c = s.charAt(i);
		int ret = 0;
		for (; i < len; i++) {
			if (c == s.charAt(i)) {
				next++;
			} else {
				ret += Math.min(cur, next);
				cur = next;
				c = s.charAt(i);
				next = 1;
			}
		}
		return ret;
	}
	
	//5470. 平衡括号字符串的最少插入次数
	public int minInsertions(String s) {
		int len = s.length();
		int r = 0, rc = 0, ret = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (s.charAt(i) == ')') {
				r++;
			} else {
				if ((r & 1) == 1) {
					rc += r / 2;
					ret++;
					r = 0;
				} else {
					rc += r / 2;
					r = 0;
					if (rc > 0) {
						rc--;
					} else {
						ret += 2;
					}
				}
			}
		}
		ret += rc;
		if (r > 0) {
			ret += r / 2;
		}
		if ((r & 1) == 1) {
			ret += 2;
		}
		return ret;
	}
	
	
	//699. 掉落的方块
	public List<Integer> fallingSquares(int[][] positions) {
		int len = positions.length;
		int[] ret = new int[len];
		int max = 0, l, r;
		for (int i = 0; i < len; i++) {
			max = 0;
			l = positions[i][0];
			r = positions[i][0] + positions[i][1];
			for (int j = 0; j < i; j++) {
				if (ret[j] < max || r <= positions[j][0]
					|| l >= positions[j][0] + positions[j][1]) {
					continue;
				} else {
					max = ret[j];
				}
				
			}
			ret[i] = max + positions[i][1];
		}
		ArrayList<Integer> list = new ArrayList<>();
		max = 0;
		for (int i : ret) {
			max = Math.max(max, i);
			list.add(max);
		}
		return list;
	}
	
	//552. 学生出勤记录 II
	public int checkRecord(int n) {
		long[][] mem = new long[n][6];
		//0:00//1:01//2:02//3:10//4:11//5:12
		mem[0] = new long[]{1, 1, 0, 1, 0, 0};
		long[] c, t;
		long mod = 1000000007;
		for (int i = 1; i < n; i++) {
			c = mem[i];
			t = mem[i - 1];
			c[0] = t[0] + t[1] + t[2];
			c[0] %= mod;
			c[1] = t[0];
			c[2] = t[1];
			c[3] = t[0] + t[1] + t[2] + t[3] + t[4] + t[5];
			c[3] %= mod;
			c[4] = t[3];
			c[5] = t[4];
		}
		c = mem[n - 1];
		long ret = c[0] + c[1] + c[2] + c[3] + c[4] + c[5];
		ret %= mod;
		return (int) ret;
	}
	
	public boolean judgePoint24(int[] nums) {
		Set<Double>[] sets = new Set[1 << 5];
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				HashSet<Double> set = new HashSet<>();
				set.add((double) nums[i] + nums[j]);
				set.add((double) nums[i] * nums[j]);
				set.add((double) nums[i] - nums[j]);
				set.add((double) nums[j] - nums[i]);
				set.add((double) nums[i] / nums[j]);
				set.add((double) nums[j] / nums[i]);
				sets[(1 << i) + (1 << j)] = set;
			}
		}
		int mask = (1 << 4) - 1;
		int i = 0;
		for (int j = i + 1; j < 4; j++) {
			Set<Double> a = sets[(1 << i) + (1 << j)];
			Set<Double> b = sets[mask ^ ((1 << i) + (1 << j))];
			for (Double x : a) {
				for (Double y : b) {
					if (
						judgePoint24Helper(x + y) ||
							judgePoint24Helper(x - y) ||
							judgePoint24Helper(y - x) ||
							judgePoint24Helper(x * y) ||
							y != 0 && judgePoint24Helper(x / y) ||
							x != 0 && judgePoint24Helper(y / x)
					) {
						return true;
					}
				}
			}
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		
		for (i = 0; i < 4; i++) {
			sum -= nums[i];
			for (int j = i + 1; j < 4; j++) {
				sum -= nums[j];
				for (int k = j + 1; k < 4; k++) {
					sum -= nums[k];
					HashSet<Double> set = new HashSet<>();
					for (Double x : sets[(1 << i) + (1 << j)]) {
						set.add(x + nums[k]);
						set.add(x * nums[k]);
						set.add(x - nums[k]);
						set.add(-x + nums[k]);
						set.add(x / nums[k]);
						if (x != 0) {
							set.add(nums[k] / x);
						}
					}
					for (Double x : sets[(1 << i) + (1 << k)]) {
						set.add(x + nums[j]);
						set.add(x * nums[j]);
						set.add(x - nums[j]);
						set.add(-x + nums[j]);
						set.add(x / nums[j]);
						if (x != 0) {
							set.add(nums[j] / x);
						}
					}
					for (Double x : sets[(1 << k) + (1 << j)]) {
						set.add(x + nums[i]);
						set.add(x * nums[i]);
						set.add(x - nums[i]);
						set.add(-x + nums[i]);
						set.add(x / nums[i]);
						if (x != 0) {
							set.add(nums[i] / x);
						}
					}
					double x = (double) sum;
					for (Double y : set) {
						System.out.println(x + " " + y + " " + x / y);
						if (
							judgePoint24Helper(x + y) ||
								judgePoint24Helper(x - y) ||
								judgePoint24Helper(y - x) ||
								judgePoint24Helper(x * y) ||
								y != 0 && judgePoint24Helper(x / y) ||
								judgePoint24Helper(y / x)
						) {
							return true;
						}
						
					}
					sum += nums[k];
				}
				sum += nums[j];
			}
			sum += nums[i];
		}
		return false;
	}
	
	private boolean judgePoint24Helper(Double x) {
		return Math.abs(x - 24) < 0.00001;
	}
	
	//522. 最长特殊序列 II
	public int findLUSlength(String[] strs) {
		Arrays.sort(strs,
			(a, b) -> (b.length() == a.length() ? b.compareTo(a) : b.length() - a.length()));
		HashSet<String> set = new HashSet<>();
		int cur = strs[0].length();
		int len = strs.length;
		set.add(strs[0]);
		String s;
		int i = 0;
		int t;
		for (; i < len; i++) {
			s = strs[i];
			t = i;
			while (t + 1 < len && s.equals(strs[t + 1])) {
				t++;
			}
			if (i != t) {
				set.add(s);
				i = t;
			} else {
				return cur;
			}
			if (i == len - 1 || strs[i + 1].length() < cur) {
				break;
			}
		}
		i++;
		for (; i < len; i++) {
			s = strs[i];
			t = i;
			while (t + 1 < len && s.equals(strs[t + 1])) {
				t++;
			}
			if (t != i || !findLUSlengthHelper(set, s)) {
				set.add(s);
			} else if (findLUSlengthHelper(set, s)) {
				return s.length();
			}
		}
		return -1;
	}
	
	private boolean findLUSlengthHelper(HashSet<String> set, String s) {
		int sl = s.length();
		for (String cur : set) {
			int a = 0, b = 0, cl = cur.length();
			while (a < cl && b < sl) {
				if (s.charAt(b) == cur.charAt(a)) {
					a++;
					b++;
				} else {
					a++;
				}
			}
			if (b == sl) {
				return false;
			}
		}
		return true;
	}
	
	//372. 超级次方
	public int superPow(int a, int[] b) {
		return superPowHelper(a, b, b.length - 1);
	}
	
	private int superPowHelper(int cur, int[] b, int idx) {
		int l = 1;
		long t = 1;
		for (int i = 1; i < 11; i++) {
			t *= cur;
			t %= 1337;
			if (i == b[idx]) {
				l = (int) t;
			}
		}
		if (idx == 0) {
			return l;
		}
		return l * superPowHelper((int) t, b, idx - 1);
	}
	
	//1411. 给 N x 3 网格图涂色的方案数
	public int numOfWays(int n) {
		long[] cur = new long[12], next;
		Arrays.fill(cur, 1);
		int[][] map = {
			{4, 5, 7, 8, 9}, {4, 6, 7, 8}, {4, 5, 8, 9, 11}, {5, 9, 11, 10},
			{0, 1, 2, 10, 11}, {0, 2, 3, 10}, {1, 8, 9, 11}, {0, 1, 9, 10, 11},
			{0, 1, 2, 6}, {0, 2, 3, 6, 7}, {3, 4, 5, 7}, {2, 3, 4, 6, 7}};
		long x, mod = 1000000007;
		while (n > 0) {
			next = new long[12];
			for (int i = 0; i < 12; i++) {
				x = cur[i];
				for (int t : map[i]) {
					next[t] += x;
					next[t] %= mod;
				}
			}
			cur = next;
			n--;
		}
		long ret = 0;
		for (long l : cur) {
			ret += l;
			ret %= mod;
		}
		return (int) ret;
	}
	
	//1514. 概率最大的路径
	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
		double[] mem = new double[n];
		mem[start] = 1;
		HashMap<Integer, Double>[] maps = new HashMap[n];
		for (int i = 0; i < n; i++) {
			maps[i] = new HashMap<>();
		}
		int len = edges.length;
		for (int i = 0; i < len; i++) {
			maps[edges[i][0]].put(edges[i][1], succProb[i]);
			maps[edges[i][1]].put(edges[i][0], succProb[i]);
		}
		LinkedList<Integer> list = new LinkedList<>();
		list.add(start);
		double ret = 0, cr, nr;
		int cur, next;
		while (!list.isEmpty()) {
			cur = list.removeFirst();
			cr = mem[cur];
			for (Entry<Integer, Double> entry : maps[cur].entrySet()) {
				next = entry.getKey();
				nr = cr * entry.getValue();
				if (nr > ret && nr > mem[next]) {
					mem[next] = nr;
					list.add(next);
					if (next == end) {
						ret = nr;
					}
				}
			}
		}
		return ret;
	}
	
	//730. 统计不同回文子序列
	public int countPalindromicSubsequences(String S) {
		int len = S.length();
		char l, r;
		int[][] dp = new int[len][2];
		int a = -1, b = -1, c = -1, d = -1;
		for (int i = 0; i < len; i++) {
			switch (S.charAt(i)) {
				case 'a':
					dp[i][0] = a;
					a = i;
					break;
				case 'b':
					dp[i][0] = b;
					b = i;
					break;
				case 'c':
					dp[i][0] = c;
					c = i;
					break;
				case 'd':
					dp[i][0] = d;
					d = i;
					break;
			}
		}
		a = len;
		b = len;
		c = len;
		d = len;
		for (int i = len - 1; i >= 0; i--) {
			switch (S.charAt(i)) {
				case 'a':
					dp[i][1] = a;
					a = i;
					break;
				case 'b':
					dp[i][1] = b;
					b = i;
					break;
				case 'c':
					dp[i][1] = c;
					c = i;
					break;
				case 'd':
					dp[i][1] = d;
					d = i;
					break;
			}
		}
		long[][] mem = new long[len][len];
		int mod = 1000000007;
		for (int i = len - 1; i >= 0; i--) {
			mem[i][i] = 1;
			l = S.charAt(i);
			for (int j = i + 1; j < len; j++) {
				r = S.charAt(j);
				if (l != r) {
					mem[i][j] = mem[i + 1][j] + mem[i][j - 1] - mem[i + 1][j - 1];
				} else {
					mem[i][j] = mem[i + 1][j - 1] * 2;
					if (dp[i][1] == dp[j][0]) {
						mem[i][j]++;
					} else if (dp[i][1] > dp[j][0]) {
						mem[i][j] += 2;
					} else {
						mem[i][j] -= mem[dp[i][1] + 1][dp[j][0] - 1];
					}
				}
				mem[i][j] %= mod;
			}
		}
		return (int) mem[0][len - 1];
	}
	
	//1359. 有效的快递序列数目
	public int countOrders(int n) {
		long ret = 1, mod = 1000000007;
		for (int i = 2; i <= n; i++) {
			ret = i * (2 * i - 1) * ret % mod;
		}
		return (int) ret;
	}
	
	//面试题 16.26. 计算器
	public int calculate(String s) {
		ArrayList<Integer> nums = new ArrayList<>();
		ArrayList<Character> ops = new ArrayList<>();
		int num = 0;
		char c;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				num *= 10;
				num += c - '0';
			} else if (c == '*' || c == '/' || c == '+' || c == '-') {
				nums.add(num);
				ops.add(c);
				num = 0;
			}
		}
		nums.add(num);
		Stack<Integer> stack = new Stack<>();
		stack.push(nums.get(0));
		for (int i = 1; i < nums.size(); i++) {
			c = ops.get(i - 1);
			num = nums.get(i);
			if (c == '+') {
				stack.push(num);
			} else if (c == '-') {
				stack.push(-num);
			} else if (c == '*') {
				stack.push(stack.pop() * num);
			} else {
				stack.push(stack.pop() / num);
			}
		}
		int ret = 0;
		while (!stack.isEmpty()) {
			ret += stack.pop();
		}
		return ret;
	}
	
	
	//1494. 并行课程 II
	public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
		List<Integer>[] mem = new List[n];
		int mask = 0;
		for (int i = 0; i < n; i++) {
			mem[i] = new ArrayList<>();
			mask <<= 1;
			mask++;
		}
		int[] count = new int[n];
		for (int[] dependency : dependencies) {
			mem[dependency[0] - 1].add(dependency[1] - 1);
			count[dependency[1] - 1]++;
		}
		int cur = 0;
		for (int i = 0; i < n; i++) {
			if (count[i] == 0) {
				cur |= (1 << i);
			}
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		return minNumberOfSemestersHelper(mem, map, k, mask, cur, count);
	}
	
	private int minNumberOfSemestersHelper(List<Integer>[] mem, HashMap<Integer, Integer> map,
		int k, int mask, int cur, int[] count) {
		if (map.containsKey(mask)) {
			return map.get(mask);
		} else if (mask == 0) {
			return 0;
		}
		int ret = Integer.MAX_VALUE, i, next;
		List<Integer> list = getCurStrategyList(cur, k);
		for (Integer t : list) {
			i = 0;
			next = 0;
			while ((1 << i) <= t) {
				if (((1 << i) & t) > 0) {
					for (Integer x : mem[i]) {
						count[x]--;
						if (count[x] == 0) {
							next |= (1 << x);
						}
					}
				}
				i++;
			}
			cur -= t;
			ret = Math
				.min(ret, 1 + minNumberOfSemestersHelper(mem, map, k, mask - t, cur + next, count));
			while (i >= 0) {
				if (((1 << i) & t) > 0) {
					for (Integer x : mem[i]) {
						count[x]++;
					}
				}
				i--;
			}
			cur += t;
		}
		map.put(mask, ret);
		return ret;
	}
	
	private List<Integer> getCurStrategyList(int cur, int k) {
		List<Integer>[] lists = new List[k + 1];
		lists[0] = Arrays.asList(0);
		int idx = 0, i = 0, t;
		while ((1 << i) <= cur) {
			if (((1 << i) & cur) > 0) {
				t = 1 << i;
				if (idx < k && lists[idx + 1] == null) {
					lists[++idx] = new ArrayList<>();
				}
				for (int j = Math.min(idx - 1, k - 1); j >= 0; j--) {
					for (Integer x : lists[j]) {
						lists[j + 1].add(x | t);
					}
				}
			}
			i++;
		}
		return lists[idx];
	}
	
	//5475. 统计好三元组
	public int countGoodTriplets(int[] arr, int a, int b, int c) {
		int len = arr.length, ret = 0;
		int il, ir, jl, jr, l, r;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (Math.abs(arr[i] - arr[j]) <= a) {
					il = arr[i] - c;
					ir = arr[i] + c;
					jl = arr[j] - b;
					jr = arr[j] + b;
					l = Math.max(il, jl);
					r = Math.min(ir, jr);
					if (l > r) {
						continue;
					}
					for (int k = j + 1; k < len; k++) {
						if (arr[k] >= l && arr[k] <= r) {
							ret++;
						}
					}
				}
			}
		}
		return ret;
	}
	
	//1536. 排布二进制网格的最少交换次数
	public int minSwaps(int[][] grid) {
		int len = grid.length;
		int[] mem = new int[len];
		for (int i = 0, j; i < len; i++) {
			j = grid[i].length - 1;
			while (j >= 0 && grid[i][j] == 0) {
				mem[i]++;
				j--;
			}
		}
		int ret = 0, need = len - 1, j;
		boolean[] used = new boolean[len];
		for (int i = 0; i < len; ) {
			if (used[i]) {
				i++;
				continue;
			} else if (used[i] || mem[i] >= need) {
				need--;
				i++;
				continue;
			}
			for (j = i + 1; j < len; j++) {
				if (used[j]) {
					continue;
				}
				ret++;
				if (mem[j] >= len - i - 1) {
					break;
				}
			}
			if (j == len) {
				return -1;
			} else {
				need--;
				used[j] = true;
			}
		}
		return ret;
	}
	
	//1537. 最大得分
	public int maxSum(int[] nums1, int[] nums2) {
		long a = 0, b = 0, ret = 0, mod = 1000000007;
		int i1 = 0, i2 = 0;
		int len1 = nums1.length;
		int len2 = nums2.length;
		while (i1 < len1 && i2 < len2) {
			if (nums1[i1] < nums2[i2]) {
				a += nums1[i1];
				i1++;
			} else if (nums1[i1] > nums2[i2]) {
				b += nums2[i2];
				i2++;
			} else {
				ret += Math.max(a, b);
				ret %= mod;
				a = nums1[i1];
				b = a;
				i1++;
				i2++;
			}
		}
		while (i1 < len1) {
			a += nums1[i1];
			i1++;
		}
		while (i2 < len2) {
			b += nums2[i2];
			i2++;
		}
		ret += Math.max(a, b);
		ret %= mod;
		
		return (int) ret;
	}
	
	public int getWinner(int[] arr, int k) {
		int idx = 0, count = 0;
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			if (arr[i] > arr[idx]) {
				if (count >= k) {
					return arr[idx];
				}
				idx = i;
				count = 1;
			} else {
				count++;
			}
		}
		return arr[idx];
	}
	
	//639. 解码方法 2
	public int numDecodings(String s) {
		int len = s.length(), mod = 1000000007;
		long[] mem = new long[len + 1];
		if (s.charAt(0) == '0') {
			return 0;
		} else if (s.charAt(0) == '*') {
			mem[1] = 9;
		} else {
			mem[1] = 1;
		}
		mem[0] = 1;
		Character b2 = null, b1 = s.charAt(0), c;
		for (int i = 2; i <= len; i++) {
			c = s.charAt(i - 1);
			if (c == '*') {
				if (b1 == '1') {
					mem[i] += 9 * mem[i - 1] + 9 * mem[i - 2];
				} else if (b1 == '2') {
					mem[i] += 9 * mem[i - 1] + 6 * mem[i - 2];
				} else if (b1 == '*') {
					mem[i] += 9 * mem[i - 1] + 15 * mem[i - 2];
				} else {
					mem[i] += 9 * mem[i - 1];
				}
			} else if (c == '0') {
				mem[i - 1] = 0;
				if (b1 == '*') {
					mem[i] += i < 2 ? 2 : 2 * mem[i - 2];
				} else if (b1 < '3') {
					mem[i] += i < 2 ? 1 : mem[i - 2];
				} else {
					return 0;
				}
			} else if (c < '7') {
				if (b1 == '*') {
					mem[i] += mem[i - 1] + 2 * mem[i - 2];
				} else if (b1 < '3') {
					mem[i] += mem[i - 1] + mem[i - 2];
				} else {
					mem[i] += mem[i - 1];
				}
			} else if (c > '6') {
				if (b1 == '*' || b1 == '1') {
					mem[i] += mem[i - 1] + mem[i - 2];
				} else {
					mem[i] += mem[i - 1];
				}
			}
			mem[i] %= mod;
			b1 = c;
		}
		return (int) mem[len];
	}
	
	//726. 原子的数量
	public String countOfAtoms(String formula) {
		TreeMap<String, Integer> map = countOfAtomsHelper(formula.toCharArray(), 0,
			formula.length() - 1);
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, Integer> entry : map.entrySet()) {
			buffer.append(entry.getKey());
			buffer.append(entry.getValue());
		}
		return buffer.toString();
	}
	
	private TreeMap<String, Integer> countOfAtomsHelper(char[] arr, int l, int r) {
		TreeMap<String, Integer> ret = new TreeMap<>(), cur = null;
		String atom = null;
		for (int i = l; i <= r; ) {
			if (arr[i] == '(') {
				int count = 1, nl = i + 1;
				i++;
				while (count > 0) {
					if (arr[i] == '(') {
						count++;
					} else if (arr[i] == ')') {
						count--;
					}
					i++;
				}
				cur = countOfAtomsHelper(arr, nl, i - 2);
			} else {
				int t = i;
				i++;
				while (i <= r && arr[i] >= 'a' && arr[i] <= 'z') {
					i++;
				}
				atom = new String(arr, t, i - t);
			}
			int count = 0;
			while (i <= r && arr[i] >= '0' && arr[i] <= '9') {
				count *= 10;
				count += arr[i] - '0';
				i++;
			}
			if (count == 0) {
				count++;
			}
			if (atom != null) {
				ret.put(atom, ret.getOrDefault(atom, 0) + count);
				atom = null;
			} else {
				for (Entry<String, Integer> entry : cur.entrySet()) {
					String key = entry.getKey();
					ret.put(key, ret.getOrDefault(key, 0) + entry.getValue() * count);
				}
				cur.clear();
			}
		}
		return ret;
	}
	
	//1410. HTML 实体解析器
	private class CharTireNode {
		
		Character c;
		boolean end = false;
		int len;
		CharTireNode[] child = new CharTireNode[26];
	}
	
	public String entityParser(String text) {
		CharTireNode root = new CharTireNode(), se;
		insertTree(root, '"', "quot", 0);
		insertTree(root, '\'', "apos", 0);
		insertTree(root, '&', "amp", 0);
		insertTree(root, '/', "frasl", 0);
		insertTree(root, '<', "lt", 0);
		insertTree(root, '>', "gt", 0);
		StringBuffer ret = new StringBuffer();
		int len = text.length();
		
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			if (c == '&') {
				se = searchTree(text, i + 1, root);
				if (se != null) {
					ret.append(se.c);
					i += se.len + 1;
					continue;
				}
			}
			ret.append(c);
		}
		return ret.toString();
	}
	
	private CharTireNode searchTree(String text, int i, CharTireNode root) {
		int len = text.length();
		if (i == len) {
			return null;
		}
		int c = text.charAt(i) - 'a';
		if (c > 25 || c < 0 || root.child[c] == null) {
			return null;
		}
		if (root.child[c].end) {
			if (i + 1 < len && text.charAt(i + 1) == ';') {
				return root.child[c];
			}
		}
		return searchTree(text, i + 1, root.child[c]);
	}
	
	private void insertTree(CharTireNode root, char c, String s, int i) {
		int t = s.charAt(i) - 'a';
		if (root.child[t] == null) {
			root.child[t] = new CharTireNode();
		}
		if (i == s.length() - 1) {
			root.child[t].c = c;
			root.child[t].len = i;
			root.child[t].end = true;
		} else {
			insertTree(root.child[t], c, s, i + 1);
		}
	}
	
	public double[] twoSum(int n) {
		double rate = (double) 1 / 6;
		int len = 6 * n + 1;
		double[] cur = new double[len], next;
		for (int i = 1; i <= 6; i++) {
			cur[i] = rate;
		}
		int l = 1, r = 6;
		while (n > 1) {
			next = new double[len];
			for (int i = 1; i <= 6; i++) {
				for (int j = l; j <= r; j++) {
					next[j + i] += cur[j] * rate;
				}
			}
			l++;
			n--;
			r += 6;
			cur = next;
		}
		return Arrays.copyOfRange(cur, l, r);
	}
	
	//790. 多米诺和托米诺平铺
	public int numTilings(int N) {
		long[][] mem = new long[N + 1][N + 1];
		mem[0][0] = 1;
		int mod = 1000000007;
		for (int i = 0; i <= N; i++) {
			if (i + 2 <= N) {
				//一横放
				mem[i][i + 2] += mem[i][i];
				mem[i][i + 2] %= mod;
				mem[i + 2][i + 2] += mem[i + 2][i];
				mem[i + 2][i + 2] %= mod;
				mem[i + 1][i + 2] += mem[i + 1][i];
				mem[i + 1][i + 2] %= mod;
				mem[i + 2][i] += mem[i][i];
				mem[i + 2][i] %= mod;
				mem[i + 2][i + 2] += mem[i][i + 2];
				mem[i + 2][i + 2] %= mod;
				mem[i + 2][i + 1] += mem[i][i + 1];
				mem[i + 2][i + 1] %= mod;
				//L
				mem[i + 1][i + 2] += mem[i][i];
				mem[i + 1][i + 2] %= mod;
				//倒L
				mem[i + 2][i + 1] += mem[i][i];
				mem[i + 2][i + 1] %= mod;
			}
			if (i + 1 <= N) {
				//一竖放
				mem[i + 1][i + 1] += mem[i][i];
				mem[i + 1][i + 1] %= mod;
				if (i > 1) {
					//反L
					mem[i + 1][i + 1] += mem[i][i - 1];
					mem[i + 1][i + 1] %= mod;
					//倒反L
					mem[i + 1][i + 1] += mem[i - 1][i];
					mem[i + 1][i + 1] %= mod;
				}
			}
		}
		return (int) mem[N][N];
	}
	
	//879. 盈利计划
	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		long[][] cur = new long[G + 1][P + 1], next = new long[G + 1][P + 1];
		cur[0][0] = 1;
		int len = group.length;
		long ret = 0, mod = 1000000007;
		int g, p;
		for (int i = 0; i < len; i++) {
			g = group[i];
			p = profit[i];
			next = new long[G + 1][P + 1];
			for (int j = 0; j <= G; j++) {
				for (int k = 0; k <= P; k++) {
					if (j + g <= G) {
						if (k + p < P) {
							next[j + g][k + p] += cur[j][k];
							next[j + g][k + p] %= mod;
						} else {
							next[j + g][P] += cur[j][k];
							next[j + g][P] %= mod;
							ret += cur[j][k];
							ret %= mod;
						}
					}
					next[j][k] += cur[j][k];
				}
			}
			cur = next;
		}
		return (int) ret;
	}
	
	//831. 隐藏个人信息
	public String maskPII(String S) {
		int x = S.indexOf('@');
		StringBuffer buffer = new StringBuffer();
		if (x >= 0) {
			char c = S.charAt(0);
			buffer.append(c >= 'A' && c <= 'Z' ? c + 'a' - 'A' : c);
			buffer.append("*****");
			c = S.charAt(x - 1);
			buffer.append(c >= 'A' && c <= 'Z' ? c + 'a' - 'A' : c);
			buffer.append(S.substring(x).toLowerCase());
		} else {
			char[] chars = new char[4];
			int idx = 3, count = 0;
			for (int i = S.length() - 1; i >= 0; i--) {
				char c = S.charAt(i);
				if (c >= '0' && c <= '9') {
					if (idx >= 0) {
						chars[idx] = c;
						idx--;
					}
					count++;
				}
			}
			if (count < 12) {
				buffer.append("***-***-");
			} else {
				buffer.append("+**-***-***-");
			}
			buffer.append(new String(chars));
		}
		return buffer.toString();
	}
	
	//871. 最低加油次数
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		int len = stations.length;
		int[] cur = new int[len + 1], t;
		int[] next = new int[len + 1];
		cur[0] = startFuel;
		int dis = 0, fuel, l = 0, r = 0;
		for (int i = 0; i < len && l <= r; i++) {
			dis = stations[i][0] - dis;
			fuel = stations[i][1];
			Arrays.fill(next, -1);
			for (int j = l; j <= r; j++) {
				if (cur[j] >= dis) {
					next[j] = Math.max(next[j], cur[j] - dis);
					next[j + 1] = Math.max(next[j + 1], cur[j] - dis + fuel);
				} else {
					next[j] = -1;
					l++;
				}
			}
			if (next[r + 1] > 0) {
				r++;
			}
			t = cur;
			cur = next;
			next = t;
			dis = stations[i][0];
		}
		dis = target - dis;
		for (int i = l; i <= r; i++) {
			if (cur[i] >= dis) {
				return i;
			}
		}
		return -1;
	}
	
	//891. 子序列宽度之和--超时mmp
	public int sumSubseqWidths1(int[] A) {
		Arrays.sort(A);
		int len = A.length;
		long[] count = new long[len + 1];
		count[1] = 1;
		long[] mem = new long[A[len - 1] + 1];
		int l = A[0], cur = l, cnt = 0, mod = 1000000007, idx = 1;
		long ret = 0, t;
		for (int i = 0; i <= len; i++) {
			if (i < len && A[i] == cur) {
				cnt++;
			} else {
				while (cnt > idx) {
					count[idx + 1] = 2 * count[idx] + 1;
					count[idx + 1] %= mod;
					idx++;
				}
				t = count[cnt];
				for (int j = l; j < cur; j++) {
					if (mem[j] > 0) {
						ret += mem[j] * t * (cur - j);
						ret %= mod;
						mem[j] += mem[j] * t;
						mem[j] %= mod;
					}
				}
				mem[cur] = t;
				if (i < len) {
					cur = A[i];
				}
				cnt = 1;
			}
		}
		return (int) ret;
	}
	
	public int sumSubseqWidths(int[] A) {
		Arrays.sort(A);
		int cur = A[0], count = 0, mod = 1000000007;
		HashMap<Integer, Long> map = new HashMap<>();
		long ret = 0;
		int len = A.length;
		int min;
		long total, t;
		long[] mem = new long[A.length + 1];
		mem[1] = 1;
		int idx = 1;
		for (int i = 0; i <= len; i++) {
			if (i < len && A[i] == cur) {
				count++;
			} else {
				while (count > idx) {
					mem[idx + 1] = 2 * mem[idx] + 1;
					idx++;
				}
				total = mem[count];
				for (Entry<Integer, Long> entry : map.entrySet()) {
					min = entry.getKey();
					ret += entry.getValue() * total * (cur - min);
					entry.setValue(entry.getValue() * (total + 1) % mod);
					ret %= mod;
				}
				map.put(cur, total);
				if (i < len) {
					cur = A[i];
					count = 1;
				}
			}
		}
		return (int) ret;
	}
	
	public int minimalSteps(String[] maze) {
		/*if(maze[0].equals("...#............#.....#.#.#...###...................#....#.........#...##........#..................")){
			return 484;
		}*/
		HashMap<Integer, int[][]> minMap = new HashMap<>();
		int high = maze.length;
		int len = maze[0].length();
		int[][] min;
		ArrayList<Integer> stones = new ArrayList<>();
		ArrayList<Integer> ms = new ArrayList<>();
		int mask = 0, ti = 0, tj = 0, si = 0, sj = 0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				char c = maze[i].charAt(j);
				if (c == 'S' || c == 'M') {
					min = new int[high][len];
					getMin(i, j, maze, min);
					min[i][j] = 0;
					minMap.put(i * 100 + j, min);
					if (c == 'M') {
						ms.add(i * 100 + j);
						mask <<= 1;
						mask++;
					} else {
						si = i;
						sj = j;
					}
				} else if (c == 'O') {
					stones.add(i * 100 + j);
				} else if (c == 'T') {
					ti = i;
					tj = j;
				}
			}
		}
		for (Entry<Integer, int[][]> entry : minMap.entrySet()) {
			int[][] value = entry.getValue();
			if (value[si][sj] == 0 && entry.getKey() != si * 100 + sj) {
				return -1;
			} else if (value[ti][tj] == 0) {
				return -1;
			} else if (ms.size() > 0) {
				boolean flag = true;
				for (Integer stone : stones) {
					if (value[stone / 100][stone % 100] > 0) {
						flag = false;
						break;
					}
				}
				if (flag) {
					return -1;
				}
			}
		}
		HashMap<Integer, Long> retMap = new HashMap<>();
		HashMap<Integer, Integer> mtom = new HashMap<>();
		return minimalStepsHelper(si, sj, retMap, mask, minMap, ms, stones, ti, tj, mtom)
			.intValue();
	}
	
	private Long minimalStepsHelper(int ci, int cj,
		HashMap<Integer, Long> retMap, int mask,
		HashMap<Integer, int[][]> minMap, ArrayList<Integer> ms, ArrayList<Integer> stones, int ti,
		int tj, HashMap<Integer, Integer> mtom) {
		if (mask == 0) {
			return (long) minMap.get(ci * 100 + cj)[ti][tj];
		}
		int key = mask * 10000 + ci * 100 + cj;
		if (retMap.containsKey(key)) {
			return retMap.get(key);
		}
		int idx = 0;
		long ret = Integer.MAX_VALUE;
		int ni, nj, next;
		int[][] cmin = minMap.get(ci * 100 + cj), nmin;
		while ((1 << idx) <= mask) {
			if ((mask & (1 << idx)) > 0) {
				next = ms.get(idx);
				ni = next / 100;
				nj = next % 100;
				nmin = minMap.get(next);
				int min = Integer.MAX_VALUE;
				Integer b = mtom.get(ci * 1000000 + cj * 10000 + ni * 100 + nj);
				if (b == null) {
					for (Integer stone : stones) {
						if (cmin[stone / 100][stone % 100] > 0
							&& nmin[stone / 100][stone % 100] > 0) {
							min = Math.min(min,
								cmin[stone / 100][stone % 100] + nmin[stone / 100][stone % 100]);
						}
					}
					mtom.put(ci * 1000000 + cj * 10000 + ni * 100 + nj, min);
				} else {
					min = b;
				}
				ret = Math.min(ret,
					min + minimalStepsHelper(ni, nj, retMap, mask - (1 << idx), minMap, ms, stones,
						ti, tj, mtom));
			}
			idx++;
		}
		retMap.put(key, ret);
		return ret;
	}
	
	private void getMin(int x, int y, String[] maze, int[][] min) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(x);
		list.add(y);
		int i, j, high = maze.length, len = min[0].length;
		while (!list.isEmpty()) {
			x = list.removeFirst();
			y = list.removeFirst();
			for (int[] d : ds_4) {
				i = x + d[0];
				j = y + d[1];
				if (i < 0 || j < 0 || i >= high || j >= len || min[i][j] != 0
					|| maze[i].charAt(j) == '#') {
					continue;
				} else {
					min[i][j] = min[x][y] + 1;
					list.add(i);
					list.add(j);
				}
			}
		}
	}
	
	//391. 完美矩形
	public boolean isRectangleCover(int[][] rectangles) {
		int sum = 0, u = Integer.MIN_VALUE, d = Integer.MAX_VALUE, l = d, r = u;
		for (int i = 0; i < rectangles.length; i++) {
			for (int j = 0; j < i; j++) {
				if (!isRectangleCoverHelper(rectangles[i], rectangles[j])) {
					return false;
				}
			}
			sum += (rectangles[i][3] - rectangles[i][1]) * (rectangles[i][2] - rectangles[i][0]);
			u = Math.max(u, rectangles[i][3]);
			r = Math.max(r, rectangles[i][2]);
			d = Math.min(d, rectangles[i][1]);
			l = Math.min(l, rectangles[i][0]);
		}
		return sum == (u - d) * (r - l);
	}
	
	private boolean isRectangleCoverHelper(int[] a, int[] b) {
		return a[2] <= b[0] || a[0] >= b[2] || a[1] >= b[3] || a[3] <= b[1];
	}
	
	public int minKBitFlips(int[] A, int K) {
		int ret = 0;
		int len = A.length;
		int t = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			if (!list.isEmpty() && i == list.getFirst()) {
				t--;
				list.removeFirst();
			}
			if (((A[i] + t) & 1) == 0) {
				t++;
				list.add(i + K);
				ret++;
			}
		}
		return list.isEmpty() || list.getLast() == len ? ret : -1;
	}
	
	//1520. 最多的不重叠子字符串
	List<Integer> ret = new ArrayList<>();
	int length = Integer.MAX_VALUE;
	
	public List<String> maxNumOfSubstrings(String s) {
		int[][] mem = new int[26][2];
		for (int i = 0; i < 26; i++) {
			mem[i][0] = s.indexOf('a' + i);
			mem[i][1] = s.lastIndexOf('a' + i);
		}
		int len = s.length();
		int x;
		for (int i = 0; i < len; i++) {
			x = s.charAt(i) - 'a';
			for (int j = 0; j < 26; j++) {
				if (mem[j][0] < i && mem[j][1] > i) {
					mem[j][0] = Math.min(mem[j][0], mem[x][0]);
					mem[j][1] = Math.max(mem[j][1], mem[x][1]);
				}
			}
		}
		Arrays.sort(mem, (a, b) -> (a[0] - b[0]));
		maxNumOfSubstringsHelper(mem, -1, new LinkedList<Integer>());
		ArrayList<String> list = new ArrayList<>();
		for (Integer t : ret) {
			list.add(s.substring(mem[t][0], mem[t][1] + 1));
		}
		return list;
	}
	
	private void maxNumOfSubstringsHelper(int[][] mem, int b, LinkedList<Integer> list) {
		int r = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			if (mem[i][0] > b && mem[i][0] < r) {
				list.add(i);
				maxNumOfSubstringsHelper(mem, mem[i][1], list);
				list.removeLast();
				r = Math.min(r, mem[i][1]);
			}
		}
		if (list.size() >= ret.size()) {
			int temp = 0;
			for (Integer x : list) {
				temp += mem[x][1] - mem[x][0];
			}
			if (list.size() > ret.size() || length > temp) {
				length = temp;
				ret = new ArrayList<>(list);
			}
		}
	}
	
	
	//1526. 形成目标数组的子数组最少增加次数
	public int minNumberOperations(int[] target) {
		int ret = 0, t = 0;
		for (int i : target) {
			if (i >= t) {
				ret += i - t;
			}
			t = i;
		}
		return ret;
	}
	
	//1525. 字符串的好分割数目
	public int numSplits(String s) {
		int[] mem = new int[26];
		int[] mem2 = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			mem[s.charAt(i) - 'a']++;
		}
		int l = 0, r = 0;
		for (int i : mem) {
			if (i > 0) {
				r++;
			}
		}
		int c, ret = 0;
		for (int i = 0; i < len && l <= r; i++) {
			c = s.charAt(i) - 'a';
			mem[c]--;
			mem2[c]++;
			if (mem[c] == 0) {
				r--;
			}
			if (mem2[c] == 1) {
				l++;
			}
			if (l == r) {
				ret++;
			}
		}
		return ret;
	}
	
	public int countOdds(int low, int high) {
		return ((high + 1) >> 1) - (low >> 1);
	}
	
	//1524. 和为奇数的子数组数目
	public int numOfSubarrays(int[] arr) {
		long a = 0, b = 0, t, ret = 0;
		int mod = 1000000007;
		for (int i : arr) {
			b++;
			if ((i & 1) == 1) {
				t = b;
				b = a;
				a = t;
			}
			ret += a;
			ret %= mod;
		}
		return (int) ret;
	}
	
	//1528. 重新排列字符串
	public String restoreString(String s, int[] indices) {
		int len = indices.length;
		char[] ret = new char[len];
		for (int i = 0; i < len; i++) {
			ret[indices[i]] = s.charAt(i);
		}
		return new String(ret);
	}
	
	//
	public int minFlips(String target) {
		int ret = 0, t;
		int len = target.length();
		for (int i = 0; i < len; i++) {
			t = target.charAt(i) - '0';
			if (((t + ret) & 1) != 0) {
				ret++;
			}
		}
		return ret;
	}
	
	int countPairs = 0;
	
	//5474. 好叶子节点对的数量
	public int countPairs(TreeNode root, int distance) {
		int[] x = countPairsHelper(root, distance);
		return countPairs;
	}
	
	private int[] countPairsHelper(TreeNode root, int distance) {
		int[] ret = new int[distance + 1], left, right;
		if (root == null) {
			return ret;
		} else if (root.left == null && root.right == null) {
			ret[0] = 1;
			return ret;
		}
		left = countPairsHelper(root.left, distance);
		right = countPairsHelper(root.right, distance);
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (i + j + 2 <= distance) {
					countPairs += left[i] * right[j];
				} else {
					break;
				}
			}
		}
		for (int i = 0; i < distance; i++) {
			ret[i + 1] = left[i] + right[i];
		}
		return ret;
	}
	
	//71. 简化路径
	public String simplifyPath(String path) {
		LinkedList<String> list = new LinkedList<>();
		String[] arr = path.split("/");
		for (String s : arr) {
			if ("..".equals(s)) {
				if (!list.isEmpty()) {
					list.removeLast();
				}
			} else if (!".".equals(s) && !"".equals(s)) {
				list.add(s);
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (String s : list) {
			buffer.append("/");
			buffer.append(s);
		}
		if (buffer.length() < 1) {
			buffer.append("/");
		}
		return buffer.toString();
	}
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> set = new HashSet<>(wordList), cur = new HashSet<>();
		if (!set.contains(endWord)) {
			return new ArrayList<>();
		}
		ArrayList<List<String>> ret = new ArrayList<>(), next;
		ret.add(Arrays.asList(beginWord));
		boolean flag = false;
		while (!set.isEmpty() && !ret.isEmpty()) {
			cur.clear();
			next = new ArrayList<>();
			for (List<String> list : ret) {
				flag |= findLaddersHelper(list, cur, set, next, endWord);
			}
			ret = next;
			if (flag) {
				break;
			}
			set.removeAll(cur);
		}
		next = new ArrayList<>();
		for (List<String> strings : ret) {
			if (strings.get(strings.size() - 1).equals(endWord)) {
				next.add(strings);
			}
		}
		return next;
	}
	
	private boolean findLaddersHelper(List<String> list, HashSet<String> cur, HashSet<String> set,
		ArrayList<List<String>> next, String endWord) {
		int len = list.size();
		boolean ret = false;
		String s = list.get(len - 1);
		for (String x : set) {
			if (dis(s, x)) {
				cur.add(x);
				ArrayList<String> l = new ArrayList<>(list);
				l.add(x);
				next.add(l);
				ret |= x.equals(endWord);
			}
		}
		return ret;
	}
	
	private boolean dis(String s, String x) {
		int c = 0;
		int len = s.length();
		for (int i = 0; i < len && c < 2; i++) {
			if (s.charAt(i) != x.charAt(i)) {
				c++;
			}
		}
		return c == 1;
	}
	
	//1025. 除数博弈
	public boolean divisorGame(int N) {
		Boolean[] ret = new Boolean[N + 1];
		for (int i = 1; i < N; i++) {
			if (N % i == 0 && !divisorGameHelper(ret, N - i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean divisorGameHelper(Boolean[] ret, int last) {
		if (ret[last] != null) {
			return ret[last];
		}
		for (int i = 1; i < last; i++) {
			if (last % i == 0 && !divisorGameHelper(ret, last - i)) {
				ret[last] = true;
				return true;
			}
		}
		ret[last] = false;
		return false;
	}
	
	class TireNode {
		
		boolean isEnd;
		TireNode[] child = new TireNode[26];
		
		public TireNode() {
			this.isEnd = false;
		}
	}
	
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Arrays.sort(words, (a, b) -> (a.length() - b.length()));
		TireNode root = new TireNode();
		ArrayList<String> ret = new ArrayList<>();
		for (String word : words) {
			boolean check = checkWords(root, word, 0, root);
			if (check) {
				ret.add(word);
			} else {
				TireNode cur = root;
				int len = word.length(), x;
				for (int i = 0; i < len; i++) {
					x = word.charAt(i) - 'a';
					if (cur.child[x] == null) {
						cur.child[x] = new TireNode();
					}
					cur = cur.child[x];
				}
				cur.isEnd = true;
			}
		}
		return ret;
	}
	
	private boolean checkWords(TireNode cur, String word, int idx, TireNode root) {
		if (cur == null) {
			return false;
		} else if (idx == word.length()) {
			return cur.isEnd;
		}
		int i = word.charAt(idx) - 'a';
		if (cur.isEnd && checkWords(root.child[i], word, idx + 1, root)) {
			return true;
		}
		return checkWords(cur.child[i], word, idx + 1, root);
	}
	
	
}
