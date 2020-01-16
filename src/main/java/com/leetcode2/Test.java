package com.leetcode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import sun.security.util.Length;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2020/1/3 10:35
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test {
	
	public class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		ArrayList<List<String>> lists = new ArrayList<>();
		lists.add(Arrays.asList("A", "B"));
		lists.add(Arrays.asList("C"));
		lists.add(Arrays.asList("C", "B"));
		lists.add(Arrays.asList("D"));
		test.longestDupSubstring("banana");
	}
	
	//1044
	public String longestDupSubstring(String S) {
		int length = S.length(), i;
		if (length < 27) {
			boolean[] exist = new boolean[26];
			for (i = 0; i < length; i++) {
				if (exist[S.charAt(i) - 'a']) {
					break;
				}
				exist[S.charAt(i) - 'a'] = true;
			}
			if (i == length) {
				return "";
			}
		}
		int len = 0, idx, max = 0, ret = 0;
		String t;
		for (i = 0; i + len < length; i++) {
			t = S.substring(i, i + len + 1);
			idx = S.indexOf(t, i + 1);
			while (idx > 0) {
				len++;
				while (idx+len<length&&S.charAt(i + len) == S.charAt(idx + len)) {
					len++;
				}
				if (len > max) {
					ret = i;
					max=len;
				}
				t = S.substring(i, i + len + 1);
				idx = S.indexOf(t, i + 1);
			}
		}
		return S.substring(ret, ret + max);
	}
	
	//316
	public String removeDuplicateLetters(String s) {
		int[] mem = new int[26];
		//Arrays.fill(mem, -1);
		int len = s.length();
		for (int i = 0; i < len; i++) {
			mem[s.charAt(i) - 'a'] = i;
		}
		char[] ret = new char[26];
		boolean[] exist = new boolean[26];
		int idx = -1;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (idx < 0) {
				exist[c - 'a'] = true;
				ret[++idx] = c;
			} else {
				if (exist[c - 'a']) {
					continue;
				}
				while (idx >= 0 && ret[idx] > c && mem[ret[idx] - 'a'] > i) {
					exist[ret[idx] - 'a'] = false;
					idx--;
				}
				
				exist[c - 'a'] = true;
				ret[++idx] = c;
				
			}
		}
		return new String(ret, 0, idx + 1);
	}
	
	//1178
	class TireNode {
		
		private TireNode[] child = new TireNode[26];
		int count = 0;
	}
	
	public List<Integer> findNumOfValidWords3(String[] words, String[] puzzles) {
		boolean[] flag;
		int count, f;
		TireNode root = new TireNode(), cur;
		StringBuffer buffer = new StringBuffer();
		for (String word : words) {
			flag = new boolean[26];
			count = 0;
			for (char c : word.toCharArray()) {
				f = (c - 'a');
				if (!flag[f]) {
					flag[f] = true;
					count++;
					if (count > 7) {
						break;
					}
				}
			}
			if (count <= 7) {
				buffer.append(word).append(',');
				cur = root;
				for (int i = 0; i < 26; i++) {
					if (flag[i]) {
						if (cur.child[i] == null) {
							cur.child[i] = new TireNode();
						}
						cur = cur.child[i];
					}
				}
				cur.count++;
			}
		}
		System.out.println(buffer.toString());
		
		ArrayList<Integer> ret = new ArrayList<>();
		for (String puzzle : puzzles) {
			flag = new boolean[26];
			count = 0;
			for (char c : puzzle.toCharArray()) {
				f = (c - 'a');
				if (!flag[f]) {
					flag[f] = true;
					count++;
				}
			}
			f = puzzle.charAt(0) - 'a';
			ret.add(findHelper(flag, root, count, f, 0, false));
		}
		return ret;
	}
	
	private int findHelper(boolean[] flag, TireNode root, int count, int f, int i,
		boolean contain) {
		int ret = 0;
		
		while (count > 0) {
			if (flag[i]) {
				count--;
				if (root.child[i] != null) {
					ret += findHelper(flag, root.child[i], count, f, i + 1, contain || f == i);
				}
			}
			i++;
		}
		if (contain) {
			ret += root.count;
		}
		return ret;
	}
	
	//1178
	class Node {
		
		private Node[] child = new Node[26];
		int count = 0;
		boolean[] contain;
		
		public Node(boolean[] contain) {
			this.contain = contain;
		}
	}
	
	public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
		boolean[] flag;
		int count, f;
		Node root = new Node(new boolean[26]);
		for (String word : words) {
			flag = new boolean[26];
			count = 0;
			for (char c : word.toCharArray()) {
				f = (c - 'a');
				if (!flag[f]) {
					flag[f] = true;
					count++;
					if (count > 7) {
						break;
					}
				}
			}
			if (count <= 7) {
				Node cur = root;
				for (int i = 0; i < 26; i++) {
					if (flag[i]) {
						if (cur.child[i] == null) {
							boolean[] contain = Arrays.copyOf(cur.contain, 26);
							contain[i] = true;
							Node child = new Node(contain);
							cur.child[i] = child;
						}
						cur = cur.child[i];
					}
				}
				cur.count++;
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (String puzzle : puzzles) {
			flag = new boolean[26];
			count = 0;
			for (char c : puzzle.toCharArray()) {
				f = (c - 'a');
				if (!flag[f]) {
					flag[f] = true;
					count++;
				}
			}
			f = puzzle.charAt(0) - 'a';
			ret.add(findNumOfValidWordsHelper(flag, root, count, f, 0));
		}
		return ret;
	}
	
	private int findNumOfValidWordsHelper(boolean[] flag, Node root, int count, int f, int i) {
		int ret = 0;
		if (root == null) {
			return ret;
		}
		while (count > 0) {
			if (flag[i]) {
				count--;
				ret += findNumOfValidWordsHelper(flag, root.child[i], count, f, i + 1);
			}
			i++;
		}
		if (root.contain[f]) {
			ret += root.count;
		}
		return ret;
	}
	
	//1178
	//位运算会超时
	public List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
		int len = words.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		int t = 0;
		int f = 0, count = 0;
		boolean[] flag;
		for (int i = 0; i < len; i++) {
			flag = new boolean[26];
			t = 0;
			count = 0;
			for (char c : words[i].toCharArray()) {
				f = (c - 'a');
				if (!flag[f]) {
					flag[f] = true;
					t |= (1 << f);
					count++;
					if (count > 7) {
						break;
					}
				}
			}
			if (count <= 7) {
				map.put(t, map.getOrDefault(t, 0) + 1);
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		HashMap<Integer, Integer> rmap = new HashMap<>();
		
		for (String puzzle : puzzles) {
			t = 0;
			for (char c : puzzle.toCharArray()) {
				t |= (1 << (c - 'a'));
			}
			if (rmap.containsKey(t)) {
				ret.add(rmap.get(t));
			} else {
				f = 1 << (puzzle.charAt(0) - 'a');
				count = 0;
				for (Entry<Integer, Integer> entry : map.entrySet()) {
					if ((entry.getKey() & f) > 0 && (entry.getKey() | t) == t) {
						count += entry.getValue();
					}
				}
				rmap.put(t, count);
				ret.add(count);
			}
			
		}
		return ret;
	}
	
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		int len = words.length;
		int[] mask = new int[len];
		for (int i = 0; i < len; i++) {
			int t = 0;
			for (char c : words[i].toCharArray()) {
				t |= (1 << (c - 'a'));
			}
			mask[i] = t;
		}
		ArrayList<Integer> ret = new ArrayList<>();
		
		for (String puzzle : puzzles) {
			int t = 0, f = 0, count = 0;
			for (char c : puzzle.toCharArray()) {
				t |= (1 << (c - 'a'));
			}
			f |= (1 << (puzzle.charAt(0) - 'a'));
			for (int i : mask) {
				if ((i & f) > 0 && (i ^ t) == t - i) {
					count++;
				}
			}
			ret.add(count);
		}
		return ret;
	}
	
	//1187
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		Arrays.sort(arr2);
		HashMap<Integer, Integer> map = new HashMap<>(), next;
		map.put(arr1[0], 0);
		int idx = 0;
		if (arr1[0] > arr2[0]) {
			map.put(arr2[0], 1);
			idx++;
		}
		int l1 = arr1.length;
		int l2 = arr2.length;
		int cur, x;
		for (int i = 1; i < l1; i++) {
			if (map.isEmpty()) {
				return -1;
			}
			cur = arr1[i];
			next = new HashMap<>();
			int nidx = l2;
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				Integer key = entry.getKey();
				Integer value = entry.getValue();
				if (key < cur) {
					next.put(cur, Math.min(next.getOrDefault(cur, Integer.MAX_VALUE), value));
					x = getBigger(arr2, idx, key, l2);
					if (x < l2 && arr2[x] < cur) {
						next.put(arr2[x],
							Math.min(next.getOrDefault(arr2[x], Integer.MAX_VALUE), value + 1));
					}
				} else {
					x = getBigger(arr2, idx, key, l2);
					if (x < l2) {
						next.put(arr2[x],
							Math.min(next.getOrDefault(arr2[x], Integer.MAX_VALUE), value + 1));
					}
				}
				nidx = Math.min(nidx, x);
			}
			idx = nidx;
			map = next;
		}
		if (map.isEmpty()) {
			return -1;
		}
		int ret = Integer.MAX_VALUE;
		for (Integer integer : map.values()) {
			ret = Math.min(integer, ret);
		}
		return ret;
	}
	
	
	private int getBigger(int[] arr2, int idx, Integer key, int l2) {
		int i = idx;
		for (; i < l2; i++) {
			if (arr2[i] > key) {
				return i;
			}
		}
		return i;
	}
	
	//1235
	class Job {
		
		int start;
		int end;
		int profit;
		
		public Job(int start, int end, int profit) {
			this.start = start;
			this.end = end;
			this.profit = profit;
		}
	}
	
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int len = startTime.length;
		Job[] jobs = new Job[len];
		for (int i = 0; i < len; i++) {
			jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
		}
		Arrays.sort(jobs, (a, b) -> (a.start == b.start ? a.end - b.end : a.start - b.start));
		int[] max = new int[len];
		int ret = 0;
		for (int i = len - 1; i >= 0; i--) {
			max[i] = getMax(jobs, i, max);
			ret = Math.max(max[i], ret);
		}
		return ret;
	}
	
	private int getMax(Job[] jobs, int cur, int[] max) {
		Job job = jobs[cur];
		int ret = job.profit;
		int start = job.end;
		int end = Integer.MAX_VALUE;
		for (int i = cur + 1; i < jobs.length; i++) {
			if (jobs[i].start < start) {
				continue;
			} else {
				end = Math.min(end, jobs[i].end);
				if (jobs[i].start < end) {
					ret = Math.max(ret, job.profit + max[i]);
				} else {
					break;
				}
			}
		}
		return ret;
		
	}
	
	
	//1320
	public int minimumDistance(String word) {
		
		int[] map = {0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 25, 30, 31, 32,
			33, 34, 35, 40, 41};
		int[][] map1 = new int[26][26];
		for (int i = 0; i < 26; i++) {
			for (int j = i + 1; j < 26; j++) {
				int x = Math.abs(map[i] % 10 - map[j] % 10);
				int y = Math.abs(map[i] / 10 - map[j] / 10);
				map1[i][j] = x + y;
				map1[j][i] = x + y;
			}
		}
		int[][][] min = new int[word.length()][26][27];
		return minimumDistanceHelper(word, 1, word.charAt(0) - 'A', 26, min, map1);
	}
	
	private int minimumDistanceHelper(String word, int idx, int l, int r, int[][][] min,
		int[][] map) {
		if (idx == word.length()) {
			return 0;
		} else if (min[idx][l][r] > 0) {
			return min[idx][l][r];
		}
		int ret = Integer.MAX_VALUE;
		int next = word.charAt(idx) - 'A';
		ret = Math.min(ret, map[l][next] + minimumDistanceHelper(word, idx + 1, next, r, min, map));
		ret = Math.min(ret,
			(r == 26 ? 0 : map[r][next]) + minimumDistanceHelper(word, idx + 1, l, next, min, map));
		min[idx][l][r] = ret;
		return ret;
	}
	
	public int[] decompressRLElist(int[] nums) {
		int len = 0;
		int length = nums.length;
		for (int i = 0; i < length; i += 2) {
			len += nums[i];
		}
		int[] ret = new int[len];
		int idx = 0, next;
		for (int i = 0; i < length; i += 2) {
			next = idx + nums[i];
			Arrays.fill(ret, idx, next, nums[i + 1]);
			idx = next;
		}
		return ret;
	}
	
	//1314
	public int[][] matrixBlockSum(int[][] mat, int K) {
		int hi = mat.length;
		int len = mat[0].length;
		
		for (int i = 1; i < len; i++) {
			mat[0][i] += mat[0][i - 1];
		}
		int sum = 0;
		for (int i = 1; i < hi; i++) {
			sum = 0;
			for (int j = 0; j < len; j++) {
				sum += mat[i][j];
				mat[i][j] = sum + mat[i - 1][j];
			}
		}
		int[][] ret = new int[hi][len];
		int x1, y1, x2, y2, a, b, c, d;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				x1 = Math.max(-1, i - K - 1);
				y1 = Math.max(-1, j - K - 1);
				x2 = Math.min(hi - 1, i + K);
				y2 = Math.min(len - 1, j + K);
				d = mat[x2][y2];
				a = x1 < 0 || y1 < 0 ? 0 : mat[x1][y1];
				b = x1 < 0 ? 0 : mat[x1][y2];
				c = y1 < 0 ? 0 : mat[x2][y1];
				ret[i][j] = a + d - b - c;
			}
		}
		return ret;
	}
	
	//1316
	public int distinctEchoSubstrings(String text) {
		int len = text.length();
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (j - i > len - j) {
					break;
				}
				String temp = text.substring(i, j);
				if (set.contains(temp)) {
					continue;
				}
				if (text.startsWith(temp, j)) {
					set.add(temp);
				}
				
			}
		}
		return set.size();
	}
	
	
	//1319
	public int makeConnected(int n, int[][] connections) {
		if (n - 1 > connections.length) {
			return -1;
		}
		Set<Integer>[] sets = new Set[n];
		for (int i = 0; i < n; i++) {
			sets[i] = new HashSet<>();
		}
		for (int[] connection : connections) {
			sets[connection[0]].add(connection[1]);
			sets[connection[1]].add(connection[0]);
		}
		
		boolean[] visited = new boolean[n];
		int count = 0;
		LinkedList<Integer> list = new LinkedList<>();
		int cur;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				list.add(i);
				while (!list.isEmpty()) {
					cur = list.removeFirst();
					if (!visited[cur]) {
						visited[cur] = true;
						list.addAll(sets[cur]);
					}
				}
			}
		}
		return count - 1;
	}
	
	//1318
	public int minFlips(int a, int b, int c) {
		int[] ab = minFlipsHelper(a);
		int[] bb = minFlipsHelper(b);
		int[] cb = minFlipsHelper(c);
		int ret = 0;
		for (int i = 0; i < 32; i++) {
			if (cb[i] != (ab[i] | bb[i])) {
				if (cb[i] == 0) {
					ret += ab[i];
					ret += bb[i];
				} else {
					ret++;
				}
			}
		}
		return ret;
	}
	
	private int[] minFlipsHelper(int a) {
		int[] ret = new int[32];
		int i = 0;
		while (a > 0) {
			ret[i] = (a & 1);
			a >>= 1;
			i++;
		}
		return ret;
	}
	
	//1317
	public int[] getNoZeroIntegers(int n) {
		for (int i = 1; i < n / 2; i++) {
			if (checkZero(i) && checkZero(n - i)) {
				return new int[]{i, n - i};
			}
		}
		return new int[2];
	}
	
	private boolean checkZero(int i) {
		while (i > 0) {
			if (i % 10 == 0) {
				return false;
			}
			i /= 10;
		}
		return true;
	}
	
	int sumEvenGrandparent = 0;
	
	//1315
	public int sumEvenGrandparent1(TreeNode root) {
		sumEvenGrandparentHelper1(root, false, false);
		return sumEvenGrandparent;
	}
	
	private void sumEvenGrandparentHelper1(TreeNode root, boolean f, boolean gf) {
		if (root == null) {
			return;
		}
		if (gf) {
			sumEvenGrandparent += root.val;
		}
		boolean cur = (root.val & 1) == 0;
		sumEvenGrandparentHelper1(root.left, cur, f);
		sumEvenGrandparentHelper1(root.right, cur, f);
	}
	
	public int sumEvenGrandparent(TreeNode root) {
		sumEvenGrandparentHelper(root, new boolean[2]);
		return sumEvenGrandparent;
	}
	
	private void sumEvenGrandparentHelper(TreeNode root, boolean[] flag) {
		if (root == null) {
			return;
		}
		if (flag[1]) {
			sumEvenGrandparent += root.val;
		}
		boolean cur = (root.val & 1) == 0;
		boolean[] next = new boolean[2];
		next[1] = flag[0];
		next[0] = cur;
		sumEvenGrandparentHelper(root.left, next);
		sumEvenGrandparentHelper(root.right, next);
	}
	
	//140
	public List<String> wordBreak(String s, List<String> wordDict) {
		ArrayList<String> ret = new ArrayList<>();
		int len = s.length();
		List<Integer>[] lists = new List[len];
		for (int i = 0; i < len; i++) {
			lists[i] = new ArrayList();
		}
		for (String cur : wordDict) {
			int clen = cur.length();
			int exist = s.indexOf(cur);
			while (exist >= 0) {
				lists[exist].add(exist + clen);
				exist = s.indexOf(cur, exist + 1);
			}
		}
		wordBreakHelper(ret, lists, "", 0, s);
		return ret;
	}
	
	private void wordBreakHelper(ArrayList<String> ret, List<Integer>[] lists, String s, int i,
		String base) {
		if (i == lists.length) {
			ret.add(s.trim());
			return;
		}
		for (int next : lists[i]) {
			wordBreakHelper(ret, lists, s + base.substring(i, next) + " ", next, base);
		}
	}
	
	//154
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r) {
			if (nums[l] == nums[r]) {
				//l++;
				r--;
			} else {
				break;
			}
		}
		return findMinHelper(nums, l, r);
	}
	
	private int findMinHelper(int[] nums, int l, int r) {
		if (r - l < 2) {
			return Math.min(nums[l], nums[r]);
		}
		if (nums[l] < nums[r]) {
			return nums[l];
		}
		int m = (l + r) >> 1;
		if (nums[m] >= nums[l]) {
			return findMinHelper(nums, m, r);
		} else {
			return findMinHelper(nums, l, m);
		}
	}
	
	//76
	public String minWindow1(String s, String t) {
		HashMap<Character, Integer> map = new HashMap<>();
		int cnt = 0, cur;
		for (char c : t.toCharArray()) {
			cur = map.getOrDefault(c, 0);
			if (cur == 0) {
				cnt++;
			}
			map.put(c, cur - 1);
		}
		int l = 0, r = 0;
		int length = s.length();
		int b = 0, e = length + 1;
		char[] array = s.toCharArray();
		while (r < length) {
			while (cnt > 0 && r < length) {
				cur = map.getOrDefault(array[r], 0);
				map.put(array[r], cur + 1);
				if (cur == -1) {
					cnt--;
				}
				r++;
			}
			while (cnt == 0) {
				cur = map.get(array[l]);
				map.put(array[l], cur - 1);
				if (cur == 0) {
					cnt++;
					if (r - l < e - b) {
						b = l;
						e = r;
					}
					l++;
					break;
				}
				l++;
			}
		}
		return e - b <= length ? s.substring(b, e) : "";
	}
	
	//76--因为不确定s中只包含大写字母导致异常
	public String minWindow(String s, String t) {
		int[] count = new int[26];
		int cnt = 0;
		for (char c : t.toCharArray()) {
			count[c - 'A']--;
			if (count[c - 'A'] == -1) {
				cnt++;
			}
		}
		int l = 0, r = 0;
		int length = s.length();
		int b = 0, e = length + 1;
		char[] array = s.toCharArray();
		while (r < length) {
			while (cnt > 0 && r < length) {
				count[array[r] - 'A']++;
				if (count[array[r] - 'A'] == 0) {
					cnt--;
				}
				r++;
			}
			while (cnt == 0) {
				count[array[l] - 'A']--;
				if (count[array[l] - 'A'] < 0) {
					cnt++;
					if (r - l < e - b) {
						b = l;
						e = r;
					}
					l++;
					break;
				}
				l++;
			}
		}
		return e - b <= length ? s.substring(b, e) : "";
	}
	
	//128
	public int longestConsecutive1(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		HashSet<Integer> visited = new HashSet<>();
		int ret = 0, cur, next;
		for (Integer x : set) {
			if (visited.contains(x)) {
				continue;
			}
			visited.add(x);
			cur = 1;
			next = x + 1;
			while (set.contains(next)) {
				visited.add(next);
				cur++;
				next++;
			}
			next = x - 1;
			while (set.contains(next)) {
				visited.add(next);
				cur++;
				next--;
			}
			ret = Math.max(ret, cur);
		}
		return ret;
	}
	
	public int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> left = new HashMap<>();
		HashMap<Integer, Integer> right = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		int l, r, ret = 0, cur;
		Integer ll, rl;
		for (int num : nums) {
			if (set.contains(num)) {
				continue;
			}
			set.add(num);
			l = num - 1;
			r = num + 1;
			ll = left.get(l);
			rl = right.get(r);
			if (ll != null && rl != null) {
				cur = 1 + ll + rl;
				left.remove(l);
				right.remove(r);
				left.put(num + rl, cur);
				right.put(num - ll, cur);
			} else if (ll != null) {
				cur = ll + 1;
				left.remove(l);
				left.put(num, cur);
				right.put(num - ll, cur);
			} else if (rl != null) {
				cur = rl + 1;
				right.remove(r);
				left.put(num + rl, cur);
				right.put(num, cur);
			} else {
				cur = 1;
				left.put(num, 1);
				right.put(num, 1);
			}
			ret = Math.max(ret, cur);
		}
		return ret;
	}
	
	//31
	public void nextPermutation1(int[] nums) {
		int tailmax = -1;
		int len = nums.length;
		for (int i = len - 1; i >= 0; i--) {
			if (tailmax > nums[i]) {
				int min = Integer.MAX_VALUE;
				int k = 0;
				for (int j = i + 1; j < len; j++) {
					if (nums[j] > nums[i] && nums[j] < min) {
						k = j;
						min = nums[j];
					}
				}
				nums[k] = nums[i];
				nums[i] = min;
				Arrays.sort(nums, i + 1, len);
				return;
			} else {
				tailmax = nums[i];
			}
		}
		Arrays.sort(nums);
	}
	
	//31 错误解
	public void nextPermutation(int[] nums) {
		int[] mem = new int[10];
		int i;
		int len = nums.length;
		for (i = len - 1; i >= 0; i--) {
			mem[nums[i]]++;
			for (int j = nums[i] + 1; j < 10; j++) {
				if (mem[j] > 0) {
					nums[i] = j;
					mem[j]--;
					int x = 0;
					for (int k = i + 1; k < len; k++) {
						while (mem[x] == 0) {
							x++;
						}
						nums[k] = x;
						mem[x]--;
					}
					return;
				}
			}
		}
		Arrays.sort(nums);
		
	}
	
	//LCP3
	public boolean robot(String command, int[][] obstacles, int x, int y) {
		int u = 0, r = 0;
		char[] array = command.toCharArray();
		for (char c : array) {
			if (c == 'U') {
				u++;
			} else {
				r++;
			}
		}
		if (robotHelper(u, r, x, y, array)) {
			for (int[] obstacle : obstacles) {
				if (obstacle[0] > x || obstacle[1] > y) {
					continue;
				}
				if (robotHelper(u, r, obstacle[0], obstacle[1], array)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean robotHelper(int u, int r, int tx, int ty, char[] array) {
		int yt = ty % u;
		int xt = tx % r;
		if (yt == 0 && xt == 0 && ty / u == tx / r) {
			return true;
		}
		int y, x;
		if (yt != 0) {
			y = u * (ty / u);
			x = r * (ty / u);
		} else if (xt != 0) {
			y = u * (tx / r);
			x = r * (tx / r);
		} else {
			int t = Math.max(ty / u, tx / r) - 1;
			y = u * t;
			x = r * t;
		}
		for (char c : array) {
			if (c == 'U') {
				y++;
			} else {
				x++;
			}
			if (x == tx && y == ty) {
				return true;
			} else if (x > tx || y > ty) {
				return false;
			}
		}
		return false;
	}
	
	//1312
	public int minInsertions1(String s) {
		int len = s.length();
		int[][] mem = new int[len][len];
		int min;
		for (int i = 1; i < len; i++) {
			for (int j = 0, k = j + i; k < len; j++, k++) {
				min = Integer.MAX_VALUE;
				min = Math.min(min, 1 + mem[j + 1][k]);
				min = Math.min(min, 1 + mem[j][k - 1]);
				if (s.charAt(j) == s.charAt(k)) {
					min = Math.min(min, mem[j + 1][k - 1]);
				}
				mem[j][k] = min;
			}
		}
		return mem[0][len - 1];
	}
	
	//1312
	public int minInsertions(String s) {
		int len = s.length();
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			Arrays.fill(mem[i], Integer.MAX_VALUE);
		}
		minInsertionsHelper(s, 0, len - 1, mem);
		return mem[0][len - 1];
	}
	
	private int minInsertionsHelper(String s, int l, int r, int[][] mem) {
		if (r <= l) {
			return 0;
		}
		if (mem[l][r] < Integer.MAX_VALUE) {
			return mem[l][r];
		}
		int ret = Integer.MAX_VALUE;
		if (s.charAt(l) == s.charAt(r)) {
			ret = Math.min(ret, minInsertionsHelper(s, l + 1, r - 1, mem));
		}
		ret = Math.min(ret, 1 + minInsertionsHelper(s, l, r - 1, mem));
		ret = Math.min(ret, 1 + minInsertionsHelper(s, l + 1, r, mem));
		mem[l][r] = ret;
		return ret;
	}
	
	//1311
	public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends,
		int id, int level) {
		int len = friends.length;
		boolean[] visited = new boolean[len];
		visited[id] = true;
		int count = 0;
		ArrayList<Integer> cur = new ArrayList<>(), next;
		cur.add(id);
		while (count < level) {
			count++;
			next = new ArrayList<>();
			for (Integer x : cur) {
				for (int i : friends[x]) {
					if (!visited[i]) {
						next.add(i);
						visited[i] = true;
					}
				}
			}
			cur = next;
		}
		HashMap<String, Integer> map = new HashMap<>();
		
		for (Integer x : cur) {
			for (String v : watchedVideos.get(x)) {
				map.put(v, map.getOrDefault(v, 0) + 1);
			}
		}
		TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			List<String> list = treeMap.get(value);
			if (list == null) {
				list = new ArrayList<>();
				treeMap.put(value, list);
			}
			list.add(key);
		}
		List<String> ret = new ArrayList<>();
		for (Entry<Integer, List<String>> entry : treeMap.entrySet()) {
			List<String> list = entry.getValue();
			list.sort((a, b) -> (a.compareTo(b)));
			ret.addAll(list);
		}
		return ret;
	}
	
	//1309
	public String freqAlphabets(String s) {
		int len = s.length();
		int i = len - 1;
		StringBuffer ret = new StringBuffer();
		int val;
		while (i >= 0) {
			if (s.charAt(i) == '#') {
				val = s.charAt(i - 1) - '0';
				val += (s.charAt(i - 2) - '0') * 10;
				i -= 3;
			} else {
				val = s.charAt(i) - '0';
				i--;
			}
			
			ret.append((char) (val + 'a' - 1));
		}
		return ret.reverse().toString();
	}
	
	//1310
	public int[] xorQueries(int[] arr, int[][] queries) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			arr[i] ^= arr[i - 1];
		}
		int length = queries.length;
		int[] ret = new int[length];
		for (int i = 0; i < length; i++) {
			int left = queries[i][0] == 0 ? 0 : arr[queries[i][0] - 1];
			int right = arr[queries[i][1]];
			ret[i] = left ^ right;
		}
		return ret;
	}
	
	//57
	public int[][] insert(int[][] intervals, int[] newInterval) {
		int l = insertLeft(intervals, newInterval[0]);
		int r = insertRight(intervals, newInterval[1]);
		int len = intervals.length;
		if (l > r) {
			int[][] ret = new int[len + 1][];
			for (int i = 0; i < l; i++) {
				ret[i] = intervals[i];
			}
			ret[l] = newInterval;
			for (int i = l; i < len; i++) {
				ret[i + 1] = intervals[i];
			}
			return ret;
		} else {
			int[][] ret = new int[len + l - r][];
			for (int i = 0; i < l; i++) {
				ret[i] = intervals[i];
			}
			ret[l] = new int[]{Math.min(newInterval[0], intervals[l][0]),
				Math.max(newInterval[1], intervals[r][1])};
			for (int i = r + 1; i < len; i++) {
				ret[++l] = intervals[i];
			}
			return ret;
		}
	}
	
	private int insertRight(int[][] intervals, int i) {
		int l = 0, r = intervals.length - 1, m;
		while (l <= r) {
			m = (l + r) >> 1;
			if (intervals[m][0] <= i && intervals[m][1] >= i) {
				return m;
			} else if (intervals[m][0] > i) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return r;
	}
	
	private int insertLeft(int[][] intervals, int i) {
		int l = 0, r = intervals.length - 1, m;
		while (l <= r) {
			m = (l + r) >> 1;
			if (intervals[m][0] <= i && intervals[m][1] >= i) {
				return m;
			} else if (intervals[m][1] < i) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return l;
	}
}
